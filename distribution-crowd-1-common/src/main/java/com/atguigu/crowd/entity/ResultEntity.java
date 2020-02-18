package com.atguigu.crowd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResultEntity<T> {
    private String result;
    private String message;
    private T data;

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String NO_MSG = "NO_MSG";
    public static final String NO_DATA = "NO_DATA";

    public static ResultEntity<String> successNoData() {
        return new ResultEntity<>(SUCCESS, NO_MSG, NO_DATA);
    }

    /**
     * 响应成功有数据
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResultEntity<T> successWithData(T data) {
        return new ResultEntity<>(SUCCESS, NO_MSG, data);
    }
    public static <T> ResultEntity<T> failed(String message){
        return new ResultEntity<>(FAILED,message,null);
    }
}
