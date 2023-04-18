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



    public MemoResponseDto(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }


    public MemoResponseDto(String username, String contents, Long id, LocalDateTime modifiedAt ){
        this.username = username;
        this.contents = contents;

        this.id = id;
    }

}


