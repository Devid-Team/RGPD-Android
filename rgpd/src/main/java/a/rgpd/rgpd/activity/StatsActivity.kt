package a.rgpd.rgpd.activity

import a.rgpd.rgpd.R
import a.rgpd.rgpd.utils.*
import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.*
import co.revely.gradient.RevelyGradient

class StatsActivity: AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var icon: ImageView
    private lateinit var description: TextView
    private lateinit var checkBox: ImageView
    private lateinit var checkBoxText: TextView
    private lateinit var nextButton: TextView
    private lateinit var nextButtonBackground: RelativeLayout
    private lateinit var background: LinearLayout

    private lateinit var theme: RGPDStylePage
    private val colorGenerator = ColorGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.statistics)

        nextButtonBackground = findViewById(R.id.validate_background)
        nextButtonBackground.isEnabled = false

        nextButton = findViewById(R.id.validate)
        if (RGPD.shared.pagesShown.count() == 1) {
            nextButton.text = getString(R.string.validate_button)
        }

        for (item in RGPD.shared.theme!!.pages) {
            if (item.pageName == RGPDPages.STATS.pageName) {
                theme = item
            }
        }

        description = findViewById(R.id.description)
        description.text = RGPD.shared.texts?.stats
        description.movementMethod = ScrollingMovementMethod()

        setColors()
    }

    private fun setColors() {
        icon = findViewById(R.id.icon)
        if (theme.iconColor.size == 1) {
            icon.setColorFilter(colorGenerator.getIntColors(theme.iconColor)[0])
        } else {
            icon.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.icon_stats, theme.iconColor))
        }

        title = findViewById(R.id.title)
        if (theme.titleColor.size == 1) {
            title.setTextColor(colorGenerator.getIntColors(theme.titleColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.titleColor))
                .on(title)
        }

        if (theme.textColor.size == 1) {
            description.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(description)
        }

        checkBoxText = findViewById(R.id.checkbox_text)
        if (theme.textColor.size == 1) {
            description.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
            checkBoxText.setTextColor(colorGenerator.getIntColors(theme.textColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(description)
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.textColor))
                .on(checkBoxText)
        }

        background = findViewById(R.id.background)
        if (theme.backgroundColor.size == 1) {
            background.setBackgroundColor(colorGenerator.getIntColors(theme.backgroundColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.backgroundColor))
                .onBackgroundOf(background)
        }

        checkBox = findViewById(R.id.checkbox)
        if (nextButtonBackground.isEnabled) {
            nextButtonBackground.post {
                run {
                    nextButtonBackground.background = colorGenerator.setColorButton(theme.fullButtonColor, nextButtonBackground)
                }
            }

            checkBox.setImageResource(R.mipmap.check_plein)
            if (theme.fullCircleColor.size == 1) {
                checkBox.setColorFilter(colorGenerator.getIntColors(theme.fullCircleColor)[0])
            } else {
                checkBox.colorFilter = null
                checkBox.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_plein, theme.fullCircleColor))
            }

            if (theme.fullButtonTextColor.size == 1) {
                nextButton.setTextColor(colorGenerator.getIntColors(theme.fullButtonTextColor)[0])
            } else {
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.fullButtonTextColor))
                    .on(nextButton)
            }
        } else {
            nextButtonBackground.setBackgroundResource(R.mipmap.rec_border)
            if (theme.emptyButtonColor.size == 1) {
                nextButtonBackground.background.setColorFilter(colorGenerator.getIntColors(theme.emptyButtonColor)[0], PorterDuff.Mode.SRC_IN)
            } else {
                nextButtonBackground.background = colorGenerator.setImageGradient(resources, R.mipmap.rec_border, theme.emptyButtonColor)
            }

            checkBox.setImageResource(R.mipmap.check_vide)
            if (theme.emptyCircleColor.size == 1) {
                checkBox.setColorFilter(colorGenerator.getIntColors(theme.emptyCircleColor)[0])
            } else {
                checkBox.colorFilter = null
                checkBox.setImageDrawable(colorGenerator.setImageGradient(resources, R.mipmap.check_vide, theme.emptyCircleColor))
            }

            if (theme.emptyButtonTextColor.size == 1) {
                nextButton.setTextColor(colorGenerator.getIntColors(theme.emptyButtonTextColor)[0])
            } else {
                RevelyGradient.linear()
                    .colors(colorGenerator.getIntColors(theme.emptyButtonTextColor))
                    .on(nextButton)
            }
        }
    }

    fun onClickOnNextButton(view: View) {
        if (RGPD.shared.pagesShown.first().pageName === RGPDPages.STATS.pageName) {
            RGPD.shared.pagesAccepted.add(RGPDPages.STATS)
            RGPD.shared.pagesShown.removeAt(0)
        }

        if (RGPD.shared.pagesShown.count() == 0) {
            var auth = "["

            RGPD.shared.pagesAccepted.forEach {
                auth += if (RGPD.shared.pagesAccepted.indexOf(it) == 0) {
                    ""
                } else {
                    ", "
                } + "\"" + RGPD.shared.pagesAccepted[RGPD.shared.pagesAccepted.indexOf(it)].pageName + "\""
            }

            auth += "]"

            Webservices.services.updateUserAuthorizations(auth) {
                finish()
                if (it == null) {
                    return@updateUserAuthorizations
                } else {
                    println("RGPD POD Return from updateUserWebservice : " + it.toString())
                }
            }
        } else {
            val intent = Intent(this@StatsActivity, RGPD.shared.pagesShown.first().activity)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }

    fun changeButtonState(view: View) {
        nextButtonBackground.isEnabled = !nextButtonBackground.isEnabled
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        setColors()
    }

    override fun onResume() {
        super.onResume()

        if (RGPD.shared.pagesShown.count() == 0) {
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (RGPD.shared.pagesAccepted.contains(RGPDPages.STATS)){
            RGPD.shared.pagesAccepted.removeAt(RGPD.shared.pagesAccepted.indexOf(RGPDPages.STATS))
            RGPD.shared.pagesShown.add(0, RGPDPages.STATS)
        }
    }
}