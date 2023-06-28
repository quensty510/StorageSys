package com.quensty.storagesys.service;

import com.quensty.storagesys.entity.ExportRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
public interface IExportRecordService extends IService<ExportRecord> {

    Integer getAmountByStoreId(String storeId,String rfid);
}
