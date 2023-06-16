package com.example.finalProjectPM.controller;

import com.example.finalProjectPM.dto.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController // JSON 객체 타입으로 정보를 반환
@RequestMapping("api")
public class TestController {
    @RequestMapping("/hello")
    public String getHello() {
        return "Hello, Spring Boot";
    }
    @GetMapping("/variable1/{variable}")
    // URL 주소에서 변수를 받을 때 사용
    public String getVariable(@PathVariable("variable") String var) {
        return var;
    }
    // @RequestParam : URL 에서 '?' 를 기준으로 우측에 '{키} = {값}' 형태로 구성된 요청을 전송
    @GetMapping("/request1")
    public String getRequestParam(
            @RequestParam String user,
            @RequestParam String name,
            @RequestParam String email) {
        return user + " " + name + " " + email;
    }
    @GetMapping("/members")
    public List<Map<String, Object>> memberList() {
        List<Map<String, Object>> members = new ArrayList<>();
        for(int i = 1; i <= 20; i++) {
            Map<String, Object> member = new HashMap<>();
            member.put("id", i);
            member.put("name", i + "번 개발자");
            member.put("age", 10 + i);
            members.add(member);
        }
        return members;
    }

//  2개의 방법으로 값을 받을 수 있다.
    @GetMapping("/members2")
    public List<MemberDto> memberList2() {
        List<MemberDto> members = new ArrayList<>();
        for(int i = 1; i <= 20; i++) {
            MemberDto member = new MemberDto();
            member.setUser("lovein6114" + i);
            member.setName("이동현" + i);
            member.setPwd("dlehdgus" + i);
            member.setEmail("lovein6114@gmail.com");
            members.add(member);
        }
        return members;
    }
    @GetMapping("/members3")
    public ResponseEntity<List<MemberDto>> memberList3() {
        List<MemberDto> members = new ArrayList<>();
        for(int i = 1; i <= 20; i++) {
            MemberDto member = new MemberDto();
            member.setUser("lovein6114" + i);
            member.setName("이동현" + i);
            member.setPwd("dlehdgus" + i);
            member.setEmail("lovein6114@gmail.com");
            members.add(member);
        }
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping("/member-reg")
    public ResponseEntity<Boolean> memberReg(@RequestBody Map<String, String> data) {
        String id = data.get("id");
        String pwd = data.get("pwd");
        String name = data.get("name");
        String email = data.get("email");
        System.out.println("id : " + id + " pwd : " + pwd + " name : " + name + " email : " + email);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
