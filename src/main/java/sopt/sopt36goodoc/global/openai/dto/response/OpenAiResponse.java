package sopt.sopt36goodoc.global.openai.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OpenAiResponse {

	private List<Choice> choices;

	@Getter
	@NoArgsConstructor
	public static class Choice {
		private int index;
		private ResponseMessage message;
	}
}
