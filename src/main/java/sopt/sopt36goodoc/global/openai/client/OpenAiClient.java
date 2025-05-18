package sopt.sopt36goodoc.global.openai.client;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import sopt.sopt36goodoc.global.openai.dto.request.OpenAiRequest;
import sopt.sopt36goodoc.global.openai.dto.request.RequestMessage;
import sopt.sopt36goodoc.global.openai.dto.response.OpenAiResponse;

@Slf4j
@Component
public class OpenAiClient {

	public final static String USER_ROLE = "user";
	public final static String SYSTEM_ROLE = "system";
	public final static String REQUEST_URI = "/chat/completions";
	public final static String BEARER_TYPE = "Bearer ";
	public final static String IMAGE_ANALYZE_LOW_DETAIL = "low";

	private final WebClient webClient;
	@Value("${openai.model}")
	private String model;
	@Value("${openai.max-tokens}")
	private int maxTokens;
	@Value("${openai.temperature}")
	private double temperature;

	public OpenAiClient(WebClient.Builder webClientBuilder,
		@Value("${openai.secret-key}") String secretKey,
		@Value("${openai.base-url}") String baseUrl) {
		this.webClient = webClientBuilder
			.baseUrl(baseUrl)
			.defaultHeader(HttpHeaders.AUTHORIZATION, BEARER_TYPE + secretKey)
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}

	public String sendRequest(List<RequestMessage> messages) {
		OpenAiRequest request = new OpenAiRequest(model, messages, maxTokens, temperature);

		OpenAiResponse response = webClient.post()
			.uri(REQUEST_URI)
			.bodyValue(request)
			.retrieve()
			.bodyToMono(OpenAiResponse.class)
			.block();

		return parseContent(Objects.requireNonNull(response));
	}

	private String parseContent(OpenAiResponse response) {
		return response.getChoices().get(0).getMessage().getContent().trim();
	}

}
