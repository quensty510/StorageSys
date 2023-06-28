package com.quensty.storagesys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quensty.storagesys.entity.Goods;
import com.quensty.storagesys.entity.ImportRecord;
import com.quensty.storagesys.service.IExportRecordService;
import com.quensty.storagesys.service.IGoodsService;
import com.quensty.storagesys.service.IImportRecordService;
import com.quensty.storagesys.util.HttpResult;
import com.quensty.storagesys.util.PageConditionObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 *  入库前端控制器
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
@RestController
@RequestMapping("/storagesys/importRecord")
@Api("入库API")
public class ImportRecordController {

    @Autowired
    private IImportRecordService importRecordService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IExportRecordService exportRecordService;

    @PostMapping("importItem")
    @ApiOperation("商品入库")
    @Transactional
    public HttpResult addItem (@RequestBody ImportRecord record){

        Goods goods = goodsService.getById(record.getRfid());
        //更新商品库存
        goods.setGoodsAmount(record.getAmount() + goods.getGoodsAmount());
        goodsService.updateById(goods);
        //保存入库记录
        record.setGoodsName(goods.getGoodsName());
        if (importRecordService.save(record)){
            HashMap<String, Object> result = new HashMap<>();
            Integer exportSum = exportRecordService.getAmountByStoreId(record.getStoreId(), record.getRfid());
            Integer importSum = importRecordService.getAmountByStoreId(record.getStoreId(), record.getRfid());
            result.put("current_amount",importSum - exportSum);
            result.put("store_id",record.getStoreId());
            return HttpResult.ok(result);
        }
        return HttpResult.error("保存失败！");
    }

    @PostMapping("/selectPage")
    @ApiOperation("入库台账分页条件查询")
    public HttpResult selectPage(@RequestBody PageConditionObject<ImportRecord> conditionObject){
        ImportRecord record = conditionObject.getEntity();
        QueryWrapper<ImportRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(record.getCreateTime() != null,"create_time",record.getCreateTime());
        queryWrapper.like(StringUtils.isNotEmpty(record.getGoodsName()), "goods_name",record.getGoodsName());
        importRecordService.page(conditionObject.getPage(),queryWrapper);
        return HttpResult.ok(conditionObject.getPage());
    }

    @GetMapping("/statByRfid/{rfid}")
    @ApiOperation("统计商品库存信息")
    public HttpResult statByRfid(@PathVariable("rfid") String rfid){
        HashMap<String, Object> result = new HashMap<>();
        Goods goods = goodsService.getById(rfid);
        result.put("current_amount",goods.getGoodsAmount());
        result.put("total_price",goods.getGoodsPrice().multiply(new BigDecimal(goods.getGoodsAmount())));
        return HttpResult.ok(result);
    }
}
