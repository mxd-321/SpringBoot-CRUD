package com.xawl.mxd.service.impl;

import com.xawl.mxd.bean.LoginUser;
import com.xawl.mxd.dao.LoginUserMapper;
import com.xawl.mxd.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginUserMapper loginByUsername;
    @Override
    public LoginUser selectByUsername(String username) {

        LoginUser loginUser = loginByUsername.loginByUsername(username);

        return loginUser;
    }
}
