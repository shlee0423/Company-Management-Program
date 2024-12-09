package com.team.controller;

import com.team.domain.EmployeeDTO;
import com.team.domain.NoticeDTO;
import com.team.domain.ProductDTO;
import com.team.mapper.NoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Notice_Service")
public class NoticeBoardController {

    @Autowired
    private NoticeMapper noticeMapper;


    // 공지사항 목록 조회
    @Transactional(readOnly = true)
    @GetMapping("/notice")
    public String create_view(
            @AuthenticationPrincipal EmployeeDTO employee,
            Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("employee",employee);
        List<NoticeDTO> notices = noticeMapper.selectAllNotice();
        model.addAttribute("notices", notices);
        System.out.println(notices);
        return "notice/notice";
    }

    // 제목과 번호로 공지사항 검색
    @Transactional(readOnly = true)
    @GetMapping("/search")
    public String getNoticesByTitle(@RequestParam("boardNo") Integer boardNo, @RequestParam("boardTitle") String boardTitle, Model model) {
        List<NoticeDTO> notices = noticeMapper.getNoticesByTitleNo(boardNo, boardTitle);
        model.addAttribute("notices", notices);
        return "notice/notice";  // 리다이렉트 대신 뷰 이름 반환
    }

    // 공지사항 등록 페이지
    @Transactional
    @GetMapping("/registration")
    public String get_registration_view(
            @AuthenticationPrincipal EmployeeDTO employee,
            Model model
            ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("employee",employee);
        return "/notice/registration";
    }

    // 공지사항 등록 처리
    @Transactional
    @PostMapping("/registration")
    public String post_registration_view(@ModelAttribute NoticeDTO notice){
        noticeMapper.insertNotice(notice);
        return "redirect:/Notice_Service/notice";  // 등록 후 목록 페이지로 리다이렉트
    }

    // 공지사항 업데이트
    @Transactional
    @PutMapping("/update/{boardNo}")
    public ResponseEntity<NoticeDTO> update_notice(
            @PathVariable("boardNo") Integer boardNo,
            @RequestBody NoticeDTO noticeDTO
    ){
        noticeDTO.setBoardNo(boardNo);
        System.out.println("업데이트: " + noticeDTO);
        noticeMapper.updateNotice(noticeDTO);
        return ResponseEntity.ok(noticeDTO);
    }



    @ResponseBody
    @DeleteMapping("/notice")
    public void delete_notice(
            @RequestBody List<Integer> notices
    ){
       for(Integer notice : notices){
            noticeMapper.deleteNotice(notice);
        }
    }

//    // 공지사항 삭제
//    @Transactional
//    @DeleteMapping("/Notice_Service/notice")
//    public ResponseEntity<Void> delete_notice(
//            @PathVariable Integer boardNo
//    ){
//        noticeMapper.deleteNotice(boardNo);
//        return ResponseEntity.ok().build();
//    }

    // 공지사항 개수 조회
    @Transactional(readOnly = true)
    @GetMapping("/notice/{boardNo}/count")
    @ResponseBody
    public Integer count_notice(@PathVariable("boardNo") Integer boardNo) {

        return noticeMapper.countNotice(boardNo);
    }
}