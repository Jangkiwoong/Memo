package com.sprta.exam.controller;

import com.sprta.exam.dto.MemoRequestDto;
import com.sprta.exam.dto.MemoResponseDto;
import com.sprta.exam.dto.Sucess;
import com.sprta.exam.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final MemoService memoService;

    @GetMapping("/")
    public ModelAndView home(){
        return new ModelAndView("index");
    }


    //Json형식으로 @RequestBody가 있는 MemoRequesDto에 담아서 Service의 createMemo메서드에 매개변수로 지정함
    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    //전체 조회
    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos(){
        return memoService.getMemo();
    }

    //수정 id를 url로 받고 json형식으로 데이터를 받음
    @PutMapping("/api/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.updateMemo(id, requestDto);
    }

    //수정 id를 url로 받고 json형식으로 데이터를 받음
    @DeleteMapping("/api/memos/{id}")
    public Sucess deletMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.deleteMemo(id, requestDto);

    }
}
