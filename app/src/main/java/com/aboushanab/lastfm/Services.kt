package com.aboushanab.lastfm

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Services {

    @GET("2.0/")
    fun getCurrentArtistData(@Query("artist") artist: String,
                             @Query("api_key") api_key: String,
    @Query("format") format: String = "json",
    @Query("method") method: String = "artist.search" ): Call<ArtistResponse>
}
