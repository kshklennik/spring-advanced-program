package com.epam.avrotask.entity;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class MessageEntity {

    private UUID id;
    private String message;
}