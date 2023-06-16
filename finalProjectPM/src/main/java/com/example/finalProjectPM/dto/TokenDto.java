package com.example.finalProjectPM.dto;

import lombok.*;

@Getter
// 빈 생성자 만드는 어노테이션
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenDto {
    private String grantType; // 토큰의 유형
    private String accessToken; // 실제 사용될 토큰
    private Long tokenExpiresIn; // 만료 시간
}
