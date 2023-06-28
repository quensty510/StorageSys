package com.quensty.storagesys.service;

import com.quensty.storagesys.entity.ImportRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
public interface IImportRecordService extends IService<ImportRecord> {

    Integer getAmountByStoreId(String storeId, String rfid);
}
