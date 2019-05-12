package com.amebaownd.pikohan_nwiatori.bpm_checker.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.View

class Bar(context: Context, max: Float,color:Int,itemSize:Int,interval:Float,parentHeight:Float,parentWidth:Float) : View(context) {


    private var parentHeight = 100f
    private var parentWidth = 100f
    private var strokeWidth=0f
    private var interval:Float=5f
    private var itemSize=10
    private var max: Float = 100f
    private var color:Int = Color.RED
    private val paint= Paint()

    private var data = mutableListOf<Float>()


    init {
        setColor(color)
        setMax(max)
        setItemSize(itemSize)
        setParentHeight(parentHeight)
        setParentWidth(parentWidth)
        setInterval(interval)
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("aaaaa", "aaaaaaaaaaaaa")
        for(i in 0 until data.size) {
            val top = i * (strokeWidth + interval)
            val bottom = top + strokeWidth
            val right = data[i] / max * parentWidth
            Log.d("aaaaa", i.toString())
            canvas!!.drawRect(0f, top, right, bottom, paint)
        }
    }
    fun setColor(color:Int){
        this.color=color
        this.paint.color=color
    }
    fun setMax(num:Float){this.max=num}
    fun setItemSize(num:Int){this.itemSize=num}
    fun setInterval(width:Float){
        this.parentHeight=500f
        this.parentWidth=500f
        this.interval=width
        this.strokeWidth=(parentHeight-(itemSize-1)*width)/itemSize
        paint.strokeWidth=this.strokeWidth
    }
    fun setParentHeight(value:Float){this.parentHeight=value}
    fun setParentWidth(value:Float){this.parentWidth=value}

    fun update(data:MutableList<Float>){
        this.data=data
        invalidate()
    }
}