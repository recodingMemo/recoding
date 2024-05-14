package recoding.example.recode.domain.post.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.post.entity.Post;
import recoding.example.recode.domain.post.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<Post> findByMember(Member member) {
        return this.postRepository.findByMember(member);
    }

    public List<Post> findByMemberAndCategory(Member member, Category category) {
        return this.postRepository.findByMemberAndCategory(member,category);
    }

    public Post findByIdAndMember(Long id, Member member) {
        return this.postRepository.findByIdAndMember(id,member).orElse(null);
    }

    public List<Post> findByShared(boolean b) {
        return this.postRepository.findByShared(b);
    }

    public void save(Post post) {
        this.postRepository.save(post);
    }
}
