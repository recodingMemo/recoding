package recoding.example.recode.domain.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;
import recoding.example.recode.domain.category.entity.Category;
import recoding.example.recode.domain.category.service.CategoryService;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.global.jwt.JwtProvider;
import recoding.example.recode.global.rs.RsData;
//import recoding.example.recode.global.tokenverify.TokenController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static recoding.example.recode.global.filter.JwtAuthorizationFilter.extractAccessToken;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/{member}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final CategoryService categoryService;
//    private final TokenController tokenController;

    @AllArgsConstructor
    @Getter
    public static class MemberInfoReponse {
        private final String name;
        private final String selfIntroduction;
        private final List<Category> categories;
    }

    @GetMapping(value = "")
    public RsData<?> getMemberInfo(@PathVariable("member") String member) {

        Member searchMember = this.memberService.findByUsername(member).orElse(null);
        if (searchMember != null) {
            String name = searchMember.getName();
            String selfIntroduction = searchMember.getSelfIntroduction();
            List<Category> categories = this.categoryService.findByMember(searchMember);

            return RsData.of("S-1", "유저 정보 조회 성공", new MemberInfoReponse(name, selfIntroduction, categories));
        } else {
            return RsData.of("E-1", "실패", null);
        }
    }

    @Data
    public static class SelfIntroductionRequest {
        private String modifyIntroduction;

    }

    @PutMapping(value = "")
    public RsData<?> modifyMemberInfo(@PathVariable("member") String member, HttpServletRequest request, @RequestBody SelfIntroductionRequest selfIntroductionRequest) {
        String token = extractAccessToken(request);
        Long userId = ((Integer) jwtProvider.getClaims(token).get("id")).longValue();
        Member loginMember = this.memberService.findbyId(userId).orElse(null);
        if (loginMember != null && loginMember.getUsername().equals(member)) {
            Member modifyMember = Member.builder()
                    .id(loginMember.getId())
                    .name(loginMember.getName())
                    .selfIntroduction(selfIntroductionRequest.getModifyIntroduction())
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

    private void addTokenToCookie(HttpServletResponse response, String accessToken, String refreshToken) {
        // 액세스 토큰 쿠키
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setPath("/"); // 쿠키의 경로 설정
        accessTokenCookie.setMaxAge(24 * 60 * 60); // 쿠키의 만료 시간 설정 (1일)

        // 리프레시 토큰 쿠키
        Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(24 * 60 * 60);

        // HttpServletResponse에 쿠키 추가
        response.addCookie(accessTokenCookie);
        response.addCookie(refreshTokenCookie);
    }

    @Getter
    public static class loginresponse {

        private String accessToken;
        private String refreshToken;

        public loginresponse(String accessToken, String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Data
    public static class KakaoLoginRequest {
        @NotBlank
        private String id;
        @NotBlank
        private String nickname;
    }


    @PostMapping(value = "/kakaologin", consumes = APPLICATION_JSON_VALUE)
    public RsData<loginresponse> KakaoLogin(@Valid @RequestBody KakaoLoginRequest kakaoLoginRequest, HttpServletResponse resp) {
        String kakaoUserId = kakaoLoginRequest.getId(); // 카카오에서 받아오는 고유 식별자

        Member searchKakaoUser = this.memberService.findByUsername(kakaoUserId).orElse(null);

        String password = "nwegjlaejzdnklkh!$@#!@123sdafsadf";

        String accessTokenCookie = null;
        String refreshTokenCookie = null;

        if(searchKakaoUser == null) {
            Member kakaomember = memberService.join(kakaoLoginRequest.getNickname(), password, kakaoUserId);
            accessTokenCookie = memberService.genAccessToken(kakaoUserId);
            refreshTokenCookie = memberService.genRefreshToken(kakaoUserId);
        } else {
            accessTokenCookie = memberService.genAccessToken(kakaoUserId);
            refreshTokenCookie = memberService.genRefreshToken(kakaoUserId);
        }

        if (accessTokenCookie == null) {
            return RsData.of("Invalid username or password", null);
        }
        // 쿠키 생성 및 클라이언트로 전송
        addTokenToCookie(resp, accessTokenCookie, refreshTokenCookie);
        return RsData.of("S-10", "토큰이 생성되었습니다.", new loginresponse(accessTokenCookie, refreshTokenCookie));
    }
}
