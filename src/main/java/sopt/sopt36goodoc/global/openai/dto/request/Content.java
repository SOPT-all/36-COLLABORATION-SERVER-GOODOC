package sopt.sopt36goodoc.global.openai.dto.request;

import static sopt.sopt36goodoc.global.openai.client.OpenAiClient.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Content(String type,
					  String text,
					  @JsonProperty(value = "image_url")
					  ImageUrl imageUrl) {

	@Builder
	public Content(String type, String text, String imageUrl) {
		this(type, text, imageUrl != null ? new ImageUrl(imageUrl) : null);
	}

	@Getter
	public static class ImageUrl{
		private String url;
		private String detail;

		public ImageUrl(String url) {
			this.url = url;
			this.detail = IMAGE_ANALYZE_LOW_DETAIL;
		}
	}

}
