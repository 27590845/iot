package com.xidian.iot.dataapi.controller.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

/**
 * 分页器
 *
 * @author: Hansey
 * @date: 2020-09-21 16:35
 */
@Data
public class Page<T> {
    /**
     * 当前页索引,默认是1.
     */
    private int page = 1;
    /**
     * 每页的记录容量,默认是10.
     */
    private int limit = 5;
    /**
     * 总记录数，默认是0
     */
    private int total = 0;
    /**
     * 总页数
     */
    private int pageCount;
    /**
     * 分页数据
     */
    private List<T> data;


    /**
     * 构造一个分页器。
     *
     * @param total 总条数
     * @param page  起始索引
     */
    public Page(int total, int page, int limit) {
        this.limit = limit;
        setTotal(total);
        setPage(page);
    }

    /**
     * 设置总记录数
     */
    public void setTotal(int total) {
        this.total = total;
        if (total % limit == 0) {
            pageCount = total / limit;
        } else {
            pageCount = total / limit + 1;
        }
    }

    /**
     * 设置分页器当前页。
     *
     * @param page 分页器当前索引页。
     */
    public void setPage(int page) {
        if (page > pageCount) {
            this.page = pageCount;
        } else {
            this.page = page;
        }
    }

    /**
     * 返回跳过的页数
     *
     * @return
     */
    @JsonIgnore
    public int getSkipResults() {
        return (page-1) * limit;
    }
}
