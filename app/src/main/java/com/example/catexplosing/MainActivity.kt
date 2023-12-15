package com.example.catexplosing

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView

class MainActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnBoom: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)
        btnBoom = findViewById(R.id.btn_boom)

        btnBoom.setOnClickListener {
            setVideoExplosing()
        }
    }

    private fun setVideoSitting(){
        videoView.setVideoURI(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.cat_sitting))
        videoView.start()
        videoView.setOnCompletionListener {
            videoView.start()
        }
    }

    private fun setVideoExplosing(){
        videoView.stopPlayback()
        videoView.setVideoURI(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.cat_explosing))
        videoView.start()
        videoView.setOnCompletionListener { setVideoSitting() }
    }

    override fun onPause() {
        super.onPause()
        videoView.stopPlayback()
    }

    override fun onStart() {
        super.onStart()
        setVideoSitting()
    }
}