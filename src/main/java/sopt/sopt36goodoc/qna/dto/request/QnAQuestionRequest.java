package sopt.sopt36goodoc.qna.dto.request;

import jakarta.validation.constraints.NotNull;

public record QnAQuestionRequest(@NotNull String question,
								 String detail) {
}
