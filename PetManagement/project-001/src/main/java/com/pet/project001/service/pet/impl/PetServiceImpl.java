package com.pet.project001.service.pet.impl;

import com.pet.project001.dao.pet.PetDAO;
import com.pet.project001.dao.pet.impl.PetDAOImpl;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.service.pet.PetService;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

public class PetServiceImpl implements PetService {
    /*创建PetDAOImpl的唯一实例*/
    private final PetDAO dao = BeanFactory.getBean(PetDAOImpl.class);
    @Override
    public List<Pet> findAll(PetSearchBean psb, PaginateInfo pi) {
        return dao.findAll(psb, pi);
    }

    @Override
    public int deleteById(Integer[] ids) {
        return dao.deleteById(ids);
    }

    public int deleteByOwnerId(Integer id) {
        return dao.deleteByOwnerId(id);
    }

    @Override
    public boolean insertPet(PetSearchBean psb) {
        return dao.insertPet(psb);
    }

    @Override
    public boolean updatePet(PetSearchBean psb) {
        return dao.updatePet(psb);
    }

    @Override
    public Pet findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public List<Pet> findByOwnerId(Integer ownId) {
        return dao.findByOwnerId(ownId);
    }
}
