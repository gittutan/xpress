package com.wuyuncheng.xpress;

import com.wuyuncheng.xpress.model.dao.PostDAO;
import com.wuyuncheng.xpress.model.dto.PostDetailDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private PostDAO postDAO;

    @Test
    public void contextLoads() {
        List<PostDetailDTO> postDetailDTOList = postDAO.selectPostDetail();
        postDetailDTOList.forEach(item -> {
            System.err.println(item);
        });
    }

}
