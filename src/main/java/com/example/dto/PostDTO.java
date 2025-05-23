package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDTO {
    private Integer id;
    private String title;
    private String contents;
    private String kakaoUrl;
    private Date createTime;
    private Date updateTime;
    private String fileUrl;
    private String address;
    private Status status;

    private String memberId; // 사용자 아이디

    @JsonProperty("mId")
    private int mId;

    public int getMid() {
        return mId;
    }

    public void setMid(int mId) {
        this.mId = mId;
    }

    public enum Status {
        // 양도 구하는 중/ 양도 중 / 양도 완료
        REQUESTING_TRANSFER,
        TRANSFERRING,
        COMPLETED

    }


}
