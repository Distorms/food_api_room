package com.example.food_api_room.retrofit

import com.example.food_api_room.DAO.CategoryList
import com.example.food_api_room.DAO.MealsByCategory
import com.example.food_api_room.DAO.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface FoodApi {

    @GET("random.php")
    fun getRandomFood(): Call<MealList>

    //Детализация еды
    @GET("lookup.php?")
    fun getMealDetails(@Query("i") id: String) : Call<MealList>

    @GET("filter.php?")
    fun getPopulatItems(@Query("c") categoryName:String): Call<MealsByCategory>

    @GET("categories.php")
    fun getCategories(): Call<CategoryList>

}