package com.laundry.app.service;

import com.laundry.app.domain.ItemsDomain;
import com.laundry.app.entity.Items;
import javassist.NotFoundException;

import java.util.List;

/**
 * @author Patrick Kwan
 * Created on 01/03/2021
 */
public interface ItemsService {
    Items createItem(ItemsDomain itemsDomain);
    Items getById(long id) throws NotFoundException;
    List<Items> getAll();
}
