package com.quensty.storagesys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Laiwenjun
 * @since 2023-06-28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "message_id", type = IdType.ASSIGN_ID)
    private String messageId;

    /**
     * 消息正文
     */
    @TableField("message_content")
    private String messageContent;

    /**
     * 消息阅读状态0-未阅读，1-已阅读
     */
    @TableField("message_status")
    private String messageStatus;

    /**
     * 出库时间
     */
    @TableField("export_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime exportTime;
}
