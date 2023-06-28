package com.quensty.storagesys.mapper;

import com.quensty.storagesys.entity.ExportRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
public interface ExportRecordMapper extends BaseMapper<ExportRecord> {

    Integer getAmountByStoreId(String storeId,String rfid);
}
