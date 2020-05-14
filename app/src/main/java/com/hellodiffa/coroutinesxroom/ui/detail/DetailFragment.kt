package com.hellodiffa.coroutinesxroom.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.lifecycle.Observer
import com.hellodiffa.coroutinesxroom.R
import com.hellodiffa.coroutinesxroom.base.BaseFragment
import com.hellodiffa.coroutinesxroom.common.Result
import com.hellodiffa.coroutinesxroom.data.model.Player
import com.hellodiffa.coroutinesxroom.databinding.FragmentDetailBinding
import kotlinx.android.synthetic.main.fragment_detail.*

/*
* created by Diffa
*/

class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    private lateinit var idPlayer: String

    private lateinit var player: Player
    private var isFavorite: Boolean = false

    private lateinit var checkBox: CheckBox

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun getLayoutResourceId(): Int = R.layout.fragment_detail

    companion object {
        const val ID_PLAYER = "id_player"

        fun newInstance(player: Player): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle().apply {
                putString(ID_PLAYER, player.id)
            }
            fragment.arguments = args
            return fragment
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsToolbar.setNavigationOnClickListener {
            dismiss()
        }

        detailsToolbar.inflateMenu(R.menu.menu_details)

        val starMenuItem = detailsToolbar.menu.findItem(R.id.action_favorite)
        checkBox = starMenuItem.actionView as CheckBox


        arguments?.getString(ID_PLAYER)?.let { idPlayer = it }
        viewModel.observePlayerByUUID(idPlayer)

        checkBox.setOnClickListener { _ ->
            player.isFavorite = !player.isFavorite
            viewModel.updateFavorite(player)
        }

        observePlayer()
        observeFavorite()

    }


    private fun observePlayer() {
        viewModel.player.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    if (it.data != null) player = it.data
                    displayPlayer()
                    binding.progressbar.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE
                }

                Result.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                    binding.content.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    it.message?.let { it1 -> snackBar(it1) }
                    binding.progressbar.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun observeFavorite() {
        viewModel.favorite.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Result.Status.SUCCESS -> {
                    binding.progressbar.visibility = View.GONE
                    isFavorite = it.data ?: false

                    if (isFavorite) {
                        snackBar("Player has been added to favorite")
                        checkBox.isChecked = isFavorite
                    } else {
                        snackBar("Player has been deleted from favorite")
                        checkBox.isChecked = player.isFavorite
                    }
                }

                Result.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                Result.Status.ERROR -> {
                    it.message?.let { it1 -> snackBar(it1) }
                    binding.progressbar.visibility = View.GONE
                }
            }
        })
    }

    private fun displayPlayer() {
        binding.player = player
        binding.image = player.imageUrl
        checkBox.isChecked = player.isFavorite
    }

}
