package sopt.sopt36goodoc.image.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sopt.sopt36goodoc.global.domain.BaseEntity;
import sopt.sopt36goodoc.qna.entity.QnA;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id")
    private QnA qnA;

    private String url;

}
