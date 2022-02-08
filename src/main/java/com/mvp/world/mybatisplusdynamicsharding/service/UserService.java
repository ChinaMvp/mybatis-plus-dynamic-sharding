package com.mvp.world.mybatisplusdynamicsharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mvp.world.mybatisplusdynamicsharding.model.po.UserPo;

import java.util.List;

public interface UserService extends IService<UserPo> {

    UserPo selectDefaultDaoById(Long id);

    UserPo selectDefaultById(Long id);

    List<UserPo> selectFromDefaultDB();

    List<UserPo> selectFromCustomDB();

    List<UserPo> queryFromDefaultDB();

    List<UserPo> queryFromCustomDB();

    Integer insertUserDefaultDB(UserPo user);

    Integer insertUserCustomDB(UserPo user);

}
