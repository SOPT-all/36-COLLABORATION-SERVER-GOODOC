package sopt.sopt36goodoc.qna.dto.response;

public record QnAAnswerResponse(String answer,
								String summary,
								String department){
	public static QnAAnswerResponse from(QnALlmAnswer qnALlmAnswer){
		return new QnAAnswerResponse(qnALlmAnswer.answer(), qnALlmAnswer.summary(), qnALlmAnswer.department());
	}
}
