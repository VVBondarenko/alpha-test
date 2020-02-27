package com.local.alpha.test_task.beans;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.local.alpha.test_task.dao.BoxRepository;
import com.local.alpha.test_task.dao.ItemRepository;
import com.local.alpha.test_task.data.Box;
import com.local.alpha.test_task.data.Item;
import com.local.alpha.test_task.data.Storage;

@Service(WebApplicationContext.SCOPE_APPLICATION)
public class ItemBean {
    @Autowired
    BoxRepository boxRepo;
    
    @Autowired
    ItemRepository itemRepo;
    
    @PostConstruct
    @Transactional
    public void parseXML() throws IOException, JAXBException {
        ClassPathResource classPathResource = new ClassPathResource("sample.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Storage.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
        Storage element = (Storage) jaxbUnmarshaller.unmarshal(classPathResource.getFile());
        Box box = convertToBox(element);
        setParentsFor(box);
        boxRepo.save(box);
    }
    
    private Box convertToBox(Storage storage) {
        Box box = new Box();
        box.setId(-1);
        box.setContentBox(storage.getBoxes());
        box.setContentItem(storage.getItems());
        box.setContainedIn(box);
        return box;
    }
    
    private void setParentsFor(Box box) {
        List<Box> content = box.getContentBox();
        if(content == null)
            return;
        for(Box item : content) {
            item.setContainedIn(box);
            setParentsFor(item);
        }
        List<Item> contentItems = box.getContentItem();
        if(contentItems == null)
            return;
        for(Item item : contentItems)
            item.setContainedIn(box);
    }
    
    @Transactional
    public List<Long> getItemsByColorAndParent(String color, long parentId) throws IOException, JAXBException {
        return itemRepo.findByContainerAndColor(parentId, color);
    }
}
