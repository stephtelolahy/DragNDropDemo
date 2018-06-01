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
    var P = ArrayList<PointF>()

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
        val S = min(w, h) - 2 * margin
        val C = S / 4
        val Ox = (w - S) / 2
        val Oy = (h - S) / 2
        P.clear()

        for (li in 0..4)
            for (co in 0..4) {
                P.add(PointF(Ox + co * C, Oy + li * C))
            }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawLine(P[0], P[4], mPaint)
        canvas?.drawLine(P[5], P[9], mPaint)
        canvas?.drawLine(P[10], P[14], mPaint)
        canvas?.drawLine(P[15], P[19], mPaint)
        canvas?.drawLine(P[20], P[24], mPaint)

        canvas?.drawLine(P[0], P[20], mPaint)
        canvas?.drawLine(P[1], P[21], mPaint)
        canvas?.drawLine(P[2], P[22], mPaint)
        canvas?.drawLine(P[3], P[23], mPaint)
        canvas?.drawLine(P[4], P[24], mPaint)

        canvas?.drawLine(P[0], P[24], mPaint)
        canvas?.drawLine(P[10], P[22], mPaint)
        canvas?.drawLine(P[2], P[14], mPaint)

        canvas?.drawLine(P[4], P[20], mPaint)
        canvas?.drawLine(P[2], P[10], mPaint)
        canvas?.drawLine(P[14], P[22], mPaint)
    }

    fun validTarget(view: View, x: Float, y: Float): PointF? {
        return P.firstOrNull { abs(x - it.x) < view.width && abs(y - it.y) < view.height }
    }
}

fun Canvas.drawLine(p1: PointF, p2: PointF, paint: Paint) {
    drawLine(p1.x, p1.y, p2.x, p2.y, paint)
}