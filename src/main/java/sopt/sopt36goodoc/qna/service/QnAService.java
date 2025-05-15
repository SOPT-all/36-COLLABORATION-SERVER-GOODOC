package sopt.sopt36goodoc.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.qna.dto.response.AllQnAPreviewResponse;
import sopt.sopt36goodoc.qna.dto.response.QnADetailResponse;
import sopt.sopt36goodoc.qna.dto.response.QnAPreviewResponses;
import sopt.sopt36goodoc.qna.domain.QnA;
import sopt.sopt36goodoc.qna.exception.QnAException;
import sopt.sopt36goodoc.qna.repository.QnARepository;

import java.util.List;
import java.util.function.Supplier;

import static sopt.sopt36goodoc.global.exception.GlobalErrorCode.*;
import static sopt.sopt36goodoc.qna.exception.QnAErrorCode.QNA_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnARepository qnARepository;

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
