package com.quensty.storagesys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.quensty.storagesys.entity.ImportRecord;
import com.quensty.storagesys.entity.Message;
import com.quensty.storagesys.service.IMessageService;
import com.quensty.storagesys.util.HttpResult;
import com.quensty.storagesys.util.MyPage;
import com.quensty.storagesys.util.PageConditionObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Map;

/**
 * <p>
 *  消息前端控制器
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
@RestController
@RequestMapping("/storagesys/message")
@Api("消息API")
public class MessageController {
    @Autowired
    private IMessageService messageService;

    @PostMapping("/selectPage")
    @ApiOperation("消息列表分页条件查询")
    public HttpResult selectPage(@RequestBody Map<String,String> params){
        MyPage<Message> myPage = new MyPage<>();
        myPage.setCurrent(Long.parseLong(params.get("current")));
        myPage.setSize(Long.parseLong(params.get("size")));

        String startTime = params.get("start_time");
        String endTime = params.get("end_time");
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(startTime)){
            queryWrapper.ge("export_time",LocalDateTime.parse(startTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        }
        if (StringUtils.isNotEmpty(endTime)){
            queryWrapper.le("export_time",LocalDateTime.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        }

        messageService.page(myPage,queryWrapper);
        return HttpResult.ok(myPage);
    }


    @GetMapping("/readMessage/{messageId}")
    @ApiOperation("读取消息")
    public HttpResult readMessage(@PathVariable String messageId){
        Message message = messageService.getById(messageId);
        message.setMessageStatus("1");
        if (messageService.updateById(message)){
            return HttpResult.ok();
        }
        return HttpResult.error("消息状态更改失败！");
    }

}
