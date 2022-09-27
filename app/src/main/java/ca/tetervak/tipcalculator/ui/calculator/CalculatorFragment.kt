package ca.tetervak.tipcalculator.ui.calculator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import ca.tetervak.tipcalculator.R
import ca.tetervak.tipcalculator.databinding.FragmentCalculatorBinding
import ca.tetervak.tipcalculator.model.ServiceQuality
import java.text.NumberFormat

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        // setup the spinner with the adapter
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.quality_input_items, R.layout.quality_input_item)
        spinnerAdapter.setDropDownViewResource(R.layout.quality_input_item)
        binding.serviceQualitySpinner.adapter = spinnerAdapter
        binding.serviceQualitySpinner.setSelection(1) // select Good (18%)

        // hide outputs when the inputs are changed

        binding.costOfServiceInput.addTextChangedListener {
            viewModel.setShowOutputs(false)
        }

        binding.serviceQualitySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.setShowOutputs(false)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // nothing to do
                }
            }
        binding.roundUpTipSwitch.setOnCheckedChangeListener { _, _ ->
            viewModel.setShowOutputs(false)
        }

        binding.calculateButton.setOnClickListener {
            val costOfServiceInput = binding.costOfServiceInput.text.toString()
            try {
                val costOfService = costOfServiceInput.toDouble()
                val qualityOfService =
                    when (binding.serviceQualitySpinner.selectedItemPosition) {
                        0 -> ServiceQuality.AMAZING
                        1 -> ServiceQuality.GOOD
                        else -> ServiceQuality.OK
                    }
                val roundUpTip = binding.roundUpTipSwitch.isSelected
                viewModel.calculate(costOfService, qualityOfService, roundUpTip)
                viewModel.setShowOutputs(true)
            } catch (e: NumberFormatException){
                binding.costOfServiceInput.error = getString(R.string.invalid_input)
            }
        }

        viewModel.liveShowOutputs.observe(viewLifecycleOwner){ showOutputs ->
            if(showOutputs) {
                showOutputs()
            } else {
                hideOutputs()
            }
        }

        viewModel.liveTipAmount.observe(viewLifecycleOwner) { tipAmount ->
            binding.tipAmountOutput.text = formatCurrency(tipAmount)
        }

        viewModel.liveBillTotal.observe(viewLifecycleOwner) { billTotal ->
            binding.billTotalOutput.text = formatCurrency(billTotal)
        }

        return binding.root
    }

    private fun hideOutputs() {
        binding.outputTable.visibility = View.INVISIBLE
    }

    private fun showOutputs() {
        binding.outputTable.visibility = View.VISIBLE
    }

    private fun formatCurrency(value: Double) =
        NumberFormat.getCurrencyInstance().format(value)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}