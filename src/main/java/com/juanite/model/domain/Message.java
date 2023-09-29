package com.juanite.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@XmlRootElement(name="Message")
@XmlAccessorType(XmlAccessType.FIELD)
public class Message implements Serializable {
    @XmlElement(name="message")
    private String message;
    @XmlElement(name="timestamp")
    private LocalDateTime timestamp;
    @XmlElement(name="user")
    private User user;
    @XmlElement(name="room")
    private Room room;

    public Message() {
        this.message = "";
        this.timestamp = LocalDateTime.now();
        this.user = new User();
        this.room = new Room();
    }

    public Message(String message, LocalDateTime timestamp, User user, Room room) {
        this.message = message;
        this.timestamp = timestamp;
        this.user = user;
        this.room = room;
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

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message) && Objects.equals(timestamp, message1.timestamp) && Objects.equals(user, message1.user) && Objects.equals(room, message1.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, timestamp, user, room);
    }
}
