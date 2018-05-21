package com.example.ayushgupta.ktmy4

import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*

class MainActivity : AppCompatActivity() {

    var webView:WebView? = null
    var pb:ProgressBar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.wview)
        pb = findViewById(R.id.pb)
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.builtInZoomControls = true
        webView!!.addJavascriptInterface(this,"MyJavaInterface")
    }
    @JavascriptInterface
    fun display(s:String, s1:String ){
        Toast.makeText(this,s+"\t"+s1,Toast.LENGTH_SHORT).show()
    }
    fun ClickEvent(v: View){
        var editText = findViewById<EditText>(R.id.et1)
        when(v.id){
            R.id.iv1->webView!!.loadUrl("file:///android_asset/Login.html")
            R.id.iv2->{editText.setText("http://")
                        webView!!.loadUrl(editText.text.toString()+editText.toString())}
            R.id.iv3->webView!!.loadUrl("http://www.facebook.com")
            R.id.iv4-> webView!!.loadUrl("https://plus.google.com")
            R.id.iv5-> webView!!.loadUrl("https://www.tumblr.com")
            R.id.iv6-> webView!!.loadUrl("https://www.twitter.com")
        }
        webView!!.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                pb!!.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                pb!!.visibility = View.INVISIBLE
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }

    }
}
