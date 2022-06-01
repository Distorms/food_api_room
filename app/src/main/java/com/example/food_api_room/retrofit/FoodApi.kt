package com.example.food_api_room.retrofit

import com.example.food_api_room.DAO.MealList
import retrofit2.Call
import retrofit2.http.GET


interface FoodApi {

    @GET("random.php")
    fun getRandomFood(): Call<MealList>

}