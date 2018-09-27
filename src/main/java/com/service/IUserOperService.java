package com.service;

import com.bean.BaseRespBean;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public interface IUserOperService {

    List<Map<String,Object>> getFXJLList();

    public BaseRespBean userLogin(Map<String, Object> paramMap);

}
