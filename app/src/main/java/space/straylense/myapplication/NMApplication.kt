package space.straylense.myapplication

import android.app.Application

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NMApplication : Application() {

    var instance: NMApplication? = null
        private set
    var service: Service? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        setupService()
    }

    private fun setupService() {
        val retrofit = Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(Service::class.java)
    }

}
