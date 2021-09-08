package com.example.roomex2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.roomex2.databinding.ActivityMainBinding
import com.example.roomex2.ui.FrmContact

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )
        supportFragmentManager.commit {
            replace<FrmContact>(R.id.container_frm)
            setReorderingAllowed(true)
            addToBackStack("A")
        }

    }
}