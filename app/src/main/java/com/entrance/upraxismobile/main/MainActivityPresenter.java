package com.entrance.upraxismobile.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.entrance.upraxismobile.ApiClient;
import com.entrance.upraxismobile.main.model.Person;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private ApiClient apiClient = ApiClient.getInstance();
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<Person>> liveData = new MutableLiveData<>();

    public MainActivityPresenter(MainActivityContract.View view) {
        this.view = view;
    }

    @Override
    public void init() {
        disposable.add(apiClient.getUserApi().getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        liveData.postValue(response);
                    }
                }, throwable -> {
                    view.onGetPersonsError();
                }));
    }

    @Override
    public void destroy() {
        disposable.dispose();
    }

    @Override
    public LiveData<List<Person>> loadUsers() {
        return liveData;
    }
}
