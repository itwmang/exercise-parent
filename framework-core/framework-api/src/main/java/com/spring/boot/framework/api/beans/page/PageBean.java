package com.spring.boot.framework.api.beans.page;

import com.spring.cloud.framework.utils.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: wangmian
 * @Date: 2018/8/30 23:21
 * @Description:
 */
@ApiModel(
        description = "分页数据Bean"
)
public class PageBean<T> implements Serializable {
    @ApiModelProperty("总记录数")
    private Long total = 0L;
    @ApiModelProperty("当前页码，默认1")
    private Integer currentPage = 1;
    @ApiModelProperty("每页多少条,默认10条")
    private Integer pageSize;
    @ApiModelProperty("列表数据")
    private List<T> list;

    public PageBean() {
        this.pageSize = CommonConstant.PAGE_NUM;
    }

    public PageBean(Integer currentPage, Integer pageSize) {
        this.pageSize = CommonConstant.PAGE_NUM;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        this.currentPage = null != this.currentPage && this.currentPage > 0 ? this.currentPage : 1;
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return this.list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
