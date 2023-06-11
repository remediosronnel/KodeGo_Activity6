package com.remedios.activity6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.remedios.activity6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Proceed()
    }
    private fun Proceed(){
        binding.buttonProceed.setOnClickListener{
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }
    }
}