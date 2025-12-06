package com.algowebpro.scm.helper;

import com.algowebpro.scm.enums.MessageType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String content;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MessageType messageType = MessageType.blue;

}
