package ca.tetervak.tipcalculator.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ca.tetervak.tipcalculator.databinding.HistoryListItemBinding

class HistoryListAdapter(
    private val onItemClick: (itemId: Int) -> Unit,
    private val onItemDelete: (itemId: Int) -> Unit
) : ListAdapter<HistoryItemUiState, HistoryListAdapter.ItemViewHolder>(ITEM_COMPARATOR) {

    inner class ItemViewHolder(
        private val binding: HistoryListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemUiState: HistoryItemUiState) {
            binding.itemUiState = itemUiState
            binding.deleteButton.setOnClickListener {
                onItemDelete(itemUiState.id)
            }
            binding.root.setOnClickListener {
                onItemClick(itemUiState.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HistoryListItemBinding.inflate(layoutInflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val itemUiState = getItem(position)
        holder.bind(itemUiState)
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<HistoryItemUiState>() {
            override fun areItemsTheSame(
                oldItem: HistoryItemUiState,
                newItem: HistoryItemUiState
            ): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: HistoryItemUiState,
                newItem: HistoryItemUiState
            ): Boolean {
                return oldItem.date == newItem.date &&
                        oldItem.tipAmount == newItem.tipAmount &&
                        oldItem.billTotal == newItem.billTotal
            }
        }
    }
}