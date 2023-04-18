package com.sprta.exam.repository;

import com.sprta.exam.Dto.MemoResponseDto;
import com.sprta.exam.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long>{

    List<Memo> findAllByOrderByModifiedAtDesc();
}
