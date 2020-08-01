package com.teckudos.architecturepatterns.application.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.teckudos.architecturepatterns.application.models.AccountProperties
import com.teckudos.architecturepatterns.application.models.AuthToken

@Database(entities = [AuthToken::class, AccountProperties::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAuthTokenDao(): AuthTokenDao

    abstract fun getAccountPropertiesDao(): AccountPropertiesDao

    companion object{
        val DATABASE_NAME: String = "app_db"
    }


}








