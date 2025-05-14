package sopt.sopt36goodoc.hospital.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record HospitalListResponse(
        @JsonProperty(value = "hospitals")
        List<HospitalResponse> hospitalResponseList
) {
    public static HospitalListResponse from(List<HospitalResponse> hospitalResponseList) {
        return HospitalListResponse.builder()
                .hospitalResponseList(hospitalResponseList)
                .build();
    }
}
