package com.sprta.exam.service;

import com.sprta.exam.dto.MemoRequestDto;
import com.sprta.exam.dto.MemoResponseDto;
import com.sprta.exam.dto.Sucess;
import com.sprta.exam.entity.Memo;
import com.sprta.exam.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    @Transactional
    public MemoResponseDto createMemo (MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo.getUsername(), memo.getContents(), memo.getId(), memo.getModifiedAt(), memo.getPassword());

        return memoResponseDto;
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemo() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc(); // 모든 데이터를 시간 역순으로 가져옵니다.
        List<MemoResponseDto> Dto = new ArrayList<>();
        for(Memo memo : memos){  //memos리스트에서 데이터를 하나씩 빼와서 memo에 저장
            MemoResponseDto Dto2 = new MemoResponseDto(memo.getUsername(), memo.getContents(), memo.getId(), memo.getModifiedAt(), memo.getPassword()
            );  //Dto2에 초기화 해주기
            Dto.add(Dto2);  //초기화 해주는 데이터를 Dto리스트에 저장
        }

        return Dto;
    }


    @Transactional
    public MemoResponseDto updateMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if(memo.getPassword() == requestDto.getPassword()) {
            MemoResponseDto Dto = new MemoResponseDto(memo.update(requestDto),memo.getId(),memo.getModifiedAt());
        return Dto;
        }
        return null;
    }
    @Transactional
    public Sucess deleteMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        if (memo.getPassword() == requestDto.getPassword()) {
            memoRepository.deleteById(id);
            MemoResponseDto dto = new MemoResponseDto();
            Sucess true2= new Sucess(true);
            return true2;
        }
        else {
            Sucess false2= new Sucess(false);
            return false2;
        }
    }
}


