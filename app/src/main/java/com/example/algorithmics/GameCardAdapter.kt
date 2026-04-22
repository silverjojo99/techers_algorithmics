package com.example.algorithmics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for game/project cards (item_game_card.xml).
 * Used in: TrendsActivity and ProjectsActivity.
 *
 * NOTE: The XML uses a GridLayout with `<include>` tags — those are static
 * previews. At runtime, we replace the GridLayout with a RecyclerView set
 * to a GridLayoutManager(2) for proper data-driven display.
 */
class GameCardAdapter(
    private var items: List<GameCard>,
    private val onItemClick: (GameCard) -> Unit
) : RecyclerView.Adapter<GameCardAdapter.GameViewHolder>() {

    inner class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivThumb: ImageView   = itemView.findViewById(R.id.iv_game_thumb)
        val tvLabel: TextView    = itemView.findViewById(R.id.tv_game_label)
        // The star rating row uses a TextView next to a star icon inside the card
        val tvRating: TextView   = itemView.findViewById(R.id.tv_rating) // see item_game_card.xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_card, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val card = items[position]

        holder.ivThumb.setImageResource(card.thumbnailRes)
        holder.tvLabel.text  = card.label
        holder.tvRating.text = card.rating.toString()   // "4.5"

        holder.itemView.setOnClickListener { onItemClick(card) }
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<GameCard>) {
        items = newItems
        notifyDataSetChanged()
    }
}
