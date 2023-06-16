package com.example.finalProjectPM.controller;

import com.example.finalProjectPM.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {
    @GetMapping("/item")
    // 정보를 전달하려면 spring 프레임워크에 Model 을 사용해야한다.
    public String thymeleafItem(Model model) {
        ItemDto itemDto = new ItemDto();
        itemDto.setName("LG 오브제 에어컨");
        itemDto.setDetail("LG 23년형 에어컨 입니다.");
        itemDto.setPrice(100000);
        itemDto.setRegTime(LocalDateTime.now());
        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafItem";
    }

    @GetMapping("/item-list")
    public String thymeleafItemList(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setName("LG 오브제 에어컨");
            itemDto.setDetail("LG 23년형 에어컨 입니다.");
            itemDto.setPrice(100000);
            itemDto.setRegTime(LocalDateTime.now());
            itemDtoList.add(itemDto);
        }
        model.addAttribute("itemDtoList", itemDtoList);

        // html 파일 위치를 지정하기
        return "thymeleafEx/thymeleafItemList.html";
    }

    @GetMapping("/link-move")
    public String thymeleafLinkMove() {
        return "thymeleafEx/thymeleafLinkMove";
    }

    @GetMapping("/link-value")
    public String thymeleafLinkMove(String param1, String param2, Model model) {
        model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);
        return "thymeleafEx/thymeleafLinkParam";
    }
}
