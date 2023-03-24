package com.pet.project001.service.pet;

import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

public interface PetService {
    /**
     * 查询全部
     * @param qsb
     * @param pi
     * @return
     */
    List<Pet> findAll(PetSearchBean qsb,PaginateInfo pi);

    int deleteById(Integer[] ids);
    int deleteByOwnerId(Integer id);

    boolean insertPet(PetSearchBean psb);

    boolean updatePet(PetSearchBean psb);

    Pet findById(Integer id);

    List<Pet> findByOwnerId(Integer ownId);
}
