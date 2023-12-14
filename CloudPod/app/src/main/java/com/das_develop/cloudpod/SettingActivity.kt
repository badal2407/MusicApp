package com.das_develop.cloudpod

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.das_develop.cloudpod.databinding.ActivitySettingBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class SettingActivity : AppCompatActivity() {

    lateinit var  binding:ActivitySettingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.currentThemeNav[MainActivity.themeIndex])
        binding= ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Setting"
        when(MainActivity.themeIndex){

            0 ->binding.redTheme.setBackgroundColor(Color.YELLOW)
            1 ->binding.blueTheme.setBackgroundColor(Color.YELLOW)
            2 ->binding.tealTheme.setBackgroundColor(Color.YELLOW)
            3 ->binding.greenTheme.setBackgroundColor(Color.YELLOW)
            4 ->binding.blackTheme.setBackgroundColor(Color.YELLOW)
        }
        binding.redTheme.setOnClickListener { saveTheme(0) }
        binding.blueTheme.setOnClickListener { saveTheme(1) }
        binding.tealTheme.setOnClickListener { saveTheme(2) }
        binding.greenTheme.setOnClickListener { saveTheme(3) }
        binding.blackTheme.setOnClickListener { saveTheme(4) }

        binding.versionName.text = setVersionDetails()

        binding.sortBtn.setOnClickListener {

            val menuList = arrayOf("RecentlyAdded","SongTitle","FileSize")
            var currentSort = MainActivity.sortOrder
            val builder = MaterialAlertDialogBuilder(this)
            builder.setTitle("Sorting")
                .setPositiveButton("OK"){_, _ ->
                    val editor = getSharedPreferences("SORTING", MODE_PRIVATE).edit()
                    editor.putInt("sortOrder",currentSort)
                    editor.apply()
                }
                .setSingleChoiceItems(menuList,currentSort){ _,which ->
                    currentSort = which

                }

            val customDialog = builder.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)

        }

    }

    @SuppressLint("NewApi")
    private fun saveTheme(index:Int){
        if(MainActivity.themeIndex != index){
            val editor = getSharedPreferences("THEMES", MODE_PRIVATE).edit()
            editor.putInt("themeIndex",index)
            editor.apply()
            val builder = MaterialAlertDialogBuilder(this)
            builder.setTitle("Apply Theme")
                .setMessage("Do you want to apply theme?")
                .setPositiveButton("Yes"){_, _ ->
                    exitApplication()
                }
                .setNegativeButton("No"){dialog, _ ->
                    dialog.dismiss()
                }
            val customDialog = builder.create()
            customDialog.show()
            customDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE)
            customDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE)
        }

    }


    private  fun setVersionDetails():String{
        return "Version Name: 1.0"
    }
}