package com.mvp.world.mybatisplusdynamicsharding.service;

import com.mvp.world.mybatisplusdynamicsharding.model.po.UserPo;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    public void selectSomeTest() {
        System.out.println("From default DB:");
        List<UserPo> userPos = userService.selectFromDefaultDB();
        if (CollectionUtils.isNotEmpty(userPos)) {
            userPos.forEach(user -> System.out.println("user: " + user));
        } else  {
            System.out.println("userPos is empty");
        }

        System.out.println("From slave DB:");
        userPos = userService.selectFromCustomDB();
        if (CollectionUtils.isNotEmpty(userPos)) {
            userPos.forEach(user -> System.out.println("user: " + user));
        } else  {
            System.out.println("userPos is empty");
        }
    }

    @Test
    public void queryFromDBTest() {
        List<UserPo> userPos = userService.queryFromDefaultDB();
        System.out.println("From default DB:");
        if (CollectionUtils.isNotEmpty(userPos)) {
            userPos.forEach(user -> System.out.println("user: " + user));
        } else  {
            System.out.println("userPos is empty");
        }

        System.out.println("From slave DB:");
        userPos = userService.queryFromCustomDB();
        if (CollectionUtils.isNotEmpty(userPos)) {
            userPos.forEach(user -> System.out.println("user: " + user));
        } else  {
            System.out.println("userPos is empty");
        }
    }

    @Test
    public void insertData() {
        UserPo user = new UserPo();
        user.setUserName("赵五");
        user.setAge(28);
        user.setMobile("45678");
        user.setSchool("清华");
        user.setSex(1);
        user.setType(1);
        Integer result = userService.insertUserDefaultDB(user);
        System.out.println("Default DB, result=" + result);
        System.out.println("Default DB, user=" + user);

        user = new UserPo();
        user.setUserName("jim");
        user.setAge(27);
        user.setMobile("2345");
        user.setSchool("北大");
        user.setSex(1);
        user.setType(1);
        result = userService.insertUserCustomDB(user);
        System.out.println("Custom DB, result=" + result);
        System.out.println("Custom DB, user=" + user);
    }
}

