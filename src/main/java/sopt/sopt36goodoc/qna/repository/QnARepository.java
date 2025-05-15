package sopt.sopt36goodoc.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.qna.domain.QnA;

import java.util.List;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {
    Page<QnA> findAllByDepartment(Pageable pageable, Department department);
    List<QnA> findAllByOrderByIdDesc();
}
