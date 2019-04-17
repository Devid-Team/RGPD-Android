package a.rgpd.rgpd.utils

import a.rgpd.rgpd.activity.*
import android.content.Context
import android.content.Intent
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import com.google.gson.Gson
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.json.JSONObject
import java.util.ArrayList
import java.util.concurrent.Semaphore

enum class RGPDPages(val pageName: String, val activity: Class<*>) {
    CGU("CGU", CGUActivity::class.java),
    CGV("CGV", CGVActivity::class.java),
    STATS("stats", StatsActivity::class.java),
    PERSONAL_DATA("private_data", PersonalDataActivity::class.java),
    COMM("comm", CommActivity::class.java),
    PAYMENT_DATA("payment_data", PaymentInfoActivity::class.java)
}

class RGPD {
    internal var pagesShown = arrayListOf<RGPDPages>()
    internal lateinit var pagesAccepted: ArrayList<RGPDPages>
    internal var theme : RGPDStyleFile? = null
    internal var applicationBundleId : String? = null
    internal var phoneId : String? = null
    internal var authGiven : applicationAuthorization? = null
    internal var texts : pageTexts? = null
    private var isInit = false

    private var semaphore = Semaphore(1, true)
    internal lateinit var completionHandler: (Boolean) -> Unit

    companion object {
        val shared = RGPD()
    }

    init {
        try {
            theme = Klaxon().parse<RGPDStyleFile>(this.javaClass.classLoader!!.getResourceAsStream("assets/document.json"))
        } catch (e: java.lang.Exception) {
            println("POD RGPD Error: " + e.message)
        }
    }

    fun setTheme(themeData: RGPDStyleFile) {
        theme = themeData
    }

    fun configure(applicationBundleId: String, view: Context) {
        if (this.applicationBundleId == null) {
            this.applicationBundleId = applicationBundleId
        }
        if (this.phoneId == null) {
            this.phoneId = UIDManager.retrieveUID(view)
        }
    }

    private fun retrieveConfigAndAuthFromServer() {
        semaphore.acquire()
        pagesAccepted = arrayListOf()
        Webservices.services.getConfig {
            val (bytes, error) = it
            if (bytes == null || error != null)
                return@getConfig

            val jsonObject = JSONObject(String(bytes))
            if (jsonObject.get("success") == false)
                return@getConfig

            try {
                if (jsonObject.get("config") != null)
                    texts = Gson().fromJson(jsonObject.get("config").toString(), pageTexts::class.java)

                isInit = jsonObject.get("success") == true && jsonObject.get("config") != null

            } catch (e: Exception) {
                println("POD RGPD Error : " + e.message)
                return@getConfig
            }
            Webservices.services.getUserAuthorizations {
                val (bytes2, error2) = it
                if (bytes2 == null || error2 != null)
                    return@getUserAuthorizations

                val jsonObject2 = JSONObject(String(bytes2))
                if (jsonObject2.get("success") == false)
                    return@getUserAuthorizations

                try {
                    println(jsonObject2)
                    if (jsonObject2.get("auth") != null)
                        authGiven = Gson().fromJson(jsonObject2.get("auth").toString(), applicationAuthorization::class.java)
                 } catch (e: Exception) {
                    println("POD RGPD Error: " + e.message)
                }
             semaphore.release()
           }
        }
    }

    fun hasAllAuthorizations(completionHandler: (Boolean) -> Unit) {
        GlobalScope.async {
            retrieveConfigAndAuthFromServer()
            semaphore.acquire()
            if (!isInit) {
                completionHandler(false)
                return@async
            }
            if (authGiven != null) {
                    if (texts?.CGU != null && !authGiven!!.keyAccepted.contains(RGPDPages.CGU.pageName)) {
                        pagesShown.add(RGPDPages.CGU)
                    }
                    if (texts?.CGV != null && !authGiven!!.keyAccepted.contains(RGPDPages.CGV.pageName)) {
                        pagesShown.add(RGPDPages.CGV)
                    }
                    if (texts?.stats != null && !authGiven!!.keyAccepted.contains(RGPDPages.STATS.pageName)) {
                        pagesShown.add(RGPDPages.STATS)
                    }
                    if (texts?.private_data != null && !authGiven!!.keyAccepted.contains(RGPDPages.PERSONAL_DATA.pageName)) {
                        pagesShown.add(RGPDPages.PERSONAL_DATA)
                    }
                    if (texts?.comm != null && !authGiven!!.keyAccepted.contains(RGPDPages.COMM.pageName)) {
                        pagesShown.add(RGPDPages.COMM)
                    }
                    if (texts?.payment_data != null && !authGiven!!.keyAccepted.contains(RGPDPages.PAYMENT_DATA.pageName)) {
                        pagesShown.add(RGPDPages.PAYMENT_DATA)
                    }
                } else {
                    if (texts?.CGU != null) {
                        pagesShown.add(RGPDPages.CGU)
                    }
                    if (texts?.CGV != null) {
                        pagesShown.add(RGPDPages.CGV)
                    }
                    if (texts?.stats != null) {
                        pagesShown.add(RGPDPages.STATS)
                    }
                    if (texts?.private_data != null) {
                        pagesShown.add(RGPDPages.PERSONAL_DATA)
                    }
                    if (texts?.comm != null) {
                        pagesShown.add(RGPDPages.COMM)
                    }
                    if (texts?.payment_data != null) {
                      pagesShown.add(RGPDPages.PAYMENT_DATA)
                    }
                }
            semaphore.release()
            completionHandler(pagesShown.isEmpty())
        }
    }

    fun showRGPDModally(view: Context) {
        if (isInit) {
            val intent = Intent(view, Class.forName("a.rgpd.rgpd.activity.WelcomeActivity"))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            view.startActivity(intent)
        }
    }

    fun showAccountModally(view: Context, completion: (Boolean) -> Unit) {
        if (isInit) {
            completionHandler = completion
            val intent = Intent(view, Class.forName("a.rgpd.rgpd.activity.AccountActivity"))
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            view.startActivity(intent)
        }
    }
}