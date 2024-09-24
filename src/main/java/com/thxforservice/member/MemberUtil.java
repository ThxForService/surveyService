package com.thxforservice.member;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.thxforservice.member.entities.Member;
import com.thxforservice.member.entities.Student;

@Component
@RequiredArgsConstructor
public class MemberUtil {

    public boolean isLogin() {
        return getMember() != null;
    }

    public boolean isStudent() {
        return isLogin() && getMember() instanceof Student;
    }

    public Member getMember() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof MemberInfo memberInfo) {

            return memberInfo.getMember();
        }

        return null;
    }
}