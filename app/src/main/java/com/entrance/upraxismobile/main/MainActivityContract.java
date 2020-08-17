package com.entrance.upraxismobile.main;

import androidx.lifecycle.LiveData;

import com.entrance.upraxismobile.main.model.Person;

import java.util.List;

public interface MainActivityContract {
    interface View {
        void onGetPersonsError();
    }

    interface Presenter {
        void init();
        void destroy();
        LiveData<List<Person>> loadUsers();
    }
}
