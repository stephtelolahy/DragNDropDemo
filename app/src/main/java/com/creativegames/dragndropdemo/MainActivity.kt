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
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pawn = ImageView(this)
        pawn.setImageResource(R.mipmap.ic_launcher)
        pawn.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        main_view.addView(pawn)

        // Drag and drop
        pawn.setOnTouchListener(TouchListener())
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
                view.visibility = View.INVISIBLE
                true
            } else {
                false
            }
        }
    }

    private var oldX = 0f
    private var oldY = 0f

    private inner class DragListener : OnDragListener {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    val view = event.localState as View
                    oldX = view.x
                    oldY = view.y
                }
                DragEvent.ACTION_DROP -> {
                    val view = event.localState as View
                    val target = board_view.validTarget(view, event.x, event.y)
                    if (target != null) {
                        view.x = target.x
                        view.y = target.y
                    } else {
                        view.x = oldX
                        view.y = oldY
                    }
                    view.visibility = View.VISIBLE
                }
            } // do nothing
            return true
        }
    }
}
