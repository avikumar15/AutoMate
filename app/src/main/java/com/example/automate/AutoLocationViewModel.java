package com.example.automate;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.automate.models.AutoClass;

import java.util.List;

public class AutoLocationViewModel extends ViewModel {

    public MutableLiveData<List<AutoClass>> autoClass;

    public LiveData<List<AutoClass>> getAutoClass() {
        return autoClass;
    }
}
