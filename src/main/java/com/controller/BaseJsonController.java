package com.controller;

import com.bean.BaseRespBean;
import com.dict.ErrorCodeEnum;
import com.exception.BizException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseJsonController {


    private static final Logger log = LoggerFactory
            .getLogger(BaseJsonController.class);

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    BaseRespBean exceptionHandler(Exception e) {
        log.error(e.getMessage(), e);

        BaseRespBean respBean = new BaseRespBean();

        if (e instanceof BizException) {
            respBean.setResult(((BizException) e).getErrorCodeEnum());
            if (StringUtils.isNotEmpty(((BizException) e).getErrorMsg())) {
                respBean.setResultNote(((BizException) e).getErrorMsg());
            }
        } else if (e instanceof HttpMessageNotReadableException) {
            respBean.setResult(ErrorCodeEnum.REQUEST_FORMAT_ERROR);
        } else {
            respBean.setResult(ErrorCodeEnum.FAILED);
        }

        return respBean;
    }

}
