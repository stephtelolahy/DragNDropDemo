package com.creativegames.dragndropdemo

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnTouchListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 1..5) {
            val pawn = Pawn(this)
            main_view.addView(pawn)
            pawn.setOnTouchListener(TouchListener())
        }
        main_view.setOnDragListener(DragListener())


        // Animate translation
        /*
        val x = imageView.x
        val y = imageView.y
        val targetX = 300f
        val targetY = 300f
        val translateAnimation = TranslateAnimation(0.0f, (targetX - x), 0.0f, (targetY - y))
        translateAnimation.duration = 2000
        translateAnimation.interpolator = AccelerateDecelerateInterpolator()
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                imageView.x = targetX
                imageView.y = targetY
            }
        })
        imageView.startAnimation(translateAnimation)
        */
    }

    private inner class TouchListener : OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDrag(data, shadowBuilder, view, 0)
                true
            } else {
                false
            }
        }
    }

    private inner class DragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            val pawn = event.localState as Pawn
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> pawn.startDragging()
                DragEvent.ACTION_DROP -> {
                    val target = board_view.validTarget(pawn, event.x, event.y)
                    if (target != null) {
                        pawn.dropAt(target)
                    } else {
                        pawn.putBack()
                    }
                    pawn.visibility = View.VISIBLE
                }
            }
            return true
        }
    }
}