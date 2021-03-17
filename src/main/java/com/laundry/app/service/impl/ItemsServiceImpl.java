package com.laundry.app.service.impl;

import com.laundry.app.domain.ItemsDomain;
import com.laundry.app.en.Const;
import com.laundry.app.entity.Items;
import com.laundry.app.repository.ItemsRepository;
import com.laundry.app.service.ItemsService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
@Service
public class ItemsServiceImpl implements ItemsService {

    @Value("${laundry.items}")
    private String[] items;

    private final Environment environment;
    private final ItemsRepository itemsRepository;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository,
                            Environment environment){
        this.itemsRepository = itemsRepository;
        this.environment = environment;
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

    @Override
    public List<Items> getAll() {
        return itemsRepository.findAll();
    }

    @Override
    public void flush() {
        List<Items> items = itemsRepository.findAll();
        for(Items x : items){
            itemsRepository.delete(x);
        }
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addBasicItems(){
        if(itemsRepository.findAll().isEmpty()) {
            for (String x : items) {
                Items item = new Items();
                item.setItemName(x);
                item.setDescription(x);
                item.setPricePerKilo(Long.parseLong(Objects.requireNonNull(environment.getProperty(Const.PER_KILO))));
                item.setPricePerPiece(Long.parseLong(Objects.requireNonNull(environment.getProperty(Const.PER_PIECE))));
                item.setCreatedDate(new Date());
                itemsRepository.save(item);
            }
        }
    }
}
