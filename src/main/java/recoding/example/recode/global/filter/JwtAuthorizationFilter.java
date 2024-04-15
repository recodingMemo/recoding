package recoding.example.recode.global.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.service.MemberService;
import recoding.example.recode.global.jwt.JwtProvider;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final MemberService memberService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 Authorization 값을 가져온다.
        String token = extractAccessToken(request);

        if (token != null) {
//            String token = bearerToken.substring("Bearer ".length());

            if (jwtProvider.verify(token)) {
                Map<String, Object> claims = jwtProvider.getClaims(token);
                String username = (String) claims.get("username");
                Long userId = ((Integer) claims.get("id")).longValue();
                Member member = memberService.findbyId(userId).orElseThrow(() -> new UsernameNotFoundException(username));
                forceAuthentication(member);
            }
        }

        filterChain.doFilter(request, response);
    }

    // 강제로 로그인 처리하는 메소드
    private void forceAuthentication(Member member) {
        User user = new User(member.getUsername(), member.getPassword(), member.getAuthorities());

        // 스프링 시큐리티 객체에 저장할 authentication 객체를 생성
        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        user,
                        null,
                        member.getAuthorities()
                );

        // 스프링 시큐리티 내에 우리가 만든 authentication 객체를 저장할 context 생성
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        // context에 authentication 객체를 저장
        context.setAuthentication(authentication);
        // 스프링 시큐리티에 context를 등록
        SecurityContextHolder.setContext(context);
    }

    public static String extractAccessToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("access_token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
