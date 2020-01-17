package com.buzzvil.labeledfab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.google.android.material.floatingactionbutton.FloatingActionButton

class LabeledFloatingActionButton : FloatingActionButton{
    constructor(ctx: Context) : super(ctx) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init()
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        setImageDrawable(createFABDrawableWithText(android.R.drawable.ic_dialog_email, "Label", 15f, Color.CYAN))
    }

    private fun createFABDrawableWithText(
        @DrawableRes iconResId: Int, text: String,
        textSizeDp: Float, @ColorInt textColor: Int
    ): Drawable? {
        val linearLayout = LinearLayout(context).apply {
            layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            orientation = LinearLayout.VERTICAL
        }

        val imageView = ImageView(context).apply {
            scaleType = ScaleType.CENTER
            setImageResource(iconResId)
        }

        val textView = TextView(context).apply {
            setTextColor(textColor)
            setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSizeDp)
            setText(text)
            gravity = Gravity.CENTER
            typeface = Typeface.create("sans-serif-medium", Typeface.NORMAL)
            setPadding(0, 0, 0, 0)
        }

        linearLayout.addView(
            imageView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        linearLayout.addView(
            textView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )

        val spec: Int = MeasureSpec.makeMeasureSpec(
            0,
            MeasureSpec.UNSPECIFIED
        )
        linearLayout.measure(spec, spec)
        linearLayout.layout(0, 0, linearLayout.getMeasuredWidth(), linearLayout.getMeasuredHeight())
        val bitmap: Bitmap = Bitmap.createBitmap(
            linearLayout.getWidth(),
            linearLayout.getHeight(),
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(bitmap)
        linearLayout.draw(canvas)
        return BitmapDrawable(bitmap)
    }

}