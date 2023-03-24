package com.pet.project001.service.kennels.impl;

import com.pet.project001.dao.customer.CusDAO;
import com.pet.project001.dao.customer.impl.CusDAOImpl;
import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.service.kennels.KenService;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

public class KenServiceImpl implements KenService {
    private final CusDAO dao = BeanFactory.getBean(CusDAOImpl.class);
    @Override
    public List<Customer> findAll(CusSearchBean csb, PaginateInfo pi) {
        return dao.findAll(csb, pi);
    }

    @Override
    public boolean deleteById(Integer[] ids) {
        return dao.deleteById(ids);
    }

    @Override
    public boolean insertCus(CusSearchBean csb) {
        return dao.insertCus(csb);
    }

    @Override
    public boolean updateCus(CusSearchBean csb) {
        return dao.updateCus(csb);
    }

    @Override
    public Customer findById(Integer id) {
        return dao.findById(id);
    }
}
