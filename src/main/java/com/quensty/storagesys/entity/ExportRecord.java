package com.quensty.storagesys.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("export_record")
public class ExportRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 出库台账ID
     */
    @TableId(value = "record_id", type = IdType.ASSIGN_ID)
    private String recordId;

    /**
     * 门店id
     */
    @TableField("store_id")
    private String storeId;

    /**
     * 商品标识
     */
    @TableField("rfid")
    private String rfid;

    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 商品数量
     */
    @TableField("amount")
    private Integer amount;

    /**
     * 出库时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
