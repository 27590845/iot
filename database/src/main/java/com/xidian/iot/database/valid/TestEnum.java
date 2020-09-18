package com.xidian.iot.database.valid;

import lombok.*;

/**
 * @author mrl
 * @Title: TestEnum
 * @Package
 * @Description:
 * @date 2020/9/18 3:05 下午
 */
@AllArgsConstructor
@Getter
public enum TestEnum {

    ENUM1("key1", "val2"),
    ENUM2("key2", "val2");

    private String key;
    private String value;
}
