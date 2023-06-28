package com.quensty.storagesys.service.impl;

import com.quensty.storagesys.entity.Message;
import com.quensty.storagesys.mapper.MessageMapper;
import com.quensty.storagesys.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
