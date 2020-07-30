package com.definesys.springboot.pojo;

import com.definesys.mpaas.common.exception.MpaasRuntimeException;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.db.PageQueryResult;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.definesys.springboot.pojo.pojo.*;

import java.util.*;


/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: 10976
 * @since: 2020-07-30
 * @history: 1.2020-07-30 created by 10976
 */
@Component
public class AddressListDAO {

    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private SWordLogger logger;

    public List<AddressList> queryAddressList() {
        List<AddressList> table = sw.buildQuery()
                .doQuery(AddressList.class);
        return table;
    }

    public PageQueryResult<AddressList> pageQueryAddressList(Integer page, Integer pageSize) {
        return sw.buildQuery()
                .doPageQuery(page, pageSize, AddressList.class);
    }

    public Object addAddressList(AddressList item) {
        Object key = sw.buildQuery()
                .bind(item)
                .doInsert();
        return key;
    }

    public void deleteAddressList(String rowId) {
        sw.buildQuery()
                .bind(AddressList.class)
                .addRowIdClause("userid", "=", rowId)
                .doDelete();
    }

    public void updateAddressList(AddressList item) {
        sw.buildQuery()
                .addRowIdClause("userid", "=", item.getRowId())
                .doUpdate(item);
    }

}