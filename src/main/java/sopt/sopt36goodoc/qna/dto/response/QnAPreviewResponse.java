package sopt.sopt36goodoc.qna.dto.response;

import lombok.Builder;
import sopt.sopt36goodoc.global.util.TimeUtil;
import sopt.sopt36goodoc.qna.domain.QnA;

public record QnAPreviewResponse(
        Long id,
        String title,
        String summary,
        String department,
        String timeElapsed) {

    public static QnAPreviewResponse from(QnA qnA){
        return new QnAPreviewResponseBuilder()
                .id(qnA.getId())
                .title(qnA.getTitle())
                .summary(qnA.getSummary())
                .department(qnA.getDepartment().getKoreanName())
                .timeElapsed(TimeUtil.convertTimeElapsed(qnA.getCreatedAt()))
                .build();
    }

    @Builder
    public QnAPreviewResponse(Long id, String title, String summary, String department, String timeElapsed) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.department = department;
        this.timeElapsed = timeElapsed;
    }

}
