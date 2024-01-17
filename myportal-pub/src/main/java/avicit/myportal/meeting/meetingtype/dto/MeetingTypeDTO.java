package avicit.myportal.meeting.meetingtype.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.annotation.log.Id;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.domain.BaseTreeDTO;
import avicit.platform6.core.properties.PlatformConstant;
import avicit.platform6.commons.utils.StringUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-15 15:21
* @类说明：会议类型表Dto
* @修改记录：
*/
@ApiModel(value = "MeetingTypeDTO", description = "会议类型表")
@PojoRemark(table="MEETING_TYPE", object="MeetingTypeDTO", name="会议类型表")
public class MeetingTypeDTO extends BaseTreeDTO {
    private static final long serialVersionUID = 1L;

    /**
    * 会议类型ID
    */
    @Id
    @Size(max = 64, message = "会议类型ID长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型ID", name = "id")
    @FieldRemark(column="ID", field="id", name="会议类型ID")
    private java.lang.String id;

    /**
    * 父节点ID
    */
    @LogField
    @ApiModelProperty(value = "父节点ID", name = "parentId")
    @FieldRemark(column="PARENT_ID", field="parentId", name="父节点ID")
    private java.lang.String parentId;

    /**
    * 父节点ID显示字段
    */
    private java.lang.String parentTypeName;

    /**
    * 会议类型编号
    */
    @Size(max = 64, message = "会议类型编号长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型编号", name = "typeCode")
    @FieldRemark(column="TYPE_CODE", field="typeCode", name="会议类型编号")
    private java.lang.String typeCode;

    /**
    * 会议类型名称
    */
    @NotBlank(message = "会议类型名称不能为空")
    @Size(max = 64, message = "会议类型名称长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型名称", name = "typeName")
    @FieldRemark(column="TYPE_NAME", field="typeName", name="会议类型名称")
    private java.lang.String typeName;

    /**
    * 备注
    */
    @Size(max = 512, message = "备注长度不能超过512个字符")
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
    @ApiModelProperty(value = "密级", name = "secretLevel")
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="密级", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private java.lang.String secretLevel;
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

    public java.lang.String getId(){
        return id;
    }

    public void setId(java.lang.String id){
        this.id = id;
    }

    public java.lang.String getParentId(){
        return parentId;
    }

    public void setParentId(java.lang.String parentId){
        this.parentId = parentId;
    }

    public java.lang.String getParentTypeName(){
        return parentTypeName;
    }

    public void setParentTypeName(java.lang.String parentTypeName){
        this.parentTypeName = parentTypeName;
    }

    public java.lang.String getTypeCode(){
        return typeCode;
    }

    public void setTypeCode(java.lang.String typeCode){
        this.typeCode = typeCode;
    }

    public java.lang.String getTypeName(){
        return typeName;
    }

    public void setTypeName(java.lang.String typeName){
        this.typeName = typeName;
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

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
             return "会议类型表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "会议类型表";
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