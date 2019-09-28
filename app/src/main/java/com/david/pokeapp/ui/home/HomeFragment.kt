package com.david.pokeapp.ui.home

import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.david.domain.model.Pokemon
import com.david.pokeapp.R
import com.david.pokeapp.base.BaseFragment
import com.david.pokeapp.viewmodel.HomeViewModel
import com.david.pokeapp.viewmodel.SearchViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment() {

    private val viewModel : HomeViewModel by sharedViewModel()
    private val searchViewModel : SearchViewModel by sharedViewModel()
    lateinit var adapter : PokemonAdapter

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        searchViewModel.showSearch.value = true
        viewModel.loadPokemonList()
    }

    override fun initObservers() {
        viewModel.pokemons.observe(this, Observer {
            setupAdapter(it)
        })
        viewModel.filteredPokemonList.observe(this, Observer {
            setupAdapter(it)
        })

        viewModel.errorMessage.observe(this, Observer {
            Snackbar.make(view!!, it, Snackbar.LENGTH_INDEFINITE)
                .setAction(resources.getString(R.string.retry)
                ) { viewModel.loadPokemonList() }
                .show()
        })

        viewModel.goToPokemonDetail.observe(this, Observer {
            val entry = it.entries.iterator().next()
            val extras = entry.value
            val direction = entry.key
            Navigation.findNavController(view!!).navigate(direction, extras)
        })
    }

    private fun setupAdapter(data: List<Pokemon>) {
        adapter = PokemonAdapter(data, viewModel)
        rvPokemon.setHasFixedSize(true)
        rvPokemon.layoutManager = GridLayoutManager(context, 3)
        rvPokemon.adapter = adapter
    }

    override fun destroyView() {
        viewModel.pokemons.removeObservers(this)
        viewModel.goToPokemonDetail.removeObservers(this)
        viewModel.filteredPokemonList.removeObservers(this)
    }
}