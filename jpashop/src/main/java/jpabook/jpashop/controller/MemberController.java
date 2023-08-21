package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new") // get 방식으로 타있음 (열어보기)
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); //controller에서 view로 넘어갈때 이 데이터를 실어서 넘김 => memberForm이라는 빈 껍데기 Member객체를 가져간대 -> 가져가는 이유는 완전 아무것도 없긴 하지만 validatiobn
        return "members/createMemberForm"; // 이 뒤에 이 html을 만들어야함
    }

    public String create(@Valid MemberForm form) {

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member); // 저장해줌
        return "redirect:/";  // 대부분 뭔가를 저장하고 나면 다시 재로딩 되는 경우가 많으므로 Redirect로 HOMe에 연결
    }

}
