package com.example.food_api_room.DAO.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.food_api_room.DAO.Meal

@Dao
interface MealDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(meal: Meal)
    // 2 функции в 1 перезапись

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM mealInformation")
    fun getAllMeals(): LiveData<List<Meal>>
}