package space.straylense.myapplication;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("2/events?&sign=true&photo-host=public&group_urlname=New-Mexico-Android-Developers&page=20")
    Call<JsonObject> getEvents();

    // TODO Setup post request to RSVP

}
