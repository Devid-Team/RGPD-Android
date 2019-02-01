package a.rgpd.rgpd.utils

class pageTexts {
    var CGU : String? = null
    var CGV : String? = null
    var private_data : String? = null
    var stats: String? = null
    var comm : String? = null
    var payment_data : String? = null
}

class applicationAuthorization {
    var vendorId : String? = null
    var keyAccepted = arrayOf<String>()
    var applicationBundleId : String? = null
}

class RGPDStylePage {
    var pageName : String? = null
    var titleColor = arrayOf<String>()
    var iconColor = arrayOf<String>()
    var checkColor = arrayOf<String>()
    var fullCircleColor = arrayOf<String>()
    var emptyCircleColor = arrayOf<String>()
    var fullButtonColor = arrayOf<String>()
    var emptyButtonColor = arrayOf<String>()
    var fullButtonTextColor = arrayOf<String>()
    var emptyButtonTextColor = arrayOf<String>()
    var textColor = arrayOf<String>()
    var backButtonColor = arrayOf<String>()
    var backgroundColor = arrayOf<String>()
}

class RGPDStyleFile {
    var name : String? = null
    var pages = arrayOf<RGPDStylePage>()
}