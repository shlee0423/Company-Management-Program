package com.team.mapper;

import com.team.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface Mapper {
//    로그인 관련
    EmployeeDTO insertEmployee(EmployeeDTO employeeDTO);
    void insertEmployeeFile(EmployeeDTO employeeDTO);
    Boolean selectEmployeeIdIsAvailable(@Param("employeeId") String employeeId);
    EmployeeDTO selectEmployeeById(String employeeId);
    void updateEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO selectAll();


//    제품 관련
    void insertProduct(ProductDTO productDTO);


//    메모 관련
    void insertMemo(MemoDTO memoDTO);
    // 메모 조회 및 검색
    // 파람을 통해 xml에서 사용함
    List<MemoDTO> selectMemoList(@Param("query") String query, @Param("keyword") String keyword);
    List<MemoDTO> selectMemoById(@Param("employeeId") String employeeId,@Param("query") String query, @Param("keyword") String keyword);

    // 메모 수정 및 보기
    MemoDTO selectMemoByNo(@Param("memoNo") Integer memoNo);
    //메모 삭제
    void deleteMemoByNo(@Param("memoNo") Integer memoNo);


    // 드라이브
    void insertFiles(EmployeeDTO employeeDTO);

}
