package com.sprta.exam.controller;

import com.sprta.exam.Dto.MemoRequestDto;
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
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos(){
        return memoService.getMemos();
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.updateMemo(id, requestDto);
    }
}
