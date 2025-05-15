package sopt.sopt36goodoc.qna.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import sopt.sopt36goodoc.global.exception.ErrorCode;

@Getter
@AllArgsConstructor
public enum QnAErrorCode implements ErrorCode {

    QNA_NOT_FOUND(HttpStatus.NOT_FOUND, "QNA_001", "QnA 를 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String errorCode;
    private final String message;


}
