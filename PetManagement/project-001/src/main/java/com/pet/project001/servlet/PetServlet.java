package com.pet.project001.servlet;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.pet.project001.model.Customer;
import com.pet.project001.model.Pet;
import com.pet.project001.model.PetSearchBean;
import com.pet.project001.model.User;
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

@WebServlet("/pet/*")
public class PetServlet extends HttpServlet {
    /*获取PetServiceImpl的唯一实例*/
    private final PetService service = BeanFactory.getBean(PetServiceImpl.class);
    private HttpServletRequest req;
    private HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String ctx = req.getContextPath();
        String action = uri.replace(ctx, "");

        if (action.equals("/pet/homePage")) {
            User user = (User) req.getSession().getAttribute("user");
            if (user != null){
                req.setAttribute("user",user);
                req.getSession().removeAttribute("user");
            }
            req.getRequestDispatcher("/WEB-INF/jsp/main/homePage.jsp").forward(req, resp);
        } else if (action.equals("/pet/petList")) {
            req.getRequestDispatcher("/WEB-INF/jsp/pet/petList.jsp").forward(req, resp);
        } else if (action.equals("/pet/petInsert")) {
            HttpSession session = req.getSession();
            Pet pet = (Pet) session.getAttribute("pets");
            String error = (String) session.getAttribute("error");
            /*String sus = (String) session.getAttribute("sus");*/
            if (pet != null) {
                req.setAttribute("pets", pet);
                session.removeAttribute("pets");
                req.setAttribute("error", error);
                session.removeAttribute("error");
                /*req.setAttribute("sus", sus);
                session.removeAttribute("sus");*/
            }
            req.getRequestDispatcher("/WEB-INF/jsp/pet/petInsList.jsp").forward(req, resp);
        } else if (action.equals("/pet/petUpdate")) {
            String id = req.getParameter("id");
            try {
                Integer petId = Integer.parseInt(id);
                Pet petById = service.findById(petId);
                if (petById != null) {
                    req.setAttribute("petById", petById);
                    req.getRequestDispatcher("/WEB-INF/jsp/pet/petUpdate.jsp").forward(req, resp);
                } else {
                    req.setAttribute("error", "修改记录的宠物编号不能为空");
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "请指定要修改的宠物的编号");
            }
           req.getRequestDispatcher("/WEB-INF/jsp/pet/petUpdate.jsp").forward(req, resp);
        } else if (action.equals("/pet/petOwner")) {
            //获得前端输入的主人id
            String id = req.getParameter("ownerId");
            try {
                Integer ownerId = Integer.parseInt(id);
                List<Pet> pets = service.findByOwnerId(ownerId);
                if (pets.size()>0) {
                    String petJson = JSON.toJSONString(Map.of("success", true));
                    Servlets.renderJson(petJson, resp);
                } else {
                    String petJson = JSON.toJSONString(Map.of("success", false));
                    Servlets.renderJson(petJson, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("error", "输入主人Id错误");
            }
        }else if (action.equals("/pet/petSearch")){
            req.getRequestDispatcher("/WEB-INF/jsp/pet/petSearch.jsp").forward(req, resp);
        }else if (action.equals("/pet/petExcOut")) {
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

        if (action.equals("/pet/petList")) {
            search();
        } else if (action.equals("/pet/petDelete")) {
            deleteById();
        } else if (action.equals("/pet/petInsert")) {
            insert();
        } else if (action.equals("/pet/petUpdate")) {
            update();
        }else if (action.equals("/pet/petExcOut")) {
            ExcelOut();
        }
    }

    private void search() {
        //获取前端的值
        String pageNum = req.getParameter("pageNum");
        String pageSize = req.getParameter("pageSize");
        String pId = req.getParameter("petId");
        String pName = req.getParameter("petName");
        String pSex = req.getParameter("petSex");
        String pAge = req.getParameter("petAge");
        String petOwner = req.getParameter("petOid");
        String pType = req.getParameter("petType");
        String pBreed = req.getParameter("petBreed");
        String petBegTimeTo = req.getParameter("petBegTimeTo");
        String petBegTimeFrom = req.getParameter("petBegTimeFrom");
        String petEndTimeTo = req.getParameter("petEndTimeTo");
        String petEndTimeFrom = req.getParameter("petEndTimeFrom");
        String pNote = req.getParameter("petNote");

        PetSearchBean psb = new PetSearchBean();//查询条件

        //校验数据
        setPsb(pName, pSex, pAge, petOwner, pType, pBreed, petBegTimeFrom, petBegTimeTo, pNote, psb);
        if (StringUtils.hasText(pId)) {
            psb.setPetId(pId);
        }
        if (StringUtils.hasText(petEndTimeFrom)) {
            LocalDate ld = LocalDate.parse(petEndTimeFrom, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepEndTimeFrom(ld);
        }
        if (StringUtils.hasText(petEndTimeTo)) {
            LocalDate ld = LocalDate.parse(petEndTimeTo, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepEndTimeTo(ld);
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
        List<Pet> petList = service.findAll(psb, pi);

        String petJson = JSON.toJSONString(Map.of("petList", petList, "pi", pi));
        Servlets.renderJson(petJson, resp);
    }

    private void deleteById() {
        String[] strIds = req.getParameterValues("ids");
        Integer[] intIds = Servlets.strToIn(strIds);

        try {
            int rows = service.deleteById(intIds);
            String petJson = JSON.toJSONString(Map.of("success", true, "suc", "删除成功", "rows", rows));
            Servlets.renderJson(petJson, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String petJson = JSON.toJSONString(Map.of("success", true, "ero", "删除失败"));
            Servlets.renderJson(petJson, resp);
        }
    }

    private void insert() throws IOException, ServletException {
        //获取前端的值
        String pName = req.getParameter("pName");
        String pSex = req.getParameter("pSex");
        String pAge = req.getParameter("pAge");
        String pType = req.getParameter("pType");
        String pBreed = req.getParameter("pBreed");
        String ownerId = req.getParameter("oId");
        String petBegTime = req.getParameter("pBegTime");
        String petEndTime = req.getParameter("pEndTime");
        String pNote = req.getParameter("note");

        PetSearchBean psb = new PetSearchBean();//查询条件

        HttpSession session = req.getSession();
        boolean passed = true;//是否通过校验
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(pName)) {
            passed = false;
            errors.add("名字不能为空");
        }
        psb.setPetName(pName);

        if (!StringUtils.hasText(pSex)) {
            passed = false;
            errors.add("性别不能为空");
        }
        psb.setPetSex(pSex);
        psb.setPetAge(pAge);

        if (!StringUtils.hasText(pType)) {
            passed = false;
            errors.add("宠物类别不能为空");
        }
        psb.setPetType(pType);

        psb.setPetBreed(pBreed);
        if (!StringUtils.hasText(ownerId)) {
            passed = false;
            errors.add("宠物主人编号不能为空");
        }
        psb.setOwnerId(ownerId);

        try {
            LocalDate ldBeg = LocalDate.parse(petBegTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepositBegTime(ldBeg);
        } catch (Exception e) {
            passed = false;
            errors.add("开始日期格式不正确");
        }

        try {
            LocalDate ldEnd = LocalDate.parse(petEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepositEndTime(ldEnd);
        } catch (Exception e) {
            passed = false;
            errors.add("开始日期格式不正确");
        }
        psb.setNote(pNote);

        if (!passed) {
            session.setAttribute("pets", psb);
            String error = errors.stream().collect(Collectors.joining(","));
            session.setAttribute("error", error);
            resp.sendRedirect(req.getContextPath() + "/pet/petInsert");
            return;
        }

        boolean result = service.insertPet(psb);
        session = req.getSession();
        if (result) {
            session.setAttribute("sus", "添加成功");
            /*req.getRequestDispatcher("/pet/petInsert").forward(req,resp);*/
            resp.sendRedirect(req.getContextPath() + "/pet/petList");
        } else {
            session.setAttribute("pets", psb);
            session.setAttribute("error", "保存信息错误");
            resp.sendRedirect(req.getContextPath() + "/pet/petInsert");
        }
    }

    private void update() throws IOException {
        //获取前端的值
        String id = req.getParameter("id");
        String pName = req.getParameter("pName");
        String pSex = req.getParameter("pSex");
        String pAge = req.getParameter("pAge");
        String pOwnerId = req.getParameter("pOwnerId");
        String pType = req.getParameter("pType");
        String pBreed = req.getParameter("pBreed");
        String petBegTime = req.getParameter("pBegTime");
        String petEndTime = req.getParameter("pEndTime");
        String pNote = req.getParameter("pNote");

        PetSearchBean psb = new PetSearchBean();//查询条件

        HttpSession session = req.getSession();
        boolean passed = true;//是否通过校验
        List<String> errors = new ArrayList<>();

        if (!StringUtils.hasText(id)) {
            passed = false;
            errors.add("宠物编号不能为空");
        }
        psb.setPetId(id);

        if (!StringUtils.hasText(pName)) {
            passed = false;
            errors.add("名字不能为空");
        }
        psb.setPetName(pName);

        psb.setPetSex(pSex);
        psb.setPetAge(pAge);
        psb.setOwnerId(pOwnerId);
        if (!StringUtils.hasText(pType)) {
            passed = false;
            errors.add("宠物类别不能为空");
        }
        psb.setPetType(pType);

        psb.setPetBreed(pBreed);

        try {
            LocalDate ldBeg = LocalDate.parse(petBegTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepositBegTime(ldBeg);
        } catch (Exception e) {
            passed = false;
            errors.add("开始日期格式不正确");
        }

        try {
            LocalDate ldEnd = LocalDate.parse(petEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepositEndTime(ldEnd);
        } catch (Exception e) {
            passed = false;
            errors.add("开始日期格式不正确");
        }
        psb.setNote(pNote);

        if (!passed) {
            session.setAttribute("pets", psb);
            String error = errors.stream().collect(Collectors.joining(","));
            session.setAttribute("error", error);
            resp.sendRedirect(req.getContextPath() + "/pet/petUpdate");
            return;
        }

        boolean result = service.updatePet(psb);

        if (result) {
            String petJson = JSON.toJSONString(Map.of("suc", true));
            Servlets.renderJson(petJson, resp);
        } else {
            String petJson = JSON.toJSONString(Map.of("suc", false));
            Servlets.renderJson(petJson, resp);
        }
    }
    private void ExcelOut() throws IOException {
        //获取选中要导出的记录的id数组
        String[] strIds = req.getParameterValues("ids");
        Integer[] intIds = Servlets.strToIn(strIds);

        //获得记录的所有信息
        List<Pet> chosenPet = new ArrayList<>();
        for (int i = 0; i < intIds.length; i++) {
            Pet pet = service.findById(intIds[i]);
            chosenPet.add(pet);
        }

        resp.setContentType("application/vnd.ms-excel");
        resp.setCharacterEncoding("utf-8");

        try {
            LocalDateTime now = LocalDateTime.now();
            String format = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileName = URLEncoder.encode("客户信息列表导出" + format, "UTF-8").replaceAll("\\+", "%20");
            resp.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            //导出
            EasyExcel.write(resp.getOutputStream(), Customer.class).sheet("模板").doWrite(chosenPet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath()+"/pet/petSearch");
    }

    private void setPsb(String pName, String pSex, String pAge, String petOwner, String pType, String pBreed, String petBegTime, String petEndTime, String pNote, PetSearchBean psb) {
        if (StringUtils.hasText(pName)) {
            psb.setPetName(pName);
        }
        if (StringUtils.hasText(pSex)) {
            psb.setPetSex(pSex);
        }
        if (StringUtils.hasText(pAge)) {
            psb.setPetAge(pAge);
        }
        if (StringUtils.hasText(petOwner)) {
            psb.setOwnerId(petOwner);
        }
        if (StringUtils.hasText(pType)) {
            psb.setPetType(pType);
        }
        if (StringUtils.hasText(pBreed)) {
            psb.setPetBreed(pBreed);
        }
        if (StringUtils.hasText(petBegTime)) {
            LocalDate ld = LocalDate.parse(petBegTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepBegTimeFrom(ld);
        }
        if (StringUtils.hasText(petEndTime)) {
            LocalDate ld = LocalDate.parse(petEndTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            psb.setDepBegTimeTo(ld);
        }
        if (StringUtils.hasText(pNote)) {
            psb.setNote(pNote);
        }
    }
}
