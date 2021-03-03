//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.dts.qlnhanvien.base;


import com.dts.qlnhanvien.enums.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;



public class Result<T> implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(Result.class);
    private static final long serialVersionUID = 1232424234L;
    public static final String SUCCESSFUL_MESG = "OK";
    private Integer code;
    private Object other;
    private String msg;
    private T data;

    public Result() {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = null;
        this.data = null;
    }

    public Result(int code, String msg, T data) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = null;
        this.data = null;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, Object other, String msg, T data) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = null;
        this.data = null;
        this.code = code;
        this.other = other;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = null;
        this.data = null;
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum resultEnum) {
        this.code = ResultEnum.ERROR.getCode();
        this.msg = null;
        this.data = null;
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getDesc();
    }

    public static <T> Result<T> error(String msg) {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.ERROR.getCode(), msg);
        return new Result(ResultEnum.ERROR.getCode(), msg, (Object)null);
    }

    public static <T> Result<T> fail(String msg) {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.FAIL.getCode(), msg);
        return new Result(ResultEnum.FAIL.getCode(), msg, (Object)null);
    }

    public static <T> Result<T> paramInvalid() {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.PARAM_INVALID_ERROR.getCode(), ResultEnum.PARAM_INVALID_ERROR.getDesc());
        return new Result(ResultEnum.PARAM_INVALID_ERROR);
    }

    public static <T> Result<T> paramInvalid(String msg) {
        logger.debug("ERROR：code={}, msg={}", ResultEnum.PARAM_INVALID_ERROR.getCode(), msg);
        return new Result(ResultEnum.PARAM_INVALID_ERROR.getCode(), msg, (Object)null);
    }

    public static <T> Result<T> error(ResultEnum resultEnum) {
        logger.debug("ERROR：code={}, msg={}", resultEnum.getCode(), resultEnum.getDesc());
        return new Result(resultEnum.getCode(), resultEnum.getDesc(), (Object)null);
    }

    public static <T> Result<T> error(int code, String msg) {
        logger.debug("ERROR：code={}, msg={}", code, msg);
        return new Result(code, msg, (Object)null);
    }

    public static <T extends Serializable> Result<T> error(int code, String msg, T data) {
        logger.debug("ERROR：code={}, msg={}", code, msg);
        return new Result(code, msg, data);
    }

    public static <T extends Serializable> Result<T> success(T data) {
        return new Result(ResultEnum.SUCCESS.getCode(), "OK", data);
    }

    public static <T> Result<T> success(Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), "OK", data);
    }

    public static <T> Result<T> success(Object other, Object data) {
        return new Result(ResultEnum.SUCCESS.getCode(), other, "OK", data);
    }

    public static <T> Result<T> success() {
        return new Result(ResultEnum.SUCCESS.getCode(), "OK");
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getOther() {
        return this.other;
    }

    public void setOther(Object other) {
        this.other = other;
    }

    public String toString() {
        return "Result [code=" + this.code + ", msg=" + this.msg + ", data=" + this.data + "]";
    }
}
