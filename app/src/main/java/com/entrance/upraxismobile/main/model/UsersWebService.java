package com.entrance.upraxismobile.main.model;

import com.entrance.upraxismobile.main.model.Person;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface UsersWebService {
    @GET("persons")
    Observable<List<Person>> getUsers();
}
