package com.youlai.system.design.state;


import com.youlai.system.design.state.enums.ErrorCodeEnum;

/**
 * Created by lsd
 * 2021-05-11 11:34
 */
public class FsmException extends RuntimeException {
    public FsmException(ErrorCodeEnum errCode) {
    }
}
