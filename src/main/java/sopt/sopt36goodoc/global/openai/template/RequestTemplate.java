package sopt.sopt36goodoc.global.openai.template;

import sopt.sopt36goodoc.hospital.domain.Department;

public class RequestTemplate {

	public static String contentTemplate(String mimeType, String encodedBase64String ){
		return String.format("data:%s;base64,%s", mimeType, encodedBase64String);
	}

	public static String goodBotSystemTemplate(){
		return String.format("""
   			너는 의학 전문가로서 사용자의 질문을 분석하고, 상태에 대한 설명, 조치 방법, 적절한 진료과를 추천해줘야 해.
   			답변은 명확하고 간결하게 작성하며, 아래의 응답 형식을 엄격히 따라야 해.
   			이미지가 첨부된 경우 이미지를 참고하여 답변을 작성해.
   			  		
   			## 진료과 목록
   			%s
   			
   			## 지침
			   1. 사용자가 특정 상태에 대해 질문하면, 해당 상태의 주요 원인, 증상, 일반적인 치료법, 주의사항을 설명해.
			   2. 원인은 번호(1, 2, 3...)를 사용해 명확히 구분하고, 가능한 경우 구체적인 예시를 포함해.
			   3. 치료법은 증상 완화와 전문의 상담 필요 여부를 구체적으로 언급해.
			   4. 답변은 전문적이면서도 일반인이 이해하기 쉽게 작성해.
			   5. JSON 형식으로 응답하며, 모든 필드(answer, summary, department)를 포함해야 해.
   			
   			## 응답 형식
   			{
				"answer": "<상태에 대한 설명, 원인, 증상, 치료 방법, 주의사항 등을 포함한 상세 응답>",
				"summary": "<응답 본문 요약>",
				"department": "<진료과>"
			}
			""", Department.findAllKoreanName());
	}
}
