package com.example.chatapp.service;

import com.example.chatapp.domain.ChatMember;
import com.example.chatapp.domain.ChatMessage;
import com.example.chatapp.domain.ChatRoom;
import com.example.chatapp.domain.subDoc.JoinedChatRoom;
import com.example.chatapp.repository.ChatMemberRepository;
import com.example.chatapp.repository.ChatRoomRepository;
import com.example.chatapp.service.dto.ChatRoomByMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomSearchService {
    private final ChatMemberRepository chatMemberRepository;
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoomByMemberDto> getChatRoomsByMemberId(Long memberId) {
        List<JoinedChatRoom> joinedChatRooms = chatMemberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."))
                .getJoinedChatRooms();

        return joinedChatRooms.stream()
                .map(this::convertToDto)
                .toList();
    }

    private ChatRoomByMemberDto convertToDto(JoinedChatRoom joinedChatRoom) {
        ChatRoomByMemberDto chatRoomByMemberDto = new ChatRoomByMemberDto();
        ChatRoom chatRoom = chatRoomRepository.findById(joinedChatRoom.getChatRoomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));


        chatRoomByMemberDto.setTitle(chatRoom.getTitle());
        chatRoomByMemberDto.setLastMessages(chatRoom.getLastMessages());
        chatRoomByMemberDto.setUnreadCount(
                getUnreadCount(chatRoom.getLastMessages(), joinedChatRoom.getLastAccessTime())
        );
        chatRoomByMemberDto.setJoinedMemberIds(chatRoom.getJoinedMemberIds());

        return chatRoomByMemberDto;
    }

    private Integer getUnreadCount(List<ChatMessage> lastMessages, Date lastReadDate) {
        // binary search - upperbound
        int lo = 0;
        int hi = lastMessages.size() - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (lastMessages.get(mid).getSendTime().after(lastReadDate)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lastMessages.size() - lo;
    }
}
