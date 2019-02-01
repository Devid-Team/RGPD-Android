package a.rgpd.rgpd.activity

import a.rgpd.rgpd.R
import a.rgpd.rgpd.utils.ColorGenerator
import a.rgpd.rgpd.utils.RGPD
import a.rgpd.rgpd.utils.RGPDPages
import a.rgpd.rgpd.utils.RGPDStylePage
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import co.revely.gradient.RevelyGradient

class PreviewActivity : AppCompatActivity() {

    private lateinit var icon : ImageView
    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var background : LinearLayout

    private lateinit var pageName : String
    private lateinit var theme : RGPDStylePage
    private val colorGenerator = ColorGenerator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.preview)

        pageName = intent.getStringExtra("selected")

        for (item in RGPD.shared.theme!!.pages) {
            if (item.pageName == pageName) {
                theme = item
            }
        }

        title = findViewById(R.id.title)
        description = findViewById(R.id.description)

        description.movementMethod = ScrollingMovementMethod()

        when (pageName) {
            RGPDPages.COMM.pageName -> {
                title.text = getString(R.string.commercial_title)
                description.text = RGPD.shared.texts!!.comm
            }
            RGPDPages.STATS.pageName -> {
                title.text = getString(R.string.statistics_title)
                description.text = RGPD.shared.texts!!.stats
            }
            RGPDPages.PERSONAL_DATA.pageName -> {
                title.text = getString(R.string.personal_data_title)
                description.text = RGPD.shared.texts!!.private_data
            }
            RGPDPages.PAYMENT_DATA.pageName -> {
                title.text = getString(R.string.payment_info_title)
                description.text = RGPD.shared.texts!!.payment_data
            }
            RGPDPages.CGU.pageName -> {
                title.text = getString(R.string.cgu_title)
                description.text = RGPD.shared.texts!!.CGU
            }
            RGPDPages.CGV.pageName -> {
                title.text = getString(R.string.cgv_title)
                description.text = RGPD.shared.texts!!.CGV
            }
        }

        setColors()
    }

    private fun setColors() {
        val image : Int? = when (pageName) {
            RGPDPages.COMM.pageName -> R.mipmap.icon_commercial
            RGPDPages.STATS.pageName -> R.mipmap.icon_stats
            RGPDPages.PERSONAL_DATA.pageName -> R.mipmap.icon_private_life
            RGPDPages.PAYMENT_DATA.pageName -> R.mipmap.icon_payment
            else -> null
        }

        if (image != null) {
            icon = findViewById(R.id.icon)
            icon.setPadding(0, 108, 0, 130)
            icon.setImageResource(image)
            if (theme.iconColor.size == 1) {
                icon.setColorFilter(colorGenerator.getIntColors(theme.iconColor)[0])
            } else {
                icon.setImageDrawable(colorGenerator.setImageGradient(resources, image, theme.iconColor))
            }
        }

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

        background = findViewById(R.id.background)
        if (theme.backgroundColor.size == 1) {
            background.setBackgroundColor(colorGenerator.getIntColors(theme.backgroundColor)[0])
        } else {
            RevelyGradient.linear()
                .colors(colorGenerator.getIntColors(theme.backgroundColor))
                .onBackgroundOf(background)
        }
    }
}