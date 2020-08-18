package com.entrance.upraxismobile.personDetails;

public interface PersonDetailsContract {
    interface View{
        void onGetAgeError();
    }

    interface Presenter{
        String getAge(String birthday);
    }
}
