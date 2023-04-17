package com.sprta.exam.service;

import com.sprta.exam.Dto.MemoRequestDto;
import com.sprta.exam.entity.Memo;
import com.sprta.exam.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    public final MemoRepository memoRepository;
    @Transactional
    public Memo createMemo (MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return memo;
    }

    @Transactional(readOnly = true)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }
    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(memo.getPassword() == requestDto.getPassword()) {
            memo.update(requestDto);
        }
        System.out.printf(String.valueOf(requestDto.getPassword()));
        return memo.getId();
    }
    }


