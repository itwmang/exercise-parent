package com.spring.boot.framework.api.beans.page;

import com.spring.cloud.framework.utils.constant.CommonConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: wangmian
 * @Date: 2018/8/30 22:48
 * @Description:
 */

@ApiModel(description = "分页查询表单")
public class PageParams {
    @ApiModelProperty("当前页码，默认1")
    private Integer currentPage = 1;
    @ApiModelProperty("每页多少条，默认10条")
    private Integer pageSize;

    public PageParams() {
        this.pageSize = CommonConstant.PAGE_NUM;
    }

    public Integer getCurrentPage() {
        this.currentPage = null != this.currentPage && this.currentPage > 0 ? this.currentPage : 1;
        return this.currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return null != this.pageSize && this.pageSize >= 0 ? this.pageSize : CommonConstant.PAGE_NUM;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
