package com.ll.exam.app10.app.member.service;

import com.ll.exam.app10.app.member.Member;
import com.ll.exam.app10.app.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Value("${custom.genFileDirPath}")
    private String genFileDirPath;
    private final MemberRepository memberRepository;

    public void create(String id, String email, String pwd, MultipartFile img1) {
        Member member = Member.builder()
                .username(id)
                .email(email)
                .password(pwd)
                .build();
        try {
            img1.transferTo(new File(genFileDirPath + "/" + UUID.randomUUID().toString() +".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        memberRepository.save(member);
    }

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
