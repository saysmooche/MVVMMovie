package com.bb.mvvmmovie.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bb.mvvmmovie.viewmodel.MainViewModel;
import com.bb.mvvmmovie.R;
import com.bb.mvvmmovie.adapter.RecyclerViewAdapter;
import com.bb.mvvmmovie.model.Result;
import com.bb.mvvmmovie.retrofit.RetroFitInstance;
import com.bb.mvvmmovie.model.RetroMovie;
import com.bb.mvvmmovie.adapter.CustomAdapter;
import com.bb.mvvmmovie.retrofit.GetDataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements LifecycleOwner {

    MainActivity context;
    MainViewModel viewModel;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    private CustomAdapter adapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        recyclerView = findViewById(R.id.recycle);
        viewModel = ViewModelProviders.of(context).get(MainViewModel.class);
        viewModel.getResultMutableLiveData().observe(context, resultListUpdateObserver);

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        GetDataService service = RetroFitInstance.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroMovie>> call = service.getAllMovies();
        call.enqueue(new Callback<List<RetroMovie>>() {
            @Override
            public void onResponse(Call<List<RetroMovie>> call, Response<List<RetroMovie>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroMovie>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<RetroMovie> photoList) {
        recyclerView = findViewById(R.id.recycle);
        adapter = new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    Observer<ArrayList<Result>> resultListUpdateObserver = new Observer<ArrayList<Result>>() {
        @Override
        public void onChanged(ArrayList<Result> resultArrayList) {
            recyclerViewAdapter = new RecyclerViewAdapter(context,resultArrayList);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };
}
