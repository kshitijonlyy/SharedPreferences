package com.kshitij.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kshitij.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var colorInterface: ColorInterface
    lateinit var sharedPrefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        editor = sharedPrefs.edit()

        if (sharedPrefs.contains("color")) {
            if (binding.rbRed.isChecked) {
                binding.rbRed.setChecked(sharedPrefs.getBoolean("color", true))
            } else if (binding.rbGreen.isChecked) {
                binding.rbGreen.setChecked(sharedPrefs.getBoolean("color", true))
            } else if (binding.rbBlue.isChecked) {
                binding.rbBlue.setChecked(sharedPrefs.getBoolean("color", true))
            }
        }
        binding.btnApply.setOnClickListener {
            if (binding.etName.text.toString().isNullOrEmpty()) {
                binding.etName.error = "Enter Name"
                binding.etName.requestFocus()
            } else if (binding.etRoll.text.toString().isNullOrEmpty()) {
                binding.etRoll.error = "Enter Roll Number"
                binding.etRoll.requestFocus()
            } else if (binding.etPrice.text.toString().isNullOrEmpty()) {
                binding.etPrice.error = "Enter Price"
                binding.etPrice.requestFocus()
            } else if (binding.rbRed.isChecked == false or binding.rbGreen.isChecked == false or binding.rbBlue.isChecked == false) {
                Toast.makeText(this, "Choose A Color", Toast.LENGTH_SHORT).show()
            } else {
                if (binding.rbRed.isChecked) {
                    colorInterface.changeColor(1)
                    editor.putBoolean("color", true)
                    editor.apply()
                    editor.commit()
                } else if (binding.rbGreen.isChecked) {
                    colorInterface.changeColor(2)
                    editor.putBoolean("color", true)
                    editor.apply()
                    editor.commit()
                } else if (binding.rbBlue.isChecked) {
                    colorInterface.changeColor(3)
                    editor.putBoolean("color", true)
                    editor.apply()
                    editor.commit()
                }
            }
        }

    }
}