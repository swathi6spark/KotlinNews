package com.swathi.kotlinnews

import androidx.appcompat.app.AppCompatActivity

import android.app.ActionBar
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

import com.squareup.picasso.Picasso

import org.w3c.dom.Text

class SecondActivity : AppCompatActivity() {


    private var thumbnailImage: ImageView? = null
    private var webView: WebView? = null
    private var progressBar: ProgressBar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        thumbnailImage = findViewById(R.id.thumbnail_image)
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)


        val intent = intent
        val title = intent.getStringExtra("title")
        (this as AppCompatActivity).supportActionBar!!.title = title

        val thumbnail = intent.getStringExtra("thumbnail")
        if (thumbnail!!.isEmpty()) {
            thumbnailImage?.visibility = View.GONE
        } else {

            Picasso.get().load(thumbnail).into(thumbnailImage)
        }

        val body = intent.getStringExtra("articleBody")
        webView?.webViewClient = WebViewClient()
        webView?.loadUrl(body)

    }

    inner class WebViewClient : android.webkit.WebViewClient() {
        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar?.visibility = View.GONE
        }
    }

}
