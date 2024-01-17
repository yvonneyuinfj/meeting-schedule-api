package avicit.myportal.meeting.meetingroom.dto;

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
* @创建时间： 2024-01-15 15:35
* @类说明：会议室台账表导入DTO
* @修改记录：
*/
@ExcelIgnoreUnannotated
@Data
public class MeetingRoomImportDTO{
private static final long serialVersionUID = 1L;
    /**
    * 会议室名称
    */
    @NotBlank(message = "会议室名称不能为空")
    @ExcelProperty("会议室名称")
    @Size(max = 100, message = "会议室名称长度不能超过100个字符")
    private String name;
    /**
    * 会议室容量
    */

    @ExcelProperty("会议室容量")
    @Digits(integer = 4, fraction = 0, message = "会议室容量格式不正确")
    private String capacity;
    /**
    * 会议室地点ID
    */
    @ExcelProperty("会议室地点ID")
    @Size(max = 50, message = "会议室地点ID长度不能超过50个字符")
    private String locationId;
    /**
    * 会议室地点名称
    */
    @ExcelProperty("会议室地点名称")
    @Size(max = 100, message = "会议室地点名称长度不能超过100个字符")
    private String locationName;
    /**
    * 会议室描述
    */
    @ExcelProperty("会议室描述")
    @Size(max = 512, message = "会议室描述长度不能超过512个字符")
    private String descrption;
    /**
      * 管理员显示字段
      */
    @ExcelProperty("管理员")
    private String adminIdAlias;
    /**
    * 管理员职工号
    */
    @ExcelProperty("管理员职工号")
    @Size(max = 50, message = "管理员职工号长度不能超过50个字符")
    private String adminCode;
    /**
    * 管理员姓名
    */
    @ExcelProperty("管理员姓名")
    @Size(max = 50, message = "管理员姓名长度不能超过50个字符")
    private String adminName;
    /**
    * 联系电话
    */
    @ExcelProperty("联系电话")
    @Size(max = 100, message = "联系电话长度不能超过100个字符")
    private String adminPhone;

    /**
    * 是否公开
    */
    @ExcelProperty("是否公开")
    private String ynPublicName;

    /**
    * 排序
    */

    @ExcelProperty("排序")
    @Digits(integer = 3, fraction = 0, message = "排序格式不正确")
    private String orderBy;
    /**
      * 管理部门显示字段
      */
    @ExcelProperty("管理部门")
    private String ownerDeptIdAlias;
    /**
    * 管理部门编号
    */
    @ExcelProperty("管理部门编号")
    @Size(max = 100, message = "管理部门编号长度不能超过100个字符")
    private String ownerDeptCode;
    /**
    * 管理部门名称
    */
    @ExcelProperty("管理部门名称")
    @Size(max = 256, message = "管理部门名称长度不能超过256个字符")
    private String ownerDeptName;

    /**
    * 是否需要审批
    */
    @ExcelProperty("是否需要审批")
    private String ynApproveName;

    /**
      * 审批人员IDS显示字段
      */
    @ExcelProperty("审批人员IDS")
    private String approvalIdsAlias;
    /**
    * 审批人员职工号
    */
    @ExcelProperty("审批人员职工号")
    @Size(max = 2000, message = "审批人员职工号长度不能超过2000个字符")
    private String approvalCodes;
    /**
    * 审批人员姓名
    */
    @ExcelProperty("审批人员姓名")
    @Size(max = 2000, message = "审批人员姓名长度不能超过2000个字符")
    private String approvalNames;

    /**
    * 是否可用
    */
    @ExcelProperty("是否可用")
    private String ynValidName;

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
    * 会议室设备ID
    */
    @ExcelProperty("会议室设备ID")
    @Size(max = 4000, message = "会议室设备ID长度不能超过4000个字符")
    private String mtDeviceIds;
    /**
    * 会议室设备编号
    */
    @ExcelProperty("会议室设备编号")
    @Size(max = 4000, message = "会议室设备编号长度不能超过4000个字符")
    private String mtDeviceCodes;
    /**
    * 会议室设备名称
    */
    @ExcelProperty("会议室设备名称")
    @Size(max = 4000, message = "会议室设备名称长度不能超过4000个字符")
    private String mtDeviceNames;

    @ColumnWidth(30)
    @ContentFontStyle(color=10)
    @ExcelProperty("错误信息")
    private String errorInfo;
}
