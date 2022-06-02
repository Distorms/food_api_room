package com.example.food_api_room.fragmentUI



import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.food_api_room.DAO.Meal
import com.example.food_api_room.activites.MealActivity
import com.example.food_api_room.databinding.FragmentHomeBinding
import com.example.food_api_room.viewModel.HomeViewModel


class HomeFragment : Fragment() {
    //ViewModel_not_work
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeMvvm: HomeViewModel
    //передача данных
    private lateinit var randomsFool:Meal
    //передача данных
    companion object{
        const val MEAL_ID = "com.example.easyFood.fragments.idMeal"
        const val MEAL_NAME = "com.example.easyFood.fragments.nameMeal"
        const val MEAL_THUMB = "com.example.easyFood.fragments.ThumbMeal"

    }

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
        onRandomMealClick()
    }

    private fun onRandomMealClick(){
        binding.randomFoodCard.setOnClickListener{
            val intent = Intent(activity, MealActivity::class.java)
            //передача данных
            intent.putExtra(MEAL_ID, randomsFool.idMeal)
            intent.putExtra(MEAL_NAME, randomsFool.strMeal)
            intent.putExtra(MEAL_THUMB, randomsFool.strMealThumb)
            startActivity(intent)
        }
    }

    //ViewModel_not_work
    private fun observerRandomMeal(){
        homeMvvm.observerRandomMealLivedata().observe(viewLifecycleOwner)
        { meal ->
            Glide.with(this@HomeFragment)
                .load(meal!!.strMealThumb)
                .into(binding.randomFood)
            //передача данных
            this.randomsFool = meal
        }
    }

}