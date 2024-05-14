package recoding.example.recode.domain.category.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.category.service.CategoryService;
import recoding.example.recode.domain.member.controller.MemberController;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.global.filter.JwtAuthorizationFilter;
import recoding.example.recode.global.jwt.JwtProvider;
import recoding.example.recode.global.rs.RsData;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static recoding.example.recode.global.filter.JwtAuthorizationFilter.extractAccessToken;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/category", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class CategoryController {
    private final CategoryService categoryService;
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @Data
    public static class CategoryRequest {
        @NotNull
        private String category;

    }

    @PostMapping(value = "")
    public RsData<?> save(@RequestBody CategoryRequest categoryRequest, HttpServletRequest request) {

        String token = extractAccessToken(request); //헤더에 담긴 쿠키에서 토큰 요청
        Long userId = ((Integer) jwtProvider.getClaims(token).get("id")).longValue(); //유저의 아이디 값
        Member loginMember = this.memberService.findbyId(userId).orElse(null);
        if (loginMember != null) {
            Category category = Category.builder()
                    .name(categoryRequest.getCategory())
                    .member(loginMember)
                    .build();
            this.categoryService.save(category);
            return RsData.of("S-1", "카테고리 저장 성공", null);
        }else {
            return RsData.of("E-1", "카테고리 저장 실패", null);
        }
    }

    @AllArgsConstructor
    @Getter
    public static class MemberCategoryReponse {
        private final List<Category> categories;
    }

    @GetMapping(value = "")
    public RsData<?> findAll(HttpServletRequest request) {
        String token = extractAccessToken(request);
        Long userId = ((Integer) jwtProvider.getClaims(token).get("id")).longValue();
        Member loginMember = this.memberService.findbyId(userId).orElse(null);
        if (loginMember != null) {
            List<Category> categories = this.categoryService.findByMember(loginMember);
            return RsData.of("S-2", "카테고리 목록 불러오기 성공", new MemberCategoryReponse(categories));
        }else {
            return RsData.of("E-1", "카테고리 목록 불러오기 실패", null);
        }
    }


}
