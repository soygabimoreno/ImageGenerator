package soy.gabimoreno.imagegenerator.framework

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun EditText.setOnTextChangedListener(onTextChanged: (CharSequence?) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(charSequence)
        }

        override fun afterTextChanged(editable: Editable?) {}
    })
}
