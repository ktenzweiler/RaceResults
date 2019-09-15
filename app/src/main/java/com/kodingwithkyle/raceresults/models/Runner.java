package com.kodingwithkyle.raceresults.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Runner implements Parcelable {

    // Creator
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Runner>() {

        @Override
        public Runner createFromParcel(Parcel source) {
            return new Runner(source);
        }

        @Override
        public Runner[] newArray(int size) {
            return new Runner[size];
        }
    };
    private String Name;
    private int Time, Age;

    public Runner(String name, int time, int age) {
        Name = name;
        Time = time;
        Age = age;
    }

    // "De-parcel object
    private Runner(Parcel in) {
        setName(in.readString());
        setTime(in.readInt());
        setAge(in.readInt());
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTime() {
        return Time;
    }

    private void setTime(int time) {
        Time = time;
    }

    public int getAge() {
        return Age;
    }

    private void setAge(int age) {
        Age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getName());
        dest.writeInt(getTime());
        dest.writeInt(getAge());
    }
}