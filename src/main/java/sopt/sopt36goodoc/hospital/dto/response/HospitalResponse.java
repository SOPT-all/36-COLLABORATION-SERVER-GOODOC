package sopt.sopt36goodoc.hospital.dto.response;

import lombok.Builder;
import sopt.sopt36goodoc.hospital.domain.Hospital;

import java.time.LocalDateTime;

@Builder
public record HospitalResponse (
        Long id,
        String name,
        Boolean isOpen,
        LocalDateTime closeAt,
        Double lat,
        Double lon,
        String address,
        String department,
        String image

) {
    public static HospitalResponse of(Hospital hospital, Boolean isOpen) {
        return HospitalResponse.builder()
                .id(hospital.getId())
                .name(hospital.getName())
                .isOpen(isOpen)
                .closeAt(hospital.getCloseAt())
                .lat(hospital.getLat())
                .lon(hospital.getLon())
                .address(hospital.getAddress())
                .department(hospital.getDepartment())
                .image(hospital.getImage())
                .build();
    }
}
