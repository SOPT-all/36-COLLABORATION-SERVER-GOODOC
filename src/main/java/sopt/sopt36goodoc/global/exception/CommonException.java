package sopt.sopt36goodoc.global.exception;

import lombok.Getter;

@Getter
public class CommonException extends RuntimeException{
    private final ErrorCode code;

    public CommonException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
}
