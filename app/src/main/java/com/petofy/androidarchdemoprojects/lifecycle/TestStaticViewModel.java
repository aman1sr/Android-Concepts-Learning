package com.petofy.androidarchdemoprojects.lifecycle;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TestStaticViewModel extends ViewModel {
    ArrayList<String> list = new ArrayList<>();

    private  static MutableLiveData<ArrayList<String>> data = new MutableLiveData<>();
    private   LiveData<ArrayList<String>> upperData = Transformations.map(data,list -> {
        ArrayList<String> newList = new ArrayList<>();
        for (String item : list) {
            newList.add(item.toUpperCase());
        }
        return newList;
    });

    public  LiveData<ArrayList<String>> getUpperData() {
        return upperData;
    }

    public  void setNewData(String str) {
        list.add(str);
        data.setValue(list);
    }
    public  void setData() {
        list.add("aman");
        list.add("dinesh");
        list.add("rakesh");
        data.setValue(list);
    }

    public void showList() {
        Log.d("TestStaticVM_d", "list: "+data.getValue());
    }


}
