package com.local.alpha.test_task.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.local.alpha.test_task.data.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {
    List<Item> findAll();
    @Query(value = "with RECURSIVE boxes(id, contained_id) as (" + 
            "select id, contained_id from box where id = :contaier" + 
            " union " + 
            "select b.id, b.contained_id from box as b join boxes as bb where b.contained_id = bb.id" + 
            ")" + 
            "select item.id from item join boxes on item.CONTAINED_ID = boxes.id where color = :color", nativeQuery = true)
    List<Long> findByContainerAndColor(@Param("contaier") Long containerId, @Param("color") String color);
}
