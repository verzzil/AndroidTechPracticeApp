package ru.itis.androidtechpracticeapp.presentation.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.Gravity
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.graphics.toRectF
import ru.itis.androidtechpracticeapp.R
import ru.itis.androidtechpracticeapp.extentions.dpToPx

class NumberFromTopTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    companion object {
        private const val DEFAULT_BORDER_WIDTH = 5
        private const val DEFAULT_BORDER_AND_TITLE_COLOR = Color.LTGRAY
        private const val DEFAULT_PLACE_IN_TOP = "??"
    }

    @Px
    private var borderWidth: Float = context.dpToPx(DEFAULT_BORDER_WIDTH)

    @ColorInt
    private var borderColor: Int = DEFAULT_BORDER_AND_TITLE_COLOR

    @ColorInt
    private var titleColor: Int = DEFAULT_BORDER_AND_TITLE_COLOR

    private var placeInTop: String = DEFAULT_PLACE_IN_TOP

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val placePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val viewRect = Rect()

    init {
        if (attrs != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.NumberFromTopTextView)
            borderWidth = ta.getDimension(
                R.styleable.NumberFromTopTextView_nftv_borderWidth,
                context.dpToPx(DEFAULT_BORDER_WIDTH)
            )
            borderColor = ta.getColor(
                R.styleable.NumberFromTopTextView_nftv_borderColor,
                DEFAULT_BORDER_AND_TITLE_COLOR
            )
            placeInTop = ta.getString(
                R.styleable.NumberFromTopTextView_nftv_placeInTop
            ) ?: DEFAULT_PLACE_IN_TOP
            titleColor = ta.getColor(
                R.styleable.NumberFromTopTextView_nftv_titleColor,
                borderColor
            )
            ta.recycle()
        }

        gravity = Gravity.CENTER
        setup()
    }

    fun setDataColor(@ColorInt color: Int) {
        borderColor = color
        titleColor = color
        borderPaint.color = color
        placePaint.color = color
        invalidate()
    }

    fun setPlace(place: String) {
        placeInTop = place
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        with(viewRect) {
            left = 0
            top = 0
            right = w
            bottom = h
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(placePaint) {
            color = titleColor
            textAlign = Paint.Align.CENTER
            textSize = height * 0.4f
            typeface = Typeface.DEFAULT_BOLD
        }
        val textOffsetY = (placePaint.descent() + placePaint.ascent()) / 2
        canvas.drawText(
            placeInTop,
            viewRect.exactCenterX(),
            viewRect.exactCenterY() - textOffsetY,
            placePaint
        )

        val half = (borderWidth / 2).toInt()
        viewRect.inset(half, half)
        canvas.drawOval(viewRect.toRectF(), borderPaint)
    }

    private fun setup() {
        with(borderPaint) {
            style = Paint.Style.STROKE
            strokeWidth = borderWidth
            color = borderColor
        }
    }

}