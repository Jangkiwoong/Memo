package com.sprta.exam.service;

import com.sprta.exam.Dto.MemoRequestDto;
import com.sprta.exam.Dto.MemoResponseDto;
import com.sprta.exam.entity.Memo;
import com.sprta.exam.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        MemoResponseDto memoResponseDto = new MemoResponseDto(requestDto);

        return memoResponseDto;
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemo() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        List<MemoResponseDto> Dto = new ArrayList<>();
        for(Memo memo : memos){
            MemoResponseDto Dto2 = new MemoResponseDto(memo.getUsername(), memo.getContents(), memo.getId(), memo.getModifiedAt()
            );
            Dto.add(Dto2);
        }

        return Dto;
    }


    @Transactional
    public String updateMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String answer;
        if(memo.getPassword() == requestDto.getPassword()) {
            memo.update(requestDto);
            answer = "수정완료";
            return answer;
        }
        else {
        answer = "수정실패";
        return answer;
        }
    }
    @Transactional
    public String deleteMemo(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String answer;
        if(memo.getPassword() == requestDto.getPassword()) {
            memoRepository.deleteById(id);
            answer = "삭제완료";
            return answer;
        }
        else {
            answer = "삭제실패";
            return answer;
        }

    }
}


