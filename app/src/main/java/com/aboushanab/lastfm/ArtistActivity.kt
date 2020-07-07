package com.aboushanab.lastfm

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.android.synthetic.main.single_artist_layout.view.*
import java.text.DecimalFormat

class ArtistActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        var intent = intent
        val name = intent.getStringExtra("ArtistName")
        val pic = intent.getStringExtra("ArtistPic")
        val listenerss = intent.getStringExtra("ArtistListeners")
        val web = intent.getStringExtra("ArtistWeb")
        //val artist = intent.getParcelableExtra<Artist>("ArtistClicked") as Artist
        activityArtistName.text = name
        val formatter = DecimalFormat("#,###,###")
        var listeners = formatter.format(listenerss?.toInt())
        activityArtistViews.text = listeners + " Listeners"
        activityArtistWeb.text = web
        var url = Uri.parse(pic)
        Picasso
            .with(this) // give it the context
            .load(url) // load the image
            .into(activityArtistImage) // select the ImageView to load it into

    }
}