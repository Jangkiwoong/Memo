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

    //entity의 데이터들을 그대로 가져오지 않고 ResponseDto의 생성자로 데이터를 MemoResponseDto에 담아 리턴해 줌
    @Transactional
    public MemoResponseDto createMemo (MemoRequestDto requestDto){
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo.getUsername(), memo.getContents(), memo.getId(), memo.getModifiedAt(), memo.getPassword());

        return memoResponseDto;
    }
    //DB와 연결된 Entity클래스에 List로 만들어서 sql메소드로 모든 데이터를 시간 역순으로 가져온다.
    //List<Memo>에서 데이터를 하나씩 빼와서 memo에 저장
    //List가 한바퀴돌면서 MemoResponseDto에 데이터를 하나씩 저장하고 하니씩 리턴
    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemo() {
        List<Memo> memos = memoRepository.findAllByOrderByModifiedAtDesc();
        List<MemoResponseDto> Dto = new ArrayList<>();
        for(Memo memo : memos){
            MemoResponseDto Dto2 = new MemoResponseDto(memo.getUsername(), memo.getContents(), memo.getId(), memo.getModifiedAt(), memo.getPassword()
            );  //Dto2에 초기화 해주기
            Dto.add(Dto2);
        }

        return Dto;
    }

    // DB와 연결된 Entitiy를 SQL메서드를 이용해서 Controller에서 넘어온 Id로 비교함
    // 같은 아이디가 있으면 if문으로 비밀번호를 비교하고 일치하면 ResponseDto에 생성자로 저장해서 반환
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


    // DB와 연결된 Entitiy를 SQL메서드를 이용해서 Controller에서 넘어온 Id로 비교함
    // 같은 아이디가 있으면 if문으로 비밀번호를 비교하고 일치하면 SQL메서드를 통해 해당 Id의 데이터를 지우고
    // Star클래스로 만들어준 success를 true 혹은 false로 넘김
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


