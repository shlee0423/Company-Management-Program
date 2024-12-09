package com.team.service.menuservice;

import com.team.domain.MemoDTO;
import com.team.mapper.Mapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class MemoServiceImpl implements MemoService {
    @Autowired
    private Mapper mapper;

    @Override
    public void insertMemo(MemoDTO memoDTO) {
        mapper.insertMemo(memoDTO);
    }
    @Override
    public List<MemoDTO> getAllMemos(String query,String keyword) {
        return mapper.selectMemoList(query, keyword);

    }

    @Override
    public void deleteMemoByNo(Integer memoNo) {
        mapper.deleteMemoByNo(memoNo);
    }
    @Override
    public List<MemoDTO> selectMemoById(String employeeId,String query, String keyword) {
        return mapper.selectMemoById(employeeId,query,keyword);
    }

    @Override
    public MemoDTO selectMemoByNo(Integer memoNo) {
        return mapper.selectMemoByNo(memoNo);
    }
}
