package com.training_helper.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TrainingSet(context: Context):SQLiteOpenHelper(context, name, null, version) {
    companion object {
        var name: String = "training_set_db"
        var version: Int = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val query: String = "CREATE TABLE if not exists tb_training(id integer PRIMARY KEY AUTOINCREMENT, topic text NOT NULL, target text NOT NULL, restTime Long NOT NULL)"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}