package com.example.neven.moviezilla.presenters;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.neven.moviezilla.activities.MainActivity;
import com.example.neven.moviezilla.models.Movies;
import com.example.neven.moviezilla.network.RestAPI;
import com.example.neven.moviezilla.views.ShowData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

/**
 * Created by Neven on 17.1.2017..
 */
public class DownloadPresenterImpl implements DownloadPresenter, Callback<List<Movies>> {

    private ShowData view;
    private Context context;
    private ProgressBar progressBar;

    public DownloadPresenterImpl(ShowData view, Context context, ProgressBar progressBar) {
        this.view = view;
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    public void downloadData() {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.androidhive.info/json/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RestAPI restAPI = retrofit.create(RestAPI.class);
        Call<List<Movies>> call = restAPI.getMovies();

        call.enqueue(this);


    }


    @Override
    public void onResponse(Call<List<Movies>> call, Response<List<Movies>> response) {

        if (response.isSuccessful()) {

            progressBar.setVisibility(View.INVISIBLE);

            List<Movies> listMovies = response.body();
            view.showData(listMovies);


        } else {

            System.out.println(response.errorBody());
            Toast.makeText(context, "we will be back soon!", Toast.LENGTH_SHORT).show();
            MainActivity mainActivity = (MainActivity) context;
            mainActivity.finishAffinity();


        }

    }

    @Override
    public void onFailure(Call<List<Movies>> call, Throwable t) {

        t.printStackTrace();
        Toast.makeText(context, "check your internet connection", Toast.LENGTH_SHORT).show();


    }
}
