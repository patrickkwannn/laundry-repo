package com.laundry.app.service;

import com.laundry.app.domain.StoreDomain;
import com.laundry.app.domain.StoreInfo;
import com.laundry.app.entity.Store;
import javassist.NotFoundException;

/**
 * @author Patrick Kwan
 * Created on 02/03/2021
 */
public interface StoreService {
    StoreInfo createStoreInfo() throws NotFoundException;
    Store addStore(StoreDomain domain);
}
