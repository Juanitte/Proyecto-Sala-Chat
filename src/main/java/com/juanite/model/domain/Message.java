package com.juanite.model.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message implements Serializable {
    private String message;
    private String timestamp;
    private User user;
    private Room room;

    public Message() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd  HH:mm:ss");
        this.message = "";
        this.timestamp = LocalDateTime.now().format(dtf);
        this.user = new User();
        this.room = new Room();
    }

    public Message(String message, String timestamp, User user, Room room) {
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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
