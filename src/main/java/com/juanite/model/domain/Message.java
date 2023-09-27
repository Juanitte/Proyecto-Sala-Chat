package com.juanite.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;
import java.util.Objects;

@XmlRootElement(name="Message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message {
    private String message;
    private LocalDateTime timestamp;
    private User user;
    private Chat chat;

    public Message() {
        this.message = "";
        this.timestamp = LocalDateTime.now();
        this.user = new User();
        this.chat = new Chat();
    }

    public Message(String message, LocalDateTime timestamp, User user, Chat chat) {
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
        this.chat = chat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(timestamp, message.timestamp) && Objects.equals(user, message.user) && Objects.equals(chat, message.chat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, user, chat);
    }
}
