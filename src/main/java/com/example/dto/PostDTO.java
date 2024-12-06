package com.example.dto;

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
    private Date createTime;
    private Date updateTime;
    private int views;
    private String memberId;
    private int fileId;


}
