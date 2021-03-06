package com.lihaiyang.learn.core.base.entity;


import java.io.Serializable;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import lombok.Data;


@Data
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
       AUTO表示主键自增
    */
    @TableId(type = IdType.AUTO)
    protected Long id;
    protected String remarks;
    @TableLogic
    protected String delFlag;

    @TableField(exist = false)
    protected Map<String, String> sqlMap;

    @JsonIgnore
    public Map<String, String> getSqlMap() {
        if (this.sqlMap == null) {
            this.sqlMap = Maps.newHashMap();
        }
        return this.sqlMap;
    }




}

