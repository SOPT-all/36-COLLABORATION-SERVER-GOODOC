package sopt.sopt36goodoc.qna.dto.response;

import lombok.Builder;
import sopt.sopt36goodoc.global.util.TimeUtil;
import sopt.sopt36goodoc.qna.domain.QnA;

public record QnADetailResponse(
        Long id,
        String question,
        String answer,
        String summary,
        String department,
        String timeElapsed) {
    public static QnADetailResponse from(QnA qnA){
        return QnADetailResponse.builder()
                .id(qnA.getId())
                .question(qnA.getQuestion())
                .answer(qnA.getAnswer())
                .summary(qnA.getSummary())
                .department(qnA.getDepartment().getKoreanName())
                .timeElapsed(TimeUtil.convertTimeElapsed(qnA.getCreatedAt()))
                .build();
    }

    @Builder
    public QnADetailResponse(Long id, String question, String answer, String summary, String department, String timeElapsed) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.summary = summary;
        this.department = department;
        this.timeElapsed = timeElapsed;
    }
}
