package com.team.service.chat;

import com.team.domain.ChatDTO;
import com.team.domain.EmployeeDTO;
import com.team.domain.RoomDTO;
import com.team.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ChatService {
    @Autowired
    private ChatMapper userMapper;

    public EmployeeDTO selectUserById(String userId){
        return userMapper.selectEmployeeById(userId);
    }
    public List<EmployeeDTO> selectAllWithoutMe(String userId)
    {
        return userMapper.selectAllWithoutMe(userId);
    }

    public void createRoom(String myUserId, String otherUserId) {
        Random random = new Random();
        Integer tempRoomNo = random.nextInt(10000);
        // 이미 해당 방에 속해있는지 검사


        // 두 명 INSERT
        userMapper.insertUserInRoom(tempRoomNo, myUserId);
        userMapper.insertUserInRoom(tempRoomNo, otherUserId);
    }
    public List<RoomDTO> selectRooms(String myUserId) {
        return userMapper.selectRooms(myUserId);
    }

    public void createChat(Integer roomNo, ChatDTO chatDTO) {
        System.out.println("asdfasdf" + roomNo);
        System.out.println("aassdd" + chatDTO);
        userMapper.insertChat(roomNo, chatDTO);
    }
}
