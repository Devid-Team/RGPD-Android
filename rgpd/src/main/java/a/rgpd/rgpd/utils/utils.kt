package a.rgpd.rgpd.utils

import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.widget.Button
import android.widget.RelativeLayout

class ColorGenerator {
    fun getIntColors(colors: Array<String>): IntArray {
        val intColors = IntArray(colors.size)

        for (color in colors) {
            assert(color[0] == '#') {
                "Expected hex string of format #RRGGBB"
            }
            intColors[colors.indexOf(color)] = Color.parseColor(color)
        }

        return intColors
    }

    fun setImageGradient(resources: Resources, image: Int, colors: Array<String>): Drawable {
        val originalBitmap = BitmapFactory.decodeResource(resources, image)

        val updatedBitmap = Bitmap.createBitmap(originalBitmap.width, originalBitmap.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(updatedBitmap)

        canvas.drawBitmap(originalBitmap, 0f, 0f, null)

        val paint = Paint()
        val shader =  LinearGradient(0f, 0f, originalBitmap.width.toFloat(), 0f, ColorGenerator().getIntColors(colors), null, Shader.TileMode.CLAMP)
        paint.shader = shader
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        canvas.drawRect(0f, 0f, originalBitmap.width.toFloat(), originalBitmap.height.toFloat(), paint)

        return BitmapDrawable(resources, updatedBitmap)
    }

    fun setColorButton(colors: Array<String>, button: RelativeLayout): Drawable {
        val drawable = GradientDrawable()
        if (colors.size == 1) {
            drawable.setColor(getIntColors(colors)[0])
        } else {
            drawable.gradientType = GradientDrawable.LINEAR_GRADIENT
            drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
            drawable.colors = getIntColors(colors)
        }
        drawable.cornerRadius = 10f
        drawable.setSize(button.width, button.height)
        return drawable
    }

    fun setColorAccountButton(colors: Array<String>, button: Button): Drawable {
        val drawable = GradientDrawable()
        if (colors.size == 1) {
            drawable.setColor(getIntColors(colors)[0])
        } else {
            drawable.gradientType = GradientDrawable.LINEAR_GRADIENT
            drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
            drawable.colors = getIntColors(colors)
        }
        drawable.cornerRadius = 70f
        drawable.setSize(button.width, button.height)
        return drawable
    }
}