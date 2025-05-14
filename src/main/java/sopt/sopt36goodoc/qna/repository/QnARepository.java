package sopt.sopt36goodoc.qna.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.sopt36goodoc.hospital.domain.enums.Department;
import sopt.sopt36goodoc.qna.entity.QnA;

@Repository
public interface QnARepository extends JpaRepository<QnA, Long> {
    Page<QnA> findAllByDepartment(Pageable pageable, Department department);
}
