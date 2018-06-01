package com.creativegames.dragndropdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View


class BoardView : View {

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        commonInit(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        commonInit(attrs)
    }

    fun validTarget(view: View, x: Float, y: Float): PointF? {
        if (x > 100 && x < 500 && y > 100 && y < 500) {
            return PointF(500f - view.width / 2, 500f - view.height / 2)
        }
        return null
    }

    private val mPaint = Paint()
    private lateinit var mPawnLocations: Array<PawnLocation>

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

        val offset = Point(100, 100)
        val sideWidth = 100


        val row1: Array<String> = arrayOf("Hi", "are", "you")
        val row2: Array<String> = arrayOf("Hi", "are", "you")
        val grid: Array<Array<String>> = arrayOf(row1, row2)

        val element = grid[0][0]

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