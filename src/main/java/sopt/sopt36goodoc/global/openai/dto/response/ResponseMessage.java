package sopt.sopt36goodoc.global.openai.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
	private String role;
	private String content;
}

