package com.sprta.exam.controller;

import com.sprta.exam.Dto.MemoRequestDto;
import com.sprta.exam.Dto.MemoResponseDto;
import com.sprta.exam.entity.Memo;
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

    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos(){
        return memoService.getMemo();
    }

    @PutMapping("/api/memos/{id}")
    public List<MemoResponseDto> updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
       memoService.updateMemo(id, requestDto);
       return  memoService.getMemo();
    }

    @DeleteMapping("/api/memos/{id}")
    public List<MemoResponseDto>  deletMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
         memoService.deleteMemo(id, requestDto);
         return memoService.getMemo();
    }
}
