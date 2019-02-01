package a.rgpd.rgpd.utils

import org.json.JSONObject

class Webservices {

    companion object {
        val services = Webservices()
    }

    fun getUserAuthorizations(completion: (JSONObject?) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.userAuthorizationURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!)

        khttp.async.get(url = url, params = data, onError = {
            println("POD RGPD Error : " + message)
            completion(null)
        }) {
            if (jsonObject.get("success") == true) {
                completion(jsonObject)
            } else  {
                println("POD RGPD Error : " + text)
                completion(null)
            }
        }
    }

    fun getConfig(completion: (JSONObject?) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.appliConfigURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!)

        khttp.async.get(url = url, params = data, onError = {
            println("POD RGPD Error : " + message)
            completion(null)
        }) {
            if (jsonObject.get("success") == true) {
                completion(jsonObject)
            } else {
                println("POD RGPD Error : " + text)
                completion(null)
            }
        }
    }

    fun updateUserAuthorizations(authKeyString: String, completion: (JSONObject?) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.userAuthorizationURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!, "auth" to authKeyString)

        khttp.async.post(url = url, data = data, onError = {
            println("POD RGPD Error : " + message)
            completion(null)
        }) {
            if (jsonObject.get("success") == true) {
                completion(jsonObject)
            } else {
                println("POD RGPD Error : " + text)
                completion(null)
            }
        }
    }

    fun deleteAuth(completion: (JSONObject?) -> Unit) {
        val url = Config.instance.baseURL + Config.instance.deleteAuthURL
        val data = mapOf("bundleId" to RGPD.shared.applicationBundleId!!, "phoneId" to RGPD.shared.phoneId!!)

        khttp.async.post(url = url, data = data, onError = {
            println("POD RGPD Error : " + message)
            completion(null)
        }) {
            if (jsonObject.get("success") == true) {
                completion(jsonObject)
            } else {
                println("POD RGPD Error : " + text)
                completion(null)
            }
        }
    }
}