package com.service.impl;

import com.bean.BaseRespBean;
import com.bean.User;
import com.dao.IUserDao;
import com.dao.auto.MenuMapper;
import com.dao.auto.UserMapper;
import com.dict.ErrorCodeEnum;
import com.service.IUserOperService;
import com.util.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserOperServiceImpl implements IUserOperService {
   // private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Resource
    private IUserDao userDao;
    @Resource
    private UserMapper userMapper;
    @Override
    public List<Map<String, Object>> getFXJLList() {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public BaseRespBean userLogin(Map<String, Object> paramMap) {
        Map<String, Object> resultMap = userDao.userLogin(paramMap);

        // 根据用户名密码没有查询到用户
        if (resultMap == null) {
            BaseRespBean respBean = new BaseRespBean();
            respBean.setResult( ErrorCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR);
            return respBean;
        }

       User user = new User();
        BeanUtil.mapToBean(resultMap, user);

        // 根据角色Id，查询菜单列表
        List<Map<String, Object>> list = MenuMapper.menuListByRoleId(respBean.getRoleId());
        respBean.setMenuList(ScfBizUtil.menuListToTree(list));

        // 根据用户id查询角色列表
        List<SysRole> roleList = userDao.selectUserRoleList(respBean.getUserId());
        respBean.setRoleList(roleList);
        return respBean;
    }
}
