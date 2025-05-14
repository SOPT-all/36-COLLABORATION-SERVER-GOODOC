package sopt.sopt36goodoc.qna.dto.response;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import sopt.sopt36goodoc.qna.entity.QnA;

import java.util.stream.Collectors;

public record QnAPreviewResponses(
        Page<QnAPreviewResponse> qnAPreviews) {
    public static QnAPreviewResponses from(Page<QnA> qnAS){
        return new QnAPreviewResponses(
                new PageImpl<>(
                        qnAS.stream()
                                .map(QnAPreviewResponse::from)
                                .collect(Collectors.toList()),
                        qnAS.getPageable(),
                        qnAS.getTotalElements()
                )
        );
    }
}
