package com.xawl.mxd.service;

import com.xawl.mxd.bean.LoginUser;

public interface LoginService {
    LoginUser selectByUsername(String username);
}
