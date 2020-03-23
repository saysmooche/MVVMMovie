package com.bb.mvvmmovie.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bb.mvvmmovie.model.Result;
import com.bb.mvvmmovie.retrofit.RetroFitInstance;
import com.bb.mvvmmovie.model.User;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {

    MutableLiveData<ArrayList<User>> userLiveData;
    MutableLiveData<ArrayList<Result>> resultLiveData;
    ArrayList<User> userArrayList;
    ArrayList<Result>resultArrayList;
    RetroFitInstance retroFitInstance = new RetroFitInstance();

    public MainViewModel() {
        resultLiveData = new MutableLiveData<>();

        init();
    }

    public MutableLiveData<ArrayList<Result>> getResultMutableLiveData() {
        return resultLiveData;
    }

    public void init(){
        populateList();
        resultLiveData.setValue(resultArrayList);
    }

    public void populateList(){

        Result result = new Result();
        result.setTitle("Movie");
        result.setOverview("Movie Description");

        resultArrayList = new ArrayList<>();
        resultArrayList.add(result);


//        User user = new User();
//        user.setTitle("Movie");
//        user.setDescription("Movie Description");
//
//        userArrayList = new ArrayList<>();
//        userArrayList.add(user);
//        userArrayList.add(user);
//        userArrayList.add(user);
//        userArrayList.add(user);
//        userArrayList.add(user);
//        userArrayList.add(user);
    }
}
