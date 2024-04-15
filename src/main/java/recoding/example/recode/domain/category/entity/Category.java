package recoding.example.recode.domain.category.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.global.baseEntity.BaseEntity;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Category extends BaseEntity {

    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Member member;
}
