package com.pet.project001.service.kennels;

import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

public interface KenService {
    List<Customer> findAll(CusSearchBean csb, PaginateInfo pi);

    boolean deleteById(Integer[] ids);

    boolean insertCus(CusSearchBean csb);

    boolean updateCus(CusSearchBean csb);

    Customer findById(Integer id);
}
