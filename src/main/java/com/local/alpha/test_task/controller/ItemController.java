package com.local.alpha.test_task.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.local.alpha.test_task.beans.ItemBean;

@RestController
public class ItemController {
    @Autowired
    private ItemBean service;
    
    @RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public List<Long> getAllItems(@RequestBody ItemFilter filter) throws IOException, JAXBException {
        return service.getItemsByColorAndParent(filter.color, filter.box);
    }
    
    private static class ItemFilter {
        public String color;
        public Long box;
    }
}
