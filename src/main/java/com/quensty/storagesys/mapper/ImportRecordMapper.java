package com.quensty.storagesys.mapper;

import com.quensty.storagesys.entity.ImportRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
public interface ImportRecordMapper extends BaseMapper<ImportRecord> {

    Integer getAmountByStoreId(String storeId, String rfid);
}
