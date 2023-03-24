package com.pet.project001.dao.pet.impl;

import com.pet.project001.dao.pet.PetDAO;
import com.pet.project001.global.Global;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.util.PaginateInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PetDAOImpl implements PetDAO {
    //Jdbc工具类，用于对数据存储的，从global那可以获取数据源
    private final JdbcTemplate template = new JdbcTemplate(Global.getDataSource());

    @Override
    public List<Pet> findAll(PetSearchBean psb, PaginateInfo pi) {
        String sqlCnt = "select count(*) from t_pet";

        String sql = "select petId,petName,petSex,petAge,ownerId,petType,petBreed,DepositBegTime,DepositEndTime,note from t_pet ";
        List<Object> args = new ArrayList<>();

        StringBuilder whereSql = new StringBuilder();
        if (psb != null) {
            whereSql.append(" where 1=1");
            if (StringUtils.hasText(psb.getPetId())) {
                whereSql.append(" and petId = ?");
                args.add(psb.getPetId());
            }
            if (StringUtils.hasText(psb.getPetName())) {
                whereSql.append(" and petName like ?");
                args.add("%" + psb.getPetName() + "%");
            }
            if (StringUtils.hasText(psb.getPetSex()) && !(psb.getPetSex()).equals("不限")) {
                whereSql.append(" and petSex = ?");
                args.add(psb.getPetSex());
            }
            if (StringUtils.hasText(psb.getPetAge())) {
                whereSql.append(" and petAge = ?");
                args.add(psb.getPetAge());
            }
            if (StringUtils.hasText(psb.getOwnerId())) {
                whereSql.append(" and ownerId = ?");
                args.add(psb.getOwnerId());
            }
            if (StringUtils.hasText(psb.getPetType())) {
                whereSql.append(" and petType = ?");
                args.add(psb.getPetType());
            }
            if (StringUtils.hasText(psb.getPetBreed())) {
                whereSql.append(" and petBreed = ?");
                args.add(psb.getPetBreed());
            }
            if (psb.getDepBegTimeFrom() != null) {
                whereSql.append(" and depositBegTime >= ?");
                args.add(psb.getDepBegTimeFrom());
            }
            if (psb.getDepBegTimeTo() != null) {
                whereSql.append(" and depositBegTime < ?");
                args.add(psb.getDepBegTimeTo());
            }
            if (psb.getDepEndTimeFrom() != null) {
                whereSql.append(" and depositEndTime >= ?");
                args.add(psb.getDepEndTimeFrom());
            }
            if (psb.getDepEndTimeTo() != null) {
                whereSql.append(" and depositEndTime < ?");
                args.add(psb.getDepEndTimeTo());
            }
            if (StringUtils.hasText(psb.getNote())) {
                whereSql.append(" and note like ?");
                args.add("%" + psb.getNote() + "%");
            }
        }

        /*把已经添加完毕的whereSql语句加到查询总数的sql语句后面
        （因为查询总数的sql不需要使用limit，所以放在这个位置）*/
        sqlCnt += whereSql.toString();
        //获得总条数
        Long count = template.queryForObject(sqlCnt, Long.class,args.toArray());
        pi.setCount(count);


        //第一个问号？：从第几行开始查；第二个问号？：查几行；
        whereSql.append(" limit ?,?");
        args.add(pi.getOffset());
        args.add(pi.getLimit());

        sql += whereSql.toString();
        //Bean：数据模型，只有数据没有行为（例如Pet类就是一个Bean）
        //第二个参数：将查询到的数据使用Property的方法映射为一个Bean；
        List<Pet> petList = template.query(sql, new BeanPropertyRowMapper(Pet.class), args.toArray());
        return petList;
    }

    @Override
    public int deleteById(Integer[] ids) {
        int id;
        int rows = 0;
        for (int i=0;i<ids.length;i++){
            id = ids[i];
            String sqlDe = "delete from t_pet where petId = ?";
            rows = template.update(sqlDe,id);
        }
        return rows;
    }

    @Override
    public int deleteByOwnerId(Integer id) {
        int rows = 0;
        String sqlDe = "delete from t_pet where ownerId = ?";
        rows = template.update(sqlDe,id);
        return rows;
    }

    @Override
    public boolean insertPet(PetSearchBean psb) {
        String sqlIns = "insert into t_pet "+
                "(petName,petSex,petAge,petType,petBreed,ownerId,depositBegTime,depositEndTime,note) "+
                "values (?,?,?,?,?,?,?,?,?)";
        /*System.out.println(psb.getPetName()+psb.getPetSex()+psb.getPetAge()+
                psb.getPetType()+psb.getPetBreed()+psb.getDepositBegTime()+
                psb.getDepositEndTime()+psb.getNote());*/
        int rows = template.update(sqlIns,psb.getPetName(),psb.getPetSex(),psb.getPetAge(),psb.getPetType(),psb.getPetBreed(),psb.getOwnerId(),psb.getDepositBegTime(),psb.getDepositEndTime(),psb.getNote());
        if (rows>0){
            return true;
        }else {
            return false;
        }
    }

    public boolean updatePet(PetSearchBean psb){
        String sqlUp = "update t_pet set petName=?,petSex=?,petAge=?,ownerId=?,petType=?,petBreed=?,depositBegTime=?,depositEndTime=?,note=? "+
                "where petId = ?";
        int rows = template.update(sqlUp,psb.getPetName(),psb.getPetSex(),psb.getPetAge(),psb.getOwnerId(),psb.getPetType(),psb.getPetBreed(),psb.getDepositBegTime(),psb.getDepositEndTime(),psb.getNote(),psb.getPetId());
        if (rows>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Pet findById(Integer id) {
        String sql = "select petId,petName,petSex,petAge,petType,petBreed,DepositBegTime,DepositEndTime,note from t_pet where petId = ?";
        List<Pet> pets = template.query(sql, new BeanPropertyRowMapper(Pet.class), id);
        Pet pet = pets.get(0);
        System.out.println("qqqqq" + pet);
        return pet;
    }

    @Override
    public List<Pet> findByOwnerId(Integer ownId) {
        String sql = "select * from t_pet where ownerId = ?";
        List<Pet> pets = template.query(sql, new BeanPropertyRowMapper(Pet.class), ownId);
        if (pets != null){
            return pets;
        }else {
            return null;
        }
    }
}
