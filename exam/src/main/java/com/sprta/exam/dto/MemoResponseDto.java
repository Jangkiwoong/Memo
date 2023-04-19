package com.sprta.exam.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class MemoResponseDto {
    private String username;
    private String contents;
    private Long id;
    private int password;
    private LocalDateTime modifiedAt;








    public MemoResponseDto(String username, String contents, Long id, LocalDateTime modifiedAt,int password){
        this.username = username;
        this.contents = contents;     // 전체조회
        this.id = id;
        this.modifiedAt = modifiedAt;
        this.password = password;
    }




    public MemoResponseDto(MemoRequestDto update) {
        this.username = update.getUsername();
        this.contents = update.getContents();
        this.password = update.getPassword();
    }


    public MemoResponseDto(MemoRequestDto update, Long id, LocalDateTime modifiedAt) {
        this.username = update.getUsername();
        this.contents = update.getContents();
        this.id = id;
        this.password = update.getPassword();
        this.modifiedAt = modifiedAt;
    }
}


