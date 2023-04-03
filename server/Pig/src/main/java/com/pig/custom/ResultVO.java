package com.pig.custom;

import lombok.Data;

import static com.pig.custom.Constant.RES_ERROR;
import static com.pig.custom.Constant.RES_SUCCESS;


/**
 * @author Blackke
 */
@Data
public class ResultVO<T> {
    private Integer status;
    private String message;
    private String errorMsg;
    private T data;
    private Long timestamp;

    /**
     * 获取当前时间戳
     */
    public ResultVO() {
        this.timestamp = System.currentTimeMillis();
    }

    public ResultVO(int status, String message, String errorMsg, T data) {
        this.status = status;
        this.message = message;
        this.errorMsg = errorMsg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 判断数据
     */
    public ResultVO(T data) {
        if (data == null) {
            this.status = RES_ERROR;
            this.message = "No data found";
        } else {
            this.status = RES_SUCCESS;
            this.message = "success";
            this.data = data;
        }
    }

    //
    public ResultVO(int code, String errorMsg) {
        this.status = code;
        this.errorMsg = errorMsg;
    }

    //
    public static <T> ResultVO<T> fail(int code, String message) {
        ResultVO<T> resultData = new ResultVO<>();
        resultData.setStatus(code);
        resultData.setErrorMsg(message);
        return resultData;
    }

    public static <T> ResultVO<T> fail(String message) {
        ResultVO<T> resultData = new ResultVO<>();
        resultData.setStatus(300);
        resultData.setErrorMsg(message);
        return resultData;
    }

    public static <T> ResultVO<T> success(T data) {
        if (data == null) {
            return ResultVO.fail(RES_ERROR, "No data found");
        }
        ResultVO<T> resultData = new ResultVO<>();
        resultData.setStatus(RES_SUCCESS);
        resultData.setMessage("Operation is successful");
        return resultData;
    }

    //
    public static <T> ResultVO<T> success(String message) {
        ResultVO<T> resultData = new ResultVO<>();
        resultData.setStatus(RES_SUCCESS);
        resultData.setMessage(message);
        return resultData;
    }

    //
    public static <T> ResultVO<T> success(String message, T data) {
        if (data == null) {
            return ResultVO.fail(RES_ERROR, "No data Found");
        }
        ResultVO<T> resultData = new ResultVO<>();
        resultData.setMessage(message);
        resultData.setData(data);
        return resultData;
    }

    //
    public static <T> ResultVO<T> error() {
        return new ResultVO<>(RES_ERROR, null, null, null);
    }

    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> resultVo = new ResultVO<>();
        resultVo.setStatus(RES_ERROR);
        resultVo.setMessage(message);
        return resultVo;
    }

}
