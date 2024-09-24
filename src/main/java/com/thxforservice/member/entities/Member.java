package com.thxforservice.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.thxforservice.global.entities.BaseEntity;
import com.thxforservice.member.constants.UserType;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Member extends BaseEntity{

    private Long seq; // 사용자 번호


    private String email; // 로그인 ID


    private String password; // 비밀번호



    private UserType userType; // 사용자 구분


    private String userName; // 성명

    private String gid;

    private String grade; // 학년

    private String stdntNo; // 학
}