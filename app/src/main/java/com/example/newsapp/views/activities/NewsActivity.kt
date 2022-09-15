package com.example.newsapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.LinearLayout
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.newsapp.R
import com.example.newsapp.customViews.ProgressDialogue
import com.example.newsapp.databinding.ActivityNewsBinding
import com.example.newsapp.helpers.Utils
import com.example.newsapp.viewModels.NewsVM
import com.example.newsapp.views.adapters.NewsAdapter
import com.example.newsapp.vmCallbacks.NewsCallback

class NewsActivity : AppCompatActivity() {
    private val TAG = NewsActivity::class.java.simpleName

    private var doubleBackToExitCheck = false

    private val newsVM by lazy { ViewModelProvider(this)[NewsVM::class.java] }

    private lateinit var binding: ActivityNewsBinding

    private val progressDialogue by lazy {
        ProgressDialogue(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView<ActivityNewsBinding>(this, R.layout.activity_news)
                .apply {
                    this.lifecycleOwner = this@NewsActivity
                }

        setContentView(binding.root)

        init()

        newsVM.getNewsApi()
    }

    private fun init() {
        Utils.applicationReference(this)

        newsVM.getCallback().observe(this, callbackObserver)

        binding.citiesRV.apply {
            adapter = NewsAdapter(newsVM)
        }

        binding.citiesRV.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
    }

    private val callbackObserver = Observer<NewsCallback> { callBack ->
        when (callBack) {
            is NewsCallback.PopulateAdapter -> {
                Utils.hideKeyboard(this, this.currentFocus)
                populateAdapters()
            }
            is NewsCallback.ShowProgressBar -> {
                if (callBack.isVisible) {
                    progressDialogue.show()
                } else {
                    progressDialogue.dismiss()
                }
            }
            is NewsCallback.ShowToastWithString -> {
                Toast.makeText(this, callBack.message, Toast.LENGTH_LONG).show()
            }
            is NewsCallback.ShowToastWithResource -> {
                Toast.makeText(
                    this,
                    getString(callBack.messageResource),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun populateAdapters() {
        binding.citiesRV.adapter?.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (doubleBackToExitCheck) {
            finish()
            return
        }
        doubleBackToExitCheck = true
        Toast.makeText(this, getString(R.string.string_press_again_to_exit), Toast.LENGTH_LONG)
            .show()
        Handler(Looper.getMainLooper()).postDelayed(
            Runnable { doubleBackToExitCheck = false },
            2000
        )
    }
}