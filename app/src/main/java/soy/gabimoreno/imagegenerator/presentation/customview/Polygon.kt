package soy.gabimoreno.imagegenerator.presentation.customview

import android.graphics.*
import android.graphics.drawable.Drawable
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class Polygon(private val nSides: Int) : Drawable() {

    private val path = Path()
    private val auxPath = Path()

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
    }

    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun getOpacity(): Int {
        return paint.alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)
        computeHex(bounds)
        invalidateSelf()
    }

    private fun computeHex(bounds: Rect) {
        val width = bounds.width()
        val height = bounds.height()
        val size = min(width, height)
        val centerX = bounds.left + width / 2
        val centerY = bounds.top + height / 2
        path.reset()
        path.addPath(createPolygon(size, centerX, centerY))
        path.addPath(createPolygon((size * .8f).toInt(), centerX, centerY))
    }

    private fun createPolygon(size: Int, centerX: Int, centerY: Int): Path {
        val section = (2.0 * Math.PI / nSides).toFloat()
        val radius = size / 2
        val polygonPath = auxPath
        polygonPath.reset()
        polygonPath.moveTo(
            centerX + radius * cos(0.0).toFloat(),
            centerY + radius * sin(0.0).toFloat()
        )
        for (i in 1 until nSides) {
            polygonPath.lineTo(
                centerX + radius * cos(section * i.toDouble()).toFloat(),
                centerY + radius * sin(section * i.toDouble()).toFloat()
            )
        }
        polygonPath.close()
        return polygonPath
    }
}
