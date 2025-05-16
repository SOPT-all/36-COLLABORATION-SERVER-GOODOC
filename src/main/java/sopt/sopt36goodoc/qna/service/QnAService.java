package sopt.sopt36goodoc.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import sopt.sopt36goodoc.global.openai.client.OpenAiClient;
import sopt.sopt36goodoc.global.openai.dto.request.Content;
import sopt.sopt36goodoc.global.openai.dto.request.RequestMessage;
import sopt.sopt36goodoc.global.openai.template.RequestTemplate;
import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.qna.dto.request.QnAQuestionRequest;
import sopt.sopt36goodoc.qna.dto.response.AllQnAPreviewResponse;
import sopt.sopt36goodoc.qna.dto.response.QnADetailResponse;
import sopt.sopt36goodoc.qna.dto.response.QnAAnswerResponse;
import sopt.sopt36goodoc.qna.dto.response.QnAPreviewResponses;
import sopt.sopt36goodoc.qna.domain.QnA;
import sopt.sopt36goodoc.qna.exception.QnAException;
import sopt.sopt36goodoc.qna.repository.QnARepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.function.Supplier;

import static sopt.sopt36goodoc.global.exception.GlobalErrorCode.*;
import static sopt.sopt36goodoc.global.openai.client.OpenAiClient.*;
import static sopt.sopt36goodoc.qna.exception.QnAErrorCode.QNA_NOT_FOUND;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnARepository qnARepository;

    private final OpenAiClient openAiClient;
    private final ObjectMapper objectMapper;

    public final static String INPUT_IMAGE = "image_url";
    public final static String INPUT_TEXT = "text";

    public QnAAnswerResponse postQuestion(QnAQuestionRequest request, List<MultipartFile> files) {
        String response = openAiClient.sendRequest(List.of(getSystemMessage(), getUserMessage(request, files)));

        QnAAnswerResponse qnAAnswerResponse = convertToAnswerResponse(response);

        QnA qnA = QnA.builder()
            .question(request.question())
            .detail(request.detail())
            .answer(qnAAnswerResponse.answer())
            .summary(qnAAnswerResponse.summary())
            .department(Department.findByKoreanName(qnAAnswerResponse.department()))
            .build();
        qnARepository.save(qnA);

        return qnAAnswerResponse;
    }

    private RequestMessage getSystemMessage(){
        Content content = Content.builder()
            .type(INPUT_TEXT)
            .text(RequestTemplate.goodBotSystemTemplate())
            .build();

        return new RequestMessage(SYSTEM_ROLE, List.of(content));
    }

    private RequestMessage getUserMessage(QnAQuestionRequest request, List<MultipartFile> files){
        List<Content> contents = new ArrayList<>();
        contents.add(Content.builder()
            .type(INPUT_TEXT)
            .text(request.question() + request.detail())
            .build());

        files.forEach(file -> {
            String mimeType = file.getContentType();
            String encodedString = Base64.getEncoder().encodeToString(convertToByte(file));
            contents.add(Content.builder()
                .type(INPUT_IMAGE)
                .imageUrl(RequestTemplate.contentTemplate(mimeType, encodedString))
                .build());
        });
        return new RequestMessage(USER_ROLE, contents);
    }

    private QnAAnswerResponse convertToAnswerResponse(String content) {
        try {
            return objectMapper.readValue(content, QnAAnswerResponse.class);
        } catch (JsonMappingException e) {
            throw new QnAException(LLM_ERROR);
        } catch (JsonProcessingException e) {
            throw new QnAException(LLM_ERROR);
        }
    }

    private byte[] convertToByte(MultipartFile file){
        try {
            return file.getBytes();
        }catch (IOException e){
            throw new QnAException(QNA_NOT_FOUND);
        }
    }

    public QnAPreviewResponses getQnAPreviews(String departmentString, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        if(departmentString != null && !departmentString.isBlank()){
            Department department = Department.findByEnglishName(departmentString);
            Page<QnA> qnAs = getDepartmentQnAs(() -> qnARepository.findAllByDepartment(pageable, department), page);
            return QnAPreviewResponses.from(qnAs);
        }

        Page<QnA> qnAs = getDepartmentQnAs(() -> qnARepository.findAll(pageable), page);
        return QnAPreviewResponses.from(qnAs);
    }

    private Page<QnA> getDepartmentQnAs(Supplier<Page<QnA>> supplier, int page){
        Page<QnA> qnAs = supplier.get();
        checkValidPageRequest(page, qnAs.getTotalPages());
        return qnAs;
    }

    private void checkValidPageRequest(int page, int totalPage){
        if(page < 0 || page > totalPage){
            throw new QnAException(NOT_FOUND_PAGE);
        }
    }

    public QnADetailResponse getQnA(Long qnAId){
        QnA qnA = findById(qnAId);
        return QnADetailResponse.from(qnA);
    }

    public QnA findById(Long id){
        return qnARepository.findById(id).orElseThrow(()-> new QnAException(QNA_NOT_FOUND));
    }

    public AllQnAPreviewResponse getAllQnAPreviews(){
        List<QnA> qnAS = qnARepository.findAllByOrderByIdDesc(); // PK 생성 전략이 단조 증가이므로 createdAt을 대신하여 클래스터링 인덱스를 사용함.
        return AllQnAPreviewResponse.from(qnAS);
    }
}
