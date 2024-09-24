package com.thxforservice.member.entities;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Table(name="STDNT_INFO")
public class Student extends Member {
    @Column(length=10)
    private String grade; // 학년

    @Column(length=10)
    private String stdntNo; // 학번
}
