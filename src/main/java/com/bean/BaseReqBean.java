package com.bean;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseReqBean {
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
