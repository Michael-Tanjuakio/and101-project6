package com.example.project6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.project6.databinding.ActivityMainBinding
import okhttp3.Headers

class boredObject(boredTitle_: String, boredInfo1_: String, boredInfo2_: String) {

    val boredTitle = boredTitle_
    val boredInfo1 = boredInfo1_
    val boredInfo2 = boredInfo2_

}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var boredList : MutableList<boredObject>
    private lateinit var rvBored : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater) // activity_main.xml -> ActivityMainBinding
        val view = binding.root // layout of activity is stored in a special property called root
        setContentView(view)

        boredList = mutableListOf()
        rvBored = findViewById(R.id.bored_list)

        getBoredListURL()

    }

    private fun getBoredListURL() {
        val client = AsyncHttpClient()

        client["https://www.boredapi.com/api/activity/", object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("bored", "response successful$json")
                var boredTitle = "I'm bored, well:\n" + json.jsonObject.getString("activity")
                var boredInfo1 = "Type: " + json.jsonObject.getString("type")
                var boredInfo2 = "Accessibility Rate: " + json.jsonObject.getString("accessibility")


                Log.d("bored", "bored content set")
            }
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("bored error", errorResponse)
            }
        }]
    }

    }
}