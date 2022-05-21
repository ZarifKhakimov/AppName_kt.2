package com.example.appname_kt

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Last : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last)
        setTags(findViewById(R.id.et_simple), "I would #like to do #something similar to the #Twitter app")
    }

    private fun setTags(pTextView: TextView, pTagString: String) {
        val string = SpannableString(pTagString)
        var start = -1
        var i = 0
        while (i < pTagString.length) {
            if (pTagString[i] == '#') {
                start = i
            } else if (pTagString[i] == ' ' || pTagString[i] == '\n' || i == pTagString.length - 1 && start != -1) {
                if (start != -1) {
                    if (i == pTagString.length - 1) {
                        i++
                    }
                    val tag = pTagString.substring(start, i)
                    string.setSpan(object : ClickableSpan() {
                        override fun onClick(widget: View) {
                            Log.d("Hash", String.format("Clicked %s!", tag))
                        }

                        override fun updateDrawState(ds: TextPaint) {
                            // link color
                            ds.color = Color.parseColor("#0AE81E")
                            ds.isUnderlineText = false
                        }
                    }, start, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                    start = -1
                }
            }
            i++
        }
        pTextView.movementMethod = LinkMovementMethod.getInstance()
        pTextView.text = string
    }
}