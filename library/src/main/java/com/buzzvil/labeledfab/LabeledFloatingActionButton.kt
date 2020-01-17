package com.buzzvil.labeledfab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.TextUtils
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

class LabeledFloatingActionButton : FloatingActionButton {
    constructor(ctx: Context) : super(ctx) {
        init(null)
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        init(attrs)
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet?) {
        var text: String?
        var textSizePx: Int
        var textColor: Int
        var icon: Int
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LabeledFloatingActionButton,
            0, 0).apply {

            try {
                text = getString(R.styleable.LabeledFloatingActionButton_labeledFabText)
                textSizePx = getDimensionPixelSize(R.styleable.LabeledFloatingActionButton_labeledFabTextSize, 0)
                textColor = getColor(R.styleable.LabeledFloatingActionButton_labeledFabTextColor, Color.BLACK)
                icon = getResourceId(R.styleable.LabeledFloatingActionButton_labeledFabIcon, 0)
            } finally {
                recycle()
            }
        }

        if (!TextUtils.isEmpty(text) && textSizePx > 0 && icon > 0) {
            scaleType = ScaleType.FIT_CENTER
            setImageDrawable(createFABDrawableWithText(icon, text!!, textSizePx, textColor))
        } else if (icon > 0) {
            setImageResource(icon)
        }
    }

    private fun createFABDrawableWithText(
        @DrawableRes iconResId: Int, text: String,
        textSizePx: Int, @ColorInt textColor: Int
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
            setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePx.toFloat())
            setText(text)
            gravity = Gravity.CENTER
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