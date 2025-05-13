package sopt.sopt36goodoc.hospital.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import sopt.sopt36goodoc.global.domain.BaseEntity;

import java.time.LocalTime;

@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "hospital")
public class Hospital extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "open_at")
    private LocalTime openAt;

    @Column(name = "close_at")
    private LocalTime closeAt;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lon")
    private Double lon;

    @Column(name = "address")
    private String address;

    @Column(name = "department")
    private String department;

    @Column(name = "review")
    private Float review;

    @Column(name = "image")
    private String image;

    @Builder
    public Hospital(
            Long id,
            String name,
            LocalTime openAt,
            LocalTime closeAt,
            Double lat,
            Double lon,
            String address,
            String department,
            Float review,
            String image
    ) {
        this.id = id;
        this.name = name;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.lat = lat;
        this.lon = lon;
        this.address = address;
        this.department = department;
        this.review = review;
        this.image = image;
    }
}
