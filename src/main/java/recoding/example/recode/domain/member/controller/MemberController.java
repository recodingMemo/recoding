package recoding.example.recode.domain.member.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.bind.annotation.*;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.global.jwt.JwtProvider;
import recoding.example.recode.global.rs.RsData;
import recoding.example.recode.global.tokenverify.TokenController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class MemberController {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final TokenController tokenController;


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
