package com.buaa.moocserver.vo.response;

import lombok.Data;

/**
 * @author Joey
 * @date 2022/11/8
 */
@Data
public class ResponseVO<T> {
    private Boolean status;
    private String message;
    private T data;
}
