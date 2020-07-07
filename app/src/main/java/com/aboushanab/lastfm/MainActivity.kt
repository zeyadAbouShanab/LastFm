package com.aboushanab.lastfm

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private var artistData: TextView? = null
    lateinit var retrofit:Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<View>(R.id.button).setOnClickListener {
            if (artistNameBox.text.toString()== ""){
                Toast.makeText(this,"Please enter a name",Toast.LENGTH_LONG)
            }
            else{
                getCurrentData()
            }
             }
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val httpClient = OkHttpClient.Builder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.interceptors().add(interceptor)

        retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()

    }
     fun getCurrentData() {
        val service = retrofit.create(Services::class.java)
         var nameView = findViewById(R.id.artistNameBox) as EditText
         var name = nameView.text.toString()
        val call = service.getCurrentArtistData(name, AppId)
        call.enqueue(object : Callback<ArtistResponse> {
            override fun onResponse(call: Call<ArtistResponse>, response: Response<ArtistResponse>) {
                if (response.code() == 200) {
                    var artists = response.body()?.results?.result?.artist?.toMutableList()!!
                    mainRecyclerView.layoutManager = GridLayoutManager(this@MainActivity, 2)
                    mainRecyclerView.adapter = ArtistsAdapter(artists)
                    val inputManager : InputMethodManager = getSystemService ( Context . INPUT_METHOD_SERVICE ) as  InputMethodManager
                    inputManager.hideSoftInputFromWindow (currentFocus?.windowToken, InputMethodManager . SHOW_FORCED )



                }
                else {
                    Log.e("Failure",response.code().toString())
                    Log.e("Failure",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<ArtistResponse>, t: Throwable) {
                Log.e("Failure",t.message.toString())
            }
        })
    }
    companion object {
        var BaseUrl = "http://ws.audioscrobbler.com/"
        var AppId = "7127a4fa7a04171653177fe3fddbc40f"
    }
}