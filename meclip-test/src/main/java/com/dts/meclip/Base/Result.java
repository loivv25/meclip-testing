package com.dts.meclip.Base;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dts.meclip.Enum.ResultEnum;

import java.io.Serializable;

/**
 * @author Rin
 */
public class Result<T> implements Serializable {

    private static final Logger logger = LoggerFactory.getLogger(Result.class);

    private static final long serialVersionUID = 1232424234L;

    public static final String SUCCESSFUL_MESG = "OK";

    private Integer code = ResultEnum.ERROR.getCode();

    private Object other;

    private String msg = null;

    private T data = null;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, Object other, String msg, T data) {
        this.code = code;
        this.other = other;
        this.msg = msg;
        this.data = data;
    }


    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getDesc();
    }

    public static <T> Result<T> error(String msg) {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.ERROR.getCode(), msg);
        return new Result<T>(ResultEnum.ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> fail(String msg) {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.FAIL.getCode(), msg);
        return new Result<T>(ResultEnum.FAIL.getCode(), msg, null);
    }


    public static <T> Result<T> paramInvalid() {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.PARAM_INVALID_ERROR.getCode(), ResultEnum.PARAM_INVALID_ERROR.getDesc());
        return new Result<T>(ResultEnum.PARAM_INVALID_ERROR);
    }

    public static <T> Result<T> paramInvalid(String msg) {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.PARAM_INVALID_ERROR.getCode(), msg);
        return new Result<T>(ResultEnum.PARAM_INVALID_ERROR.getCode(), msg, null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        logger.debug("ERROR：code={}, msg={}", resultEnum.getCode(), resultEnum.getDesc());
        return new Result<T>(resultEnum.getCode(), resultEnum.getDesc(), null);
    }

    public static <T> Result<T> error(int code, String msg) {
        logger.debug("ERROR：code={}, msg={}", code, msg);
        return new Result<T>(code, msg, null);
    }

    public static <T extends Serializable> Result<T> error(int code, String msg, T data) {
        logger.debug("ERROR：code={}, msg={}", code, msg);
        return new Result<T>(code, msg, data);
    }

    public static <T extends Serializable> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), SUCCESSFUL_MESG, data);
    }


    public static <T> Result<T> success(Object data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), SUCCESSFUL_MESG, (T) data);
    }

    public static <T> Result<T> success(Object other, Object data) {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), other, SUCCESSFUL_MESG, (T) data);
    }

    public static <T> Result<T> success() {
        return new Result<T>(ResultEnum.SUCCESS.getCode(), SUCCESSFUL_MESG);
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getOther() {
        return other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}


