package com.example.examen2uf1.databases

import android.content.Context
import com.example.examen2uf1.model.Moble

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Moble::class],
    version = 1,
    exportSchema = true
)

abstract class InventariDatabase : RoomDatabase(){
    abstract fun inventariDAO(): InventariDAO

    companion object {
        @Volatile
        private var INSTANCE: InventariDatabase? = null

        fun getDatabase(context: Context): InventariDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): InventariDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                InventariDatabase::class.java,
                "inventari_database"
            ).createFromAsset("databases/inventari.db")
                //.addMigrations(MIGRATION_1_2)
                .build()
        }
    }
}