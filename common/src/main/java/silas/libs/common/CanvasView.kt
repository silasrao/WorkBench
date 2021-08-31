package silas.libs.common

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Canvas view
 * [Matrices for developers](https://i-rant.arnaudbos.com/matrices-for-developers/)
 * [Understanding Android Matrix transformations](https://medium.com/a-problem-like-maria/understanding-android-matrix-transformations-25e028f56dc7)
 */
class CanvasView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private var viewWidth = 0
    private var viewHeight = 0
    private var onDraw: OnDraw? = null
    private var onSizeChanged: OnSizeChanged? = null

    fun getViewWidth() = viewWidth
    fun getViewHeight() = viewHeight

    fun setOnDraw(onDraw: OnDraw) {
        this.onDraw = onDraw
    }

    fun setOnSizeChanged(onSizeChanged: OnSizeChanged) {
        this.onSizeChanged = onSizeChanged
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            onDraw?.onDraw(this, canvas)
        }
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
        onSizeChanged?.onSizeChanged(this)
    }

    interface OnSizeChanged {
        fun onSizeChanged(view: CanvasView)
    }

    interface OnDraw {
        fun onDraw(view: CanvasView, canvas: Canvas)
    }
}