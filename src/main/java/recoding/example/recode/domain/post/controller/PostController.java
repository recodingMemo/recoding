package recoding.example.recode.domain.post.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.category.service.CategoryService;
import recoding.example.recode.domain.member.controller.MemberController;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.domain.post.entity.Post;
import recoding.example.recode.domain.post.service.PostService;
import recoding.example.recode.global.filter.JwtAuthorizationFilter;
import recoding.example.recode.global.jwt.JwtProvider;
import recoding.example.recode.global.rs.RsData;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static recoding.example.recode.global.filter.JwtAuthorizationFilter.extractAccessToken;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final MemberService memberService;
    private final JwtProvider jwtProvider;

    @AllArgsConstructor
    @Getter
    public static class PostsReponse {
        private final List<Post> posts;
    }

    @GetMapping(value = "/{member}/post")
    public RsData<?> getPosts(@PathVariable("member") String username, @RequestParam(value = "category", defaultValue = "total") String categoryName) {
        List<Post> posts;
        Member member = this.memberService.findByUsername(username).orElse(null);
        Category category = this.categoryService.findByName(categoryName);
        if (member != null) {
            if (categoryName.equals("total")) {
                posts = this.postService.findByMember(member);
            } else if (category != null) {
                posts = this.postService.findByMemberAndCategory(member, category);
            } else {
                posts = null;
            }
            Collections.reverse(Objects.requireNonNull(posts));
            return RsData.of("S-1", "게시글 목록 조회 성공", new PostsReponse(posts));
        } else {
            return RsData.of("E-1", "게시글 목록 조회 실패", null);
        }
    }

    @AllArgsConstructor
    @Getter
    public static class PostReponse {
        private final Post post;
    }

    @GetMapping(value = "/{member}/{id}")
    public RsData<?> getPost(@PathVariable("member") String username, @PathVariable("id") Long id) {
        Member member = this.memberService.findByUsername(username).orElse(null);
        if (member != null) {
            Post post = this.postService.findByIdAndMember(id, member);
            return RsData.of("S-2", "게시글 조회 성공", new PostReponse(post));
        } else {
            return RsData.of("E-2", "게시글 조회 실패", null);
        }
    }

    @GetMapping(value = "/list")
    public RsData<?> getPosts() {
        List<Post> posts = this.postService.findByShared(true);
        return RsData.of("S-3", "게시글 목록 조회 성공", new PostsReponse(posts));
    }

    @Data
    public static class PostRequest {
        private String title;
        private String content;
        private String category;
        private boolean shared;
    }

    @PostMapping(value = "/post")
    public RsData<?> post(@RequestBody PostRequest postRequest, HttpServletRequest request) {
        String token = extractAccessToken(request);
        Long userId = ((Integer) jwtProvider.getClaims(token).get("id")).longValue();
        Member loginMember = this.memberService.findbyId(userId).orElse(null);
        if (loginMember != null) {
            Category category = this.categoryService.findByMemberAndName(loginMember, postRequest.getCategory()).orElse(null);
            Post post = Post.builder()
                    .title(postRequest.getTitle())
                    .content(postRequest.getContent())
                    .category(category)
                    .shared(postRequest.isShared())
                    .member(loginMember).build();

            this.postService.save(post);
            return RsData.of("S-4", "게시글 등록 성공", null);
        } else {
            return RsData.of("E-4", "게시글 등록 실패", null);
        }
    }
}
