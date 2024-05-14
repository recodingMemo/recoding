package recoding.example.recode.domain.member.service;

import lombok.RequiredArgsConstructor;
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
}
