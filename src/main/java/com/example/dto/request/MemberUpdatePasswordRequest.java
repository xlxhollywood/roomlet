package com.example.dto.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class MemberUpdatePasswordRequest {
    @NonNull
    private String beforePassword;
    @NonNull
    private String afterPassword;
}
