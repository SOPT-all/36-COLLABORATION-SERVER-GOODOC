package sopt.sopt36goodoc.global.openai.dto.request;

import java.util.List;

public record RequestMessage(String role,
							 List<Content> content) {

}
