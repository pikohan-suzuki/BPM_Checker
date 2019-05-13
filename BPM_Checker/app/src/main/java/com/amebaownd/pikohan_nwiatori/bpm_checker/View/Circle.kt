package com.amebaownd.pikohan_nwiatori.bpm_checker.View

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.amebaownd.pikohan_nwiatori.bpm_checker.R

class Circle : View {
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private var centerx = 0f
    private var centery = 0f
    private var radius = 0f
    private var previousRadius = 0f
    private var previousAlpha =0f
    private var max = 300f
    private var color: Int = Color.RED
    private var textColor:Int =Color.CYAN
    private var strokeWidth = 0.5f
    private var primaryPaint = Paint()
    private var secondaryPaint = Paint()
    private var textPaint=Paint()
    private var data=0f

    fun setCenterx(num: Float) {
        this.centerx = num
    }

    fun setCentery(num: Float) {
        this.centery = num
    }

    fun setRaduis(num: Float) {
        this.radius = num
    }

    fun setMax(num: Float) {
        this.max = num
    }

    fun setStrokeWidth(width: Float) {
        this.strokeWidth = width
        primaryPaint.strokeWidth = width
        secondaryPaint.strokeWidth = 0.5f
    }

    fun setColor(color: Int) {
        this.color = color
        primaryPaint.color = color
        secondaryPaint.color = color
    }
    fun setTextColor(color:Int){
        this.textColor=color
        textPaint.color=color
    }

    private fun initView(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.Circle)
        setColor(typedArray.getColor(R.styleable.Circle_circle_color, Color.RED))
        setStrokeWidth(typedArray.getFloat(R.styleable.Circle_stroke_width, 2f))
        setMax(typedArray.getFloat(R.styleable.Circle_circle_max, 300f))
        setTextColor(typedArray.getColor(R.styleable.Circle_circle_text_color, Color.CYAN))

        setCenterx(width/2f)
        setCentery(height/2f)
        primaryPaint.style = Paint.Style.STROKE
    }

    fun update(data: Float) {
        this.data=data
        previousRadius = radius
        if (data > max) {
            radius = height / 2f
            previousAlpha=secondaryPaint.alpha.toFloat()
            secondaryPaint.alpha = 130
        } else {
            radius = height / 2 * data / max
            previousAlpha=secondaryPaint.alpha.toFloat()
            secondaryPaint.alpha = 200 - (70 * data / max).toInt()
        }
        setCenterx(width /2f)
        setCentery(height / 2f)
        textPaint.textSize=radius/2.5f
        invalidate()
    }

    fun reset(){
        update(0f)
    }

    override fun onDraw(canvas: Canvas?) {
        if (centerx != 0f) {
            canvas!!.drawCircle(centerx, centery, radius, primaryPaint)
            canvas!!.drawCircle(centerx, centery, radius - strokeWidth, secondaryPaint)
            canvas!!.drawText("♩= "+data.toInt().toString(),centerx-(textPaint.textSize*3)/2,centery+textPaint.textSize/2,textPaint)
            val alphaAnimation = PropertyValuesHolder.ofFloat("alpha", previousAlpha/255f, secondaryPaint.alpha/255f)
            val xScaleAnimation = PropertyValuesHolder.ofFloat("scaleX",previousRadius/radius,1f)
            val yScaleAnimation = PropertyValuesHolder.ofFloat("scaleY",previousRadius/radius,1f)
            val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, alphaAnimation,xScaleAnimation,yScaleAnimation)
            objectAnimator.setDuration(200)
            objectAnimator.start()
        }

    }
}
