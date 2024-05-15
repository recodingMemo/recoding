package recoding.example.recode.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.repository.MemberRepository;
import recoding.example.recode.global.jwt.JwtProvider;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;


    public Optional<Member> findbyId(Long id) {
        return this.memberRepository.findById(id);
    }

    public void save(Member modifyMember) {
        this.memberRepository.save(modifyMember);
    }

    public Optional<Member> findByUsername(String username) {
        return this.memberRepository.findByUsername(username);
    }


    public String genAccessToken(String username) {
        Member member = findByUsername(username).orElse(null);

        if (member == null) return null;


        return jwtProvider.genToken(member.toClaims(), 60 * 60 * member.getTokenLifeSpan());
    }

    public String genRefreshToken(String username) {
        Member member = findByUsername(username).orElse(null);

        if (member == null) return null;

        return jwtProvider.genToken(member.toClaims(), 60 * 60 * 24 * 365);
    }
    public String genNewAccessToken(String username) {
        Member member = findByKakaoId(username).orElse(null);
        if (member == null) return null;
        return jwtProvider.genToken(member.toClaims(), 60 * 5);
    }
    public Optional<Member> findByKakaoId(String kakaoUserId) {
        return memberRepository.findByUsername(kakaoUserId);
    }
    public Member join(String name, String password, String username) {
        Member member = Member.builder()
                .name(name)
                .password(passwordEncoder.encode(password))
                .tokenLifeSpan(10)
                .username(username)
                .build();

        memberRepository.save(member);
        return member;
    }
    //소셜 로그인
    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String name, String username) {
        Optional<Member> os = this.memberRepository.findByUsername(username);
        if (os.isPresent()) return os.get();

        return join(null, "", null); // 최초 로그인 시 딱 한번 실행
    }
}

