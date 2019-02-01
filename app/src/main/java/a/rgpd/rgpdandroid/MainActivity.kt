package a.rgpd.rgpdandroid

import a.rgpd.rgpd.utils.RGPD
import a.rgpd.rgpd.utils.RGPDButton
import a.rgpd.rgpd.utils.RGPDButtonDelegate
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainActivity : AppCompatActivity(), RGPDButtonDelegate {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RGPDButton.delegate = this

        RGPD.shared.configure("application.test", this)
    }

    override fun onResume() {
        super.onResume()
        RGPD.shared.hasAllAuthorizations {
            if (!it) {
                RGPD.shared.showRGPDModally(this@MainActivity)
                GlobalScope.async {

                }
            }
        }
    }

    override fun didAskForDeletion() {
        println("Account deleted")
    }
}
