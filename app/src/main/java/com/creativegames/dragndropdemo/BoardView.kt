package com.creativegames.dragndropdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import kotlin.math.abs
import kotlin.math.min


class BoardView : View {

    private val mPaint = Paint()
    private var P = ArrayList<PointF>()

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        commonInit(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        commonInit(attrs)
    }

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val margin = 100f
        val W = min(w, h) - 2 * margin
        val Ox = margin
        val Oy = margin
        P.clear()
        P.add(PointF(Ox, Oy))
        P.add(PointF(Ox + W, Oy))
        P.add(PointF(Ox, Oy + W))
        P.add(PointF(Ox + W, Oy + W))
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(P[0], P[1], mPaint)
        canvas?.drawLine(P[1], P[3], mPaint)
        canvas?.drawLine(P[3], P[2], mPaint)
        canvas?.drawLine(P[2], P[0], mPaint)
        canvas?.drawLine(P[0], P[3], mPaint)
        canvas?.drawLine(P[1], P[2], mPaint)
    }

    fun validTarget(view: View, x: Float, y: Float): PointF? {
        return P.firstOrNull { abs(x - it.x) < view.width && abs(y - it.y) < view.height }
    }
}

fun Canvas.drawLine(p1: PointF, p2: PointF, paint: Paint) {
    drawLine(p1.x, p1.y, p2.x, p2.y, paint)
}