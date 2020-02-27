package com.local.alpha.test_task.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@Entity(name = "box")
public class Box {
    @Id
    protected long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "contained_id")
    private List<Box> contentBox;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "contained_id")
    private List<Item> contentItem;

    @ManyToOne
    protected Box containedIn;

    @XmlElement(type = Box.class, name = "Box")
    public List<Box> getContentBox() {
        return contentBox;
    }

    public void setContentBox(List<Box> content) {
        this.contentBox = content;
    }

    @XmlElement(type = Item.class, name = "Item")
    public List<Item> getContentItem() {
        return contentItem;
    }

    public void setContentItem(List<Item> contentItem) {
        this.contentItem = contentItem;
    }
    
    public void setContainedIn(Box containedIn) {
        this.containedIn = containedIn;
    }

    @XmlAttribute
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Box, id=" + id;
    }


}
