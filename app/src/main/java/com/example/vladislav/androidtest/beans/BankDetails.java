package com.example.vladislav.androidtest.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vladislav on 05.02.17.
 */

public class BankDetails implements Parcelable {

    private String address;
    private String distance;
    private String name; // extra office
    private String latitude;
    private String longtitude;
    private String workingHours;
    private String phoneNumber;
    private String qualityControl;

    protected BankDetails(Parcel in) {
        setAddress(in.readString());
        setName(in.readString());
        setLatitude(in.readString());
        setLongtitude(in.readString());
        setDistance(in.readString());
        setWorkingHours(in.readString());
        setPhoneNumber(in.readString());
        setQualityControl(in.readString());
    }

    public static final Creator<BankDetails> CREATOR = new Creator<BankDetails>() {
        @Override
        public BankDetails createFromParcel(Parcel in) {
            return new BankDetails(in);
        }

        @Override
        public BankDetails[] newArray(int size) {
            return new BankDetails[size];
        }
    };

    public BankDetails() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getAddress());
        dest.writeString(getName());
        dest.writeString(getLatitude());
        dest.writeString(getLongtitude());
        dest.writeString(getDistance());
        dest.writeString(getWorkingHours());
        dest.writeString(getPhoneNumber());
        dest.writeString(getQualityControl());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getQualityControl() {
        return qualityControl;
    }

    public void setQualityControl(String qualityControl) {
        this.qualityControl = qualityControl;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}