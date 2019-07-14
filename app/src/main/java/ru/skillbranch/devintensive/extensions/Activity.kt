package ru.skillbranch.devintensive.extensions


import android.app.Activity
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.RelativeLayout


fun Activity.hideKeyboard() {
    val inputMethodManager = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

    var view = this.currentFocus

    if (view == null) {
        view = View(this)
    }
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    var result = false
    val contentView = RelativeLayout(this)
    contentView.viewTreeObserver.addOnGlobalLayoutListener {
        val r = Rect()
        contentView.getWindowVisibleDisplayFrame(r)
        val screenHeight = contentView.rootView.height

        val keypadHeight = screenHeight - r.bottom

        result = keypadHeight > screenHeight * 0.15
    }

    val rootView = this.window.decorView // this = activity
    rootView.getWindowVisibleDisplayFrame(Rect())

    rootView.height
    rootView.width

    rootView.rootView.height

    return result
}