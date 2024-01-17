package avicit.myportal.meeting.meetingroom.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.annotation.log.Id;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.domain.BeanDTO;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.commons.utils.StringUtils;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.*;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-15 15:35
* @类说明：会议室台账表DTO
* @修改记录：
*/
@ApiModel(value = "MeetingRoomDTO", description = "会议室台账表")
@PojoRemark(table="MEETING_ROOM", object="MeetingRoomDTO", name="会议室台账表")
@ExcelIgnoreUnannotated
public class MeetingRoomDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * 会议室ID
    */
    @Id
    @Size(max = 50, message = "会议室ID长度不能超过50个字符")
    @ApiModelProperty(value = "会议室ID", name = "id")
    @FieldRemark(column="ID", field="id", name="会议室ID")
    private java.lang.String id;

    /**
    * 会议室名称
    */
    @LogField
    @NotBlank(message = "会议室名称不能为空")
    @ExcelProperty("会议室名称")
    @Size(max = 100, message = "会议室名称长度不能超过100个字符")
    @ApiModelProperty(value = "会议室名称", name = "name")
    @FieldRemark(column="NAME", field="name", name="会议室名称")
    private java.lang.String name;
    /**
    * 会议室容量
    */
    @ExcelProperty("会议室容量")
    @ColumnWidth(15)
    @NumberFormat("0")
    @Digits(integer = 4, fraction = 0, message = "会议室容量格式不正确")
    @ApiModelProperty(value = "会议室容量", name = "capacity")
    @FieldRemark(column="CAPACITY", field="capacity", name="会议室容量")
    private java.lang.Long capacity;

    /**
    * 会议室地点ID
    */
    @ExcelProperty("会议室地点ID")
    @Size(max = 50, message = "会议室地点ID长度不能超过50个字符")
    @ApiModelProperty(value = "会议室地点ID", name = "locationId")
    @FieldRemark(column="LOCATION_ID", field="locationId", name="会议室地点ID")
    private java.lang.String locationId;

    /**
    * 会议室地点名称
    */
    @ExcelProperty("会议室地点名称")
    @Size(max = 100, message = "会议室地点名称长度不能超过100个字符")
    @ApiModelProperty(value = "会议室地点名称", name = "locationName")
    @FieldRemark(column="LOCATION_NAME", field="locationName", name="会议室地点名称")
    private java.lang.String locationName;

    /**
    * 会议室描述
    */
    @ExcelProperty("会议室描述")
    @Size(max = 512, message = "会议室描述长度不能超过512个字符")
    @ApiModelProperty(value = "会议室描述", name = "descrption")
    @FieldRemark(column="DESCRPTION", field="descrption", name="会议室描述")
    private java.lang.String descrption;
    /**
    * 管理员
    */
    @Size(max = 50, message = "管理员长度不能超过50个字符")
    @ApiModelProperty(value = "管理员", name = "adminId")
    @FieldRemark(column="ADMIN_ID", field="adminId", name="管理员", dataType="user")
    private java.lang.String adminId;

    /**
    * 管理员显示字段
    */
    @ExcelProperty("管理员")
    private java.lang.String adminIdAlias;

    /**
    * 管理员职工号
    */
    @ExcelProperty("管理员职工号")
    @Size(max = 50, message = "管理员职工号长度不能超过50个字符")
    @ApiModelProperty(value = "管理员职工号", name = "adminCode")
    @FieldRemark(column="ADMIN_CODE", field="adminCode", name="管理员职工号")
    private java.lang.String adminCode;

    /**
    * 管理员姓名
    */
    @ExcelProperty("管理员姓名")
    @Size(max = 50, message = "管理员姓名长度不能超过50个字符")
    @ApiModelProperty(value = "管理员姓名", name = "adminName")
    @FieldRemark(column="ADMIN_NAME", field="adminName", name="管理员姓名")
    private java.lang.String adminName;

    /**
    * 联系电话
    */
    @ExcelProperty("联系电话")
    @Size(max = 100, message = "联系电话长度不能超过100个字符")
    @ApiModelProperty(value = "联系电话", name = "adminPhone")
    @FieldRemark(column="ADMIN_PHONE", field="adminPhone", name="联系电话")
    private java.lang.String adminPhone;

    /**
    * 是否公开
    */
    @ApiModelProperty(value = "是否公开", name = "ynPublic")
    @FieldRemark(column="YN_PUBLIC", field="ynPublic", name="是否公开", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynPublic;
    @ExcelProperty("是否公开")
    private java.lang.String ynPublicName;

    /**
    * 排序
    */
    @ExcelProperty("排序")
    @ColumnWidth(15)
    @NumberFormat("0")
    @Digits(integer = 3, fraction = 0, message = "排序格式不正确")
    @ApiModelProperty(value = "排序", name = "orderBy")
    @FieldRemark(column="ORDER_BY", field="orderBy", name="排序")
    private java.lang.Long orderBy;

    /**
    * 管理部门
    */
    @Size(max = 100, message = "管理部门长度不能超过100个字符")
    @ApiModelProperty(value = "管理部门", name = "ownerDeptId")
    @FieldRemark(column="OWNER_DEPT_ID", field="ownerDeptId", name="管理部门", dataType="dept")
    private java.lang.String ownerDeptId;

    /**
    * 管理部门显示字段
    */
    @ExcelProperty("管理部门")
    private java.lang.String ownerDeptIdAlias;

    /**
    * 管理部门编号
    */
    @ExcelProperty("管理部门编号")
    @Size(max = 100, message = "管理部门编号长度不能超过100个字符")
    @ApiModelProperty(value = "管理部门编号", name = "ownerDeptCode")
    @FieldRemark(column="OWNER_DEPT_CODE", field="ownerDeptCode", name="管理部门编号")
    private java.lang.String ownerDeptCode;

    /**
    * 管理部门名称
    */
    @ExcelProperty("管理部门名称")
    @Size(max = 256, message = "管理部门名称长度不能超过256个字符")
    @ApiModelProperty(value = "管理部门名称", name = "ownerDeptName")
    @FieldRemark(column="OWNER_DEPT_NAME", field="ownerDeptName", name="管理部门名称")
    private java.lang.String ownerDeptName;

    /**
    * 是否需要审批
    */
    @ApiModelProperty(value = "是否需要审批", name = "ynApprove")
    @FieldRemark(column="YN_APPROVE", field="ynApprove", name="是否需要审批", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynApprove;
    @ExcelProperty("是否需要审批")
    private java.lang.String ynApproveName;

    /**
    * 审批人员IDS
    */
    @Size(max = 2000, message = "审批人员IDS长度不能超过2000个字符")
    @ApiModelProperty(value = "审批人员IDS", name = "approvalIds")
    @FieldRemark(column="APPROVAL_IDS", field="approvalIds", name="审批人员IDS", dataType="user")
    private java.lang.String approvalIds;

    /**
    * 审批人员IDS显示字段
    */
    @ExcelProperty("审批人员IDS")
    private java.lang.String approvalIdsAlias;

    /**
    * 审批人员职工号
    */
    @ExcelProperty("审批人员职工号")
    @Size(max = 2000, message = "审批人员职工号长度不能超过2000个字符")
    @ApiModelProperty(value = "审批人员职工号", name = "approvalCodes")
    @FieldRemark(column="APPROVAL_CODES", field="approvalCodes", name="审批人员职工号")
    private java.lang.String approvalCodes;

    /**
    * 审批人员姓名
    */
    @ExcelProperty("审批人员姓名")
    @Size(max = 2000, message = "审批人员姓名长度不能超过2000个字符")
    @ApiModelProperty(value = "审批人员姓名", name = "approvalNames")
    @FieldRemark(column="APPROVAL_NAMES", field="approvalNames", name="审批人员姓名")
    private java.lang.String approvalNames;

    /**
    * 是否可用
    */
    @ApiModelProperty(value = "是否可用", name = "ynValid")
    @FieldRemark(column="YN_VALID", field="ynValid", name="是否可用", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynValid;
    @ExcelProperty("是否可用")
    private java.lang.String ynValidName;


    /**
    * 备注
    */
    @ExcelProperty("备注")
    @Size(max = 256, message = "备注长度不能超过256个字符")
    @ApiModelProperty(value = "备注", name = "note")
    @FieldRemark(column="NOTE", field="note", name="备注")
    private java.lang.String note;

    /**
    * 预留字段01
    */
    @Size(max = 256, message = "预留字段01长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段01", name = "attribute01")
    @FieldRemark(column="ATTRIBUTE_01", field="attribute01", name="预留字段01")
    private java.lang.String attribute01;

    /**
    * 预留字段02
    */
    @Size(max = 256, message = "预留字段02长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段02", name = "attribute02")
    @FieldRemark(column="ATTRIBUTE_02", field="attribute02", name="预留字段02")
    private java.lang.String attribute02;

    /**
    * 预留字段03
    */
    @Size(max = 256, message = "预留字段03长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段03", name = "attribute03")
    @FieldRemark(column="ATTRIBUTE_03", field="attribute03", name="预留字段03")
    private java.lang.String attribute03;

    /**
    * 预留字段04
    */
    @Size(max = 256, message = "预留字段04长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段04", name = "attribute04")
    @FieldRemark(column="ATTRIBUTE_04", field="attribute04", name="预留字段04")
    private java.lang.String attribute04;

    /**
    * 预留字段05
    */
    @Size(max = 256, message = "预留字段05长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段05", name = "attribute05")
    @FieldRemark(column="ATTRIBUTE_05", field="attribute05", name="预留字段05")
    private java.lang.String attribute05;

    /**
    * 预留字段06
    */
    @Size(max = 256, message = "预留字段06长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段06", name = "attribute06")
    @FieldRemark(column="ATTRIBUTE_06", field="attribute06", name="预留字段06")
    private java.lang.String attribute06;

    /**
    * 预留字段07
    */
    @Size(max = 256, message = "预留字段07长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段07", name = "attribute07")
    @FieldRemark(column="ATTRIBUTE_07", field="attribute07", name="预留字段07")
    private java.lang.String attribute07;

    /**
    * 预留字段08
    */
    @Size(max = 256, message = "预留字段08长度不能超过256个字符")
    @ApiModelProperty(value = "预留字段08", name = "attribute08")
    @FieldRemark(column="ATTRIBUTE_08", field="attribute08", name="预留字段08")
    private java.lang.String attribute08;
    /**
    * 预留字段09
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "预留字段09", name = "attribute09")
    @FieldRemark(column="ATTRIBUTE_09", field="attribute09", name="预留字段09")
    private java.util.Date attribute09;

    /**
    * 预留字段09起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute09Begin;

    /**
    * 预留字段09止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute09End;
    /**
    * 预留字段10
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "预留字段10", name = "attribute10")
    @FieldRemark(column="ATTRIBUTE_10", field="attribute10", name="预留字段10")
    private java.util.Date attribute10;

    /**
    * 预留字段10起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute10Begin;

    /**
    * 预留字段10止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute10End;

    /**
    * 密级
    */
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="密级", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private java.lang.String secretLevel;
    @ExcelProperty("密级")
    private java.lang.String secretLevelName;

    /**
    * 创建时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date creationDateBegin;

    /**
    * 创建时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date creationDateEnd;

    /**
    * 最后修改时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date lastUpdateDateBegin;

    /**
    * 最后修改时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date lastUpdateDateEnd;

    /**
    * 会议室设备ID
    */
    @ExcelProperty("会议室设备ID")
    @Size(max = 4000, message = "会议室设备ID长度不能超过4000个字符")
    @ApiModelProperty(value = "会议室设备ID", name = "mtDeviceIds")
    @FieldRemark(column="MT_DEVICE_IDS", field="mtDeviceIds", name="会议室设备ID")
    private java.lang.String mtDeviceIds;

    /**
    * 会议室设备编号
    */
    @ExcelProperty("会议室设备编号")
    @Size(max = 4000, message = "会议室设备编号长度不能超过4000个字符")
    @ApiModelProperty(value = "会议室设备编号", name = "mtDeviceCodes")
    @FieldRemark(column="MT_DEVICE_CODES", field="mtDeviceCodes", name="会议室设备编号")
    private java.lang.String mtDeviceCodes;

    /**
    * 会议室设备名称
    */
    @ExcelProperty("会议室设备名称")
    @Size(max = 4000, message = "会议室设备名称长度不能超过4000个字符")
    @ApiModelProperty(value = "会议室设备名称", name = "mtDeviceNames")
    @FieldRemark(column="MT_DEVICE_NAMES", field="mtDeviceNames", name="会议室设备名称")
    private java.lang.String mtDeviceNames;

    public java.lang.String getId(){
        return id;
    }

    public void setId(java.lang.String id){
        this.id = id;
    }

    public java.lang.String getName(){
        return name;
    }

    public void setName(java.lang.String name){
        this.name = name;
    }

    public java.lang.Long getCapacity(){
        return capacity;
    }

    public void setCapacity(java.lang.Long capacity){
        this.capacity = capacity;
        super.handleNullValue();
    }

    public java.lang.String getLocationId(){
        return locationId;
    }

    public void setLocationId(java.lang.String locationId){
        this.locationId = locationId;
    }

    public java.lang.String getLocationName(){
        return locationName;
    }

    public void setLocationName(java.lang.String locationName){
        this.locationName = locationName;
    }

    public java.lang.String getDescrption(){
        return descrption;
    }

    public void setDescrption(java.lang.String descrption){
        this.descrption = descrption;
    }

    public java.lang.String getAdminId(){
        return adminId;
    }

    public void setAdminId(java.lang.String adminId){
        this.adminId = adminId;
    }

    public java.lang.String getAdminIdAlias(){
        return adminIdAlias;
    }

    public void setAdminIdAlias(java.lang.String adminIdAlias){
        this.adminIdAlias = adminIdAlias;
    }

    public java.lang.String getAdminCode(){
        return adminCode;
    }

    public void setAdminCode(java.lang.String adminCode){
        this.adminCode = adminCode;
    }

    public java.lang.String getAdminName(){
        return adminName;
    }

    public void setAdminName(java.lang.String adminName){
        this.adminName = adminName;
    }

    public java.lang.String getAdminPhone(){
        return adminPhone;
    }

    public void setAdminPhone(java.lang.String adminPhone){
        this.adminPhone = adminPhone;
    }
    public java.lang.String getYnPublic(){
        return ynPublic;
    }

    public void setYnPublic(java.lang.String ynPublic){
        this.ynPublic = ynPublic;
    }

    public java.lang.String getYnPublicName(){
        return ynPublicName;
    }

    public void setYnPublicName(java.lang.String ynPublicName){
        this.ynPublicName = ynPublicName;
    }

    public java.lang.Long getOrderBy(){
        return orderBy;
    }

    public void setOrderBy(java.lang.Long orderBy){
        this.orderBy = orderBy;
        super.handleNullValue();
    }

    public java.lang.String getOwnerDeptId(){
        return ownerDeptId;
    }

    public void setOwnerDeptId(java.lang.String ownerDeptId){
        this.ownerDeptId = ownerDeptId;
    }

    public java.lang.String getOwnerDeptIdAlias(){
        return ownerDeptIdAlias;
    }

    public void setOwnerDeptIdAlias(java.lang.String ownerDeptIdAlias){
        this.ownerDeptIdAlias = ownerDeptIdAlias;
    }

    public java.lang.String getOwnerDeptCode(){
        return ownerDeptCode;
    }

    public void setOwnerDeptCode(java.lang.String ownerDeptCode){
        this.ownerDeptCode = ownerDeptCode;
    }

    public java.lang.String getOwnerDeptName(){
        return ownerDeptName;
    }

    public void setOwnerDeptName(java.lang.String ownerDeptName){
        this.ownerDeptName = ownerDeptName;
    }
    public java.lang.String getYnApprove(){
        return ynApprove;
    }

    public void setYnApprove(java.lang.String ynApprove){
        this.ynApprove = ynApprove;
    }

    public java.lang.String getYnApproveName(){
        return ynApproveName;
    }

    public void setYnApproveName(java.lang.String ynApproveName){
        this.ynApproveName = ynApproveName;
    }

    public java.lang.String getApprovalIds(){
        return approvalIds;
    }

    public void setApprovalIds(java.lang.String approvalIds){
        this.approvalIds = approvalIds;
    }

    public java.lang.String getApprovalIdsAlias(){
        return approvalIdsAlias;
    }

    public void setApprovalIdsAlias(java.lang.String approvalIdsAlias){
        this.approvalIdsAlias = approvalIdsAlias;
    }

    public java.lang.String getApprovalCodes(){
        return approvalCodes;
    }

    public void setApprovalCodes(java.lang.String approvalCodes){
        this.approvalCodes = approvalCodes;
    }

    public java.lang.String getApprovalNames(){
        return approvalNames;
    }

    public void setApprovalNames(java.lang.String approvalNames){
        this.approvalNames = approvalNames;
    }
    public java.lang.String getYnValid(){
        return ynValid;
    }

    public void setYnValid(java.lang.String ynValid){
        this.ynValid = ynValid;
    }

    public java.lang.String getYnValidName(){
        return ynValidName;
    }

    public void setYnValidName(java.lang.String ynValidName){
        this.ynValidName = ynValidName;
    }

    public java.lang.String getNote(){
        return note;
    }

    public void setNote(java.lang.String note){
        this.note = note;
    }

    public java.lang.String getAttribute01(){
        return attribute01;
    }

    public void setAttribute01(java.lang.String attribute01){
        this.attribute01 = attribute01;
    }

    public java.lang.String getAttribute02(){
        return attribute02;
    }

    public void setAttribute02(java.lang.String attribute02){
        this.attribute02 = attribute02;
    }

    public java.lang.String getAttribute03(){
        return attribute03;
    }

    public void setAttribute03(java.lang.String attribute03){
        this.attribute03 = attribute03;
    }

    public java.lang.String getAttribute04(){
        return attribute04;
    }

    public void setAttribute04(java.lang.String attribute04){
        this.attribute04 = attribute04;
    }

    public java.lang.String getAttribute05(){
        return attribute05;
    }

    public void setAttribute05(java.lang.String attribute05){
        this.attribute05 = attribute05;
    }

    public java.lang.String getAttribute06(){
        return attribute06;
    }

    public void setAttribute06(java.lang.String attribute06){
        this.attribute06 = attribute06;
    }

    public java.lang.String getAttribute07(){
        return attribute07;
    }

    public void setAttribute07(java.lang.String attribute07){
        this.attribute07 = attribute07;
    }

    public java.lang.String getAttribute08(){
        return attribute08;
    }

    public void setAttribute08(java.lang.String attribute08){
        this.attribute08 = attribute08;
    }

    public java.util.Date getAttribute09(){
        return attribute09;
    }

    public void setAttribute09(java.util.Date attribute09){
        this.attribute09 = attribute09;
        super.handleNullValue();
    }

    public java.util.Date getAttribute09Begin(){
        return attribute09Begin;
    }

    public void setAttribute09Begin(java.util.Date attribute09Begin){
        this.attribute09Begin = attribute09Begin;
    }

    public java.util.Date getAttribute09End(){
        return attribute09End;
    }

    public void setAttribute09End(java.util.Date attribute09End){
        this.attribute09End = attribute09End;
    }

    public java.util.Date getAttribute10(){
        return attribute10;
    }

    public void setAttribute10(java.util.Date attribute10){
        this.attribute10 = attribute10;
        super.handleNullValue();
    }

    public java.util.Date getAttribute10Begin(){
        return attribute10Begin;
    }

    public void setAttribute10Begin(java.util.Date attribute10Begin){
        this.attribute10Begin = attribute10Begin;
    }

    public java.util.Date getAttribute10End(){
        return attribute10End;
    }

    public void setAttribute10End(java.util.Date attribute10End){
        this.attribute10End = attribute10End;
    }
    public java.lang.String getSecretLevel(){
        return secretLevel;
    }

    public void setSecretLevel(java.lang.String secretLevel){
        this.secretLevel = secretLevel;
    }

    public java.lang.String getSecretLevelName(){
        return secretLevelName;
    }

    public void setSecretLevelName(java.lang.String secretLevelName){
        this.secretLevelName = secretLevelName;
    }

    public java.util.Date getCreationDateBegin(){
        return creationDateBegin;
    }

    public void setCreationDateBegin(java.util.Date creationDateBegin){
        this.creationDateBegin = creationDateBegin;
    }

    public java.util.Date getCreationDateEnd(){
        return creationDateEnd;
    }

    public void setCreationDateEnd(java.util.Date creationDateEnd){
        this.creationDateEnd = creationDateEnd;
    }

    public java.util.Date getLastUpdateDateBegin(){
        return lastUpdateDateBegin;
    }

    public void setLastUpdateDateBegin(java.util.Date lastUpdateDateBegin){
        this.lastUpdateDateBegin = lastUpdateDateBegin;
    }

    public java.util.Date getLastUpdateDateEnd(){
        return lastUpdateDateEnd;
    }

    public void setLastUpdateDateEnd(java.util.Date lastUpdateDateEnd){
        this.lastUpdateDateEnd = lastUpdateDateEnd;
    }

    public java.lang.String getMtDeviceIds(){
        return mtDeviceIds;
    }

    public void setMtDeviceIds(java.lang.String mtDeviceIds){
        this.mtDeviceIds = mtDeviceIds;
    }

    public java.lang.String getMtDeviceCodes(){
        return mtDeviceCodes;
    }

    public void setMtDeviceCodes(java.lang.String mtDeviceCodes){
        this.mtDeviceCodes = mtDeviceCodes;
    }

    public java.lang.String getMtDeviceNames(){
        return mtDeviceNames;
    }

    public void setMtDeviceNames(java.lang.String mtDeviceNames){
        this.mtDeviceNames = mtDeviceNames;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "会议室台账表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "会议室台账表";
        } else {
            return super.logTitle;
        }
    }

    @Override
    public PlatformConstant.LogType getLogType() {
        if (super.logType == null) {
            return PlatformConstant.LogType.module_operate;
        } else {
            return super.logType;
        }
    }
}
