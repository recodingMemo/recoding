package recoding.example.recode.global.initdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import recoding.example.recode.domain.member.entity.Member;
import recoding.example.recode.domain.member.repository.MemberRepository;

import java.time.LocalDate;

@Configuration
@Profile({"dev", "test"})
public class NotProd {
    @Bean
    CommandLineRunner initData(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return args -> {


            Member admin = Member.builder()
                    .name("admin")
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .tokenLifeSpan(4)
                    .build();
            Member check = memberRepository.findByUsername(admin.getUsername()).orElse(null);
            if(check == null) {
                memberRepository.save(admin);
            }
        };
    }
}
