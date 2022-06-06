package com.example.food_api_room.DAO.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.food_api_room.DAO.Meal

@Database(entities = [Meal::class], version = 1)//когда изменаем базу нужно изменить и версию
@TypeConverters(MealTypeConvertor::class)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDAO

    companion object{
        @Volatile // изменение в потоке будет видно остольным потокам
        var INSTANCE: MealDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MealDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MealDatabase::class.java,
                    name = "meal.db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as MealDatabase
        }
    }
}