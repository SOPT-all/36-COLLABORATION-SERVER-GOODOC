package sopt.sopt36goodoc.qna.dto.response;

import sopt.sopt36goodoc.qna.domain.QnA;

import java.util.List;

public record AllQnAPreviewResponse(List<QnAPreviewResponse> qnAPreviews) {
    public static AllQnAPreviewResponse from(List<QnA> qnAS){
        return new AllQnAPreviewResponse(qnAS.stream()
                .map(QnAPreviewResponse::from)
                .toList());
    }
}
