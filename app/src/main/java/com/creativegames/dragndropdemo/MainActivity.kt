package com.creativegames.dragndropdemo

import android.app.Activity
import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.view.View.OnDragListener
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.ImageView


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val contentView = findViewById<View>(android.R.id.content) as FrameLayout

        val imageView = ImageView(this)
        imageView.setImageResource(R.mipmap.ic_launcher)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        contentView.addView(imageView)

        // Drag and drop
        imageView.setOnTouchListener(TouchListener())
        contentView.setOnDragListener(DragListener())

        // Restrict drop area


        // Animate translation
        val x = imageView.x
        val y = imageView.y
        val translateAnimation = TranslateAnimation(0.0f, 100.0f, 0.0f, 300.0f)
        translateAnimation.duration = 2000
        translateAnimation.interpolator = AccelerateDecelerateInterpolator()
        translateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                imageView.x = x + 100f
                imageView.y = y + 300f
            }
        })
        imageView.startAnimation(translateAnimation)

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
