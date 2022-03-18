package com.sahrulhidayat.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sahrulhidayat.core.databinding.ItemListBinding
import com.sahrulhidayat.core.domain.model.GameModel
import com.sahrulhidayat.core.utils.DiffCallback
import com.sahrulhidayat.core.utils.loadImage

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private val gameList = ArrayList<GameModel>()
    var onClickItem: ((GameModel) -> Unit)? = null

    fun setData(newGameList: List<GameModel>?) {
        if (newGameList == null) return

        val diffCallback = DiffCallback(this.gameList, newGameList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        gameList.clear()
        gameList.addAll(newGameList)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.bind(gameList[position])
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    inner class GameViewHolder(private val binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(game: GameModel) {
            val context = itemView.context
            with(binding) {
                txtName.text = game.name
                context.loadImage(game.background, imgPoster)
                txtRating.text = game.rating.toString()
                txtGenres.text = game.genres
            }
        }
    }
}