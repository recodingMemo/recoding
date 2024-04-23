package recoding.example.recode.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.post.entity.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMember(Member member);

    List<Post> findByMemberAndCategory(Member member, Category category);

    Optional<Post> findByIdAndMember(Long id, Member member);

    List<Post> findByShared(boolean b);
}
