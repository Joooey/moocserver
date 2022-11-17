package com.buaa.moocserver.utils;

import com.buaa.moocserver.vo.Status;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.logging.Level;


/**
 * @author Joey
 * @date 2022/11/8
 */
public class ResponseBuilder {
    public static <T> T buildSuccessResponse(Object data, Class<T> cls) {
        try {
            T object = cls.newInstance();
            //设置返回的status
            Field fieldStatus = object.getClass().getDeclaredField("status");
            Method method = cls.getMethod("setStatus", fieldStatus.getType());
            method.invoke(object, Status.SUCCESS.getValue());
            //设置返回的data，有就设置，无就不设置
            Field fieldData = null;
            try {
                fieldData = object.getClass().getDeclaredField("data");
            } catch (NoSuchFieldException e) {
                LogUtils.logger.log(Level.INFO, "buildRightResponse,class {} not have data field");
            }
            if (Objects.nonNull(fieldData)) {
                method = cls.getMethod("setData", fieldData.getType());
                method.invoke(object, data);
            }
            return object;
        } catch (Exception e) {
            LogUtils.logger.log(Level.INFO, "ResponseBuilder-buildErrorResponse exception:");
            return null;
        }
    }

    public static <T> T buildErrorResponse(Object error, Class<T> cls) {
        try {
            T object = cls.newInstance();
            //设置返回的status
            Field fieldStatus = object.getClass().getDeclaredField("status");
            Method method = cls.getMethod("setStatus", fieldStatus.getType());
            method.invoke(object, Status.FAIL.getValue());

            Field fieldError = object.getClass().getDeclaredField("message");
            method = cls.getMethod("setMessage", fieldError.getType());
            if (error instanceof Exception) {
                Exception exception = (Exception) error;
                method.invoke(object, exception.getMessage());
            }
            if (error instanceof String) {
                method.invoke(object, error);
            }
            return object;
        } catch (Exception e) {
            LogUtils.logger.log(Level.INFO, "ResponseBuilder-buildErrorResponse exception:"+e);
            return null;
        }
    }
}
