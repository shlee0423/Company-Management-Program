package com.team.controller;

import com.team.domain.ChatDTO;
import com.team.domain.EmployeeDTO;
import com.team.domain.RoomDTO;
import com.team.service.chat.ChatService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ChatController {
    @Autowired private SimpMessagingTemplate template;
    @Autowired private ChatService chatService;

    @GetMapping("/chat")
    public String chat(
            HttpSession session,
            String userid, // 내 아이디
            Integer roomNo,
            @AuthenticationPrincipal EmployeeDTO employee,
            Model model
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("employee",employee);
        System.out.println("나" + userid);
//        System.out.println(roomNo);
        if(userid == null) {
        }
        else {
            EmployeeDTO employeeDTO = chatService.selectUserById(userid);
            session.setAttribute("user", employeeDTO);
            System.out.println("나 자신 조회함: " + employeeDTO);
        }
        List<EmployeeDTO> employees = chatService.selectAllWithoutMe(userid);
        List<RoomDTO> rooms = chatService.selectRooms(userid);
        if(roomNo != null) {
            for (RoomDTO room : rooms) {
                if (roomNo.equals(room.getRoomNo())) {
                    System.out.println(room.getRoomNo());
                    System.out.println(room.getChats());
                    model.addAttribute("room", room);
                    break;
                }
            }
        }

        model.addAttribute("rooms", rooms);
        model.addAttribute("employees", employees);
        return "chat/chat";
    }






    // 처음 방을 생성했다
    @ResponseBody
    @PostMapping("/room")
    public ResponseEntity<Void> room(
            HttpSession session,
            @RequestBody EmployeeDTO otherUser
    ){
        System.out.println(session);
        EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
        chatService.createRoom(user.getEmployeeId(), otherUser.getEmployeeId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ResponseBody
    @PostMapping("/room/{roomNo}/chat")
    public ResponseEntity<Void> chat_insert(
            @PathVariable("roomNo") Integer roomNo,
            @RequestBody ChatDTO chat
    ){
//        System.out.println("남바" + roomNo);
        System.out.println("채채챗:" + chat);
//        System.out.println("챗챗" + chat.getChatMessage());
        chatService.createChat(roomNo, chat);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    /*******************************/
    @MessageMapping("/{roomNo}/{userId}")
    public void greeting(
            @DestinationVariable("roomNo") String roomNo,
            @DestinationVariable("userId") String userId,
            ChatDTO chatDTO
    ) {
        System.out.println("me:" + userId);
        System.out.println("채팅옴: " + roomNo);
        EmployeeDTO employeeDTO = chatService.selectUserById(userId);
//        EmployeeDTO user = (EmployeeDTO) session.getAttribute("user");
//        String employeeName = user.getEmployeeId();
//        System.out.println(employeeDTO);
//        System.out.println(chatDTO);
//        System.out.println(userId);

        if(chatDTO.getChatStatus().equals("ENTER")){
//            template.convertAndSend("/sub/" + roomNo, employeeDTO.getEmployeeName() + "님이 입장하셨습니다");
        }
        else{
            template.convertAndSend("/sub/" + roomNo, "[" + employeeDTO.getEmployeeName() + "]" + chatDTO.getChatMessage());
        }
//        System.out.println("Received message from user: " + msg + " / " + roomNo);
    }











}
