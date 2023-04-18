package com.sprta.exam.Dto;

import com.sprta.exam.entity.Memo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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


