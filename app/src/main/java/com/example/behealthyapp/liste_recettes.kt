package com.example.behealthyApp
import android.content.ContentValues
import android.os.Bundle
import android.widget.* import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
class ListeRecettesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
// Assurez-vous que le nom du layout correspond à votre fichier XML
        setContentView(R.layout.activity_liste_recettes)
        val listView = findViewById<ListView>(R.id.listViewRecettes)
        val recettesData = lireRecettesDepuisJSON()
// On affiche uniquement les noms dans la ListView
        val nomsRecettes = recettesData.map { it["nom"] }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nomsRecettes)
        listView.adapter = adapter
        listView.setOnItemClickListener { _, _, position, _ ->
            val recetteSelectionnee = recettesData[position]
            val nom = recetteSelectionnee["nom"]
            val ingredients = recetteSelectionnee["ingredients"]
            Toast.makeText(this, "Ajout de : $nom", Toast.LENGTH_SHORT).show()
            val dbHelper = DBHelper(this)
            val db = dbHelper.writableDatabase
            val values = ContentValues().apply {
                put("nom", nom)
                put("ingredients", ingredients) // Remplacement de "note" par "ingredients" }
// On insère dans la table "recettes" (pensez à mettre à jour votre DBHelper)
                db.insert("recettes", null, values)
// db.close()
            }
        }
        private fun lireRecettesDepuisJSON(): ArrayList<Map<String, String>> {
            val liste = ArrayList<Map<String, String>>()
            try {
                val inputStream = assets.open("recettes.json")
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val jsonObject = JSONObject(jsonString)
                val jsonArray = jsonObject.getJSONArray("recettes")
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)
                    val map = mapOf
                    "nom" to item.getString("nom"), "ingredients" to item.getString("ingredients")
                    )
                    liste.add(map)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return liste
        }
    }