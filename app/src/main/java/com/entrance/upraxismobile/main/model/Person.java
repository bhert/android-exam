package com.entrance.upraxismobile.main.model;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.Getter;

@Getter
public class Person implements Parcelable {
    String firstName = "";
    String lastName = "";
    String birthday = "";
    int age;
    String emailAddress = "";
    String mobileNumber = "";
    String address = "";
    String contactPerson = "";
    String contactPersonsMobileNumber = "";

    protected Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        birthday = in.readString();
        age = in.readInt();
        emailAddress = in.readString();
        mobileNumber = in.readString();
        address = in.readString();
        contactPerson = in.readString();
        contactPersonsMobileNumber = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(birthday);
        parcel.writeInt(age);
        parcel.writeString(emailAddress);
        parcel.writeString(mobileNumber);
        parcel.writeString(address);
        parcel.writeString(contactPerson);
        parcel.writeString(contactPersonsMobileNumber);
    }
}
