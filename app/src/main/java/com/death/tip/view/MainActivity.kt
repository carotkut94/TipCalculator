package com.death.tip.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.death.tip.R
import com.death.tip.viewmodel.CalculatorViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: com.death.tip.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = CalculatorViewModel()
    }
}
