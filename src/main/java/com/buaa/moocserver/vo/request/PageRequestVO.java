package com.buaa.moocserver.vo.request;

import lombok.Data;

/**
 * @author Joey
 * @date 2022/11/9
 */
@Data
public class PageRequestVO {
    /**
     * 每页条数
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer pageNum;
}
