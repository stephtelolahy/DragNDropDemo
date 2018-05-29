package com.creativegames.dragndropdemo

import android.content.Context
import android.util.AttributeSet
import android.view.View

class BoardView : View {

    @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        commonInit()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        commonInit()
    }

    private fun commonInit() {}
}