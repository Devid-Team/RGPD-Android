package a.rgpd.rgpd.utils

import khttp.responses.Response
import org.json.JSONObject
import kotlin.concurrent.thread

class Webservices {

    companion object {
        val services = Webservices()
    }

    fun getUserAuthorizations(completion: Response.() -> Unit) {
        val url = Config.instance.baseURL + Config.instance.userAuthorizationURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!)

        completion(khttp.request("GET", url, mapOf(), data))
    }

    fun getConfig(completion: Response.() -> Unit) {
        val url = Config.instance.baseURL + Config.instance.appliConfigURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!)

        completion(khttp.request("GET", url, mapOf(), data))
    }

    fun updateUserAuthorizations(authKeyString: String, completion: Response.() -> Unit) {
        val url = Config.instance.baseURL + Config.instance.userAuthorizationURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!, "auth" to authKeyString)

        completion(khttp.request("POST", url, mapOf(), mapOf(), data))
    }

    fun deleteAuth(completion: Response.() -> Unit) {
        val url = Config.instance.baseURL + Config.instance.deleteAuthURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!)

        completion(khttp.request("POST", url, mapOf(), mapOf(), data))
    }
}