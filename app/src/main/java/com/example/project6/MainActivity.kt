package com.example.project6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project6.databinding.ActivityMainBinding
import okhttp3.Headers
import kotlin.random.Random

class amiibo(name_: String, game_series_: String, image_link_: String) {
    val name = name_
    val game_series = game_series_
    val image_link = image_link_
}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var amiiboList : MutableList<amiibo>
    private lateinit var rvAmiibo : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater) // activity_main.xml -> ActivityMainBinding
        val view = binding.root // layout of activity is stored in a special property called root
        setContentView(view)

        amiiboList = mutableListOf()
        rvAmiibo = findViewById(R.id.amiibo_list)

        getAmiiboListURL()
    }

    private fun getAmiiboListURL() {
        val client = AsyncHttpClient()

        client["https://www.amiiboapi.com/api/amiibo", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("amiibo", "response successful$json")
                val amiiboArray = (json.jsonObject.getJSONArray("amiibo"))
                val amiibo1 = amiiboArray[0]
                val amiibo2 = amiiboArray[1]
                val amiibo_len = amiiboArray.length()
                val randomValues = List(40) { Random.nextInt(0, amiiboArray.length() - 1) }
                Log.d("amiibo", "Amiibo List: $amiiboArray")
                Log.d("amiibo", "Amiibo 1: $amiibo1")
                Log.d("amiibo", "Amiibo 2: $amiibo2")
                Log.d("amiibo", "Amiibo Length: $amiibo_len")
                Log.d("amiibo", "Random Index List: $randomValues")


                for (i in 0 until randomValues.size) {
                    val randomIndex = randomValues[i]
                    Log.d("amiibo", "Random Index: $randomIndex")
                    val randomAmiibo = amiiboArray.getJSONObject(randomIndex)
                    val character_name = randomAmiibo.getString("character")
                    val game_series = randomAmiibo.getString("gameSeries")
                    val image = randomAmiibo.getString("image")
                    Log.d("amiibo", "Character name: $character_name")
                    Log.d("amiibo", "Game Series: $game_series")
                    Log.d("amiibo", "Image link: $image")
                    Log.d("amiibo", "----------")
                    amiiboList.add(amiibo(character_name, game_series, image))

                }

                val adapter = AmiiboAdapter(amiiboList)
                rvAmiibo.adapter = adapter
                rvAmiibo.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("amiibo", "amiibo content set")
            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("amiibo error", errorResponse)
            }
        }]
    }
}
