package com.zerobase.travel.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO {
    private String accessToken;
    private String refreshToken;
    private String message;
}
