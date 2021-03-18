package com.edward.system.viewobject;

import lombok.Data;

@Data
public class UserVo {
    private String username;
    private String nickname;
    private String email;
    private Integer sex;
    private Long departmentId;
}
