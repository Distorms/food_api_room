package com.example.food_api_room.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.food_api_room.DAO.*
import com.example.food_api_room.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//ViewModel_not_work
class HomeViewModel(): ViewModel() {
    private var randomMealLiveData = MutableLiveData<Meal>()
    private var popularItemsLiveData = MutableLiveData<List<CategoryMeals>>()
    private var categoryLiveData = MutableLiveData<List<Category>>()
     //проверка
    fun getRandomMeal(){
        RetrofitInstance.api.getRandomFood().enqueue(object: Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() !=null){
                    val randomMeal: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value = randomMeal
                } else {
                    return
                }
            }
            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("HomeFragment", t.message.toString())
            }
        })
    }

    fun getPopularItems(){
        RetrofitInstance.api.getPopulatItems("Seafood")
            .enqueue(object : Callback<MealsByCategory>{

                override fun onResponse(
                    call: Call<MealsByCategory>,
                    response: Response<MealsByCategory>) {
                    if (response.body() != null){
                        popularItemsLiveData.value = response.body()!!.meals
                    }
                }

                override fun onFailure(call: Call<MealsByCategory>, t: Throwable) {
                    Log.d("HomeFragment", t.message.toString())
                }
            })
    }
    //category
    fun getCategories(){
        RetrofitInstance.api.getCategories().enqueue(object : Callback<CategoryList>{

            override fun onResponse(call: Call<CategoryList>, response: Response<CategoryList>) {
                response.body()?.let { CategoryList ->
                    categoryLiveData.postValue(CategoryList.categories)
                }
            }

            override fun onFailure(call: Call<CategoryList>, t: Throwable) {
                Log.e("HomeViewModel", t.message.toString())
            }
        })
    }

    //ViewModel_not_work
    fun observerRandomMealLivedata():LiveData<Meal>{
        return randomMealLiveData
    }

    //fun observer
    fun observeRandomMealListvideodata():LiveData<List<CategoryMeals>>{
        return popularItemsLiveData
    }

    // fun observ live data
    fun observeCatigoresLiveData():LiveData<List<Category>>{
        return categoryLiveData
    }
}