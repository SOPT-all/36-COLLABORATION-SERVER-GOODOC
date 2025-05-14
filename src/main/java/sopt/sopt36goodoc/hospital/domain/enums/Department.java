package sopt.sopt36goodoc.hospital.domain.enums;

import lombok.Getter;

@Getter
public enum Department {
    PEDIATRICS("소아청소년과", "pediatrics"),
    DENTISTRY("치과", "dentistry"),
    INTERNAL_MEDICINE("내과", "internal-medicine"),
    OTOLARYNGOLOGY("이비인후과", "otolaryngology"),
    DERMATOLOGY("피부과", "dermatology"),
    OBSTETRICS_GYNECOLOGY("산부인과", "obstetrics-gynecology"),
    OPHTHALMOLOGY("안과", "ophthalmology"),
    PSYCHIATRY("정신의학과", "psychiatry"),
    PLASTIC_SURGERY("성형외과", "plastic-surgery"),
    ORTHOPEDICS("정형외과", "orthopedics"),
    ORIENTAL_MEDICINE("한의과", "oriental-medicine"),
    UROLOGY("비뇨기과", "urology"),
    FAMILY_MEDICINE("가정의학과", "family-medicine"),
    NEUROSURGERY("신경외과", "neurosurgery"),
    SURGERY("외과", "surgery"),
    THORACIC_SURGERY("흉부외과", "thoracic-surgery"),
    ANESTHESIOLOGY("마취통증의학과", "anesthesiology"),
    RADIOLOGY("영상의학과", "radiology"),
    NEUROLOGY("신경과", "neurology"),
    REHABILITATION_MEDICINE("재활의학과", "rehabilitation-medicine"),
    PREVENTIVE_MEDICINE("예방의학과", "preventive-medicine"),
    OCCUPATIONAL_MEDICINE("직업환경의학과", "occupational-medicine"),
    EMERGENCY_MEDICINE("응급의학과", "emergency-medicine"),
    NUCLEAR_MEDICINE("핵의학과", "nuclear-medicine"),
    TUBERCULOSIS("결핵과", "tuberculosis"),
    LABORATORY_MEDICINE("진단검사의학과", "laboratory-medicine"),
    PATHOLOGY("병리과", "pathology"),
    RADIATION_ONCOLOGY("방사선종양학과", "radiation-oncology");

    private final String koreanName;
    private final String englishName; // queryString 으로 전달할 경우 이 형태로 받음

    Department(String koreanName, String englishName) {
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    public static Department findByEnglishName(String englishName) {
        if (englishName == null) {
            return null;
        }
        String normalized = englishName.toLowerCase().trim();
        for (Department department : values()) {
            if (department.englishName.equals(normalized)) {
                return department;
            }
        }
        return null;
    }

    public static Department findByKoreanName(String koreanName) {
        if (koreanName == null) {
            return null;
        }
        for (Department department : values()) {
            if (department.koreanName.equals(koreanName)) {
                return department;
            }
        }
        return null;
    }
}

