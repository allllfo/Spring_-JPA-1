package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new") // get 방식으로 타있음 (열어보기)
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm()); //controller에서 view로 넘어갈때 이 데이터를 실어서 넘김 => memberForm이라는 빈 껍데기 Member객체를 가져간대 -> 가져가는 이유는 완전 아무것도 없긴 하지만 validatiobn
        return "members/createMemberForm"; // 이 뒤에 이 html을 만들어야함
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) {// 원래는 Valid하지 않으면 코드 자체가 실행이 안되는데, BindingResult 가 있으면 오류가 담겨서 실행이되게 된다.

        if(result.hasErrors()){
            return "members/creatememberForm"; // 타임리프랑 스프링이 integration이 강하게 되어 있어서 result를 끌고 와서 어떤 에러가 있는지 화면에 뿌려줌
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member); // 저장해줌
        return "redirect:/";  // 대부분 뭔가를 저장하고 나면 다시 재로딩 되는 경우가 많으므로 Redirect로 HOMe에 연결
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers(); // 실무에서는 더 복잡해진다면 폼객체나 DTO를 쓸 것을 권장
        model.addAttribute("members", members); // key가 "members"가 되고, 얘를 꺼내면 members 리스트가 꺼내짐
        return "members/memberList";

    }

}
