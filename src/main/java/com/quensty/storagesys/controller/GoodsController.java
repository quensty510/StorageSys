package com.quensty.storagesys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quensty.storagesys.entity.Goods;
import com.quensty.storagesys.service.IGoodsService;
import com.quensty.storagesys.util.HttpResult;
import com.quensty.storagesys.util.PageConditionObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  商品前端控制器
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-27
 */
@RestController
@RequestMapping("/goods")
@Api("商品接口")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @GetMapping("/getItem/{rfid}")
    @ApiOperation("查询商品详情")
    public HttpResult getItem(@PathVariable("rfid") String rfid){
        Goods item = goodsService.getById(String.valueOf(rfid));
        if (item != null){
            return HttpResult.ok(item);
        }
        return HttpResult.ok("未查询到该标识对应的商品信息");
    }

    @PostMapping("/addItem")
    @ApiOperation("增加商品")
    public HttpResult addItem (@RequestBody Goods goods){
        if (goodsService.save(goods)){
            return HttpResult.ok();
        }
        return HttpResult.error("保存失败！");
    }

    @PostMapping("/updateItem")
    @ApiOperation("修改商品")
    public HttpResult updateItem (@RequestBody Goods goods){
        if (goodsService.updateById(goods)){
            return HttpResult.ok();
        }
        return HttpResult.error("保存失败！");
    }

    @DeleteMapping("/deleteItem/{rfid}")
    @ApiOperation("删除商品")
    public HttpResult deleteItem (@PathVariable("rfid") String rfid){
        if (goodsService.removeById(rfid)){
            return HttpResult.ok();
        }
        return HttpResult.error("保存失败！");
    }


    @PostMapping("/selectPage")
    @ApiOperation("商品分页条件查询")
    public HttpResult selectPage(@RequestBody PageConditionObject<Goods> conditionObject){
        Goods entity = conditionObject.getEntity();
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(true,true,"create_time");
        queryWrapper.like(StringUtils.isNotEmpty(entity.getGoodsName()), "goods_name",entity.getGoodsName());
        goodsService.page(conditionObject.getPage(),queryWrapper);
        return HttpResult.ok(conditionObject.getPage());
    }
}
