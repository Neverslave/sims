package com.dao;

import com.bean.PageInfoBean;
import com.bean.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {

    Map<String, Object> userLogin(Map<String, Object> paramMap);

    int updateUser(User user);

    int hasUserByName(String username);

    List<Map<String, Object>> userListInfo(Map<String, Object> paramMap,
                                           PageInfoBean page);
    List<Map<String, Object>> userListInfo(Map<String, Object> paramMap);


}
