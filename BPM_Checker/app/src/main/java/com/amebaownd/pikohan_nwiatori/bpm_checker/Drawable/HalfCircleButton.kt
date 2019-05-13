package com.amebaownd.pikohan_nwiatori.bpm_checker.Drawable

import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.Drawable

class HalfCircleButton(private val color:Int,private val direction:Direction) : Drawable() {
    private var paint = Paint()

    override fun draw(canvas: Canvas) {
        paint.color=color
        paint.isAntiAlias=true
        val rectF= RectF(bounds)
        if(direction==Direction.LEFT) {
            canvas.translate(bounds.right/2f,0f)
            canvas.drawArc(rectF, 90f, 180f, true, paint)
            canvas.scale(2f,2f)
        }else if(direction==Direction.RIGHT){
            canvas.translate(-bounds.right/2f,0f)
            canvas.drawArc(rectF, -90f, 180f, true, paint)
            canvas.scale(2f,2f)
        }else if(direction==Direction.BOTTOM){
            canvas.translate(-bounds.right/2f,-bounds.bottom.toFloat())
            canvas.scale(2f,2f)
            canvas.drawArc(rectF,0f, 180f, true, paint)

        }else{
            canvas.translate(-bounds.right/2f,0f)
            canvas.scale(2f,2f)
            canvas.drawArc(rectF, -180f, 180f, true, paint)

        }
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }
    override fun getOpacity() = paint.alpha

    override fun setColorFilter(colorFilter: ColorFilter) {
        paint.colorFilter = colorFilter
    }
}