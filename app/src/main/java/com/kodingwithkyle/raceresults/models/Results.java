package com.kodingwithkyle.raceresults.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Results implements Parcelable {

    public static final Creator<Results> CREATOR = new Creator<Results>() {
        @Override
        public Results createFromParcel(Parcel in) {
            return new Results(in);
        }

        @Override
        public Results[] newArray(int size) {
            return new Results[size];
        }
    };
    private String Name;
    private ArrayList<Runner> Runners;

    public Results(String name, ArrayList<Runner> runners) {
        Name = name;
        Runners = runners;
    }

    public Results(Parcel in) {
        Name = in.readString();
        in.readTypedList(Runners, Runner.CREATOR);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ArrayList<Runner> getRunners() {
        return Runners;
    }

    public void setRunners(ArrayList<Runner> runners) {
        Runners = runners;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeTypedList(Runners);
    }
}
