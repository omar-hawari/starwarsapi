package com.omarhawari.starwarstrivia.domain.models

import android.os.Parcel
import android.os.Parcelable

data class Film(
    val characters: List<String>,
    val created: String,
    val director: String,
    val edited: String,
    val episodeId: Int,
    val openingCrawl: String,
    val planets: List<String>,
    val producer: String,
    val releaseDate: String,
    val species: List<String>,
    val starships: List<String>,
    val title: String,
    val vehicles: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(characters)
        parcel.writeString(created)
        parcel.writeString(director)
        parcel.writeString(edited)
        parcel.writeInt(episodeId)
        parcel.writeString(openingCrawl)
        parcel.writeStringList(planets)
        parcel.writeString(producer)
        parcel.writeString(releaseDate)
        parcel.writeStringList(species)
        parcel.writeStringList(starships)
        parcel.writeString(title)
        parcel.writeStringList(vehicles)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}