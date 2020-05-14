package com.hellodiffa.coroutinesxroom.ui.main

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hellodiffa.coroutinesxroom.R
import com.hellodiffa.coroutinesxroom.data.model.Player
import com.hellodiffa.coroutinesxroom.databinding.PlayerRowBinding
import java.util.*

/*
* created by Diffa
*/

class MainAdapter : RecyclerView.Adapter<MainAdapter.Holder>() {

    private val list = arrayListOf<Player>()
    private lateinit var activity: Activity

    internal fun setActivity(activity: Activity) {
        this.activity = activity
    }

    internal fun setPlayerList(list: List<Player>) {
        if (this.list.isNotEmpty()) {
            this.list.clear()
        }
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<PlayerRowBinding>(inflater, R.layout.player_row, parent, false)

        return Holder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position])
    }

    inner class Holder(var binding: PlayerRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Player) = binding.apply {
            player = item
            image = item.imageUrl
            name = String.format(Locale.getDefault(), "%s %s", item.firstName, item.lastName)
            val icon = if (item.isFavorite) {
                R.drawable.ic_star
            } else {
                R.drawable.ic_star_border
            }
            imageViewFavorite.setImageResource(icon)
            handler = activity as MainActivity?

        }

    }

}
