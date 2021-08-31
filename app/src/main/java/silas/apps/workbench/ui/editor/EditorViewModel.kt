package silas.apps.workbench.ui.editor

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import silas.libs.common.CanvasView

class EditorViewModel : ViewModel(), CanvasView.OnDraw, CanvasView.OnSizeChanged {
    private val _needsRefresh = MutableLiveData(false)
    val needsRefresh get() = _needsRefresh as LiveData<Boolean>

    private var imageBitmap: Bitmap? = null

    override fun onDraw(view: CanvasView, canvas: Canvas) {
        fit(view, canvas)
        _needsRefresh.value = false
    }

    private fun center(view: CanvasView, canvas: Canvas) {
        imageBitmap?.let {
            Log.e("Draw", "Drawing ...")
            val matrix = Matrix()
//            matrix.setRotate(90f, it.width / 2f, it.height / 2f)
            matrix.setTranslate((canvas.width - it.width) / 2f, (canvas.height - it.height) / 2f)
            canvas.drawBitmap(it, matrix, null)
        }
    }

    private fun fit(view: CanvasView, canvas: Canvas) {
        imageBitmap?.let {
            val viewAspect = 1f * canvas.width / canvas.height
            val imageAspect = 1f * it.width / it.height
            Log.e("Aspect", "View: $viewAspect, image: $imageAspect")
            var ratio = if (viewAspect > imageAspect) {
                1f * canvas.height / it.height
            } else {
                1f * canvas.width / it.height
            }
            Log.e("Draw", "Drawing $ratio")
            val matrix = Matrix()
            matrix.setScale(ratio, ratio)
            canvas.drawBitmap(it, matrix, null)
        }
    }

    override fun onSizeChanged(view: CanvasView) {
        _needsRefresh.value = true
    }

    fun setImageBitmap(bitmap: Bitmap?) {
        imageBitmap = bitmap
        _needsRefresh.value = true
    }
}