package com.nextjob.back.auth.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoogleLoginResult {
    private GoogleUserInfo userInfo;
    private String accessToken;
    private int expiresIn;
}