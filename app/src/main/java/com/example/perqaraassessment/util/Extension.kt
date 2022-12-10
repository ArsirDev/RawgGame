package com.example.perqaraassessment.util

import android.os.SystemClock
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.perqaraassessment.R

fun View.startAnimation(animation: Animation, onFinish: () -> Unit) {
    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(p0: Animation?) {

        }

        override fun onAnimationEnd(p0: Animation?) {
            onFinish()
        }

        override fun onAnimationRepeat(p0: Animation?) {

        }

    })
    this.startAnimation(animation)
}

fun View.setOnClickListenerWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(p0: View?) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()

            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun ImageView.loadImage(imageUrl: String, roundedValue: Int = 24) {
    Glide.with(this.context)
        .load(imageUrl)
        .error(ContextCompat.getDrawable(this.context, R.drawable.ic_broken_image_black))
        .placeholder(ContextCompat.getDrawable(this.context, R.drawable.ic_image_black))
        .transform(RoundedCorners(roundedValue))
        .into(this)
}

fun isLastVisible(rv: RecyclerView, shouldFindLastCompletelyVisible: Boolean = true): Boolean {
    val layoutManager = rv.layoutManager as LinearLayoutManager
    val pos = if (shouldFindLastCompletelyVisible) {
        layoutManager.findLastCompletelyVisibleItemPosition()
    } else {
        layoutManager.findFirstVisibleItemPosition()
    }
    val numItems: Int = rv.adapter?.itemCount ?: 0
    return pos >= numItems - 1
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.removeView() {
    this.visibility = View.GONE
}

fun ArrayList<View>.removeAllView() {
    this.forEach {
        it.removeView()
    }
}