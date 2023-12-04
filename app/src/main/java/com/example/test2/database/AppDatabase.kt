package com.example.test2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Contacts::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDAO():ContactDAO

    companion object {
        private var DB_INSTANCE:AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase{
            return DB_INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "db_my_app"
                ).build()
                DB_INSTANCE = instance
                instance
            }
        }
    }
}