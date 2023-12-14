package com.das_develop.cloudpod

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.das_develop.cloudpod.databinding.ActivityFeedbackBinding

class FeedbackActivity : AppCompatActivity() {

    lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.currentThemeNav[MainActivity.themeIndex])
        binding= ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Feedback"

        binding.sendFA.setOnClickListener {
            Toast.makeText(this,"feedbackMsg",Toast.LENGTH_LONG).show()
        }
    }
}