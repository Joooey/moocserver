package com.buaa.moocserver.vo;

/**
 * @author Joey
 * @date 2022/11/8
 */
public enum Status {
    /**
     * 失败
     */
    FAIL(false),
    /**
     * 成功
     */
    SUCCESS(true);

    private boolean value;

    Status(boolean value) {
        this.value = value;
    }
    public boolean getValue() {
        return this.value;
    }

    }
