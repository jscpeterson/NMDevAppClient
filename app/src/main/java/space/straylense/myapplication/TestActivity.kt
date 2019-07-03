package space.straylense.myapplication

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

import java.io.IOException

import retrofit2.Call
import retrofit2.Response

class TestActivity : AppCompatActivity() {

    internal lateinit var nmApplication: NMApplication
    internal var jsonObject: JsonObject? = null
    internal lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        nmApplication = application as NMApplication
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        textView = findViewById(R.id.test_view)
        textView.text = nmApplication.instance!!.service!!.events.toString()
        TestTask().execute()
    }

    private inner class TestTask : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg voids: Void): Void? {
            val call = nmApplication.instance!!.service!!.events
            println(call)
            try {
                val response = call.execute()
                println(response)
                if (response.isSuccessful) {
                    println(response.body())
                    jsonObject = response.body()
                    println("we did it")
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }

        override fun onPostExecute(aVoid: Void?) {
            textView.text = jsonObject!!.toString()
            val array = jsonObject!!.getAsJsonArray("results")
            textView.text = array.toString()
            for (event in array) {
                println(event.asJsonObject.get("description").toString())
            }
            super.onPostExecute(aVoid)
        }
    }

}
