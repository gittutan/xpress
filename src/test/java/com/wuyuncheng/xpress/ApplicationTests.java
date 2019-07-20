package com.wuyuncheng.xpress;

import com.wuyuncheng.xpress.model.dao.UserDAO;
import com.wuyuncheng.xpress.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserDAO userDAO;

    @Test
    public void contextLoads() {
    }

}
