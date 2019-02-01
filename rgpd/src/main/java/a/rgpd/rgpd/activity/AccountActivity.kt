package a.rgpd.rgpd.activity

import a.rgpd.rgpd.R
import a.rgpd.rgpd.utils.*
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import co.revely.gradient.RevelyGradient
import java.lang.Exception

class AccountActivity : AppCompatActivity() {

    private lateinit var title : TextView
    private lateinit var checkBoxComm : ImageView
    private lateinit var checkBoxCommText : TextView
    private lateinit var checkBoxCommMore : TextView
    private lateinit var checkBoxStats : ImageView
    private lateinit var checkBoxStatsText : TextView
    private lateinit var checkBoxStatsMore : TextView
    private lateinit var checkBoxPersonal : ImageView
    private lateinit var checkBoxPersonalText : TextView
    private lateinit var checkBoxPersonalMore : TextView
    private lateinit var checkBoxPayment : ImageView
    private lateinit var checkBoxPaymentText : TextView
    private lateinit var checkBoxPaymentMore : TextView
    private lateinit var cgu : TextView
    private lateinit var cgv : TextView
    private lateinit var description : TextView
    private lateinit var nextButton : TextView
    private lateinit var nextButtonBackground : RelativeLayout
    private lateinit var background : LinearLayout

