package com.you.springsecuritydemo.service.impl;

import com.you.springsecuritydemo.domain.pojo.LoginUser;
import com.you.springsecuritydemo.domain.pojo.Permission;
import com.you.springsecuritydemo.domain.pojo.User;
import com.you.springsecuritydemo.mapper.PermissionMapper;
import com.you.springsecuritydemo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName: UserDetailServiceImpl
 * @Description: UserDetailService 的实现
 * @author: D
 * @create: 2020-06-24 15:51
 **/
@Service
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;




    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {





        User dbUser = null;
        if (s.length() > 5){
            dbUser = userMapper.selectByPhone(s);
        }else {
            dbUser = userMapper.selectByUsername(s);
        }

        log.info("dbUser:"+dbUser);

        LoginUser loginUserDto = new LoginUser();

        BeanUtils.copyProperties(dbUser,loginUserDto);
        log.info("loginUserDto:" + loginUserDto);
        List<Permission> dbPermissionList = permissionMapper.selectPermissionByUserId(dbUser.getId());
        log.info("dbPermissionList:" + dbPermissionList);

        loginUserDto.setPermissions(dbPermissionList);

        return loginUserDto;
    }
}
