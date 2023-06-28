package com.quensty.storagesys.controller;

import com.quensty.storagesys.entity.ExportRecord;
import com.quensty.storagesys.entity.Goods;
import com.quensty.storagesys.entity.ImportRecord;
import com.quensty.storagesys.entity.Message;
import com.quensty.storagesys.service.IExportRecordService;
import com.quensty.storagesys.service.IGoodsService;
import com.quensty.storagesys.service.IImportRecordService;
import com.quensty.storagesys.service.IMessageService;
import com.quensty.storagesys.util.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 *  出库前端控制器
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
@RestController
@RequestMapping("/storagesys/exportRecord")
@Api("出库API")
public class ExportRecordController {

    @Autowired
    private IImportRecordService importRecordService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IExportRecordService exportRecordService;

    @Autowired
    private IMessageService messageService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping("exportItem")
    @ApiOperation("商品出库")
    @Transactional
    public HttpResult addItem (@RequestBody ExportRecord record){

        Goods goods = goodsService.getById(record.getRfid());
        int differ = goods.getGoodsAmount() - record.getAmount();
        if (differ < 0){
            return HttpResult.error("出库数量大于商品库存，无法出库！");
        }
        //更新商品库存
        goods.setGoodsAmount(differ);
        goodsService.updateById(goods);
        //保存出库记录
        record.setGoodsName(goods.getGoodsName());
        if (exportRecordService.save(record)){
            HashMap<String, Object> result = new HashMap<>();
            Integer exportSum = exportRecordService.getAmountByStoreId(record.getStoreId(), record.getRfid());
            Integer importSum = importRecordService.getAmountByStoreId(record.getStoreId(), record.getRfid());
            result.put("current_amount",importSum - exportSum);
            result.put("store_id",record.getStoreId());

            //发送消息
            Message message = new Message(null,"商品" + record.getGoodsName() + "出库!","0",record.getCreateTime());
            messageService.save(message);
            //在redis中存放过期过期时间为3小时的键，过期时触发监听器给目标用户发短信
            stringRedisTemplate.opsForValue().set("export_message:admin",record.getCreateTime().toString(),3, TimeUnit.HOURS);

            return HttpResult.ok(result);
        }
        return HttpResult.error("保存失败！");
    }

}
