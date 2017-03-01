package com.example.vladislav.androidtest.BanksOfficesList;

import com.example.vladislav.androidtest.datasource.BanksDetailsOperating;

/**
 * Created by vladislav on 20.02.17.
 */

public class BankOfficeListController {

    BankOfficeListFragment bankOfficeListFragment;
    BanksDetailsOperating banksDetailsOperating;

    public BankOfficeListController (BankOfficeListActivity bankOfficeListActivity,
                              BanksDetailsOperating banksDetailsOperating) {
        this.bankOfficeListFragment = bankOfficeListFragment;
        this.banksDetailsOperating = banksDetailsOperating;
    }

}
