package com.amebaownd.pikohan_nwiatori.bpm_checker.View

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.amebaownd.pikohan_nwiatori.bpm_checker.R

class Circle : View {
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        initView(attrs)
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        initView(attrs)
    }

    private var centerx = 0f
    private var centery = 0f
    private var radius = 100f
    private var max=300f
    private var color:Int=Color.RED
    private var strokeWidth=0.5f
    private var primaryPaint = Paint()
    private var secondaryPaint=Paint()

    fun setCenterx(num:Float){this.centerx=num}
    fun setCentery(num:Float){this.centery=num}
    fun setRaduis(num:Float){this.radius=num}
    fun setMax(num:Float){this.max=num}
    fun setStrokeWidth(width:Float){
        this.strokeWidth=width
        primaryPaint.strokeWidth=width
        secondaryPaint.strokeWidth=0.5f
    }
    fun setColor(color:Int){
        this.color=color
        primaryPaint.color=color
        secondaryPaint.color=color
    }

    private fun initView(attrs:AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Circle)
        setColor(typedArray.getColor(R.styleable.Circle_circle_color, Color.RED))
        setStrokeWidth(typedArray.getFloat(R.styleable.Circle_stroke_width, 2f))
        setMax(typedArray.getFloat(R.styleable.Circle_circle_max, 300f))

        setCenterx(width/2f)
        setCentery(height/2f)
        primaryPaint.style=Paint.Style.STROKE
    }

    fun update(data:Float){
        if(data>max)
            radius=height/2f
        else
            radius=height/2*data/max
        setCenterx(width/2f)
        setCentery(height/2f)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        if (centerx != 0f) {
            secondaryPaint.alpha=30
            canvas!!.drawCircle(centerx, centery, radius, primaryPaint)
            canvas!!.drawCircle(centerx,centery,radius-strokeWidth,secondaryPaint)
        }
    }
}