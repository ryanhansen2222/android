package com.example.cvisionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() implements{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val noobButton = findViewById<Button>(R.id.button3)
        noobButton.setOnClickListener(onClick(R))
    }

    fun onClick(View v){
        switch  (v.getId()){

        }
    }
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onResume() {
        super.onResume()
    }
}

