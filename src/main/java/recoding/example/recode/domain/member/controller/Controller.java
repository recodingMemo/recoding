package recoding.example.recode.domain.member.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.global.jwt.JwtProvider;
import recoding.example.recode.global.rs.RsData;
import recoding.example.recode.global.tokenverify.TokenController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.ALL_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/member", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class Controller {
    private final MemberService memberService;
    private final JwtProvider jwtProvider;
    private final TokenController tokenController;

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
        String kakaoUserId = "KAKAO_" + kakaoLoginRequest.getId();

        Member searchKakaoUser = this.memberService.findByUsername(kakaoUserId).orElse(null);
        String password = "nwegjlaejzdnklkh!$@#!@123sdafsadf";

        String accessToken = null;
        String refreshToken = null;


        if (searchKakaoUser != null) {
            accessToken = memberService.genAccessToken(kakaoUserId, password);
            refreshToken = memberService.genRefreshToken(kakaoUserId, password);
        } else {
            Member kakaomember = memberService.whenSocialLogin(kakaoLoginRequest.getNickname(), password, kakaoUserId);
            accessToken = memberService.genAccessToken(kakaoUserId, password);
            refreshToken = memberService.genRefreshToken(kakaoUserId, password);
        }

        if (accessToken == null) {
            return RsData.of("Invalid username or password", null);
        }
        resp.addHeader("Authentication", accessToken);
        resp.addHeader("Authentication", refreshToken);

        return RsData.of("S-10", "토큰이 생성되었습니다.", new loginresponse(accessToken, refreshToken));
    }
}
