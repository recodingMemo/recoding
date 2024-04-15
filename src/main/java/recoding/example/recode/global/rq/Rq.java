package recoding.example.recode.global.rq;//package org.example.stockswiftservice.domain.global.rq;
//
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import lombok.ToString;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.annotation.RequestScope;
//
//import java.util.Optional;
//
//@Component
//@RequestScope
//@ToString
//public class Rq {
//    private final MemberService memberService;
//    private final HttpServletRequest req;
//    private final HttpServletResponse resp;
//    private final HttpSession session;
//    private final User user;
//    private Optional<Member> member = null;
//
//    public Rq(MemberService memberService, HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
//        this.memberService = memberService;
//        this.req = req;
//        this.resp = resp;
//        this.session = session;
//
//        // 현재 로그인한 회원의 인증정보를 가져옴
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        if (authentication.getPrincipal() instanceof User) {
//            this.user = (User) authentication.getPrincipal();
//        } else {
//            this.user = null;
//        }
//    }
//
//    private String getLoginedMemberUsername() {
//        if (isLogout()) return null;
//
//        return user.getUsername();
//    }
//
//    public boolean isLogin() {
//        return user != null;
//    }
//
//    public boolean isLogout() {
//
//        return !isLogin();
//    }
//
//    public Optional<Member> getMember() {
//        if (isLogout()) {
//            return null;
//        }
//        if (member == null) {
//            member = memberService.findByUsername(user.getUsername());
//        }
//        return member;
//    }
//
//
//    // 세션 관련 함수
//    public void setSession(String name, Object value) {
//        session.setAttribute(name, value);
//    }
//
//    private Object getSession(String name, Object defaultValue) {
//        Object value = session.getAttribute(name);
//
//        if (value == null) {
//            return defaultValue;
//        }
//
//        return value;
//    }
//
//    private long getSessionAsLong(String name, long defaultValue) {
//        Object value = getSession(name, null);
//
//        if (value == null) return defaultValue;
//
//        return (long) value;
//    }
//
//    public void removeSession(String name) {
//        session.removeAttribute(name);
//    }
//
//    // 쿠키 관련
//    public void setCookie(String name, String value) {
//        Cookie cookie = new Cookie(name, value);
//        cookie.setPath("/");
//        resp.addCookie(cookie);
//    }
//
//    private String getCookie(String name, String defaultValue) {
//        Cookie[] cookies = req.getCookies();
//
//        if (cookies == null) {
//            return defaultValue;
//        }
//
//        for (Cookie cookie : cookies) {
//            if (cookie.getName().equals(name)) {
//                return cookie.getValue();
//            }
//        }
//
//        return defaultValue;
//    }
//
//    private long getCookieAsLong(String name, int defaultValue) {
//        String value = getCookie(name, null);
//
//        if (value == null) {
//            return defaultValue;
//        }
//
//        return Long.parseLong(value);
//    }
//
//    public void removeCookie(String name) {
//        Cookie cookie = new Cookie(name, "");
//        cookie.setMaxAge(0);
//        cookie.setPath("/");
//        resp.addCookie(cookie);
//    }
//
//
//    public String getAllCookieValuesAsString() {
//        StringBuilder sb = new StringBuilder();
//
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                sb.append(cookie.getName()).append(": ").append(cookie.getValue()).append("\n");
//            }
//        }
//
//        return sb.toString();
//    }
//
//    public String getAllSessionValuesAsString() {
//        StringBuilder sb = new StringBuilder();
//
//        java.util.Enumeration<String> attributeNames = session.getAttributeNames();
//        while (attributeNames.hasMoreElements()) {
//            String attributeName = attributeNames.nextElement();
//            sb.append(attributeName).append(": ").append(session.getAttribute(attributeName)).append("\n");
//        }
//
//        return sb.toString();
//    }
//
////    public String historyBack(RsData rs) {
////        return historyBack(rs.getMsg());
////    }
//
////    public String historyBack(String msg) {
////        // http요청에서 referer 가져오기
////        String referer = req.getHeader("referer");
////        //referer기반 키 생성
////        String key = "historyBackFailMsg___" + referer;
////        //요청 객체 속성 설정
////        req.setAttribute("localStorageKeyAboutHistoryBackFailMsg", key);
////        req.setAttribute("historyBackFailMsg", Ut.url.withTtl(msg));
////        // 200 이 아니라 400 으로 응답코드가 지정되도록
////        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
////
////        return "common/js";
////    }
//
////    public String redirect(String url, RsData rs) {
////        return redirect(url, rs.getMsg());
////    }
//
////    public String redirect(String url, String msg) {
////        return "redirect:" + Ut.url.modifyQueryParam(url, "msg", Ut.url.encodeWithTtl(msg));
////    }
//
////    public String redirectOrBack(String url, RsData rs) {
////        if (rs.isFail()) return historyBack(rs);
////        return redirect(url, rs);
////    }
//
////    public String getProfileImgUrl() {
////        return this.memberService.getFindProfileImgUrl(getMember());
////    }
//}