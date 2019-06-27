package space.straylense.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class TestActivity extends AppCompatActivity {

    NMApplication nmApplication;
    JsonObject jsonObject;
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        nmApplication = (NMApplication) getApplication();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.test_view);
        textView.setText(nmApplication.getInstance().getService().getEvents().toString());
        new TestTask().execute();
    }

    private class TestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            Call<JsonObject> call = nmApplication.getInstance().getService().getEvents();
            System.out.println(call);
            try {
                Response<JsonObject> response = call.execute();
                System.out.println(response);
                if (response.isSuccessful()) {
                    System.out.println(response.body());
                    jsonObject = response.body();
                    System.out.println("we did it");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            textView.setText(jsonObject.toString());
            JsonArray array = jsonObject.getAsJsonArray("results");
            textView.setText(array.toString());
            for (JsonElement event : array) {
                System.out.println(event.getAsJsonObject().get("description").toString());
            }
            super.onPostExecute(aVoid);
        }
    }

}
