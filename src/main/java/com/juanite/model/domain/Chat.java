package com.juanite.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement(name="Chat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chat {
    private String  name;
    private List<User> users;
    private List<Message> messages;

    public Chat() {
        this.name = "";
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public Chat(String name, List<User> users, List<Message> messages) {
        this.name = name;
        this.users = users;
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(name, chat.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
