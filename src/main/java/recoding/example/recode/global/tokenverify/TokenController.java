package recoding.example.recode.global.tokenverify;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recoding.example.recode.global.jwt.JwtProvider;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class TokenController {

    private final JwtProvider jwtProvider;

    @PostMapping(value = "/verify-token", consumes = APPLICATION_JSON_VALUE)
    public String verifyToken(HttpServletRequest request) {
        String token = extractTokenFromHeader(request);
        String username = jwtProvider.getUsername(token);
        return username;
    }

    public String extractTokenFromHeader(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 다음의 문자열이 토큰이므로 잘라냄
        }
        return null; // 유효한 토큰이 없는 경우
    }
}