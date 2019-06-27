package space.straylense.myapplication;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NMApplication extends Application {

    private NMApplication instance = null;
    private Service service;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        setupService();
    }

    void setupService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Service.class);
    }

    public NMApplication getInstance() {
        return instance;
    }

    public Service getService() {
        return service;
    }

}
