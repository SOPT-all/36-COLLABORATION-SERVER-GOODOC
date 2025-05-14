package sopt.sopt36goodoc.qna.dto.response;

import lombok.Builder;
import sopt.sopt36goodoc.global.util.TimeUtil;
import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.qna.entity.QnA;

public record QnAPreviewResponse(
        Long id,
        String question,
        String summary,
        Department department,
        String timeElapsed) {

    public static QnAPreviewResponse from(QnA qnA){
        return new QnAPreviewResponseBuilder()
                .id(qnA.getId())
                .question(qnA.getQuestion())
                .summary(qnA.getSummary())
                .department(qnA.getDepartment())
                .timeElapsed(TimeUtil.convertTimeElapsed(qnA.getCreatedAt()))
                .build();
    }

    @Builder
    public QnAPreviewResponse(Long id, String question, String summary, Department department, String timeElapsed) {
        this.id = id;
        this.question = question;
        this.summary = summary;
        this.department = department;
        this.timeElapsed = timeElapsed;
    }

}
