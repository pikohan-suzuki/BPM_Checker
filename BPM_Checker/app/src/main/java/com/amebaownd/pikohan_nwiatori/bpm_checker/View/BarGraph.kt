package com.amebaownd.pikohan_nwiatori.bpm_checker.View

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import com.amebaownd.pikohan_nwiatori.bpm_checker.R

class BarGraph : LinearLayout {
    constructor(context: Context):super(context,null)
    constructor(context: Context, attrs: AttributeSet?): super(context, attrs){initView(context,attrs)}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :super(context, attrs, defStyleAttr)

    private var color:Int =Color.RED
    private var textColor= Color.CYAN
    private var max:Float =100f
    private var itemSize:Int =10
    private var interval:Float=5f
    private var barQueue=BarQueue()
    lateinit var bar:Bar

    private fun initView(context: Context, attrs: AttributeSet?){
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.BarGraph)
        setColor(typedArray.getColor(R.styleable.BarGraph_bar_color,Color.RED))
        setTextColor(typedArray.getColor(R.styleable.BarGraph_bar_text_color,Color.CYAN))
        setMax(typedArray.getFloat(R.styleable.BarGraph_max,100f))
        setItemSize(typedArray.getInteger(R.styleable.BarGraph_item_size,10))
        setInterval(typedArray.getFloat(R.styleable.BarGraph_interval,5f))
        bar=Bar(this.context,this.max,this.color,this.textColor,this.itemSize,this.interval,this.height.toFloat(),this.width.toFloat())
        this.addView(bar)
    }

    fun addBar(data:Float){
        if(bar.getParentHeight()==0f) {
            bar.setParentWidth(this.width.toFloat())
            bar.setParentHeight(this.height.toFloat())
        }
        if(barQueue.getSize()<itemSize) {
            barQueue.enqueue(data)
        }else{
            barQueue.dequeue()
            barQueue.enqueue(data)
        }
        bar.update(barQueue.getList())
    }

    fun setColor(color:Int){this.color=color}
    fun setTextColor(color:Int){this.textColor=color}
    fun setMax(num:Float){max=num}
    fun setItemSize(num:Int){this.itemSize=num}
    fun setInterval(num:Float){this.interval=num}
    fun getColor() = color
    fun getMax()=max
    fun getItemSize()=itemSize
    fun reset(){
        barQueue.reset()
        bar.update(barQueue.getList())
    }

    class BarQueue(){
        private val mutableList= mutableListOf<Float>()
        fun enqueue(data:Float){
            mutableList.add(data)
        }
        fun dequeue():Float?{
            if(mutableList.size!=0){
                val result = mutableList[0]
                mutableList.removeAt(0)
                return result
            }
            return null
        }
        fun getSize() = mutableList.size
        fun getList()=mutableList
        fun reset(){
            while(mutableList.size!=0){
                dequeue()
            }
        }
    }
}