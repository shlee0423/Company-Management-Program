package com.team.mapper;


import com.team.domain.NoticeDTO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 공지사항 목록 조회
    @Transactional(readOnly = true)
    @Select("SELECT * FROM `board`")
    List<NoticeDTO> selectAllNotice();

    // 제목과 번호로 공지사항 검색
    @Transactional(readOnly = true)
    @Select("SELECT * FROM `board` WHERE `board_title` = #{boardTitle} AND `board_no` = #{boardNo}")
    List<NoticeDTO> getNoticesByTitleNo(@Param("boardNo") Integer boardNo, @Param("boardTitle") String boardTitle);

    // 공지사항 등록 처리
    @Transactional
    @Insert("INSERT INTO `board` (board_no, board_title, board_content) VALUES (NULL, #{boardTitle}, #{boardContent})")
    void insertNotice(NoticeDTO notice);


    // 공지사항 업데이트
    @Transactional
    @Update("UPDATE `board` SET `board_title` = #{boardTitle}, `board_content` = #{boardContent} WHERE `board_no` = #{boardNo}")
    void updateNotice(NoticeDTO noticeDTO);



    // 공지사항 삭제
    @Transactional
    @Delete("DELETE FROM `board` WHERE board_no = #{boardNo}")
    void deleteNotice(Integer boardNo);

    // 공지사항 개수 조회
    @Transactional(readOnly = true)
    @Select("SELECT COUNT(*) FROM `board` WHERE board_no = #{boardNo} AND board_status = 1")
    Integer countNotice(Integer boardNo);
}
