package sopt.sopt36goodoc.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sopt.sopt36goodoc.hospital.domain.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
