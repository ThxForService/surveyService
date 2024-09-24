package xyz.sangdam.member.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import xyz.sangdam.global.entities.BaseEntity;
import xyz.sangdam.member.constants.Gender;
import xyz.sangdam.member.constants.Status;
import xyz.sangdam.member.constants.UserType;

import java.time.LocalDate;
import java.util.UUID;

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