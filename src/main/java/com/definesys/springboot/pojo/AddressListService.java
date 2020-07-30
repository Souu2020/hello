package com.definesys.springboot.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.definesys.springboot.pojo.pojo.*;

import java.util.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: 10976
 * @since: 2020-07-30
 * @history: 1.2020-07-30 created by 10976
 */
@Service
public class AddressListService {
    @Autowired
    private AddressListDAO swAddressListDao;

    public List<AddressList> queryAddressList() {
        return swAddressListDao.queryAddressList();
    }

    public void addAddressList(AddressList item) {
        swAddressListDao.addAddressList(item);
    }

    public void deleteAddressList(String rowId) {
        swAddressListDao.deleteAddressList(rowId);
    }

    public void updateAddressList(AddressList item) {
        swAddressListDao.updateAddressList(item);
    }
}