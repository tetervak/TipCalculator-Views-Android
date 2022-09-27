package ca.tetervak.tipcalculator.ui.calculator

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import ca.tetervak.tipcalculator.R
import ca.tetervak.tipcalculator.databinding.FragmentCalculatorBinding
import ca.tetervak.tipcalculator.model.ServiceQuality

class CalculatorFragment : Fragment(), MenuProvider {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)

        // setup fragment menu
        requireActivity().addMenuProvider(
            this, viewLifecycleOwner, Lifecycle.State.RESUMED
        )

        // setup the spinner with the adapter
        val spinnerAdapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.quality_input_items, R.layout.quality_input_item
        )
        spinnerAdapter.setDropDownViewResource(R.layout.quality_input_item)
        with(binding.serviceQualitySpinner) {
            adapter = spinnerAdapter
            setSelection(1) // select Good (18%)
        }

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

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
                val roundUpTip = binding.roundUpTipSwitch.isChecked
                viewModel.calculate(costOfService, qualityOfService, roundUpTip)
            } catch (e: NumberFormatException) {
                binding.costOfServiceInput.error = getString(R.string.invalid_input)
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_calculator, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_history -> {
                findNavController().navigate(R.id.action_global_historyFragment)
                true
            }
            else -> false
        }
    }
}