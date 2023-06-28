package com.quensty.storagesys.service.impl;

import com.quensty.storagesys.entity.ImportRecord;
import com.quensty.storagesys.mapper.ImportRecordMapper;
import com.quensty.storagesys.service.IImportRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
@Service
public class ImportRecordServiceImpl extends ServiceImpl<ImportRecordMapper, ImportRecord> implements IImportRecordService {

    @Override
    public Integer getAmountByStoreId(String storeId, String rfid) {
        Integer amount = this.baseMapper.getAmountByStoreId(storeId, rfid);
        return amount == null ? 0 : amount;
    }
}
