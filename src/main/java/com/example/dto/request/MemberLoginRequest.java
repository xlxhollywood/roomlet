package com.example.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class MemberLoginRequest {
    @NonNull
    private String memberId;
    @NonNull
    private String password;
}
