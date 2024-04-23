package recoding.example.recode.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public Optional<Member> findbyId(Long id) {
        return this.memberRepository.findById(id);
    }

    public void save(Member modifyMember) {
        this.memberRepository.save(modifyMember);
    }

    public Member findbyUsername(String username) {
        return this.memberRepository.findByUsername(username).orElse(null);
    }
}
