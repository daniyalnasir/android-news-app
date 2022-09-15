package com.example.newsapp.vmCallbacks


sealed class NewsCallback {
    object PopulateAdapter : NewsCallback()

    data class ShowProgressBar(var isVisible: Boolean) : NewsCallback()

    data class ShowToastWithResource(val messageResource: Int) : NewsCallback()

    data class ShowToastWithString(val message: String) : NewsCallback()

    companion object {
        fun showProgressBar(isVisible: Boolean): NewsCallback =
            ShowProgressBar(isVisible)


        fun showToastWithResource(messageResource: Int): NewsCallback =
            ShowToastWithResource(messageResource)

        fun showToastWithString(message: String): NewsCallback =
            ShowToastWithString(message)
    }
}