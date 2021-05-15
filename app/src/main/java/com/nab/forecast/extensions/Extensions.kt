package com.nab.forecast.extensions

import android.app.Activity
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import java.util.*

fun View.setOnDebounceClickListener(debounceTime: Long = 600L, action: () -> Unit) {
    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) {
                return
            }

            action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun Activity.hideKeyboard(view: View){
    val imm: InputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Calendar.getTimeAtBeginningOfDay() : Long{
    set(Calendar.HOUR,0)
    set(Calendar.MINUTE,0)
    set(Calendar.SECOND,0)
    set(Calendar.MILLISECOND,0)
    return timeInMillis
}
