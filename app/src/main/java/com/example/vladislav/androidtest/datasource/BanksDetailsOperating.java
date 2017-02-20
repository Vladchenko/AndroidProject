package com.example.vladislav.androidtest.datasource;

import com.example.vladislav.androidtest.beans.BankDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vladislav on 05.02.17.
 */

public class BanksDetailsOperating {

    private int mBanksNumberDefault = 500;
    private int mBanksNumber = 10;
    private int mTemp;
    private List<BankDetails> mBanksDetails;

    public BanksDetailsOperating() {
        this.mBanksDetails = createBanksDetailsByDefault();
    }

    private List<BankDetails> createBanksDetailsByDefault() {

        List<BankDetails> banksDetails = new ArrayList<>();
        for (int i = 0; i < mBanksNumberDefault; i++) {

            banksDetails.add(new BankDetails());
            banksDetails.get(i).setAddress("Тукая, " + (int) (Math.random() * 100));
            banksDetails.get(i).setLatitude((int) (Math.random() * 100) + "." + (int) (Math.random() * 10000));
            banksDetails.get(i).setLongtitude((int) (Math.random() * 100) + "." + (int) (Math.random() * 10000));
            banksDetails.get(i).setDistance((int) (Math.random() * 20) + "." + (int) (Math.random() * 100) + " км.");
            mTemp = (int) (Math.random() * mBanksNumber);
            switch (mTemp) {
                case 0:{
                    banksDetails.get(i).setName("Банк Авангард");
                    break;
                }
                case 1:{
                    banksDetails.get(i).setName("АкБарс Банк");
                    break;
                }
                case 2:{
                    banksDetails.get(i).setName("СберБанк");
                    break;
                }
                case 3:{
                    banksDetails.get(i).setName("Совком Банк");
                    break;
                }
                case 4:{
                    banksDetails.get(i).setName("Райффайзен Банк");
                    break;
                }
                case 5:{
                    banksDetails.get(i).setName("Аки Банк");
                    break;
                }
                case 6:{
                    banksDetails.get(i).setName("ВТБ 24");
                    break;
                }
                case 7:{
                    banksDetails.get(i).setName("Газпром Банк");
                    break;
                }
                case 8:{
                    banksDetails.get(i).setName("Альфа Банк");
                    break;
                }
                case 9:{
                    banksDetails.get(i).setName("Промсвязь Банк");
                    break;
                }
            }

            banksDetails.get(i).setPhoneNumber("+7 (" + (int) (Math.random() * 10000) + ") "
                    + (int) (Math.random() * 1000)
                    + "-"
                    + (int) (Math.random() * 100)
                    + "-"
                    + (int) (Math.random() * 100));
            banksDetails.get(i).setQualityControl("Прекрасно");
            int extraHours = (int) (Math.random() * 4);
            banksDetails.get(i).setWorkingHours(extraHours + 7 + ".00-" + (extraHours + 7 + 9) + ".00");
        }

        return banksDetails;
    }

    public void printBanksDetailsByDefault() {

        for (int i = 0; i < mBanksNumberDefault; i++) {
            System.out.println("Address is: " + mBanksDetails.get(i).getAddress());
            System.out.println("Latitude is: " + mBanksDetails.get(i).getLatitude());
            System.out.println("Longitude is: " + mBanksDetails.get(i).getLongtitude());
            System.out.println("Name is: " + mBanksDetails.get(i).getName());
            System.out.println("Phone number is: " + mBanksDetails.get(i).getPhoneNumber());
            System.out.println("Quality control is: " + mBanksDetails.get(i).getQualityControl());
            System.out.println("Work mode is: " + mBanksDetails.get(i).getWorkingHours());
            System.out.println("Distance is: " + mBanksDetails.get(i).getDistance());
            System.out.println();
        }

    }

    public List<BankDetails> getmBanksDetails() {
        return mBanksDetails;
    }

    public void setmBanksDetails(List<BankDetails> mBanksDetails) {
        this.mBanksDetails = mBanksDetails;
    }

}
