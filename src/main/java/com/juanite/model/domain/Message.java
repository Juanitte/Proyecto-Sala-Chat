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

    public Message() {
        this.message = "";
        this.timestamp = LocalDateTime.now();
        this.user = new User();
    }

    public Message(String message, LocalDateTime timestamp, User user) {
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message) && Objects.equals(timestamp, message1.timestamp) && Objects.equals(user, message1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, timestamp, user);
    }
}
