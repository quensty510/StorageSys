package com.quensty.storagesys.service.impl;

import com.quensty.storagesys.entity.ExportRecord;
import com.quensty.storagesys.mapper.ExportRecordMapper;
import com.quensty.storagesys.service.IExportRecordService;
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
public class ExportRecordServiceImpl extends ServiceImpl<ExportRecordMapper, ExportRecord> implements IExportRecordService {

    @Override
    public Integer getAmountByStoreId(String storeId,String rfid) {
        Integer amount = this.baseMapper.getAmountByStoreId(storeId, rfid);
        return amount == null ? 0 : amount;
    }
}
