package com.example.callinginternetdata_project1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import coil.load
import com.example.callinginternetdata_project1.network.DogViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.dogImages.observe(this, //
            {
                findViewById<ImageView>(R.id.dogImage).load(
                    it.message?.toUri()?.buildUpon()?.scheme("https")?.build()
                    //Sets the ImageView to load pics using the Coil library/dependency.
                    // Translates the key "message" listed from data class into a buildable image.
                )
                findViewById<Button>(R.id.button).setOnClickListener {
                    //At each click, the viewModel handles the call for retrieving the getNewDog object
                    viewModel.getNewDog()

                }
            })
    }
}


