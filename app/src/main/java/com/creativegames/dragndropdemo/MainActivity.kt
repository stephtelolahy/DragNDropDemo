package com.creativegames.dragndropdemo

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnTouchListener
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Drag and drop
        myimage1.setOnTouchListener(TouchListener())
        findViewById<View>(android.R.id.content).setOnDragListener(DragListener())

        // Restrict drop area


        // Animate translation
        val x = myimage1.x
        val y = myimage1.y
        val translateAnimation = TranslateAnimation(0.0f, 100.0f, 0.0f, 300.0f)
        translateAnimation.duration = 2000
        translateAnimation.interpolator = AccelerateDecelerateInterpolator()
        translateAnimation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                myimage1.x = x + 100f
                myimage1.y = y + 300f
            }
        })
        myimage1.startAnimation(translateAnimation)

    }

    private inner class TouchListener : OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            return if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val data = ClipData.newPlainText("", "")
                val shadowBuilder = View.DragShadowBuilder(
                        view)
                view.startDrag(data, shadowBuilder, view, 0)
                view.visibility = View.INVISIBLE
                true
            } else {
                false
            }
        }
    }

    private inner class DragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                }
                DragEvent.ACTION_DROP -> {
                    val view = event.localState as View
                    view.x = event.x - view.width / 2
                    view.y = event.y - view.height / 2
                    view.visibility = View.VISIBLE
                }
            }// do nothing
            return true
        }
    }

}
