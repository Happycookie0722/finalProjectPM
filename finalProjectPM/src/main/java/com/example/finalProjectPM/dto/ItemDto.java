package com.example.finalProjectPM.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemDto {
    private String name;
    private int price;
    private String detail;
    private LocalDateTime regTime;
}
