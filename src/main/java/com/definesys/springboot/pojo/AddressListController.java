package com.definesys.springboot.pojo;

import com.definesys.mpaas.common.exception.MpaasRuntimeException;
import com.definesys.mpaas.common.http.Response;
import com.definesys.mpaas.log.SWordLogger;
import com.definesys.mpaas.query.MpaasQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.definesys.springboot.pojo.pojo.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: 10976
 * @since: 2020-07-30
 * @history: 1.2020-07-30 created by 10976
 */
@RestController
@RequestMapping(value = "/pojo/AddressList")
public class AddressListController {
    @Autowired
    private MpaasQueryFactory sw;

    @Autowired
    private AddressListService serivce;

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Response queryAddressList() {

        return Response.ok().table(serivce.queryAddressList());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Response addAddressList(@RequestBody AddressList item) {
        serivce.addAddressList(item);
        return Response.ok();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Response deleteAddressList(@RequestParam(value = "rowId") String rowId) {
        serivce.deleteAddressList(rowId);
        return Response.ok();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response updateAddressList(@RequestBody AddressList item) {
        serivce.updateAddressList(item);
        return Response.ok();
    }


    /**
     * 导出excel
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public void export(HttpServletResponse response) {
        sw.buildQuery()
                .fileName("pojo.xlsx")
                .doExport(response, AddressList.class);
    }

}