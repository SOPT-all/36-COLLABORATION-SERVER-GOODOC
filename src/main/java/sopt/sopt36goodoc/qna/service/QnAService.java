package sopt.sopt36goodoc.qna.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.qna.dto.response.QnADetailResponse;
import sopt.sopt36goodoc.qna.dto.response.QnAPreviewResponses;
import sopt.sopt36goodoc.qna.entity.QnA;
import sopt.sopt36goodoc.qna.exception.QnAException;
import sopt.sopt36goodoc.qna.repository.QnARepository;

import static sopt.sopt36goodoc.qna.exception.QnAErrorCode.QNA_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class QnAService {
    private final QnARepository qnARepository;

    public QnAPreviewResponses getQnAPreviews(String departmentString, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        if(departmentString != null && !departmentString.isBlank()){
            Department department = Department.findByEnglishName(departmentString);
            Page<QnA> qnAS = qnARepository.findAllByDepartment(pageable, department);
            return QnAPreviewResponses.from(qnAS);
        }

        Page<QnA> qnAS = qnARepository.findAll(pageable);
        return QnAPreviewResponses.from(qnAS);
    }

    public QnADetailResponse getQnA(Long qnAId){
        QnA qnA = findById(qnAId);
        return QnADetailResponse.from(qnA);
    }

    public QnA findById(Long id){
        return qnARepository.findById(id).orElseThrow(()-> new QnAException(QNA_NOT_FOUND));
    }
}
