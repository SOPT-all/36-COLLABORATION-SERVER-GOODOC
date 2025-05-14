package sopt.sopt36goodoc.hospital.dto.response;

import lombok.Builder;
import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.hospital.domain.Hospital;

import java.time.LocalTime;

@Builder
public record HospitalResponse (
        Long id,
        String name,
        Boolean isOpen,
        LocalTime closeAt,
        Float distance,
        String address,
        Department department,
        String image

) {
    public static HospitalResponse of(Hospital hospital, Boolean isOpen) {
        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .isOpen(isOpen)
                .closeAt(hospital.getCloseAt())
                .distance(hospital.getDistance())
                .address(hospital.getAddress())
                .department(hospital.getDepartment())
                .image(hospital.getImage())
                .build();
    }
}
