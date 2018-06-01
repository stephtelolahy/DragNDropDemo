package com.creativegames.dragndropdemo

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

class Pawn : ImageView {

    private var oldPosition = PointF(0f, 0f)

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        commonInit(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        commonInit(attrs)
    }

    private fun commonInit(attrs: AttributeSet?) {
        setImageResource(R.mipmap.ic_launcher)
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    fun startDragging() {
        oldPosition = position()
        visibility = View.INVISIBLE
    }

    fun dropAt(center: PointF) {
        val point = PointF(center.x - width / 2, center.y - height / 2)
        setPosition(point)
        visibility = View.VISIBLE
    }

    fun putBack() {
        setPosition(oldPosition)
        visibility = View.VISIBLE
    }

}

private fun View.setPosition(p: PointF) {
    x = p.x
    y = p.y
}

private fun View.position() = PointF(x, y)