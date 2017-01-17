package com.example.neven.moviezilla.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.neven.moviezilla.R;
import com.example.neven.moviezilla.adapters.MoviesAdapter;
import com.example.neven.moviezilla.models.Movies;
import com.example.neven.moviezilla.presenters.DownloadPresenter;
import com.example.neven.moviezilla.presenters.DownloadPresenterImpl;
import com.example.neven.moviezilla.views.ShowData;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ShowData {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        DownloadPresenter presenter = new DownloadPresenterImpl(this, getBaseContext(), progressBar);
        presenter.downloadData();


    }

    @Override
    public void showData(List<Movies> listMovies) {

        MoviesAdapter adapter = new MoviesAdapter(listMovies, getBaseContext());

        recyclerView.setAdapter(adapter);


    }
}
