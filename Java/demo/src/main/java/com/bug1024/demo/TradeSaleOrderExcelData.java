package com.bug1024.demo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 销售单Excel数据
 *
 * @author wangyu024
 * @date 2020-04-20
 */
@Data
public class TradeSaleOrderExcelData implements Serializable {

	@ExcelProperty("项目归属城市")
	private String projectOwnerCityName;

	@ExcelProperty("代理公司")
	private String companyName;

	@ExcelProperty("项目来源")
	private String projectSourceText;

	@ExcelProperty("合作模式")
	private String cooperationModeText;

	@ExcelProperty("项目名称")
	private String projectName;

	@ExcelProperty("物业类型")
	private String propertyTypeName;

	@ExcelProperty("房间ID")
	private String houseId;

	@ExcelProperty("房间名称")
	private String houseName;

	@ExcelProperty("客户姓名")
	private String customerName;

	@ExcelProperty("认购人")
	private String purchaserName;

	@ExcelProperty("客户来源")
	private String customerSource;

	@ExcelProperty("销售单类型")
	private String tradeTypeText;

	@ExcelProperty("销售单状态")
	private String tradeStatusText;

	@ExcelProperty("签署日期")
	@DateTimeFormat(value = "yyyy.MM.dd")
	private Date signTime;

	@ExcelProperty("关闭日期")
	@DateTimeFormat(value = "yyyy.MM.dd")
	private Date closeTime;

	@ExcelProperty("关闭原因")
	private String closeReasonText;

	@ExcelProperty("创建日期")
	private Date createTime;

	@ExcelProperty("置业顾问")
	private String propertyConsultantName;

	@ExcelProperty(" 协议/合同总价")
	private BigDecimal contractTotalPrice;

	@ExcelProperty({"收入合计"})
	private BigDecimal totalIncomes;

	@ExcelProperty({"开发商1"})
	private String develop1;

	@ExcelProperty({"基础代理费点位"})
	private BigDecimal baseAgencyFeeRate1;

	@ExcelProperty({"基础代理费补提点位"})
	private BigDecimal baseAgencyFeeBonusRate1;

	@ExcelProperty({"超定价点位"})
	private BigDecimal overPriceRate1;

	@ExcelProperty({"营销推广费点位"})
	private BigDecimal marketPromotionFeeRate1;

	@ExcelProperty({"其他收入点位"})
	private BigDecimal otherIncomeFeeRate1;

	@ExcelProperty({"计提收入"})
	private BigDecimal accrualIncome1;

	@ExcelProperty({"开发商2"})
	private String develop2;

	@ExcelProperty({"基础代理费点位"})
	private BigDecimal baseAgencyFeeRate2;

	@ExcelProperty({"基础代理费补提点位"})
	private BigDecimal baseAgencyFeeBonusRate2;

	@ExcelProperty({"超定价点位"})
	private BigDecimal overPriceRate2;

	@ExcelProperty({"营销推广费点位"})
	private BigDecimal marketPromotionFeeRate2;

	@ExcelProperty({"其他收入点位"})
	private BigDecimal otherIncomeFeeRate2;

	@ExcelProperty({"计提收入"})
	private BigDecimal accrualIncome2;

	@ExcelProperty({"开发商3"})
	private String develop3;

	@ExcelProperty({"基础代理费点位"})
	private BigDecimal baseAgencyFeeRate3;

	@ExcelProperty({"基础代理费补提点位"})
	private BigDecimal baseAgencyFeeBonusRate3;

	@ExcelProperty({"超定价点位"})
	private BigDecimal overPriceRate3;

	@ExcelProperty({"营销推广费点位"})
	private BigDecimal marketPromotionFeeRate3;

	@ExcelProperty({"其他收入点位"})
	private BigDecimal otherIncomeFeeRate3;

	@ExcelProperty({"计提收入"})
	private BigDecimal accrualIncome3;

	@ExcelProperty({"开发商4"})
	private String develop4;

	@ExcelProperty({"基础代理费点位"})
	private BigDecimal baseAgencyFeeRate4;

	@ExcelProperty({"基础代理费补提点位"})
	private BigDecimal baseAgencyFeeBonusRate4;

	@ExcelProperty({"超定价点位"})
	private BigDecimal overPriceRate4;

	@ExcelProperty({"营销推广费点位"})
	private BigDecimal marketPromotionFeeRate4;

	@ExcelProperty({"其他收入点位"})
	private BigDecimal otherIncomeFeeRate4;

	@ExcelProperty({"计提收入"})
	private BigDecimal accrualIncome4;

}
