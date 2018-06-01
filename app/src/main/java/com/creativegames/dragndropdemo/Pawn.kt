package com.creativegames.dragndropdemo

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.ImageView

class Pawn : ImageView {

    var oldPosition = PointF(0f, 0f)

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

}