package com.juanite.model.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name="Chat")
@XmlAccessorType(XmlAccessType.FIELD)
public class Chat {
    private String  name;

    public Chat() {
        this.name = "";
    }

    public Chat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
