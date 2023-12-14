package com.das_develop.cloudpod

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.das_develop.cloudpod.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.currentThemeNav[MainActivity.themeIndex])
        binding= ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "About"

        binding.aboutText.text = aboutText()
    }

    private fun aboutText():String {

        return "Developed By: Badal Das" +
                "\n\nif you want provide feedback,I will love to hear that."
    }
}