package com.example.food_api_room.fragmentUI



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.food_api_room.DAO.Meal
import com.example.food_api_room.databinding.FragmentHomeBinding
import com.example.food_api_room.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    //ViewModel_not_work
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            //ViewModel_not_work
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }
    //ViewModel_not_work
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeMvvm.getRandomMeal()
        observerRandomMeal()
    }
    //ViewModel_not_work
    private fun observerRandomMeal(){
        homeMvvm.observerRandomMealLivedata().observe(viewLifecycleOwner, object : Observer<Meal>{
            override fun onChanged(t: Meal?) {
                Glide.with(this@HomeFragment)
                    .load(t!!.strMealThumb)
                    .into(binding.randomFood)
            }
        })
    }

}