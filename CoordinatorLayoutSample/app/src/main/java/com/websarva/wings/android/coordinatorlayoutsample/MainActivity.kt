package com.websarva.wings.android.coordinatorlayoutsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setLogo(R.mipmap.ic_launcher)
        setSupportActionBar(toolbar)

        val toolbarlayout = findViewById<CollapsingToolbarLayout>(R.id.toolbarLayout)
        toolbarlayout.title = getString(R.string.toolbar_title
        )
        toolbarlayout.setExpandedTitleColor(Color.WHITE)
        toolbarlayout.setCollapsedTitleTextColor(Color.LTGRAY)

    }
}