package com.example.vladislav.androidtest;

import com.example.vladislav.androidtest.entities.BankDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by vladislav on 05.02.17.
 */

public class BanksDetailsOperating {

    private int banksNumberDefault = 10;
    private List<BankDetails> banksDetails;

    public BanksDetailsOperating() {
        this.banksDetails = createBanksDetailsByDefault();
    }

    private List<BankDetails> createBanksDetailsByDefault() {

        List<BankDetails> banksDetails = new ArrayList<>();
        for (int i=0; i < banksNumberDefault; i++) {

            banksDetails.add(new BankDetails());
            banksDetails.get(i).setAddress("Тукая, " + (int)(Math.random() * 100));
            banksDetails.get(i).setLatitude((int)(Math.random() * 100) + "." + (int)(Math.random() * 10000));
            banksDetails.get(i).setLongtitude((int)(Math.random() * 100) + "." + (int)(Math.random() * 10000));
            banksDetails.get(i).setName("Банк Авангард");
            banksDetails.get(i).setPhoneNumber("+7 (" + (int)(Math.random() * 10000) + ") "
                    + (int)(Math.random() * 1000)
                    + "-"
                    + (int)(Math.random() * 100)
                    + "-"
                    + (int)(Math.random() * 100));
            banksDetails.get(i).setQualityControl("Прекрасно");
            banksDetails.get(i).setWorkMode("8.00-17.00");
        }

        return banksDetails;
    }

    public void printBanksDetailsByDefault() {

        for (int i=0; i < banksNumberDefault; i++) {
            System.out.println("Address is: " + banksDetails.get(i).getAddress());
            System.out.println("Latitude is: " + banksDetails.get(i).getLatitude());
            System.out.println("Longitude is: " + banksDetails.get(i).getLongtitude());
            System.out.println("Name is: " + banksDetails.get(i).getName());
            System.out.println("Phone number is: " + banksDetails.get(i).getPhoneNumber());
            System.out.println("Quality control is: " + banksDetails.get(i).getQualityControl());
            System.out.println("Work mode is: " + banksDetails.get(i).getWorkMode());
            System.out.println();
        }

    }

    public List<BankDetails> getBanksDetails() {
        return banksDetails;
    }

    public void setBanksDetails(List<BankDetails> banksDetails) {
        this.banksDetails = banksDetails;
    }

}
