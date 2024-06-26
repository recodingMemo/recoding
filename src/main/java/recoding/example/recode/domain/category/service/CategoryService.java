package recoding.example.recode.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.category.repository.CategoryRepository;
import recoding.example.recode.domain.member.entity.Member;

import java.util.List;

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
}
