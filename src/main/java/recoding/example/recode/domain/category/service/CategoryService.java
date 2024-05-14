package recoding.example.recode.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.category.repository.CategoryRepository;
import recoding.example.recode.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findByMember(Member member) {
        return this.categoryRepository.findByMember(member);
    }

    public void save(Category category) {
        this.categoryRepository.save(category);
    }

    public Category findByName(String categoryName) {
        return this.categoryRepository.findByName(categoryName).orElse(null);
    }

    public Optional<Category> findByMemberAndName(Member loginMember, String category) {
        return this.categoryRepository.findByMemberAndName(loginMember,category);
    }
}
