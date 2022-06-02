package com.example.food_api_room.activites

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.food_api_room.DAO.Meal
import com.example.food_api_room.R
import com.example.food_api_room.databinding.ActivityMealBinding
import com.example.food_api_room.fragmentUI.HomeFragment
import com.example.food_api_room.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var yotubeLink: String
    private lateinit var mealMvvm: MealViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealMvvm = ViewModelProviders.of(this)[MealViewModel::class.java]

        getMealInformationFromIntent()
        setInformationInViews()

        loadingCase()

        mealMvvm.getMealDetail(mealId)

        observerMealDetailalsLiveData()

        onYouTubeImageClick()
    }
    private fun onYouTubeImageClick(){
        binding.imgYouTube.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(yotubeLink))
            startActivity(intent)
        }
    }

    private fun observerMealDetailalsLiveData() {
        mealMvvm.observerMealDetailsLiveData().observe(this, object : Observer<Meal>{
            override fun onChanged(meal: Meal?) {
                onResponseCase()

                binding.tvCategory.text = "Категория: ${meal!!.strCategory}"
                binding.tvArea.text = "Область: ${meal.strArea}"
                binding.tvInsructionsSt.text = meal.strInstructions

                yotubeLink = meal.strYoutube

            }
        })
    }

    private fun setInformationInViews() {
        Glide.with(applicationContext)
            .load(mealThumb)
            .into(binding.imgMealDetail)
        binding.collapsingTolBar.title = mealName
        binding.collapsingTolBar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingTolBar.setExpandedTitleColor(resources.getColor(R.color.white))
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!
    }


    private fun loadingCase(){

        binding.progressBar.visibility = View.VISIBLE
        binding.btnAddToFav.visibility = View.INVISIBLE
        binding.tvInsructions.visibility = View.INVISIBLE
        binding.tvCategory.visibility = View.INVISIBLE
        binding.tvArea.visibility = View.INVISIBLE
        binding.imgYouTube.visibility = View.INVISIBLE
    }
    private fun onResponseCase(){

        binding.progressBar.visibility = View.INVISIBLE
        binding.btnAddToFav.visibility = View.VISIBLE
        binding.tvInsructions.visibility = View.VISIBLE
        binding.tvCategory.visibility = View.VISIBLE
        binding.tvArea.visibility = View.VISIBLE
        binding.imgYouTube.visibility = View.VISIBLE
    }

}