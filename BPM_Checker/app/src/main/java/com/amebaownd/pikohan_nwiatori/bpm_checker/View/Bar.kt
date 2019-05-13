package com.amebaownd.pikohan_nwiatori.bpm_checker.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View

class Bar(context: Context, max: Float,color:Int,textColor:Int,itemSize:Int,interval:Float,parentHeight:Float,parentWidth:Float) : View(context) {


    private var parentHeight = 100f
    private var parentWidth = 100f
    private var strokeWidth=0f
    private var interval:Float=5f
    private var itemSize=10
    private var max: Float = 100f
    private var color:Int = Color.RED
    private var textColor=Color.CYAN
    private val paint= Paint()
    private val textPaint=Paint()

    private var data = mutableListOf<Float>()


    init {
        setColor(color)
        setTextColor(textColor)
        setMax(max)
        setItemSize(itemSize)
        setParentHeight(parentHeight)
        setParentWidth(parentWidth)
        setInterval(interval)
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas?) {
        textPaint.textSize=strokeWidth/2
        for(i in 0 until data.size) {
            val top = i * (strokeWidth + interval)
            val bottom = top + strokeWidth
            val right = data[i] / max * parentWidth
            paint.alpha=255-(200*data[i]/max).toInt()
            canvas!!.drawRect(0f, top, right, bottom, paint)
            canvas.drawText(data[i].toInt().toString(),right+5,top+strokeWidth/2,textPaint)
        }
    }
    fun setColor(color:Int){
        this.color=color
        this.paint.color=color
    }
    fun setTextColor(color:Int){
        this.textColor = color
        this.textPaint.color=color
    }
    fun setMax(num:Float){this.max=num}
    fun setItemSize(num:Int){this.itemSize=num}
    fun setInterval(width:Float){
        this.interval=width
        this.strokeWidth=(parentHeight-(itemSize-1)*interval)/itemSize
        paint.strokeWidth=this.strokeWidth
    }
    fun setParentHeight(value:Float){
        this.parentHeight=value
        setInterval(this.interval)
    }
    fun setParentWidth(value:Float){
        this.parentWidth=value
        setInterval(this.interval)
    }

    fun update(data:MutableList<Float>){
        this.data=data
        invalidate()
    }
    fun getParentHeight()=this.parentHeight
}