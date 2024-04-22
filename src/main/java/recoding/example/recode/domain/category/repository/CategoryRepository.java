package recoding.example.recode.domain.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.member.entity.Member;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByMember(Member member);
}
