package com.amebaownd.pikohan_nwiatori.bpm_checker

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amebaownd.pikohan_nwiatori.bpm_checker.Drawable.Direction
import com.amebaownd.pikohan_nwiatori.bpm_checker.Drawable.HalfCircleButton
import com.amebaownd.pikohan_nwiatori.bpm_checker.Drawable.TabButton
import com.amebaownd.pikohan_nwiatori.bpm_checker.View.BarGraph
import com.amebaownd.pikohan_nwiatori.bpm_checker.View.Circle

class MainActivity : AppCompatActivity() {

    lateinit var barGraph: BarGraph
    lateinit var circle: Circle
    private var previousTime:Long=0
    private var sum =0L
    private var count=0
    private var isSaved=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barGraph = findViewById(R.id.bar_graph)
        circle = findViewById(R.id.circle)
        val recordButton = findViewById<Button>(R.id.record_button)
        recordButton.background=HalfCircleButton(Color.YELLOW, Direction.TOP)
        val saveButton = findViewById<Button>(R.id.save)
        saveButton.background=TabButton(Color.YELLOW,Direction.LEFT)
        saveButton.setOnClickListener{
            isSaved=true
            saveButton.text=getString(R.string.saved)
        }
        val resetButton = findViewById<Button>(R.id.reset)
        resetButton.background=TabButton(Color.YELLOW,Direction.LEFT)
        resetButton.setOnClickListener{
            barGraph.reset()
            circle.reset()
            previousTime=0L
            sum=0L
            count=0
            isSaved=false
            saveButton.text=getString(R.string.save)
        }

        findViewById<Button>(R.id.record_button).setOnClickListener {
            if(!isSaved) {
                if (previousTime == 0L)
                    previousTime = System.currentTimeMillis()
                else {
                    count += 1
                    val time = System.currentTimeMillis()
                    sum += time - previousTime
                    barGraph.addBar((time - previousTime).toFloat())
                    circle.update((60000 / (sum / count)).toFloat())
                    previousTime = time
                }
            }
        }
    }
}
