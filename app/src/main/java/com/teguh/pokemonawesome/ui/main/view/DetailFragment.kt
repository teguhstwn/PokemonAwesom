package com.teguh.pokemonawesome.ui.main.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.teguh.pokemonawesome.R
import com.teguh.pokemonawesome.data.model.PokemonDetail
import com.teguh.pokemonawesome.data.model.Stat
import com.teguh.pokemonawesome.databinding.FragmentDetailBinding
import com.teguh.pokemonawesome.ui.main.viewmodel.HomeViewModel
import com.teguh.pokemonawesome.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val args: DetailFragmentArgs by navArgs()
    private val homeViewModel : HomeViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.fetchDetailPokemon(args.id)

        setupObserver()

        _binding?.ivBack?.setOnClickListener {
            Toast.makeText(activity, "click back", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupObserver(){
        homeViewModel.details.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    _binding?.loading?.visibility = View.GONE
                    it.data?.pokemon?.let { it1 -> populateView(it1) }
                    _binding?.constraint?.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    _binding?.loading?.visibility = View.VISIBLE
                    _binding?.constraint?.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    _binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun calStatNumber(value: Int): Double {
        val hasil = (value.toDouble() / 300) * 100
        return hasil
    }

    private fun setValueStats(stats: List<Stat>){
        _binding?.apply {
            for (i in stats.indices){
                if(stats[i].name.lowercase(Locale.getDefault()) == "hp"){
                    pokemonStats.tvHpNumber.text = stats[i].base_stat.toString()
                    pokemonStats.indicatorHp.progress = calStatNumber(stats[i].base_stat).toInt()
                }
                if (stats[i].name.lowercase(Locale.getDefault()) == "attack") {
                    pokemonStats.tvSpAttackNumber.text = stats[i].base_stat.toString()
                    pokemonStats.indicatorAttack.progress =
                        calStatNumber(stats[i].base_stat).toInt()
                }
                if (stats[i].name.lowercase(Locale.getDefault()) == "defense") {
                    pokemonStats.tvDefenseNumber.text = stats[i].base_stat.toString()
                    pokemonStats.indicatorDefense.progress =
                        calStatNumber(stats[i].base_stat).toInt()
                }
                if (stats[i].name.lowercase(Locale.getDefault()) == "special-attack") {
                    pokemonStats.tvSpAttackNumber.text = stats[i].base_stat.toString()
                    pokemonStats.indicatorSpAttack.progress =
                        calStatNumber(stats[i].base_stat).toInt()
                }
                if (stats[i].name.lowercase(Locale.getDefault()) == "special-defense") {
                    pokemonStats.tvSpDefenseNumber.text = stats[i].base_stat.toString()
                    pokemonStats.indicatorSpDefense.progress =
                        calStatNumber(stats[i].base_stat).toInt()
                }
                if (stats[i].name.lowercase(Locale.getDefault()) == "speed") {
                    pokemonStats.tvSpeedNumber.text = stats[i].base_stat.toString()
                    pokemonStats.indicatorSpeed.progress = calStatNumber(stats[i].base_stat).toInt()
                }
            }
        }
    }

    private fun setColorStats(color: String){
        _binding?.apply {
            pokemonStats.indicatorHp.setIndicatorColor(Color.parseColor(color))
            pokemonStats.indicatorAttack.setIndicatorColor(Color.parseColor(color))
            pokemonStats.indicatorDefense.setIndicatorColor(Color.parseColor(color))
            pokemonStats.indicatorSpAttack.setIndicatorColor(Color.parseColor(color))
            pokemonStats.indicatorSpDefense.setIndicatorColor(Color.parseColor(color))
            pokemonStats.indicatorSpeed.setIndicatorColor(Color.parseColor(color))
        }
    }

    private fun setColorType(type: String): String{
        var rtn: String? = null

        if(type == "normal"){
            rtn = resources.getString(R.color.type_normal)
        }
        if(type == "fire"){
            rtn = resources.getString(R.color.type_fire)
        }
        if(type == "water"){
            rtn = resources.getString(R.color.type_water)
        }
        if(type == "electric"){
            rtn = resources.getString(R.color.type_electric)
        }
        if(type == "grass"){
            rtn = resources.getString(R.color.type_grass)
        }
        if(type == "ice"){
            rtn = resources.getString(R.color.type_ice)
        }
        if(type == "fighting"){
            rtn = resources.getString(R.color.type_fighting)
        }
        if(type == "poison"){
            rtn = resources.getString(R.color.type_poison)
        }
        if(type == "ground"){
            rtn = resources.getString(R.color.type_ground)
        }
        if(type == "flying"){
            rtn = resources.getString(R.color.type_flying)
        }
        if(type == "psychic"){
            rtn = resources.getString(R.color.type_psychic)
        }
        if(type == "bug"){
            rtn = resources.getString(R.color.type_bug)
        }
        if(type == "rock"){
            rtn = resources.getString(R.color.type_rock)
        }
        if(type == "ghost"){
            rtn = resources.getString(R.color.type_ghost)
        }
        if(type == "dragon"){
            rtn = resources.getString(R.color.type_dragon)
        }
        if(type == "dark"){
            rtn = resources.getString(R.color.type_dark)
        }
        if(type == "steel"){
            rtn = resources.getString(R.color.type_steel)
        }
        if(type == "fairy"){
            rtn = resources.getString(R.color.type_fairy)
        }

        return rtn!!
    }

    private fun populateView(data: PokemonDetail) {
        _binding?.apply {
            constraint.setBackgroundColor(Color.parseColor(
                setColorType(
                    data.types[0].name
                )
            ))
            activity?.window?.statusBarColor = Color.parseColor(
                setColorType(
                    data.types[0].name
                )
            )

            tvPokemonName.text = data.name
            tvPokemonNumber.text = "#" + data.id.toString()

            Glide.with(ivPokemonImage.context)
                .load(data.imageUrl)
                .into(ivPokemonImage)

            if(data.types.size > 1) {
                pokemonInfo.typeColor1.setBackgroundColor(
                    Color.parseColor(
                        setColorType(
                            data.types[0].name
                        )
                    )
                )
                pokemonInfo.typeColor2.setBackgroundColor(
                    Color.parseColor(
                        setColorType(
                            data.types[1].name
                        )
                    )
                )
                pokemonInfo.tvTypeText.text = data.types[0].name + " / " + data.types[1].name
            } else {
                pokemonInfo.typeColor1.setBackgroundColor(
                    Color.parseColor(
                        setColorType(
                            data.types[0].name
                        )
                    )
                )
                pokemonInfo.typeColor2.visibility = View.GONE
                pokemonInfo.tvTypeText.text = data.types[0].name
            }

            val strHeight = (data.height.toDouble() / 10)
            pokemonInfo.tvHeight.text = strHeight.toString() + " m"

            val strWeight = (data.weight.toDouble() / 10).toString()
            pokemonInfo.tvWeight.text = strWeight + " kg"

            val listSkill: MutableList<String> = mutableListOf<String>()

            for (i in data.abilities.indices){
                listSkill.add(data.abilities[i].name)
            }

            val separator = ", "
            val stringSkill = listSkill.joinToString(separator)
            pokemonAbilty.tvAbiltyName.text = stringSkill

            setColorStats(setColorType(data.types[0].name))

            setValueStats(data.stats)

        }
    }
}