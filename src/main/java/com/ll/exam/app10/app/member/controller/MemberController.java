package com.ll.exam.app10.app.member.controller;

import com.ll.exam.app10.app.member.Member;
import com.ll.exam.app10.app.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/member")
@RequiredArgsConstructor
@Controller
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/join")
    public String join(){
        return "/home/sign_up";
    }
    @PostMapping("/join")
    public String join(String id, String email, String pwd, @RequestParam("img1") MultipartFile img1, HttpServletRequest req){
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));

        SecurityContext sc = SecurityContextHolder.getContext();
        //아이디, 패스워드, 권한을 설정합니다. 아이디는 Object단위로 넣어도 무방하며
        //패스워드는 null로 하여도 값이 생성됩니다.
        sc.setAuthentication(new UsernamePasswordAuthenticationToken(id, null, list));
        HttpSession session = req.getSession(true);

        //위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정해줍니다.
        session.setAttribute(HttpSessionSecurityContextRepository.
                SPRING_SECURITY_CONTEXT_KEY, sc);
        memberService.create(id,email,pwd,img1);
        return "redirect:/";

    }
    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        Member member = memberService.findByUsername(principal.getName());
        model.addAttribute("member",member);
        return "/home/profile";
    }
}
