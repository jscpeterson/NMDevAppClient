package space.straylense.myapplication;

import retrofit2.Retrofit;

public class Application extends android.app.Application {

    private Application instance;
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
                .build();
        service = retrofit.create(Service.class);
    }

    public Application getInstance() {
        return instance;
    }

    public Service getService() {
        return service;
    }

}
