package com.pet.project001.service.customer.impl;

import com.pet.project001.dao.customer.CusDAO;
import com.pet.project001.dao.customer.impl.CusDAOImpl;
import com.pet.project001.dao.pet.PetDAO;
import com.pet.project001.dao.pet.impl.PetDAOImpl;
import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.service.customer.CusService;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

public class CusServiceImpl implements CusService {
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
