package com.laundry.app.service.impl;

import com.laundry.app.domain.ItemsDomain;
import com.laundry.app.entity.Items;
import com.laundry.app.repository.ItemsRepository;
import com.laundry.app.service.ItemsService;
import javassist.NotFoundException;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository){
        this.itemsRepository = itemsRepository;
    }

    @Override
    public Items createItem(ItemsDomain itemsDomain) {
        Items items = new Items();
        items.setItemName(itemsDomain.getName());
        items.setCreatedDate(new Date());
        items.setPricePerKilo(itemsDomain.getPricePerKilo());
        items.setPricePerPiece(itemsDomain.getPricePerPiece());
        items.setDescription(itemsDomain.getDescription());

        return itemsRepository.save(items);
    }

    @Override
    public Items getById(long id) throws NotFoundException {
        if(!itemsRepository.existsById(id)){
            throw new NotFoundException("Item for id : " + id + " is not found");
        }

        return itemsRepository.getOne(id);
    }
}
