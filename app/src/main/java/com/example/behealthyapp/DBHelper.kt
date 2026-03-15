package com.example.behealthyApp //selon votre package
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DBHelper(context: Context) : SQLiteOpenHelper(context, "recettes.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
// Création de la table 'recettes' avec la colonne 'ingredients' en TEXT
        db.execSQL(""" CREATE TABLE recettes (
id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, ingredients TEXT
) """)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
// Suppression de l'ancienne table si elle existe lors d'une mise à jour
        db.execSQL("DROP TABLE IF EXISTS recettes")
        onCreate(db)
    }
}