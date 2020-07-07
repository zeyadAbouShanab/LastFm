package com.aboushanab.lastfm

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


class ArtistResponse {
    @SerializedName("results")
    var results: Results? = null
}

class Results{
    @SerializedName("artistmatches")
    var result: ArtistMatches? = null
}

class ArtistMatches{
    @SerializedName("artist")
    var artist : Array<Artist>? = null
}
@Parcelize
class Artist: Parcelable {
    @SerializedName("name")
    var name : String? = null
    @SerializedName("listeners")
    var listeners : String? = null
    @SerializedName("url")
    var url : String? = null
    @SerializedName("image")
    var image : Array<Map<String,String>>?= null

}
