package com.kshitij.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.kshitij.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs = getSharedPreferences(resources.getString(R.string.app_name), MODE_PRIVATE)
        editor = sharedPrefs.edit()

        getPrefs()

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
                editor.putString("name", binding.etName.text.toString())
                editor.putInt("roll", (binding.etRoll.text.toString()).toInt())
                editor.putFloat("price", (binding.etPrice.text.toString()).toFloat())
                if (binding.rbRed.isChecked) {
                    changeColor(1)
                    editor.putInt("color", 1)
                } else if (binding.rbGreen.isChecked) {
                    changeColor(2)
                    editor.putInt("color", 2)
                } else if (binding.rbBlue.isChecked) {
                    changeColor(3)
                    editor.putInt("color", 3)
                }
                editor.apply()
                editor.commit()
            }
        }

    }

    fun changeColor(color: Int) {
        when (color) {
            0 -> binding.colorFragment.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.white
                )
            )
            1 -> binding.colorFragment.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_red_dark
                )
            )
            2 -> binding.colorFragment.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_green_dark
                )
            )
            3 -> binding.colorFragment.setBackgroundColor(
                ContextCompat.getColor(
                    this,
                    android.R.color.holo_blue_dark
                )
            )
        }
    }

    fun getPrefs() {
        binding.etName.setText(sharedPrefs.getString("name", ""))
        binding.etRoll.setText(sharedPrefs.getInt("roll", 0).toString())
        binding.etPrice.setText(sharedPrefs.getFloat("price", 0F).toString())
            when (sharedPrefs.getInt("color", 0)) {
                0 -> {
                    binding.rgColors.clearCheck()
                    changeColor(0)
                }
                1 -> {
                    binding.rbRed.isChecked = true
                    changeColor(1)
                }
                2 -> {
                    binding.rbGreen.isChecked = true
                    changeColor(2)
                }
                3 -> {
                    binding.rbBlue.isChecked = true
                    changeColor(3)
                }
            }

    }

    fun clearPrefs(view: View) {
        editor.clear()
        editor.apply()
        getPrefs()
    }
}