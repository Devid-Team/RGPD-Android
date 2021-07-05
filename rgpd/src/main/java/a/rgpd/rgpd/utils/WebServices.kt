package a.rgpd.rgpd.utils

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import java.util.*

class Webservices {

    companion object {
        val services = Webservices()
    }

    fun getUserAuthorizations(completion: (Result<ByteArray, FuelError>) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.userAuthorizationURL
        val data = listOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!)

        Fuel.get(url, data)
            .response { result ->
                completion(result)
            }
    }

    fun getConfig(completion: (Result<ByteArray, FuelError>) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.appliConfigURL
        val data = listOf("bundleId" to RGPD.shared.applicationBundleId!!, "language" to Locale.getDefault().displayLanguage)

        Fuel.get(url, data)
            .response { result ->
                completion(result)
            }
    }

    fun updateUserAuthorizations(authKeyString: String, completion: (Result<ByteArray, FuelError>) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.userAuthorizationURL
        val data = listOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!, "auth" to authKeyString)

        Fuel.post(url, data)
            .response { result ->
                completion(result)
            }
    }

    fun deleteAuth(completion: (Result<ByteArray, FuelError>) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.deleteAuthURL
        val data = listOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!)

        Fuel.post(url, data)
            .response { result ->
                completion(result)
            }
    }
}