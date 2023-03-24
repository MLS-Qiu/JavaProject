package com.pet.project001.global;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.model.Pet;
import com.pet.project001.service.customer.CusService;
import com.pet.project001.service.customer.impl.CusServiceImpl;
import com.pet.project001.servlet.CustomerServlet;
import com.pet.project001.util.BeanFactory;

import java.util.ArrayList;
import java.util.List;


public class UploadDataListener implements ReadListener<CusSearchBean> {
    private final CusService cusService = BeanFactory.getBean(CusServiceImpl.class);
    private final List<CusSearchBean> list = new ArrayList<>();

    @Override
    public void invoke(CusSearchBean csb, AnalysisContext analysisContext) {
        list.add(csb);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取结束");
        for (CusSearchBean csb : list) {
            cusService.insertCus(csb);
        }
    }
}
