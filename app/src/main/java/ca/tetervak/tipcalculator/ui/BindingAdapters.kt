package ca.tetervak.tipcalculator.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat
import java.util.*

@BindingAdapter("currencyValue")
fun setCurrencyValue(textView: TextView, value: Double?) {
    if (value is Double) {
        textView.text = NumberFormat.getCurrencyInstance().format(value)
    }
}

@BindingAdapter("dateValue")
fun setDateValue(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDate(date)
}

@BindingAdapter("timeValue")
fun setTimeValue(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatTime(date)
}

@BindingAdapter("dateAndTimeValues")
fun bindDateAndTimeValues(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDateAndTime(date)
}