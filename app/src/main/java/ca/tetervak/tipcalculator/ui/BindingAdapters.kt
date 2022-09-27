package ca.tetervak.tipcalculator.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.NumberFormat

@BindingAdapter("currencyValue")
fun setCurrencyValue(textView: TextView, value: Double?) {
    if (value is Double) {
        textView.text = NumberFormat.getCurrencyInstance().format(value)
    }
}