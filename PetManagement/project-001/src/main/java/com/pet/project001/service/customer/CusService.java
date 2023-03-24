package com.pet.project001.service.customer;

import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

public interface CusService {
    List<Customer> findAll(CusSearchBean csb, PaginateInfo pi);

    boolean deleteById(Integer[] ids);

    boolean insertCus(CusSearchBean csb);

    boolean updateCus(CusSearchBean csb);

    Customer findById(Integer id);
}
