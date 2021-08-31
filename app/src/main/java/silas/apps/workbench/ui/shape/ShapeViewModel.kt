package silas.apps.workbench.ui.shape

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.ViewModel
import silas.libs.common.CanvasView

class ShapeViewModel : ViewModel(), CanvasView.OnDraw {
    override fun onDraw(view: CanvasView, canvas: Canvas) {
        canvas.drawCircle(400f, 400f, 100f, Paint().apply {
            color = Color.BLUE
            style = Paint.Style.FILL_AND_STROKE
            strokeWidth = 5f
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        })
    }
}