package com.controller;


import com.bean.BaseRespBean;
import com.bean.User;
import com.bean.UserLoginReqBean;
import com.bean.UserLoginRespBean;
import com.dict.ErrorCodeEnum;
import com.dict.SimsConsts;
import com.service.IUserOperService;
import com.util.BeanUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class LoginController {


    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    //登录相关操作服务类
    private IUserOperService userOperService;



    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = {"application/json"}, produces = {
            "application/json;charset=UTF-8"})
    public @ResponseBody
    BaseRespBean login(HttpServletRequest request, HttpSession httpSession,
                       @Valid @RequestBody UserLoginReqBean userLogin, BindingResult bindingResult) throws IOException {
        BaseRespBean respBean = new BaseRespBean();
        if (bindingResult.hasErrors()) {
            log.warn("bindingResult has error");
            respBean.setResult( ErrorCodeEnum.PARAM_VALID_ERROR);
            respBean.setResultErrorMap(bindingResult);
            return respBean;
        }

        // 从session中取校验码，校验是否正确或过期。
        String loginCodeSession = (String) httpSession.getAttribute( SimsConsts.SESSION_LOGIN_CODE);
        Date loginCodeExpireTime = (Date) httpSession.getAttribute(SimsConsts.SESSION_LOGIN_CODE_EXPIRE_TIME);
        log.debug("loginCodeSession " + loginCodeSession);
        log.debug("loginCodeExpireTime " + loginCodeExpireTime);
        if (StringUtils.isBlank(loginCodeSession)) {
            respBean.setResult(ErrorCodeEnum.LOGIN_CODE_EXPIRE);
            return respBean;
        } else {
            if (!userLogin.getCode().equalsIgnoreCase(loginCodeSession)) {
                respBean.setResult(ErrorCodeEnum.LOGIN_CODE_ERROR);
                return respBean;
            }
            if (loginCodeExpireTime == null
                    || (Calendar.getInstance().getTimeInMillis() - loginCodeExpireTime.getTime() > 60 * 1000)) {
                respBean.setResult(ErrorCodeEnum.LOGIN_CODE_EXPIRE);
                return respBean;
            }
        }

        // 从session中取用户，校验用户是否已登陆过
        String userNameSession = (String) httpSession.getAttribute(SimsConsts.SESSION_USERNAME);
        if (userLogin.getUsername().equals(userNameSession)) {
            // || ScfCacheDict.loginUserMap.get(userLogin.getUsername()) != null) {
            respBean.setResult(ErrorCodeEnum.USER_REPEAT_LOGIN);
            return respBean;
        }

        Map<String, Object> paramMap = BeanUtil.beanToMap(userLogin);
        respBean = this.userOperService.userLogin(paramMap);

        // session存放用户相关信息
        if (respBean.getResult() == ErrorCodeEnum.SUCCESS.getValue()) {
            setUserSession((UserLoginRespBean) respBean, httpSession);

            // 登陆成功，清除验证码
            httpSession.removeAttribute(SimsConsts.SESSION_LOGIN_CODE);
            httpSession.removeAttribute(SimsConsts.SESSION_LOGIN_CODE_EXPIRE_TIME);
        }

        return respBean;
    }


    /**
     * 设置session存放用户信息
     *
     * @param user
     * @param httpSession
     */
    private void setUserSession(User user, HttpSession httpSession) {
        httpSession.setAttribute(SimsConsts.SESSION_USER_ID, user.getId());
        httpSession.setAttribute(SimsConsts.SESSION_USERNAME, user.getUsername());
        httpSession.setAttribute(SimsConsts.SESSION_ROLE_ID, user.getStatus());
       // httpSession.setAttribute(SimsConsts.SESSION_ROLE_NAME,user.getRoleName());
      //  httpSession.setAttribute(SimsConsts.SESSION_ROLE_TYPE, respBean.getRoleType());
       // httpSession.setAttribute(SimsConsts.SESSION_CORP_ID, respBean.getCorpId());
      //  httpSession.setAttribute(SimsConsts.SESSION_DEPT_ID, respBean.getDeptId());
        //httpSession.setAttribute(SimsConsts.SESSION_REPRESENT_ID, respBean.getRepresentId());
       // httpSession.setAttribute(SimsConsts.SESSION_MENU_LIST, respBean.getMenuList());
    }




}
