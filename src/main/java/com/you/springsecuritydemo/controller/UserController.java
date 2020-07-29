package com.you.springsecuritydemo.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName: UserController
 * @Description: 用户 controller
 * @author: D
 * @create: 2020-07-22 15:07
 **/

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("saveUser")

    @PreAuthorize("hasAnyAuthority('sys:user:manager','sys:user:query','sys:user:password')")
    public String saveUser(){
        //throw new RuntimeException("我错了");
        return "saveUser";
    }


    @PostMapping("delUser")
    @PreAuthorize("hasAuthority('sys:file:del')")
    public String deleteUser(){
        return "delUser";
    }







}
