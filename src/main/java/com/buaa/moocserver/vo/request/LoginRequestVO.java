package com.buaa.moocserver.vo.request;

import lombok.Data;

/**
 * @author Joey
 * @date 2022/11/8
 */
@Data
public class LoginRequestVO {
    private String username;
    private String password;
}
