package com.creativegames.dragndropdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class BoardView : View {

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        commonInit(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        commonInit(attrs)
    }

    private val mPaint = Paint()

    private fun commonInit(attrs: AttributeSet?) {
        val attributes = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.BoardViewStyleable,
                0, 0
        )

        try {
            mPaint.color = attributes.getColor(R.styleable.BoardViewStyleable_lines_color, -0x1000000)
        } finally {
            // release the TypedArray so that it can be reused.
            attributes.recycle()
        }


        mPaint.strokeWidth = 4f
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(100f, 100f, 500f, 100f, mPaint)
        canvas?.drawLine(500f, 100f, 500f, 500f, mPaint)
        canvas?.drawLine(500f, 500f, 100f, 500f, mPaint)
        canvas?.drawLine(100f, 500f, 100f, 100f, mPaint)
        canvas?.drawLine(100f, 100f, 500f, 500f, mPaint)
        canvas?.drawLine(500f, 100f, 100f, 500f, mPaint)
    }
}