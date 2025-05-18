package sopt.sopt36goodoc.qna.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import sopt.sopt36goodoc.global.dto.ResponseDto;
import sopt.sopt36goodoc.qna.dto.request.QnAQuestionRequest;
import sopt.sopt36goodoc.qna.dto.response.AllQnAPreviewResponse;
import sopt.sopt36goodoc.qna.dto.response.QnADetailResponse;
import sopt.sopt36goodoc.qna.dto.response.QnAAnswerResponse;
import sopt.sopt36goodoc.qna.dto.response.QnAPreviewResponses;
import sopt.sopt36goodoc.qna.service.QnAService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnAController {

    private final QnAService qnAService;

    @PostMapping
    public ResponseDto<QnAAnswerResponse> postQuestion(
        @RequestPart("request") @Valid QnAQuestionRequest request,
        @RequestPart("files") @Size(max = 3) List<MultipartFile> multipartFiles
        ){
        return ResponseDto.created("답변 생성 성공", qnAService.postQuestion(request, multipartFiles));
    }

    @GetMapping
    public ResponseDto<QnAPreviewResponses> getQnAs(
            @RequestParam(name = "department", required = false) String department,
            @RequestParam(name = "size", defaultValue = "15") @Min(value = 0, message = "size 는 {value}이상이어야 합니다.") int size,
            @RequestParam(name = "page", defaultValue = "0") @Min(value = 0, message = "page 는 {value}이상이어야 합니다.") int page){
        return ResponseDto.success("QnA 목록 조회 성공", qnAService.getQnAPreviews(department, page, size));
    }

    @GetMapping("/{qna-id}")
    public ResponseDto<QnADetailResponse> getQnAs(
            @PathVariable("qna-id") Long qnAId){
        return ResponseDto.success("QnA 상세 조회 성공", qnAService.getQnA(qnAId));
    }

    @GetMapping("/all")
    public ResponseDto<AllQnAPreviewResponse> getAllQnA(){
        return ResponseDto.success("QnA 목록 조회 성공(전체)", qnAService.getAllQnAPreviews());
    }
}
