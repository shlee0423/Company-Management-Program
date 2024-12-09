package com.team.service.menuservice;

import com.team.domain.MemoDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;


public interface MemoService {
    void insertMemo(MemoDTO memoDTO);

    List<MemoDTO> getAllMemos(String query, String keyword);

    void deleteMemoByNo(Integer memoNo);
    List<MemoDTO> selectMemoById(String employeeId,String query,String keyword);
    MemoDTO selectMemoByNo(Integer memoNo);
}
