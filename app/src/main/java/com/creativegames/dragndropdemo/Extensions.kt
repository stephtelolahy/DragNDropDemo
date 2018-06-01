package com.creativegames.dragndropdemo

import android.graphics.PointF
import android.view.View

fun View.setPosition(p: PointF) {
    this.x = p.x
    this.y = p.y
}

fun View.position() = PointF(this.x, this.y)