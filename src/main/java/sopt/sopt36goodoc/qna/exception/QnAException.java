package sopt.sopt36goodoc.qna.exception;

import sopt.sopt36goodoc.global.exception.CustomException;
import sopt.sopt36goodoc.global.exception.ErrorCode;

public class QnAException extends CustomException {
    public QnAException(ErrorCode code) {
        super(code);
    }
}
