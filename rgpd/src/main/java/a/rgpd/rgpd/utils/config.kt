package a.rgpd.rgpd.utils

class Config {
    var baseURL = "http://dev.dev-id.fr:8080"
    var userAuthorizationURL = "/Api/authorization"
    var appliConfigURL = "/Api/appliConfig"
    var deleteAuthURL = "/Api/DeleteAuth"

    companion object {
        var instance : Config = Config()
    }
}