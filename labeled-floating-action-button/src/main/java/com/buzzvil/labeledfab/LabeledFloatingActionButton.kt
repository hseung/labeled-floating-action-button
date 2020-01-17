package com.buzzvil.labeledfab

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
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
    var config: LabeledFloatingActionButtonConfig? = null
        set (value) {
            field = value
            setDrawableWithConfig()
        }

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
        var icon = 0
        var labelText = ""
        var labelTextSizePx = 0
        var labelTextColor = 0
        var labelTextPadding = 0
        var labelPosition: LabelPosition

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.LabeledFloatingActionButton,
            0, 0).apply {

            try {
                labelText = getString(R.styleable.LabeledFloatingActionButton_labeledFabText) ?: ""
                labelTextSizePx = getDimensionPixelSize(R.styleable.LabeledFloatingActionButton_labeledFabTextSize, 0)
                labelTextColor = getColor(R.styleable.LabeledFloatingActionButton_labeledFabTextColor, Color.BLACK)
                icon = getResourceId(R.styleable.LabeledFloatingActionButton_labeledFabIcon, 0)
                labelPosition = LabelPosition.fromInt(getInteger(R.styleable.LabeledFloatingActionButton_labeledFabTextPosition, 1))
                labelTextPadding = getDimensionPixelSize(R.styleable.LabeledFloatingActionButton_labeledFabTextPadding, 0)
            } finally {
                recycle()
            }
        }

        if (!TextUtils.isEmpty(labelText) && labelTextSizePx > 0 && icon > 0) {
            config = LabeledFloatingActionButtonConfig(
                icon = icon,
                labelText = labelText,
                labelTextSizePx = labelTextSizePx,
                labelTextColor = labelTextColor,
                labelTextPadding = labelTextPadding,
                labelPosition = labelPosition
            )
        } else if (icon > 0) {
            setImageResource(icon)
        }
    }

    private fun setDrawableWithConfig() {
        config?.apply {
            scaleType = ScaleType.FIT_CENTER
            setImageDrawable(
                createFABDrawableWithText(
                    icon,
                    labelText,
                    labelTextSizePx,
                    labelTextColor,
                    labelPosition,
                    labelTextPadding
                )
            )
        }
    }

    private fun createFABDrawableWithText(
        @DrawableRes iconResId: Int, text: String,
        textSizePx: Int, @ColorInt textColor: Int,
        labelPosition: LabelPosition, labelTextPadding: Int
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
            if (labelPosition == LabelPosition.TOP) {
                setPadding(0, 0, 0, labelTextPadding)
            } else {
                setPadding(0, labelTextPadding, 0, 0)
            }
        }

        linearLayout.addView(
            imageView,
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        )
        var textViewIndex = if (labelPosition == LabelPosition.BOTTOM) 1 else 0
        linearLayout.addView(
            textView,
            textViewIndex,
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