package sopt.sopt36goodoc.hospital.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.sopt36goodoc.hospital.domain.Hospital;
import sopt.sopt36goodoc.hospital.dto.response.HospitalListResponse;
import sopt.sopt36goodoc.hospital.dto.response.HospitalResponse;
import sopt.sopt36goodoc.hospital.repository.HospitalRepository;

import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalListResponse getHospitals() {
        return HospitalListResponse.from(
                hospitalRepository.findAll()
                        .stream()
                        .map(hospital -> HospitalResponse.of(hospital, isOpen(hospital)))
                        .toList()
                );
    }

    private Boolean isOpen(Hospital hospital) {
        LocalTime now = LocalTime.now();
        LocalTime openAt = hospital.getOpenAt();
        LocalTime closeAt = hospital.getCloseAt();

        if (openAt == null || closeAt == null) {
            return null;
        }
        return now.isAfter(hospital.getOpenAt()) && now.isBefore(hospital.getCloseAt());
    }
}
