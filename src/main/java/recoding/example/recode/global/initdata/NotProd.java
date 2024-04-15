//package recoding.example.recode.global.initdata;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import recoding.example.recode.domain.member.entity.Member;
//import recoding.example.recode.domain.member.repository.MemberRepository;
//
//import java.time.LocalDate;
//
//@Configuration
//@Profile({"dev", "test"})
//public class NotProd {
//    @Bean
//    CommandLineRunner initData(MemberRepository memberRepository, CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//
//            Company adminCompany = Company.builder()
//                    .name("SSS")
//                    .businessNumber("0000000000")
//                    .repName("김조은")
//                    .email("joeun065@gmail.com")
//                    .companyCode("AAAAAA")
//                    .address("대전광역시 서구 대덕대로 179")
//                    .detailAddress("9층 sbs컴퓨터아트학원 대전점")
//                    .isApproved(true)
//                    .build();
//            Member admin = Member.builder()
//                    .name("admin")
//                    .position("관리자")
//                    .username("admin")
//                    .password(passwordEncoder.encode("1234"))
//                    .birthday(LocalDate.ofEpochDay(2024-02-19))
//                    .authority(1)
//                    .tokenLifeSpan(4)
//                    .company(adminCompany)
//                    .build();
//
//            Company rep = Company.builder()
//                    .name("SSS")
//                    .businessNumber("0987655555")
//                    .repName("김조은")
//                    .email("oo@gmail.com")
//                    .companyCode("AOIDJS")
//                    .address("대전광역시 서구 대덕대로 179")
//                    .detailAddress("9층 sbs컴퓨터아트학원 대전점")
//                    .isApproved(true)
//                    .build();
//            Member member = Member.builder()
//                    .name("김조른")
//                    .position("관리자")
//                    .username("jo")
//                    .password(passwordEncoder.encode("qwe123!"))
//                    .birthday(LocalDate.ofEpochDay(2024-02-19))
//                    .authority(2)
//                    .tokenLifeSpan(4)
//                    .company(rep)
//                    .build();
//
//            if (companyRepository.findByCompanyCode(adminCompany.getCompanyCode()).isEmpty()){
//                companyRepository.save(adminCompany);
//                memberRepository.save(admin);
//                companyRepository.save(rep);
//                memberRepository.save(member);
//            }
//        };
//    }
//}
