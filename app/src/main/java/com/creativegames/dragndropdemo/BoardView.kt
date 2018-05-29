package com.creativegames.dragndropdemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class BoardView : View {

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        commonInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        commonInit()
    }

    private val mPaint = Paint()

    private fun commonInit() {

//        mPaint.isAntiAlias = true
//        mPaint.isDither = true
//        mPaint.style = Paint.Style.STROKE
//        mPaint.strokeJoin = Paint.Join.ROUND
//        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = 4f
//        mPaint.alpha = 255
        mPaint.color = Color.GRAY
//        mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DARKEN)

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