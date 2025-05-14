package sopt.sopt36goodoc.hospital.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.sopt36goodoc.global.dto.ResponseDto;
import sopt.sopt36goodoc.hospital.dto.response.HospitalListResponse;
import sopt.sopt36goodoc.hospital.service.HospitalService;

@RestController
@RequiredArgsConstructor
public class HospitalController {
    private final HospitalService hospitalService;

    @GetMapping("/hospitals")
    public ResponseDto<HospitalListResponse> getHospitals() {
        return ResponseDto.success("병원 목록 조회에 성공했습니다.", hospitalService.getHospitals());
    }
}
