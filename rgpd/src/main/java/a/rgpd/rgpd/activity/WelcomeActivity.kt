package a.rgpd.rgpd.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import co.revely.gradient.RevelyGradient
import a.rgpd.rgpd.R
import a.rgpd.rgpd.utils.*
import a.rgpd.rgpd.utils.RGPDStylePage

class WelcomeActivity: AppCompatActivity() {

    private lateinit var title1: TextView
    private lateinit var title2: TextView
    private lateinit var icon: ImageView
    private lateinit var description1: TextView
    private lateinit var description2: TextView
    private lateinit var nextButtonBackground: RelativeLayout
    private lateinit var nextButton: TextView
    private lateinit var background: LinearLayout

    private val colorGenerator = ColorGenerator()
    private lateinit var theme : RGPDStylePage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        for (item in RGPD.shared.theme!!.pages) {
            if (item.pageName == "welcome") {
                theme = item
            }
        }

        setColors()
    }

    private fun setColors() {
        title1 = findViewById(R.id.title1)
        if (theme.textColor.size == 1) {
            title1.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(title1)
        }

        title2 = findViewById(R.id.title2)
        if (theme.titleColor.size == 1) {
            title2.setTextColor(colorGenerator.getIntColors(theme.titleColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.titleColor))
                .on(title2)
        }

        description1 = findViewById(R.id.description1)
        if (theme.textColor.size == 1) {
            description1.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(description1)
        }

        icon = findViewById(R.id.icon)
        if (theme.iconColor.size == 1) {
            icon.setColorFilter(colorGenerator.getIntColors(theme.iconColor)[0])
        } else {
            icon.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.icon_private_life, theme.iconColor))
        }

        description2 = findViewById(R.id.description2)
        if (theme.textColor.size == 1) {
            description2.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(description2)
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

    fun onClickOnNextButton(view: View) {
        val intent = Intent(this@WelcomeActivity, RGPD.shared.pagesShown.first().activity)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        println("LOL")
        if (RGPD.shared.pagesShown.count() == 0) {
            finish()
        }
    }

    override fun onBackPressed() {
        return
    }

}