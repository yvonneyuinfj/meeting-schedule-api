package avicit.myportal.event.event.dto;

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
* @创建时间： 2024-01-16 13:47
* @类说明：日程表DTO
* @修改记录：
*/
@ApiModel(value = "EventDTO", description = "日程表")
@PojoRemark(table="EVENT", object="EventDTO", name="日程表")
@ExcelIgnoreUnannotated
public class EventDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * 日程ID
    */
    @Id
    @Size(max = 50, message = "日程ID长度不能超过50个字符")
    @ApiModelProperty(value = "日程ID", name = "id")
    @FieldRemark(column="ID", field="id", name="日程ID")
    private java.lang.String id;

    /**
    * 日程主题
    */
    @LogField
    @NotBlank(message = "日程主题不能为空")
    @ExcelProperty("日程主题")
    @Size(max = 100, message = "日程主题长度不能超过100个字符")
    @ApiModelProperty(value = "日程主题", name = "name")
    @FieldRemark(column="NAME", field="name", name="日程主题")
    private java.lang.String name;
    /**
    * 作者
    */
    @Size(max = 50, message = "作者长度不能超过50个字符")
    @ApiModelProperty(value = "作者", name = "authorId")
    @FieldRemark(column="AUTHOR_ID", field="authorId", name="作者", dataType="user")
    private java.lang.String authorId;

    /**
    * 作者显示字段
    */
    @ExcelProperty("作者")
    private java.lang.String authorIdAlias;

    /**
    * 作者职工号
    */
    @ExcelProperty("作者职工号")
    @Size(max = 50, message = "作者职工号长度不能超过50个字符")
    @ApiModelProperty(value = "作者职工号", name = "authorCode")
    @FieldRemark(column="AUTHOR_CODE", field="authorCode", name="作者职工号")
    private java.lang.String authorCode;

    /**
    * 作者
    */
    @ExcelProperty("作者")
    @Size(max = 100, message = "作者长度不能超过100个字符")
    @ApiModelProperty(value = "作者", name = "authorName")
    @FieldRemark(column="AUTHOR_NAME", field="authorName", name="作者")
    private java.lang.String authorName;

    /**
    * 日程地点ID
    */
    @ExcelProperty("日程地点ID")
    @Size(max = 50, message = "日程地点ID长度不能超过50个字符")
    @ApiModelProperty(value = "日程地点ID", name = "placeId")
    @FieldRemark(column="PLACE_ID", field="placeId", name="日程地点ID")
    private java.lang.String placeId;

    /**
    * 日程地点
    */
    @ExcelProperty("日程地点")
    @Size(max = 100, message = "日程地点长度不能超过100个字符")
    @ApiModelProperty(value = "日程地点", name = "placeName")
    @FieldRemark(column="PLACE_NAME", field="placeName", name="日程地点")
    private java.lang.String placeName;
    /**
    * 开始时间
    */
    @ExcelProperty("开始时间")
    @DateTimeFormat("yyyy/MM/dd HH:mm")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty(value = "开始时间", name = "startTime")
    @FieldRemark(column="START_TIME", field="startTime", name="开始时间")
    private java.util.Date startTime;

    /**
    * 开始时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private java.util.Date startTimeBegin;

    /**
    * 开始时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private java.util.Date startTimeEnd;
    /**
    * 结束时间
    */
    @ExcelProperty("结束时间")
    @DateTimeFormat("yyyy/MM/dd HH:mm")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty(value = "结束时间", name = "endTime")
    @FieldRemark(column="END_TIME", field="endTime", name="结束时间")
    private java.util.Date endTime;

    /**
    * 结束时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private java.util.Date endTimeBegin;

    /**
    * 结束时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private java.util.Date endTimeEnd;

    /**
    * 是否可用
    */
    @ApiModelProperty(value = "是否可用", name = "ynValid")
    @FieldRemark(column="YN_VALID", field="ynValid", name="是否可用", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynValid;
    @ExcelProperty("是否可用")
    private java.lang.String ynValidName;


    /**
    * 是否公开
    */
    @ApiModelProperty(value = "是否公开", name = "ynPublic")
    @FieldRemark(column="YN_PUBLIC", field="ynPublic", name="是否公开", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynPublic;
    @ExcelProperty("是否公开")
    private java.lang.String ynPublicName;


    /**
    * 日程内容
    */
    @ExcelProperty("日程内容")
    @Size(max = 512, message = "日程内容长度不能超过512个字符")
    @ApiModelProperty(value = "日程内容", name = "content")
    @FieldRemark(column="CONTENT", field="content", name="日程内容")
    private java.lang.String content;

    /**
    * 日程类型
    */
    @ApiModelProperty(value = "日程类型", name = "type")
    @FieldRemark(column="TYPE", field="type", name="日程类型", dataType="lookup", lookupType="MYPORTAL_EVENT_TYPE")
    private java.lang.String type;
    @ExcelProperty("日程类型")
    private java.lang.String typeName;


    /**
    * 提醒类型
    */
    @ApiModelProperty(value = "提醒类型", name = "remindType")
    @FieldRemark(column="REMIND_TYPE", field="remindType", name="提醒类型", dataType="lookup", lookupType="MYPORTAL_REMIND_TYPE")
    private java.lang.String remindType;
    @ExcelProperty("提醒类型")
    private java.lang.String remindTypeName;

    /**
    * 共享给
    */
    @Size(max = 2000, message = "共享给长度不能超过2000个字符")
    @ApiModelProperty(value = "共享给", name = "sharedUserIds")
    @FieldRemark(column="SHARED_USER_IDS", field="sharedUserIds", name="共享给", dataType="user")
    private java.lang.String sharedUserIds;

    /**
    * 共享给显示字段
    */
    @ExcelProperty("共享给")
    private java.lang.String sharedUserIdsAlias;

    /**
    * 共享人员职工号
    */
    @ExcelProperty("共享人员职工号")
    @Size(max = 2000, message = "共享人员职工号长度不能超过2000个字符")
    @ApiModelProperty(value = "共享人员职工号", name = "sharedUserCodes")
    @FieldRemark(column="SHARED_USER_CODES", field="sharedUserCodes", name="共享人员职工号")
    private java.lang.String sharedUserCodes;

    /**
    * 共享人员姓名
    */
    @ExcelProperty("共享人员姓名")
    @Size(max = 2000, message = "共享人员姓名长度不能超过2000个字符")
    @ApiModelProperty(value = "共享人员姓名", name = "sharedUserNames")
    @FieldRemark(column="SHARED_USER_NAMES", field="sharedUserNames", name="共享人员姓名")
    private java.lang.String sharedUserNames;

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
    * 提醒天数
    */
    @ExcelProperty("提醒天数")
    @ColumnWidth(15)
    @NumberFormat("0")
    @Digits(integer = 2, fraction = 0, message = "提醒天数格式不正确")
    @ApiModelProperty(value = "提醒天数", name = "remindDuration")
    @FieldRemark(column="REMIND_DURATION", field="remindDuration", name="提醒天数")
    private java.lang.Long remindDuration;

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

    public java.lang.String getAuthorId(){
        return authorId;
    }

    public void setAuthorId(java.lang.String authorId){
        this.authorId = authorId;
    }

    public java.lang.String getAuthorIdAlias(){
        return authorIdAlias;
    }

    public void setAuthorIdAlias(java.lang.String authorIdAlias){
        this.authorIdAlias = authorIdAlias;
    }

    public java.lang.String getAuthorCode(){
        return authorCode;
    }

    public void setAuthorCode(java.lang.String authorCode){
        this.authorCode = authorCode;
    }

    public java.lang.String getAuthorName(){
        return authorName;
    }

    public void setAuthorName(java.lang.String authorName){
        this.authorName = authorName;
    }

    public java.lang.String getPlaceId(){
        return placeId;
    }

    public void setPlaceId(java.lang.String placeId){
        this.placeId = placeId;
    }

    public java.lang.String getPlaceName(){
        return placeName;
    }

    public void setPlaceName(java.lang.String placeName){
        this.placeName = placeName;
    }

    public java.util.Date getStartTime(){
        return startTime;
    }

    public void setStartTime(java.util.Date startTime){
        this.startTime = startTime;
        super.handleNullValue();
    }

    public java.util.Date getStartTimeBegin(){
        return startTimeBegin;
    }

    public void setStartTimeBegin(java.util.Date startTimeBegin){
        this.startTimeBegin = startTimeBegin;
    }

    public java.util.Date getStartTimeEnd(){
        return startTimeEnd;
    }

    public void setStartTimeEnd(java.util.Date startTimeEnd){
        this.startTimeEnd = startTimeEnd;
    }

    public java.util.Date getEndTime(){
        return endTime;
    }

    public void setEndTime(java.util.Date endTime){
        this.endTime = endTime;
        super.handleNullValue();
    }

    public java.util.Date getEndTimeBegin(){
        return endTimeBegin;
    }

    public void setEndTimeBegin(java.util.Date endTimeBegin){
        this.endTimeBegin = endTimeBegin;
    }

    public java.util.Date getEndTimeEnd(){
        return endTimeEnd;
    }

    public void setEndTimeEnd(java.util.Date endTimeEnd){
        this.endTimeEnd = endTimeEnd;
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

    public java.lang.String getContent(){
        return content;
    }

    public void setContent(java.lang.String content){
        this.content = content;
    }
    public java.lang.String getType(){
        return type;
    }

    public void setType(java.lang.String type){
        this.type = type;
    }

    public java.lang.String getTypeName(){
        return typeName;
    }

    public void setTypeName(java.lang.String typeName){
        this.typeName = typeName;
    }
    public java.lang.String getRemindType(){
        return remindType;
    }

    public void setRemindType(java.lang.String remindType){
        this.remindType = remindType;
    }

    public java.lang.String getRemindTypeName(){
        return remindTypeName;
    }

    public void setRemindTypeName(java.lang.String remindTypeName){
        this.remindTypeName = remindTypeName;
    }

    public java.lang.String getSharedUserIds(){
        return sharedUserIds;
    }

    public void setSharedUserIds(java.lang.String sharedUserIds){
        this.sharedUserIds = sharedUserIds;
    }

    public java.lang.String getSharedUserIdsAlias(){
        return sharedUserIdsAlias;
    }

    public void setSharedUserIdsAlias(java.lang.String sharedUserIdsAlias){
        this.sharedUserIdsAlias = sharedUserIdsAlias;
    }

    public java.lang.String getSharedUserCodes(){
        return sharedUserCodes;
    }

    public void setSharedUserCodes(java.lang.String sharedUserCodes){
        this.sharedUserCodes = sharedUserCodes;
    }

    public java.lang.String getSharedUserNames(){
        return sharedUserNames;
    }

    public void setSharedUserNames(java.lang.String sharedUserNames){
        this.sharedUserNames = sharedUserNames;
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

    public java.lang.Long getRemindDuration(){
        return remindDuration;
    }

    public void setRemindDuration(java.lang.Long remindDuration){
        this.remindDuration = remindDuration;
        super.handleNullValue();
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "日程表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "日程表";
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
