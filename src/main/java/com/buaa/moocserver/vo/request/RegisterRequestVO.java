package com.buaa.moocserver.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Joey
 * @date 2022/11/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestVO {
    private String username;
    private String name;
    private String password;
}
