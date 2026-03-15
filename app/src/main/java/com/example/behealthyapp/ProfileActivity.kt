package com.example.behealthyapp  // <--- ton package exact ici

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlin.jvm.java

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile) // R.layout.activity_profile doit exister

        // Ici on précise le type Button <Button>
        val btnCatalogue: Button = findViewById(R.id.btnC)

        btnCatalogue.setOnClickListener {
            // Intent vers CatalogueActivity
            val intent = Intent(this@ProfileActivity, Catalogue::class.java)
            startActivity(intent)
        }
    }
}