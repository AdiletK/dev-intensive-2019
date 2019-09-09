package ru.skillbranch.devintensive.extensions


import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun Activity.isKeyboardOpen(): Boolean {
    val view = findViewById<View>(android.R.id.content)
    val rect = Rect()
    view.getWindowVisibleDisplayFrame(rect)
    return view.height > rect.height()
}

fun Activity.isKeyboardClosed() = !isKeyboardOpen()

fun Activity.convertDpToPx(dp: Float): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    resources.displayMetrics
)

fun Activity.convertPxToDp(px: Float): Float = px / resources.displayMetrics.density