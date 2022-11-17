package com.buaa.moocserver.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Joey
 * @date 2022/11/9
 */
@Data
public class Course {
    private String cid;
    private String cname;
    private String prerequisites;
    private String about;
    private String cover;
    private Integer popular;
    private List<String> preCidList;
    private Integer itemId;

}
