package com.david.pokeapp.ui.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.david.pokeapp.R
import com.david.pokeapp.base.BaseBindingFragment
import com.david.pokeapp.databinding.FragmentPokemonDetailBinding
import com.david.pokeapp.viewmodel.PokemonDetailViewModel
import com.david.pokeapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailFragment : BaseBindingFragment() {

    private val viewModel: PokemonDetailViewModel by viewModel()
    private val searchViewModel: SearchViewModel by sharedViewModel()
    lateinit var binding: FragmentPokemonDetailBinding

    override fun getLayoutId(): Int = R.layout.fragment_pokemon_detail

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.pokemonDetailViewModel = viewModel
        initObservers()
        return binding.root
    }

    override fun initView() {
        searchViewModel.showSearch.value = false
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        setHasOptionsMenu(true)
        val spriteUrl = PokemonDetailFragmentArgs.fromBundle(arguments!!).imageUrl
        val pokemonNumber = PokemonDetailFragmentArgs.fromBundle(arguments!!).pokemonNumber
        val pokemonName = PokemonDetailFragmentArgs.fromBundle(arguments!!).name
        ivPokemonPhoto.transitionName = pokemonNumber.toString()
        tvPokemonName.transitionName = pokemonName
        tvPokemonName.text = pokemonName
        Glide.with(context!!)
            .load(spriteUrl)
            .into(ivPokemonPhoto)
        viewModel.loadPokemon(pokemonNumber)
    }

    override fun destroyView() {
        searchViewModel.showSearch.removeObservers(this)
    }
}