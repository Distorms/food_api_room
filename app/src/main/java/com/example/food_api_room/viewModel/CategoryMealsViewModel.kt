package com.example.food_api_room.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_api_room.DAO.*
import com.example.food_api_room.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class CategoryMealsViewModel : ViewModel() {
    val mealsLiveData = MutableLiveData<List<CategoryMeals>>()



    fun getMealsByCategory(categoryName: String){
        RetrofitInstance.api.getMealsByCategory(categoryName).enqueue(object : retrofit2.Callback<MealsByCategory>{
            override fun onResponse(
                call: Call<MealsByCategory>,
                response: Response<MealsByCategory>
            ) {
                response.body()?.let { mealsList ->
                    mealsLiveData.postValue(mealsList.meals) // из MealsByCategory
                }
            }

            override fun onFailure(call: Call<MealsByCategory>, t: Throwable) {
                Log.e("CategoryMealsViewModel", t.message.toString())
            }

        })
    }
    fun observeMealsLiveData(): MutableLiveData<List<CategoryMeals>> {
        return mealsLiveData //point
    }
}


