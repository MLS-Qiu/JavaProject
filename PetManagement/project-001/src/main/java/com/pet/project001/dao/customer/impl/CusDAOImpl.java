package com.pet.project001.dao.customer.impl;

import com.pet.project001.dao.customer.CusDAO;
import com.pet.project001.global.Global;
import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.service.pet.PetService;
import com.pet.project001.service.pet.impl.PetServiceImpl;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.PaginateInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CusDAOImpl implements CusDAO {
    private final JdbcTemplate template = new JdbcTemplate(Global.getDataSource());
    private final PetService service = BeanFactory.getBean(PetServiceImpl.class);


    @Override
    public List<Customer> findAll(CusSearchBean csb, PaginateInfo pi) {
        String sqlCnt = "select count(*) from t_customer";

        String sql = "select * from t_customer";
        List<Object> args = new ArrayList<>();

        StringBuilder whereSql = new StringBuilder();
        if (csb != null) {
            whereSql.append(" where 1=1");
            if (StringUtils.hasText(csb.getCusId())) {
                whereSql.append(" and cusId = ?");
                args.add(csb.getCusId());
            }
            if (StringUtils.hasText(csb.getCusName())) {
                whereSql.append(" and cusName like ?");
                args.add("%" + csb.getCusName() + "%");
            }
            if (StringUtils.hasText(csb.getCusSex()) && !csb.getCusSex().equals("不限")) {
                whereSql.append(" and cusSex = ?");
                args.add(csb.getCusSex());
            }
            if (StringUtils.hasText(csb.getCusAge())) {
                whereSql.append(" and cusAge = ?");
                args.add(csb.getCusAge());
            }
            if (StringUtils.hasText(csb.getCusPhone())) {
                whereSql.append(" and cusPhone like ?");
                args.add("%" + csb.getCusPhone() + "%");
            }
            if (StringUtils.hasText(csb.getCusEmail())) {
                whereSql.append(" and cusEmail like ?");
                args.add("%" + csb.getCusEmail() + "%");
            }
            if (csb.getBirthdayFrom() != null) {
                whereSql.append(" and cusBirthday >= ?");
                args.add(csb.getBirthdayFrom());
            }
            if (csb.getBirthdayTo() != null) {
                whereSql.append(" and cusBirthday < ?");
                args.add(csb.getBirthdayTo());
            }
            if (StringUtils.hasText(csb.getCusAddress())) {
                whereSql.append(" and cusAddress like ?");
                args.add("%" + csb.getCusAddress() + "%");
            }
            if (StringUtils.hasText(csb.getNote())) {
                whereSql.append(" and note like ?");
                args.add("%" + csb.getNote() + "%");
            }
        }

        /*把已经添加完毕的whereSql语句加到查询总数的sql语句后面
        （因为查询总数的sql不需要使用limit，所以放在这个位置）*/
        sqlCnt += whereSql.toString();
        //获得总条数
        Long count = template.queryForObject(sqlCnt, Long.class, args.toArray());
        pi.setCount(count);

        //第一个问号？：从第几行开始查；第二个问号？：查几行；
        whereSql.append(" limit ?,?");
        args.add(pi.getOffset());
        args.add(pi.getLimit());

        sql += whereSql.toString();
        //Bean：数据模型，只有数据没有行为（例如Pet类就是一个Bean）
        //第二个参数：将查询到的数据使用Property的方法映射为一个Bean；
        List<Customer> cusList = template.query(sql, new BeanPropertyRowMapper(Customer.class), args.toArray());
        return cusList;
    }

    @Override
    public boolean deleteById(Integer[] ids) {
        int id;
        int rows = 0;
        for (int i = 0; i < ids.length; i++) {
            id = ids[i];
            List<Pet> pet = service.findByOwnerId(id);

                if (pet.size() > 0) {
                    for (int j = 0; j < pet.size(); j++) {
                    int pRows = service.deleteByOwnerId(Integer.parseInt(pet.get(j).getPetId()));
                    if (pRows < 0) {
                        return false;
                    }
                }
            }
            String sqlDe = "delete from t_customer where cusId = ?";
            rows = template.update(sqlDe, id);
        }
        if (rows > 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean insertCus(CusSearchBean csb) {
        String sqlIns = "insert into t_customer " +
                "(cusName,cusSex,cusAge,cusPhone,cusEmail,cusBirthday,cusAddress,note) " +
                "values (?,?,?,?,?,?,?,?)";
        int rows = template.update(sqlIns, csb.getCusName(), csb.getCusSex(), csb.getCusAge(),
                csb.getCusPhone(), csb.getCusEmail(), csb.getCusBirthday(), csb.getCusAddress(),
                csb.getNote());
        if (rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateCus(CusSearchBean csb) {
        String sqlUp = "update t_customer set cusName=?,cusSex=?,cusAge=?,cusPhone=?," +
                "cusEmail=?,cusBirthday=?,cusAddress=?,note=? " +
                "where cusId = ?";
        int rows = template.update(sqlUp, csb.getCusName(), csb.getCusSex(), csb.getCusAge(),
                csb.getCusPhone(), csb.getCusEmail(), csb.getCusBirthday(), csb.getCusAddress(),
                csb.getNote(), csb.getCusId());
        if (rows > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Customer findById(Integer id) {
        String sql = "select * from t_customer where cusId = ?";
        List<Customer> cus = template.query(sql, new BeanPropertyRowMapper(Customer.class), id);
        Customer customer = cus.get(0);
        return customer;
    }
}
