package com.sprta.exam.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoRequestDto {
    private String username;
    private String contents;
    private Long id;
    private int password;
    private LocalDateTime modifiedAt;
}
