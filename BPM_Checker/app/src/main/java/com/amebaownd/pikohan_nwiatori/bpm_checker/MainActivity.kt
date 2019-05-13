package com.amebaownd.pikohan_nwiatori.bpm_checker

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amebaownd.pikohan_nwiatori.bpm_checker.Drawable.Direction
import com.amebaownd.pikohan_nwiatori.bpm_checker.Drawable.HalfCircle
import com.amebaownd.pikohan_nwiatori.bpm_checker.View.BarGraph
import com.amebaownd.pikohan_nwiatori.bpm_checker.View.Circle

class MainActivity : AppCompatActivity() {

    lateinit var barGraph: BarGraph
    lateinit var circle: Circle
    private var previousTime:Long=0
    private var sum =0L
    private var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barGraph = findViewById(R.id.bar_graph)
        circle = findViewById(R.id.circle)
        val button = findViewById<Button>(R.id.button)
        button.background=HalfCircle(Color.YELLOW, Direction.BOTTOM)

        findViewById<Button>(R.id.button).setOnClickListener {
            if(previousTime==0L)
                previousTime=System.currentTimeMillis()
            else{
                count+=1
                val time = System.currentTimeMillis()
                sum+=time-previousTime
                barGraph.addBar((time-previousTime).toFloat())
                circle.update((60000/(sum/count)).toFloat())
                previousTime=time
            }
        }
    }
}
