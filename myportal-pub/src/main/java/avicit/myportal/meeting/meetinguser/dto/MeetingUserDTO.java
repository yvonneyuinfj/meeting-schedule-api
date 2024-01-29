package avicit.myportal.meeting.meetinguser.dto;

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
* @创建时间： 2024-01-19 15:13
* @类说明：会议与参会人关系表DTO
* @修改记录：
*/
@ApiModel(value = "MeetingUserDTO", description = "会议与参会人关系表")
@PojoRemark(table="MEETING_USER", object="MeetingUserDTO", name="会议与参会人关系表")
@ExcelIgnoreUnannotated
public class MeetingUserDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * ID
    */
    @Id
    @Size(max = 50, message = "ID长度不能超过50个字符")
    @ApiModelProperty(value = "ID", name = "id")
    @FieldRemark(column="ID", field="id", name="ID")
    private java.lang.String id;

    /**
    * MEETING_ID
    */
    @LogField
    @NotBlank(message = "MEETING_ID不能为空")
    @Size(max = 50, message = "MEETING_ID长度不能超过50个字符")
    @ApiModelProperty(value = "MEETING_ID", name = "meetingId")
    @FieldRemark(column="MEETING_ID", field="meetingId", name="MEETING_ID")
    private java.lang.String meetingId;

    /**
    * USER_ID
    */
    @LogField
    @NotBlank(message = "USER_ID不能为空")
    @ExcelProperty("USER_ID")
    @Size(max = 50, message = "USER_ID长度不能超过50个字符")
    @ApiModelProperty(value = "USER_ID", name = "userId")
    @FieldRemark(column="USER_ID", field="userId", name="USER_ID")
    private java.lang.String userId;

    /**
    * ATTRIBUTE_01
    */
    @Size(max = 256, message = "ATTRIBUTE_01长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_01", name = "attribute01")
    @FieldRemark(column="ATTRIBUTE_01", field="attribute01", name="ATTRIBUTE_01")
    private java.lang.String attribute01;

    /**
    * ATTRIBUTE_02
    */
    @Size(max = 256, message = "ATTRIBUTE_02长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_02", name = "attribute02")
    @FieldRemark(column="ATTRIBUTE_02", field="attribute02", name="ATTRIBUTE_02")
    private java.lang.String attribute02;

    /**
    * ATTRIBUTE_03
    */
    @Size(max = 256, message = "ATTRIBUTE_03长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_03", name = "attribute03")
    @FieldRemark(column="ATTRIBUTE_03", field="attribute03", name="ATTRIBUTE_03")
    private java.lang.String attribute03;

    /**
    * ATTRIBUTE_04
    */
    @Size(max = 256, message = "ATTRIBUTE_04长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_04", name = "attribute04")
    @FieldRemark(column="ATTRIBUTE_04", field="attribute04", name="ATTRIBUTE_04")
    private java.lang.String attribute04;

    /**
    * ATTRIBUTE_05
    */
    @Size(max = 256, message = "ATTRIBUTE_05长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_05", name = "attribute05")
    @FieldRemark(column="ATTRIBUTE_05", field="attribute05", name="ATTRIBUTE_05")
    private java.lang.String attribute05;

    /**
    * ATTRIBUTE_06
    */
    @Size(max = 256, message = "ATTRIBUTE_06长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_06", name = "attribute06")
    @FieldRemark(column="ATTRIBUTE_06", field="attribute06", name="ATTRIBUTE_06")
    private java.lang.String attribute06;

    /**
    * ATTRIBUTE_07
    */
    @Size(max = 256, message = "ATTRIBUTE_07长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_07", name = "attribute07")
    @FieldRemark(column="ATTRIBUTE_07", field="attribute07", name="ATTRIBUTE_07")
    private java.lang.String attribute07;

    /**
    * ATTRIBUTE_08
    */
    @Size(max = 256, message = "ATTRIBUTE_08长度不能超过256个字符")
    @ApiModelProperty(value = "ATTRIBUTE_08", name = "attribute08")
    @FieldRemark(column="ATTRIBUTE_08", field="attribute08", name="ATTRIBUTE_08")
    private java.lang.String attribute08;
    /**
    * ATTRIBUTE_09
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "ATTRIBUTE_09", name = "attribute09")
    @FieldRemark(column="ATTRIBUTE_09", field="attribute09", name="ATTRIBUTE_09")
    private java.util.Date attribute09;

    /**
    * ATTRIBUTE_09起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute09Begin;

    /**
    * ATTRIBUTE_09止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute09End;
    /**
    * ATTRIBUTE_10
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "ATTRIBUTE_10", name = "attribute10")
    @FieldRemark(column="ATTRIBUTE_10", field="attribute10", name="ATTRIBUTE_10")
    private java.util.Date attribute10;

    /**
    * ATTRIBUTE_10起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute10Begin;

    /**
    * ATTRIBUTE_10止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute10End;

    /**
    * SECRET_LEVEL
    */
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="SECRET_LEVEL", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private java.lang.String secretLevel;
    @ExcelProperty("SECRET_LEVEL")
    private java.lang.String secretLevelName;

    /**
    * CREATION_DATE起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date creationDateBegin;

    /**
    * CREATION_DATE止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date creationDateEnd;

    /**
    * LAST_UPDATE_DATE起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date lastUpdateDateBegin;

    /**
    * LAST_UPDATE_DATE止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date lastUpdateDateEnd;
    /**
     * 子表操作类型：insert-新增(可省略), update-更新, delete-删除
     */
    private String operationType_;

    public java.lang.String getId(){
        return id;
    }

    public void setId(java.lang.String id){
        this.id = id;
    }

    public java.lang.String getMeetingId(){
        return meetingId;
    }

    public void setMeetingId(java.lang.String meetingId){
        this.meetingId = meetingId;
    }

    public java.lang.String getUserId(){
        return userId;
    }

    public void setUserId(java.lang.String userId){
        this.userId = userId;
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

    public String getOperationType_() {
        return operationType_;
    }

    public void setOperationType_(String operationType_) {
        this.operationType_ = operationType_;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "会议与参会人关系表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "会议与参会人关系表";
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
