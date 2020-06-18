package soy.gabimoreno.imagegenerator.framework

import android.os.Handler
import android.os.Looper
import android.widget.EditText

class SearchManager(
    private val et: EditText,
    private val listener: Listener
) {

    companion object {
        const val CHARS_SEARCH_LENGTH = 2
        const val DELAY_IN_MILLIS = 10L
    }

    interface Listener {
        fun onComplete(input: String)
        fun onTextBlanked() {}
    }

    private var searchHandler: Handler = Handler(Looper.getMainLooper())

    init {
        et.clearFocus()
        et.setOnTextChangedListener { input ->
            search(input.toString())
        }
    }

    private fun search(input: String) {
        searchHandler.removeCallbacks(searchRunnable)
        val sTrimmed = input.trim()
        if (input.isEmpty()) {
            listener.onTextBlanked()
        } else if (sTrimmed.length >= CHARS_SEARCH_LENGTH) {
            searchHandler.postDelayed(searchRunnable, DELAY_IN_MILLIS)
        }
    }

    private var searchRunnable = Runnable {
        val input = et.text.toString()
        listener.onComplete(input)
    }
}
