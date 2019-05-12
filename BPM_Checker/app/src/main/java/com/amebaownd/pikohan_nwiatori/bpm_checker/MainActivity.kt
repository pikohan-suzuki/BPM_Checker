package com.amebaownd.pikohan_nwiatori.bpm_checker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.amebaownd.pikohan_nwiatori.bpm_checker.View.BarGraph
import com.amebaownd.pikohan_nwiatori.bpm_checker.View.Circle
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var barGraph:BarGraph
    lateinit var circle:Circle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        barGraph = findViewById<com.amebaownd.pikohan_nwiatori.bpm_checker.View.BarGraph>(R.id.bar_graph)
        circle=findViewById(R.id.circle)

        val rand = Random()

        findViewById<Button>(R.id.button).setOnClickListener{barGraph.addBar(rand.nextInt(100).toFloat())
        circle.update()}
    }

    override fun onResume() {
        super.onResume()
        barGraph.addBar(10f)

    }
}
