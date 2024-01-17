package avicit.myportal.event.event.dto;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.ExcelProperty;
import avicit.platform6.core.validate.DateTimeStr;
import javax.validation.constraints.*;
import lombok.Data;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-16 13:47
* @类说明：日程表导入DTO
* @修改记录：
*/
@ExcelIgnoreUnannotated
@Data
public class EventImportDTO{
private static final long serialVersionUID = 1L;
    /**
    * 日程主题
    */
    @NotBlank(message = "日程主题不能为空")
    @ExcelProperty("日程主题")
    @Size(max = 100, message = "日程主题长度不能超过100个字符")
    private String name;
    /**
      * 作者显示字段
      */
    @ExcelProperty("作者")
    private String authorIdAlias;
    /**
    * 作者职工号
    */
    @ExcelProperty("作者职工号")
    @Size(max = 50, message = "作者职工号长度不能超过50个字符")
    private String authorCode;
    /**
    * 作者
    */
    @ExcelProperty("作者")
    @Size(max = 100, message = "作者长度不能超过100个字符")
    private String authorName;
    /**
    * 日程地点ID
    */
    @ExcelProperty("日程地点ID")
    @Size(max = 50, message = "日程地点ID长度不能超过50个字符")
    private String placeId;
    /**
    * 日程地点
    */
    @ExcelProperty("日程地点")
    @Size(max = 100, message = "日程地点长度不能超过100个字符")
    private String placeName;
    /**
    * 开始时间
    */

    @ExcelProperty("开始时间")
    @DateTimeStr(format ="yyyy/MM/dd HH:mm", message = "【开始时间】格式错误，正确格式为：yyyy/MM/dd")
    private String startTime;
    /**
    * 结束时间
    */

    @ExcelProperty("结束时间")
    @DateTimeStr(format ="yyyy/MM/dd HH:mm", message = "【结束时间】格式错误，正确格式为：yyyy/MM/dd")
    private String endTime;

    /**
    * 是否可用
    */
    @ExcelProperty("是否可用")
    private String ynValidName;


    /**
    * 是否公开
    */
    @ExcelProperty("是否公开")
    private String ynPublicName;

    /**
    * 日程内容
    */
    @ExcelProperty("日程内容")
    @Size(max = 512, message = "日程内容长度不能超过512个字符")
    private String content;

    /**
    * 日程类型
    */
    @ExcelProperty("日程类型")
    private String typeName;


    /**
    * 提醒类型
    */
    @ExcelProperty("提醒类型")
    private String remindTypeName;

    /**
      * 共享给显示字段
      */
    @ExcelProperty("共享给")
    private String sharedUserIdsAlias;
    /**
    * 共享人员职工号
    */
    @ExcelProperty("共享人员职工号")
    @Size(max = 2000, message = "共享人员职工号长度不能超过2000个字符")
    private String sharedUserCodes;
    /**
    * 共享人员姓名
    */
    @ExcelProperty("共享人员姓名")
    @Size(max = 2000, message = "共享人员姓名长度不能超过2000个字符")
    private String sharedUserNames;
    /**
    * 备注
    */
    @ExcelProperty("备注")
    @Size(max = 256, message = "备注长度不能超过256个字符")
    private String note;
    /**
    * 预留字段01
    */
    @Size(max = 256, message = "预留字段01长度不能超过256个字符")
    private String attribute01;
    /**
    * 预留字段02
    */
    @Size(max = 256, message = "预留字段02长度不能超过256个字符")
    private String attribute02;
    /**
    * 预留字段03
    */
    @Size(max = 256, message = "预留字段03长度不能超过256个字符")
    private String attribute03;
    /**
    * 预留字段04
    */
    @Size(max = 256, message = "预留字段04长度不能超过256个字符")
    private String attribute04;
    /**
    * 预留字段05
    */
    @Size(max = 256, message = "预留字段05长度不能超过256个字符")
    private String attribute05;
    /**
    * 预留字段06
    */
    @Size(max = 256, message = "预留字段06长度不能超过256个字符")
    private String attribute06;
    /**
    * 预留字段07
    */
    @Size(max = 256, message = "预留字段07长度不能超过256个字符")
    private String attribute07;
    /**
    * 预留字段08
    */
    @Size(max = 256, message = "预留字段08长度不能超过256个字符")
    private String attribute08;
    /**
    * 预留字段09
    */

    @DateTimeStr(format ="yyyy/MM/dd", message = "【预留字段09】格式错误，正确格式为：yyyy/MM/dd")
    private String attribute09;
    /**
    * 预留字段10
    */

    @DateTimeStr(format ="yyyy/MM/dd", message = "【预留字段10】格式错误，正确格式为：yyyy/MM/dd")
    private String attribute10;
    @ExcelProperty("密级")
    private String secretLevelName;
    /**
    * 提醒天数
    */

    @ExcelProperty("提醒天数")
    @Digits(integer = 2, fraction = 0, message = "提醒天数格式不正确")
    private String remindDuration;

    @ColumnWidth(30)
    @ContentFontStyle(color=10)
    @ExcelProperty("错误信息")
    private String errorInfo;
}
