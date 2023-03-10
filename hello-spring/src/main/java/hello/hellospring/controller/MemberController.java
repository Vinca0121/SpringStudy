package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.logging.Logger;


@Controller
@Slf4j
public class MemberController {

    private final MemberService memberservice;

    // DI
    @Autowired
    private MemberController(MemberService memberService){
        this.memberservice = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    // MemberFrom 데이터 클래스라고 생각하면 된다.
    public String create(MemberForm form){
        Member member = new Member();
        System.out.println(form.getName() + "<<form에 저장된 getName 값");
        log.info(form.getName(), "<<form에 저장된 getName 값");
        member.setName(form.getName());
        memberservice.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String getlist(Model model){
        List<Member> members = memberservice.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
