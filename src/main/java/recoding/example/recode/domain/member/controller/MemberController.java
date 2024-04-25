package recoding.example.recode.domain.member.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.category.service.CategoryService;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.global.jwt.JwtProvider;
import recoding.example.recode.global.rs.RsData;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static recoding.example.recode.global.filter.JwtAuthorizationFilter.extractAccessToken;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/{member}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final CategoryService categoryService;

    @AllArgsConstructor
    @Getter
    public static class MemberInfoReponse {
        private final String name;
        private final String selfIntroduction;
        private final List<Category> categories;
    }

    @GetMapping(value = "")
    public RsData<?> getMemberInfo(@PathVariable("member") String member, HttpServletRequest request) {

        String token = extractAccessToken(request); //헤더에 담긴 쿠키에서 토큰 요청
        Long userId = ((Integer) jwtProvider.getClaims(token).get("id")).longValue(); //유저의 아이디 값
        Member loginMember = this.memberService.findbyId(userId).orElse(null);
        if (loginMember != null && loginMember.getUsername().equals(member)) {
            String name = loginMember.getName();
            String selfIntroduction = loginMember.getSelfIntroduction();
            List<Category> categories = this.categoryService.findByMember(loginMember);

            return RsData.of("S-1", "유저 정보 조회 성공", new MemberInfoReponse(name, selfIntroduction, categories));
        } else {
            return RsData.of("E-1", "실패", null);
        }
    }

    @Data
    public static class SelfIntroductionRequest {
        @NotNull
        private String name;

    }

    @PutMapping(value = "")
    public RsData<?> modifyMemberInfo(@PathVariable("member") String member, HttpServletRequest request, SelfIntroductionRequest selfIntroductionRequest) {
        String token = extractAccessToken(request);
        Long userId = ((Integer) jwtProvider.getClaims(token).get("id")).longValue();
        Member loginMember = this.memberService.findbyId(userId).orElse(null);
        if (loginMember != null && loginMember.getUsername().equals(member)) {
            Member modifyMember = Member.builder()
                    .id(loginMember.getId())
                    .name(loginMember.getName())
                    .selfIntroduction(selfIntroductionRequest.getName())
                    .username(loginMember.getUsername())
                    .password(loginMember.getPassword())
                    .tokenLifeSpan(loginMember.getTokenLifeSpan())
                    .build();
            this.memberService.save(modifyMember);
            return RsData.of("S-2", "자기소개 수정 성공", null);
        }else {
            return RsData.of("E-2", "자기소개 수정 실패", null);
        }
    }
}
