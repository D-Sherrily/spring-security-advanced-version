package com.you.springsecuritydemo.domain.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: User
 * @Description: User实体类
 * @author: D
 * @create: 2020-06-24 9:27
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "User")
public class User  implements Serializable {
    private static final long serialVersionUID = 2646549708067524760L;
    @TableId(value = "id")
    @TableField(value = "id")
    private Integer id;

    @TableField(value = "姓名")
    private String username;

    @TableField(value = "密码")
    private String password;

    @TableField(value = "nickname")
    private String nickname;

    @TableField(value = "headimgurl")
    private String headimgurl;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "telephone")
    private String telephone;

    @TableField(value = "email")
    private String email;

    @TableField(value = "birthday")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @TableField(value = "sex")
    private Boolean sex;

    @TableField(value = "status")
    private Integer status;

    @TableField(value = "createtime")
    private Date createtime;

    @TableField(value = "updatetime")
    private Date updatetime;

    public interface Status {
        int DISABLED = 0;
        int VALID = 1;
        int LOCKED = 2;
    }
}