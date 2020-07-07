package com.aboushanab.lastfm

import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_artist_layout.view.*
import java.text.DecimalFormat


class ArtistsAdapter(public val artists: MutableList<Artist>) :
    RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ArtistsAdapter.ViewHolder(parent.inflate(R.layout.single_artist_layout))
    }

    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artists[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private lateinit var artist: Artist
        init {
            itemView.setOnClickListener(this)
        }
        fun bind(artist: Artist) {
            this.artist = artist
            val context = itemView.context
            var pic = artist.image?.get(2)?.get("#text")!!
            var url = Uri.parse(pic)
            Picasso
                .with(context) // give it the context
                .load(url) // load the image
                .into(itemView.artistImage) // select the ImageView to load it into
            itemView.artistNameText.text = artist.name
            val formatter = DecimalFormat("#,###,###")
            var listeners = formatter.format(artist.listeners?.toInt())
            itemView.listenersText.text = listeners+" Listeners"
        }
        override fun onClick(view: View) {
            val context = view.context
            var intent = Intent(context, ArtistActivity::class.java)
           // intent.putExtra("ArtistClicked",artist)
            intent.putExtra("ArtistName", artist.name)
            intent.putExtra("ArtistListeners", artist.listeners)
            intent.putExtra("ArtistWeb", artist.url)
            intent.putExtra("ArtistPic", artist.image?.get(2)?.get("#text")!!)

            context.startActivity(intent)

        }
    }



        }
