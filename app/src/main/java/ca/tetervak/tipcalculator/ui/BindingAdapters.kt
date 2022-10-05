package ca.tetervak.tipcalculator.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import ca.tetervak.tipcalculator.R
import ca.tetervak.tipcalculator.model.ServiceQuality
import java.util.*

@BindingAdapter("currencyValue")
fun setCurrencyValue(textView: TextView, value: Double?) {
    if (value is Double) {
        textView.text = formatCurrency(value)
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
fun setDateAndTimeValues(textView: TextView, date: Date?) {
    if (date is Date)
        textView.text = formatDateAndTime(date)
}

@BindingAdapter("serviceQuality")
fun setServiceQuality(textView: TextView, serviceQuality: ServiceQuality?) {
    if (serviceQuality is ServiceQuality) {
        val qualityInputItems = textView.resources.getStringArray(R.array.quality_input_items)
        textView.text = qualityInputItems[serviceQuality.ordinal]
    }
}

@BindingAdapter("booleanYesNo")
fun setBooleanYesNo(textView: TextView, value: Boolean?) {
    if (value is Boolean) {
        with(textView.resources) {
            textView.text = if (value) getString(R.string.yes) else getString(R.string.no)
        }
    }
}