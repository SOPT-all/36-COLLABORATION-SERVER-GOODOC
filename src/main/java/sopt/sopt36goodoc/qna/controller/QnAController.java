package sopt.sopt36goodoc.qna.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sopt.sopt36goodoc.global.dto.ResponseDto;
import sopt.sopt36goodoc.qna.dto.response.QnAPreviewResponses;
import sopt.sopt36goodoc.qna.service.QnAService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnAController {

    private final QnAService qnAService;

    @GetMapping
    public ResponseDto<QnAPreviewResponses> getQnAs(
            @RequestParam(name = "department", required = false) String department,
            @RequestParam(name = "size", defaultValue = "15") int size,
            @RequestParam(name = "page", defaultValue = "0") int page){
        return ResponseDto.success("QnA 목록 조회 성공", qnAService.getQnAPreviews(department, page, size));
    }
}
