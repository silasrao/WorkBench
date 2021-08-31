package silas.apps.workbench.ui.text

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.ViewModel
import silas.libs.common.CanvasView

class TextViewModel : ViewModel(), CanvasView.OnDraw {
    private val paint = Paint().apply {
        color = Color.RED
        textSize = 50f
    }

    override fun onDraw(view: CanvasView, canvas: Canvas) {
        canvas.drawText("This is the test!!!", 100f, 100f, paint)
    }
}