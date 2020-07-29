package com.you.springsecuritydemo.domain.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.you.springsecuritydemo.domain.pojo.Permission;
import com.you.springsecuritydemo.domain.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName: UserDto
 * @Description: 用户登录的实体类
 * @author: D
 * @create: 2020-06-24 09:43
 **/
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser extends User implements UserDetails{
    private static final long serialVersionUID = -5911645384918125716L;

    private Integer id;

    private String username;

    private String password;

    private String phone;

    private List<Permission> permissions;

    private String token;
    /**登陆时间戳（毫秒）**/
    private Long loginTime;

    /** 过期时间戳 **/
    private Long expireTime;



    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> collect = permissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPermission()))
                .map(p -> new SimpleGrantedAuthority(p.getPermission())).collect(Collectors.toSet());
        log.info("collect:"+collect);
        return collect;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        //return getStatus() != Status.LOCKED;
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
