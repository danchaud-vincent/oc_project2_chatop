package com.chatop.api.dto.response;

import java.util.List;

import com.chatop.api.dto.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessagesDto {

    private List<MessageDto> messages;

}
