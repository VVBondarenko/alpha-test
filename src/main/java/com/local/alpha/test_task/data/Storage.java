package com.local.alpha.test_task.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Storage")
public class Storage {
    private List<Box> boxes;
    private List<Item> items;
    
    @XmlElement(type = Box.class, name = "Box")
    public List<Box> getBoxes() {
        return boxes;
    }
    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }
    
    @XmlElement(type = Item.class, name = "Item")
    public List<Item> getItems() {
        return items;
    }
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
