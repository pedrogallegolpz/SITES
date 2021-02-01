package com.example.sites

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.util.DisplayMetrics
import android.util.Log
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.datatransport.runtime.util.PriorityMapping.toInt


class SitesBot : AppCompatActivity(), RecognitionListener {
    private val MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 101
    private var myRequest: PermissionRequest? = null
    var vistachat: WebView?= null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sites_bot)

    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()

        vistachat = findViewById<WebView>(R.id.webview)

        val height =  (getResources().getDisplayMetrics().heightPixels/getResources().getDisplayMetrics().density).toInt()-22 // ancho absoluto en pixels
        val width =  (getResources().getDisplayMetrics().widthPixels/getResources().getDisplayMetrics().density).toInt()-22


        val html =
            "<iframe allow=\"microphone\" height=\""+height.toString()+"\" width=\""+width.toString()+"\" src=\"https://console.dialogflow.com/api-client/demo/embedded/5853c2c1-a522-4534-b82f-5b3c72c64389\"></iframe>"

        vistachat?.settings?.javaScriptEnabled = true
        vistachat?.settings?.javaScriptCanOpenWindowsAutomatically = true
        vistachat?.webViewClient= WebViewClient()
        vistachat?.settings?.setSupportZoom(false)
        vistachat?.settings?.cacheMode=WebSettings.LOAD_NO_CACHE

        vistachat?.webChromeClient= object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                myRequest = request
                for (permission in request.resources) {
                    when (permission) {
                        "android.webkit.resource.AUDIO_CAPTURE" -> {
                            askForPermission(
                                request.origin.toString(),
                                Manifest.permission.RECORD_AUDIO,
                                MY_PERMISSIONS_REQUEST_RECORD_AUDIO
                            )
                        }
                    }
                }
            }
        }

        vistachat?.loadData(html, "text/html", null)

    }

    override fun onBackPressed() {
        if(vistachat?.canGoBack()!!) {
            vistachat?.goBack()
        }else{
            super.onBackPressed()
        }
    }

    fun askForPermission(origin: String, permission: String, requestCode: Int) {
        Log.d("WebView", "inside askForPermission for" + origin + "with" + permission)
        if (ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this@SitesBot,
                    permission
                )
            ) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {

                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this@SitesBot, arrayOf(permission),
                    requestCode
                )
            }
        } else {
            myRequest!!.grant(myRequest!!.resources)
        }
    }

    override fun onReadyForSpeech(params: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onBeginningOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onRmsChanged(rmsdB: Float) {
        TODO("Not yet implemented")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onError(error: Int) {
        TODO("Not yet implemented")
    }

    override fun onResults(results: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        TODO("Not yet implemented")
    }

}