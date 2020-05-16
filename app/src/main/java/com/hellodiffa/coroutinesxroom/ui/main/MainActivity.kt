package com.hellodiffa.coroutinesxroom.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.hellodiffa.coroutinesxroom.R
import com.hellodiffa.coroutinesxroom.common.Result
import com.hellodiffa.coroutinesxroom.data.model.Player
import com.hellodiffa.coroutinesxroom.databinding.ActivityMainBinding
import com.hellodiffa.coroutinesxroom.di.injectViewModel
import com.hellodiffa.coroutinesxroom.ui.detail.DetailFragment
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/*
* created by Diffa
*/

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter

    @Inject
    lateinit var detailFragment: DetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        adapter = MainAdapter()
        adapter.setActivity(this)
        viewModel = injectViewModel(viewModelFactory)

        binding.recyclerView.layoutManager =
            LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL,
                false
            )

        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, RecyclerView.VERTICAL))

        binding.adapter = adapter

        observeUi()
    }

    private fun observeUi() {
        viewModel.player.observe(this, Observer { result ->
            when (result.status) {

                Result.Status.SUCCESS -> {
                    if (result.data != null) {
                        adapter.setPlayerList(result.data)
                    }
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }

                Result.Status.ERROR -> {
                    result.message?.let {
                        Snackbar.make(
                            binding.recyclerView,
                            it,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.progressbar.visibility = View.GONE
                }

                Result.Status.LOADING -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
            }
        })
    }

    fun onItemClicked(player: Player) {
        val args = Bundle().apply {
            putString(DetailFragment.ID_PLAYER, player.id)
        }
        detailFragment.arguments = args
        detailFragment.show(supportFragmentManager, "DetailFragment")
    }

}
