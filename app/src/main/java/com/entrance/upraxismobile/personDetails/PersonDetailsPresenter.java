package com.entrance.upraxismobile.personDetails;

import android.text.TextUtils;
import java.util.Calendar;

public class PersonDetailsPresenter implements PersonDetailsContract.Presenter {
    private PersonDetailsContract.View view;

    public PersonDetailsPresenter(PersonDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public String getAge(String birthday) {
        if (TextUtils.isEmpty(birthday)) {
            view.onGetAgeError();
        }

        String[] arrBirthday = birthday.split("-");
        int month = Integer.parseInt(arrBirthday[0]);
        int day = Integer.parseInt(arrBirthday[1]);
        int year = Integer.parseInt(arrBirthday[2]);

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        return String.valueOf(age);
    }
}
