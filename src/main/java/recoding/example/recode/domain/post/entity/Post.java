package recoding.example.recode.domain.post.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.global.baseEntity.BaseEntity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Post extends BaseEntity {

    private String title;

    private String content;

    private boolean shared;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;

    @ManyToOne
    private Category category;
}
