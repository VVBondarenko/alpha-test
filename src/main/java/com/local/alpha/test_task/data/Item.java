package com.local.alpha.test_task.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;

@Entity(name = "item")
public class Item {
    @Id
    private long id;

    private String color;

    @ManyToOne
    protected Box containedIn;

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlAttribute
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setContainedIn(Box containedIn) {
        this.containedIn = containedIn;
    }

    @Override
    public String toString() {
        return "Item, id=" + id;
    }
}
