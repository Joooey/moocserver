package com.buaa.moocserver.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joey
 * @date 2022/11/9
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseVO {
    /**
     * 分页时总记录数
     */
    private long total = 0;

    /**
     * 分页时当前页数据
     */
    private List list = new ArrayList();
}

