package com.dict;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class ErrorCode {

    private static final Logger log = LoggerFactory.getLogger(ErrorCode.class);

    private static Properties prop = new Properties();

    // 加载错误码properties文件，便于支持国际化
    static {
        InputStream in = ErrorCode.class.getClassLoader().getResourceAsStream(
                "simsErrorMessages.properties");

        try {
            prop.load(in);
        } catch (Exception e) {
            log.error("simsErrorMessages.properties load exception", e);
        }
    }

    public static String errorMsg(ErrorCodeEnum ece) {
        if (!prop.containsKey(ece.name())) {
            log.error("simsErrorMessages.properties not contains key "
                    + ece.name());
            return prop.getProperty(ErrorCodeEnum.UNKNOWN_ERROR.name()).trim();
        }

        String errorMsg = prop.getProperty(ece.name()).trim();
        if (StringUtils.isBlank(errorMsg)) {
            log.error("simsErrorMessages.properties key " + ece.name()
                    + "'s value is blank");
            return prop.getProperty(ErrorCodeEnum.UNKNOWN_ERROR.name()).trim();
        }

        return errorMsg;
    }

}
