package com.pet.project001.dao.pet;

import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.util.PaginateInfo;

import java.util.List;

//创建接口是因为以后可能下面会有多个方法，到时候创建类，直接继承PetDAO即可；
public interface PetDAO {
    List<Pet> findAll(PetSearchBean psb, PaginateInfo pi);

    int deleteById(Integer[] ids);
    int deleteByOwnerId(Integer id);

    boolean insertPet(PetSearchBean psb);

    boolean updatePet(PetSearchBean psb);

    Pet findById(Integer id);

    List<Pet> findByOwnerId(Integer ownId);
}
