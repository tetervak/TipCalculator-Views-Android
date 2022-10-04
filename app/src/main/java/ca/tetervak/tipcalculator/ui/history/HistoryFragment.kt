package ca.tetervak.tipcalculator.ui.history

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import ca.tetervak.tipcalculator.R
import ca.tetervak.tipcalculator.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment(), MenuProvider {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHistoryBinding.inflate(inflater, container, false)

        // setup fragment menu
        requireActivity().addMenuProvider(
            this, viewLifecycleOwner, Lifecycle.State.RESUMED
        )

        val adapter = HistoryListAdapter(
            onItemClick = { itemId: Int -> showDetails(itemId) },
            onItemDelete = { itemId: Int -> viewModel.deleteTipDataById(itemId) }
        )
        binding.recyclerView.adapter = adapter

        viewModel.liveItemUiStateList.observe(viewLifecycleOwner){ uiItemStateList ->
            if (uiItemStateList.isNotEmpty()){
                adapter.submitList(uiItemStateList)
                binding.recyclerView.visibility = View.VISIBLE
                binding.emptyHistoryMessage.visibility = View.GONE
            } else {
                binding.recyclerView.visibility = View.GONE
                binding.emptyHistoryMessage.visibility = View.VISIBLE
            }
        }

        return binding.root
    }

    private fun showDetails(itemId: Int) {
        val action = HistoryFragmentDirections.actionHistoryFragmentToDetailsFragment(itemId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_history, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.action_clear_all_history -> {
                viewModel.clearAllHistory()
                true
            }
            else -> false
        }
    }
}