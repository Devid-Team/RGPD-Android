package a.rgpd.rgpd.utils

import a.rgpd.rgpd.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.Button

interface RGPDButtonDelegate {
    fun didAskForDeletion()
}

class RGPDButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null)
    : Button(context, attrs, android.R.attr.borderlessButtonStyle) {

    companion object {
        var delegate : RGPDButtonDelegate? = null
    }

    init {
        setBackgroundColor(Color.BLUE)
        setPadding(50, 0, 50, 0)
        elevation = 0f

        text = context.getString(R.string.account_button)

        background = getContext().resources.getDrawable(R.drawable.account_button, context.theme)

        val drawable : Drawable = getContext().resources.getDrawable(R.mipmap.icon_data, context.theme)

        post {
            run {
                drawable.setBounds(0, 0, 100, 100)
                setCompoundDrawables(drawable, null, null, null)
            }
        }

        setOnClickListener {
            RGPD.shared.showAccountModally(delegate as Context) {it ->
                if (it) {
                    delegate!!.didAskForDeletion()
                }
            }
        }
    }
}