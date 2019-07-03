package space.straylense.myapplication

import com.google.gson.JsonObject

import retrofit2.Call
import retrofit2.http.GET

interface Service {

    @get:GET("2/events?&sign=true&photo-host=public&group_urlname=New-Mexico-Android-Developers&page=20")
    val events: Call<JsonObject>

    // TODO Setup post request to RSVP

}
