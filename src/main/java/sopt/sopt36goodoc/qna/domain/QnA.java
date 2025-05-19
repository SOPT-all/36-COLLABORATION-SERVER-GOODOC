package sopt.sopt36goodoc.qna.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.sopt36goodoc.global.domain.BaseEntity;
import sopt.sopt36goodoc.hospital.domain.Department;
import sopt.sopt36goodoc.image.domain.Image;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QnA extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "qnA")
    private List<Image> images = new ArrayList<>();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String question;
    @Column(length = 500)
    private String detail;

    @Column(length = 1000)
    private String answer;
    @Column(length = 500)
    private String summary;

    @Enumerated(EnumType.STRING)
    private Department department;

    @Builder
    private QnA(List<Image> images, String title, String question, String detail, String answer, String summary,
        Department department) {
        this.images = images;
        this.title = title;
        this.question = question;
        this.detail = detail;
        this.answer = answer;
        this.summary = summary;
        this.department = department;
    }
}
