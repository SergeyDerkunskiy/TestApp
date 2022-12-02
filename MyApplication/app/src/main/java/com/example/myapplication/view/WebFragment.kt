package com.example.myapplication.view

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.*
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentWebBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebFragment : ViewBindingFragment<FragmentWebBinding>(FragmentWebBinding::inflate) {

    private var url = ""
    private lateinit var webView: WebView
    private val mainViewModel: MainViewModel by viewModels()
    private val args : WebFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        webView = binding.webView

        url = if(args.counter > 0){
            mainViewModel.getRemoteData().home
        } else {
            mainViewModel.saveLaunchCounter(1)
            mainViewModel.getRemoteData().link
        }

        CookieManager.getInstance().setAcceptCookie(true)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.settings.loadsImagesAutomatically = true

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        webView.loadUrl(url)
        CookieManager.getInstance().setAcceptCookie(true)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onStop() {
        super.onStop()
        CookieManager.getInstance().setAcceptCookie(true)
        CookieManager.getInstance().acceptCookie()
        CookieManager.getInstance().flush()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(webView.canGoBack()){
                    webView.goBack()
                }else{
                    ActivityCompat.finishAffinity(requireActivity())
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
}