    private lateinit var theme : RGPDStylePage
    private val colorGenerator = ColorGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account)

        for (item in RGPD.shared.theme!!.pages) {
            if (item.pageName == "account") {
                theme = item
            }
        }

        setColors()
    }

    private fun setColors() {
        title = findViewById(R.id.title)
        if (theme.titleColor.size == 1) {
            title.setTextColor(colorGenerator.getIntColors(theme.titleColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.titleColor))
                .on(title)
        }

        checkBoxCommText = findViewById(R.id.checkbox_comm_text)
        if (theme.textColor.size == 1) {
            checkBoxCommText.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(checkBoxCommText)
        }

        checkBoxComm = findViewById(R.id.checkbox_comm)
        checkBoxCommMore = findViewById(R.id.more_comm)
        if (RGPD.shared.authGiven != null && RGPD.shared.authGiven!!.keyAccepted.contains(RGPDPages.COMM.pageName)) {
            checkBoxComm.setImageResource(R.mipmap.check_plein)
            if (theme.fullCircleColor.size == 1) {
                checkBoxComm.setColorFilter(colorGenerator.getIntColors(theme.fullCircleColor)[0])
                checkBoxCommMore.setTextColor(colorGenerator.getIntColors(theme.fullCircleColor)[0])
            } else {
                checkBoxComm.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_plein, theme.fullCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.fullCircleColor))
                    .on(checkBoxCommMore)
            }
        } else {
            checkBoxCommMore.isEnabled = false
            checkBoxComm.setImageResource(R.mipmap.check_vide)
            if (theme.emptyCircleColor.size == 1) {
                checkBoxComm.setColorFilter(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
                checkBoxCommMore.setTextColor(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
            } else {
                checkBoxComm.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_vide, theme.emptyCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.emptyCircleColor))
                    .on(checkBoxCommMore)
            }
        }

        checkBoxStatsText = findViewById(R.id.checkbox_stats_text)
        if (theme.textColor.size == 1) {
            checkBoxStatsText.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(checkBoxStatsText)
        }

        checkBoxStats = findViewById(R.id.checkbox_stats)
        checkBoxStatsMore = findViewById(R.id.more_stats)
        if (RGPD.shared.authGiven != null && RGPD.shared.authGiven!!.keyAccepted.contains(RGPDPages.STATS.pageName)) {
            checkBoxStats.setImageResource(R.mipmap.check_plein)
            if (theme.fullCircleColor.size == 1) {
                checkBoxStats.setColorFilter(colorGenerator.getIntColors(theme.fullCircleColor)[0])
                checkBoxStatsMore.setTextColor(colorGenerator.getIntColors(theme.fullCircleColor)[0])
            } else {
                checkBoxStats.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_plein, theme.fullCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.fullCircleColor))
                    .on(checkBoxStatsMore)
            }
        } else {
            checkBoxStatsMore.isEnabled = false
            checkBoxStats.setImageResource(R.mipmap.check_vide)
            if (theme.emptyCircleColor.size == 1) {
                checkBoxStats.setColorFilter(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
                checkBoxStatsMore.setTextColor(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
            } else {
                checkBoxStats.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_vide, theme.emptyCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.emptyCircleColor))
                    .on(checkBoxStatsMore)
            }
        }

        checkBoxPersonalText = findViewById(R.id.checkbox_personal_text)
        if (theme.textColor.size == 1) {
            checkBoxPersonalText.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(checkBoxPersonalText)
        }

        checkBoxPersonal = findViewById(R.id.checkbox_personal)
        checkBoxPersonalMore = findViewById(R.id.more_personal)
        if (RGPD.shared.authGiven != null && RGPD.shared.authGiven!!.keyAccepted.contains(RGPDPages.PERSONAL_DATA.pageName)) {
            checkBoxPersonal.setImageResource(R.mipmap.check_plein)
            if (theme.fullCircleColor.size == 1) {
                checkBoxPersonal.setColorFilter(colorGenerator.getIntColors(theme.fullCircleColor)[0])
                checkBoxPersonalMore.setTextColor(colorGenerator.getIntColors(theme.fullCircleColor)[0])
            } else {
                checkBoxPersonal.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_plein, theme.fullCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.fullCircleColor))
                    .on(checkBoxPersonalMore)
            }
        } else {
            checkBoxPersonalMore.isEnabled = false
            checkBoxPersonal.setImageResource(R.mipmap.check_vide)
            if (theme.emptyCircleColor.size == 1) {
                checkBoxPersonal.setColorFilter(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
                checkBoxPersonalMore.setTextColor(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
            } else {
                checkBoxPersonal.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_vide, theme.emptyCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.emptyCircleColor))
                    .on(checkBoxPersonalMore)
            }
        }

        checkBoxPaymentText = findViewById(R.id.checkbox_payment_text)
        if (theme.textColor.size == 1) {
            checkBoxPaymentText.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(checkBoxPaymentText)
        }

        checkBoxPayment = findViewById(R.id.checkbox_payment)
        checkBoxPaymentMore = findViewById(R.id.more_payment)
        if (RGPD.shared.authGiven != null && RGPD.shared.authGiven!!.keyAccepted.contains(RGPDPages.PAYMENT_DATA.pageName)) {
            checkBoxPayment.setImageResource(R.mipmap.check_plein)
            if (theme.fullCircleColor.size == 1) {
                checkBoxPayment.setColorFilter(colorGenerator.getIntColors(theme.fullCircleColor)[0])
                checkBoxPaymentMore.setTextColor(colorGenerator.getIntColors(theme.fullCircleColor)[0])
            } else {
                checkBoxPayment.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_plein, theme.fullCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.fullCircleColor))
                    .on(checkBoxPaymentMore)
            }
        } else {
            checkBoxPaymentMore.isEnabled = false
            checkBoxPayment.setImageResource(R.mipmap.check_vide)
            if (theme.emptyCircleColor.size == 1) {
                checkBoxPayment.setColorFilter(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
                checkBoxPaymentMore.setTextColor(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
            } else {
                checkBoxPayment.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_vide, theme.emptyCircleColor))
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.emptyCircleColor))
                    .on(checkBoxPaymentMore)
            }
        }

        cgu = findViewById(R.id.cgu)
        if (theme.textColor.size == 1) {
            cgu.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(cgu)
        }

        cgv = findViewById(R.id.cgv)
        if (theme.textColor.size == 1) {
            cgv.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(cgv)
        }

        description = findViewById(R.id.description)
        if (theme.textColor.size == 1) {
            description.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(description)
        }

        nextButton = findViewById(R.id.validate)
        if (theme.fullButtonTextColor.size == 1) {
            nextButton.setTextColor(colorGenerator.getIntColors(theme.fullButtonTextColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.fullButtonTextColor))
                .on(nextButton)
        }

        nextButtonBackground = findViewById(R.id.validate_background)
        nextButtonBackground.post {
            run {
                nextButtonBackground.background = colorGenerator.setColorButton(theme.fullButtonColor, nextButtonBackground)
            }
        }

        background = findViewById(R.id.background)
        if (theme.backgroundColor.size == 1) {
            background.setBackgroundColor(colorGenerator.getIntColors(theme.backgroundColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.backgroundColor))
                .onBackgroundOf(background)
        }
    }

    fun seeMore(view: View) {
        val more : TextView = findViewById(view.id)
        val intent = Intent(this@AccountActivity, PreviewActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK

        when (more.id) {
            R.id.more_comm -> intent.putExtra("selected", RGPDPages.COMM.pageName)
            R.id.more_stats -> intent.putExtra("selected", RGPDPages.STATS.pageName)
            R.id.more_personal -> intent.putExtra("selected", RGPDPages.PERSONAL_DATA.pageName)
            R.id.more_payment -> intent.putExtra("selected", RGPDPages.PAYMENT_DATA.pageName)
            R.id.cgv -> intent.putExtra("selected", RGPDPages.CGV.pageName)
            R.id.cgu -> intent.putExtra("selected", RGPDPages.CGU.pageName)
        }

        startActivity(intent)
    }

    fun onClickOnNextButton(view: View) {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Attention !")
        alert.setMessage("Vous êtes sur le point de supprimer votre compte. Êtes-vous sûr ?")
        alert.setPositiveButton("Oui") {dialog, which ->
            Webservices.services.deleteAuth {
                if (it == null) {
                    return@deleteAuth
                }
                try {
                    println("RGPD POD Return from updateUserWebservice : " + it.toString())
                    RGPD.shared.completionHandler(true)
                    finish()
                } catch (e: Exception) {
                    println("POD RGPD Error : " + e.message)
                }
            }
        }
        alert.setNegativeButton("Non", null)
        alert.show()
    }
}