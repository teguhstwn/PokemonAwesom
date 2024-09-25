package com.teguh.pokemonawesome.ui.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.teguh.pokemonawesome.R
import com.teguh.pokemonawesome.data.model.Pokemon
import com.teguh.pokemonawesome.databinding.FragmentHomeBinding
import com.teguh.pokemonawesome.ui.main.adapter.HomeAdapter
import com.teguh.pokemonawesome.ui.main.viewmodel.HomeViewModel
import com.teguh.pokemonawesome.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), OnClickListener {
    private var _binding: FragmentHomeBinding? = null
    private val homeViewModel : HomeViewModel by viewModel()
    private lateinit var adapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.window?.statusBarColor = resources.getColor(R.color.black)
        setupUI()
        setupObserver()
        onClick(view)
    }

    private fun handleUsers(users: List<Pokemon>){
        _binding?.contentMain?.rvPokemon?.adapter?.let { a ->
            if(a is HomeAdapter){
                a.renderList(users)
            }
        }
    }

    private fun setupUI() {
        _binding?.contentMain?.rvPokemon?.layoutManager = LinearLayoutManager(activity)
        adapter = HomeAdapter(arrayListOf())
        _binding?.contentMain?.rvPokemon?.addItemDecoration(
            DividerItemDecoration(
                _binding?.contentMain?.rvPokemon?.context,
                (_binding?.contentMain?.rvPokemon?.layoutManager  as LinearLayoutManager).orientation
            )
        )
        _binding?.contentMain?.rvPokemon?.adapter = adapter
    }

    private fun setupObserver() {
        homeViewModel.pokemons.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    _binding?.loading?.visibility = View.GONE
                    it.data?.pokemons?.let { it1 -> handleUsers(it1) }
                    _binding?.contentMain?.rvPokemon?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    _binding?.loading?.visibility = View.VISIBLE
                    _binding?.contentMain?.rvPokemon?.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    _binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onClick(v: View?) {
        adapter.onItemClick = { pokemon: Pokemon ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFragmentDetail(pokemon.id))
        }
    }

}