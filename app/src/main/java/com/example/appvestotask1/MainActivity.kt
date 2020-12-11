package com.example.appvestotask1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.appvestotask1.data.InfoData
import com.example.appvestotask1.util.PageParser
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val parser = PageParser()
        var dataList = ArrayList<InfoData>()
        val rotateAnimation: Animation = AnimationUtils.loadAnimation(this, R.anim.rotate)
        val loadingImage: ImageView = findViewById(R.id.loadingImage)
        val nameLayout: LinearLayout = findViewById(R.id.nameLayout)
        val timeLayout: LinearLayout = findViewById(R.id.timeLayout)
        val loadButton: Button = findViewById(R.id.loadButton)
        val sortButton: Button = findViewById(R.id.sortButton)
        loadButton.setOnClickListener {
            loadingImage.visibility = View.VISIBLE
            loadingImage.startAnimation(rotateAnimation)

            Thread {
                dataList = parser.parse("https://www.udemy.com/course/learn-flutter-dart-to-build-ios-android-apps/")
                runOnUiThread {
                    sortButton.isEnabled = true
                    updateData(nameLayout, timeLayout, dataList)
                }
                loadingImage.clearAnimation()
                loadingImage.visibility = View.INVISIBLE
            }.start()
        }
        sortButton.setOnClickListener {
            loadingImage.clearAnimation()
            dataList.sort()
            runOnUiThread {
                updateData(nameLayout, timeLayout, dataList)
            }

        }
    }

    private fun updateData(nameLayout: LinearLayout, timeLayout: LinearLayout, dataList: List<InfoData>) {
        nameLayout.removeAllViews()
        timeLayout.removeAllViews()
        dataList.forEach { data ->
            val nameTextView = TextView(this)
            val timeTextView = TextView(this)
            nameTextView.text = data.title
            timeTextView.text = data.time
            nameLayout.addView(nameTextView)
            timeLayout.addView(timeTextView)
        }
    }
}