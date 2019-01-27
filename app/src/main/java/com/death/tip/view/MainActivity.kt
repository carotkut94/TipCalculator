package com.death.tip.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.death.tip.R
import com.death.tip.viewmodel.CalculatorViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), SaveDialogFragment.Callback {

    lateinit var binding: com.death.tip.databinding.ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e(TAG,"onCreate()")
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.vm = ViewModelProviders.of(this).get(CalculatorViewModel::class.java)
    }

    override fun onSaveTip(name: String) {
        Snackbar.make(binding.root, "Saved $name", Snackbar.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tip, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_save ->{
                showSaveDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSaveDialog() {
        val saveDialogFragment = SaveDialogFragment()
        saveDialogFragment.show(supportFragmentManager,"SaveDialog")
    }
}

const val TAG = "MainActivity"
