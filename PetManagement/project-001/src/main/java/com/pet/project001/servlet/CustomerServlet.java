package com.pet.project001.servlet;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.pet.project001.model.CusSearchBean;
import com.pet.project001.model.Customer;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.service.customer.CusService;
import com.pet.project001.service.customer.impl.CusServiceImpl;
import com.pet.project001.service.pet.PetService;
import com.pet.project001.service.pet.impl.PetServiceImpl;
import com.pet.project001.util.BeanFactory;
import com.pet.project001.util.PaginateInfo;
import com.pet.project001.util.Servlets;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet("/customer/*")
public class CustomerServlet extends HttpServlet {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    private final CusService service = BeanFactory.getBean(CusServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String action = uri.replace(ctx, "");

        if (action.equals("/customer/cueList")) {
            req.getRequestDispatcher("/WEB-INF/jsp/customer/cusList.jsp").forward(req, resp);
        }
        else if (action.equals("/customer/cusDelete")) {
            req.getRequestDispatcher("/WEB-INF/jsp/customer/cusSearch.jsp").forward(req, resp);
        }
        else if (action.equals("/customer/cusInsert")) {
            HttpSession session = req.getSession();
            Customer cus = (Customer) session.getAttribute("cus");
            String error = (String) session.getAttribute("error");

            if (cus != null) {
                req.setAttribute("cus", cus);
                session.removeAttribute("cus");
                req.setAttribute("error", error);
                session.removeAttribute("error");
            }

            req.getRequestDispatcher("/WEB-INF/jsp/customer/cusInsert.jsp").forward(req, resp);
        }
        else if (action.equals("/customer/cusUpdate")) {
            String id = req.getParameter("id");
            try {
                Integer cusId = Integer.parseInt(id);
                Customer cusById = service.findById(cusId);
                if (cusById != null) {
                    req.setAttribute("cusById", cusById);
                } else {
                    req.setAttribute("error", "修改记录的客户编号不能为空");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "请指定要修改的客户的编号");
            }
            req.getRequestDispatcher("/WEB-INF/jsp/customer/cusUpdate.jsp").forward(req, resp);
        }else if (action.equals("/customer/cusSearch")){
            req.getRequestDispatcher("/WEB-INF/jsp/customer/cusSearch.jsp").forward(req, resp);
        }else if(action.equals("/customer/cusExcOut")){
            doPost(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String action = uri.replace(ctx, "");

        if (action.equals("/customer/cueList")) {
            search();
        } else if (action.equals("/customer/cusDelete")) {
            deleteById();
        } else if (action.equals("/customer/cusInsert")) {
            insert();
        } else if (action.equals("/customer/cusUpdate")) {
            update();
        }else if (action.equals("/customer/cusExcOut")) {
            ExcelOut();
        }
    }

    private void search() {
        //获取前端的值
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        String cId = req.getParameter("cusId");
        String cName = req.getParameter("cusName");
        String cSex = req.getParameter("cusSex");
        String cAge = req.getParameter("cusAge");
        String cPhone = req.getParameter("cusPhone");
        String cEmail = req.getParameter("cusEmail");
        String cBirthFrom = req.getParameter("cusBirthFrom");
        String cBirthTo = req.getParameter("cusBirthTo");
        String cAddress = req.getParameter("cusAddress");
        String cNote = req.getParameter("cusNote");

        CusSearchBean csb = new CusSearchBean();//查询条件

        //校验数据

        if (StringUtils.hasText(cId)) {
            csb.setCusId(cId);
        }
        if (StringUtils.hasText(cName)) {
            csb.setCusName(cName);
        }
        if (StringUtils.hasText(cSex)) {
            csb.setCusSex(cSex);
        }
        if (StringUtils.hasText(cAge)) {
            csb.setCusAge(cAge);
        }
        if (StringUtils.hasText(cPhone)) {
            csb.setCusPhone(cPhone);
        }
        if (StringUtils.hasText(cEmail)) {
            csb.setCusEmail(cEmail);
        }
        if (StringUtils.hasText(cAddress)) {
            csb.setCusAddress(cAddress);
        }
        if (StringUtils.hasText(cBirthFrom)) {
            LocalDate ld = LocalDate.parse(cBirthFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            csb.setBirthdayFrom(ld);
        }
        if (StringUtils.hasText(cBirthTo)) {
            LocalDate ld = LocalDate.parse(cBirthTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            csb.setBirthdayTo(ld);
        }
        if (StringUtils.hasText(cNote)) {
            csb.setNote(cNote);
        }

        //从ajax获取的pageNum有可能为空，设置默认值为1，如果不为空就将值转为int赋值给jPageNum
        int jPageNum = 1;
        //hasText.()判断括号内的内容是否为空
        if (StringUtils.hasText(pageNum)) {
            jPageNum = Integer.parseInt(pageNum);
        }
        int jPageSize = 15;
        if (StringUtils.hasText(pageSize)) {
            jPageSize = Integer.parseInt(pageSize);
        }

        PaginateInfo pi = new PaginateInfo(jPageNum, jPageSize);
        List<Customer> cusList = service.findAll(csb, pi);

        String cusJson = JSON.toJSONString(Map.of("cusList", cusList, "pi", pi));
        Servlets.renderJson(cusJson, resp);
    }

    private void deleteById() {
        String[] strIds = req.getParameterValues("ids");

        Integer[] intIds = Servlets.strToIn(strIds);

        try {
            boolean result = service.deleteById(intIds);
            if (result) {
                String petJson = JSON.toJSONString(Map.of("success", true, "suc", "删除成功"));
                Servlets.renderJson(petJson, resp);
            } else {
                String petJson = JSON.toJSONString(Map.of("success", true, "ero", "删除失败"));
                Servlets.renderJson(petJson, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String petJson = JSON.toJSONString(Map.of("success", true, "ero", "删除失败"));
            Servlets.renderJson(petJson, resp);
        }
    }

    private void insert() throws IOException {
        //获取前端的值
        String cName = req.getParameter("cName");
        String cSex = req.getParameter("cSex");
        String cAge = req.getParameter("cAge");
        String cPhone = req.getParameter("cPhone");
        String cEmail = req.getParameter("cEmail");
        String cBirth = req.getParameter("cBirth");
        String cAddress = req.getParameter("cAddress");
        String cNote = req.getParameter("note");

        CusSearchBean csb = new CusSearchBean();//查询条件

        HttpSession session = req.getSession();
        boolean passed = true;//是否通过校验
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(cName)) {
            passed = false;
            errors.add("名字不能为空");
        }
        csb.setCusName(cName);

        csb.setCusSex(cSex);
        csb.setCusAge(cAge);

        if (!StringUtils.hasText(cPhone)) {
            passed = false;
            errors.add("电话不能为空");
        }
        csb.setCusPhone(cPhone);

        csb.setCusEmail(cEmail);

        try {
            LocalDate ldBeg = LocalDate.parse(cBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            csb.setCusBirthday(ldBeg);
        } catch (Exception e) {
            passed = false;
            errors.add("生日格式不正确");
        }

        if (!StringUtils.hasText(cAddress)) {
            passed = false;
            errors.add("地址不能为空");
        }
        csb.setCusAddress(cAddress);

        csb.setNote(cNote);

        if (!passed) {
            session.setAttribute("cus", csb);
            String error = errors.stream().collect(Collectors.joining(","));
            session.setAttribute("error", error);
            resp.sendRedirect(req.getContextPath() + "/customer/cusInsert");
            return;
        }

        boolean result = service.insertCus(csb);
        session = req.getSession();
        if (result) {
            resp.sendRedirect(req.getContextPath() + "/customer/cueList");
        } else {
            session.setAttribute("cus", csb);
            session.setAttribute("error", "保存信息错误");
            resp.sendRedirect(req.getContextPath() + "/customer/cusInsert");
        }
    }

    private void update() throws IOException, ServletException {
        //获取前端的值
        String id = req.getParameter("id");
        String cName = req.getParameter("cName");
        String cSex = req.getParameter("cSex");
        String cAge = req.getParameter("cAge");
        String cPhone = req.getParameter("cPhone");
        String cEmail = req.getParameter("cEmail");
        String cBirth = req.getParameter("cBirth");
        String cAddress = req.getParameter("cAddress");
        String cNote = req.getParameter("note");

        CusSearchBean csb = new CusSearchBean();//查询条件

        HttpSession session = req.getSession();
        boolean passed = true;//是否通过校验
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(id)) {
            passed = false;
            errors.add("客户编号不能为空");
        }
        csb.setCusId(id);

        if (!StringUtils.hasText(cName)) {
            passed = false;
            errors.add("名字不能为空");
        }
        csb.setCusName(cName);

        csb.setCusSex(cSex);
        csb.setCusAge(cAge);

        if (!StringUtils.hasText(cPhone)) {
            passed = false;
            errors.add("电话不能为空");
        }
        csb.setCusPhone(cPhone);

        csb.setCusEmail(cEmail);

        try {
            LocalDate ldBeg = LocalDate.parse(cBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            csb.setCusBirthday(ldBeg);
        } catch (Exception e) {
            passed = false;
            errors.add("生日格式不正确");
        }

        if (!StringUtils.hasText(cAddress)) {
            passed = false;
            errors.add("地址不能为空");
        }
        csb.setCusAddress(cAddress);

        csb.setNote(cNote);

        if (!passed) {
            session.setAttribute("cus", csb);
            String error = errors.stream().collect(Collectors.joining(","));
            session.setAttribute("error", error);
            resp.sendRedirect(req.getContextPath() + "/customer/cusUpdate");
            return;
        }

        boolean result = service.updateCus(csb);
        if (result) {
            String cusJson = JSON.toJSONString(Map.of("suc", true));
            Servlets.renderJson(cusJson, resp);
        } else {
            String cusJson = JSON.toJSONString(Map.of("suc", false));
            Servlets.renderJson(cusJson, resp);
        }
    }

    private void ExcelOut() throws IOException {
        //获取选中要导出的记录的id数组
        String[] strIds = req.getParameterValues("ids");
        Integer[] intIds = Servlets.strToIn(strIds);

        //获得记录的所有信息
        List<Customer> chosenCus = new ArrayList<>();
        for (int i = 0; i < intIds.length; i++) {
            Customer cus = service.findById(intIds[i]);
            chosenCus.add(cus);
        }

        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");

        try {
            LocalDateTime now = LocalDateTime.now();
            String format = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileName = URLEncoder.encode("客户信息列表导出" + format, "UTF-8").replaceAll("\\+", "%20");
            resp.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            //导出
            EasyExcel.write(resp.getOutputStream(), Customer.class).sheet("模板").doWrite(chosenCus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath()+"/customer/cusSearch");
    }
}
