package avicit.myportal.meeting.meeting.dto;

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
* @创建时间： 2024-01-15 15:48
* @类说明：会议表DTO
* @修改记录：
*/
@ApiModel(value = "MeetingDTO", description = "会议表")
@PojoRemark(table="MEETING", object="MeetingDTO", name="会议表")
@ExcelIgnoreUnannotated
public class MeetingDTO extends BeanDTO{
    private static final long serialVersionUID = 1L;


    /**
    * 会议ID
    */
    @Id
    @Size(max = 64, message = "会议ID长度不能超过64个字符")
    @ApiModelProperty(value = "会议ID", name = "id")
    @FieldRemark(column="ID", field="id", name="会议ID")
    private java.lang.String id;

    /**
    * 会议名称
    */
    @LogField
    @NotBlank(message = "会议名称不能为空")
    @ExcelProperty("会议名称")
    @Size(max = 128, message = "会议名称长度不能超过128个字符")
    @ApiModelProperty(value = "会议名称", name = "name")
    @FieldRemark(column="NAME", field="name", name="会议名称")
    private java.lang.String name;
    /**
    * 作者
    */
    @Size(max = 64, message = "作者长度不能超过64个字符")
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
    @Size(max = 64, message = "作者职工号长度不能超过64个字符")
    @ApiModelProperty(value = "作者职工号", name = "authorCode")
    @FieldRemark(column="AUTHOR_CODE", field="authorCode", name="作者职工号")
    private java.lang.String authorCode;

    /**
    * 作者名称
    */
    @ExcelProperty("作者名称")
    @Size(max = 128, message = "作者名称长度不能超过128个字符")
    @ApiModelProperty(value = "作者名称", name = "authorName")
    @FieldRemark(column="AUTHOR_NAME", field="authorName", name="作者名称")
    private java.lang.String authorName;

    /**
    * 会议室
    */
    @Size(max = 64, message = "会议室长度不能超过64个字符")
    @ApiModelProperty(value = "会议室", name = "meetingRoomId")
    @FieldRemark(column="MEETING_ROOM_ID", field="meetingRoomId", name="会议室")
    private java.lang.String meetingRoomId;

    /**
    * 会议室显示字段
    */
    @ExcelProperty("会议室")
    private java.lang.String meetingRoomIdAlias;

    /**
    * 会议室名称
    */
    @ExcelProperty("会议室名称")
    @Size(max = 128, message = "会议室名称长度不能超过128个字符")
    @ApiModelProperty(value = "会议室名称", name = "meetingRoomName")
    @FieldRemark(column="MEETING_ROOM_NAME", field="meetingRoomName", name="会议室名称")
    private java.lang.String meetingRoomName;

    /**
    * 是否需要审批
    */
    @ApiModelProperty(value = "是否需要审批", name = "ynApprove")
    @FieldRemark(column="YN_APPROVE", field="ynApprove", name="是否需要审批", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynApprove;
    @ExcelProperty("是否需要审批")
    private java.lang.String ynApproveName;

    /**
    * 占用开始时间
    */
    @ExcelProperty("占用开始时间")
    @DateTimeFormat("yyyy/MM/dd")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "占用开始时间", name = "preStartTime")
    @FieldRemark(column="PRE_START_TIME", field="preStartTime", name="占用开始时间")
    private java.util.Date preStartTime;

    /**
    * 占用开始时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date preStartTimeBegin;

    /**
    * 占用开始时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date preStartTimeEnd;
    /**
    * 会议开始时间
    */
    @ExcelProperty("会议开始时间")
    @DateTimeFormat("yyyy/MM/dd")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "会议开始时间", name = "startTime")
    @FieldRemark(column="START_TIME", field="startTime", name="会议开始时间")
    private java.util.Date startTime;

    /**
    * 会议开始时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date startTimeBegin;

    /**
    * 会议开始时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date startTimeEnd;
    /**
    * 会议结束时间
    */
    @ExcelProperty("会议结束时间")
    @DateTimeFormat("yyyy/MM/dd")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "会议结束时间", name = "endTime")
    @FieldRemark(column="END_TIME", field="endTime", name="会议结束时间")
    private java.util.Date endTime;

    /**
    * 会议结束时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date endTimeBegin;

    /**
    * 会议结束时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date endTimeEnd;
    /**
    * 主持人
    */
    @Size(max = 64, message = "主持人长度不能超过64个字符")
    @ApiModelProperty(value = "主持人", name = "hostId")
    @FieldRemark(column="HOST_ID", field="hostId", name="主持人", dataType="user")
    private java.lang.String hostId;

    /**
    * 主持人显示字段
    */
    @ExcelProperty("主持人")
    private java.lang.String hostIdAlias;

    /**
    * 主持人职工号
    */
    @ExcelProperty("主持人职工号")
    @Size(max = 64, message = "主持人职工号长度不能超过64个字符")
    @ApiModelProperty(value = "主持人职工号", name = "hostCode")
    @FieldRemark(column="HOST_CODE", field="hostCode", name="主持人职工号")
    private java.lang.String hostCode;

    /**
    * 主持人名称
    */
    @ExcelProperty("主持人名称")
    @Size(max = 128, message = "主持人名称长度不能超过128个字符")
    @ApiModelProperty(value = "主持人名称", name = "hostName")
    @FieldRemark(column="HOST_NAME", field="hostName", name="主持人名称")
    private java.lang.String hostName;

    /**
    * 是否可用
    */
    @ApiModelProperty(value = "是否可用", name = "ynValid")
    @FieldRemark(column="YN_VALID", field="ynValid", name="是否可用", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private java.lang.String ynValid;
    @ExcelProperty("是否可用")
    private java.lang.String ynValidName;


    /**
    * 会议内容
    */
    @ExcelProperty("会议内容")
    @Size(max = 512, message = "会议内容长度不能超过512个字符")
    @ApiModelProperty(value = "会议内容", name = "content")
    @FieldRemark(column="CONTENT", field="content", name="会议内容")
    private java.lang.String content;

    /**
    * 会议类型
    */
    @ExcelProperty("会议类型")
    @Size(max = 64, message = "会议类型长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型", name = "meetingTypeId")
    @FieldRemark(column="MEETING_TYPE_ID", field="meetingTypeId", name="会议类型")
    private java.lang.String meetingTypeId;

    /**
    * 会议类型编号
    */
    @ExcelProperty("会议类型编号")
    @Size(max = 64, message = "会议类型编号长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型编号", name = "meetingTypeCode")
    @FieldRemark(column="MEETING_TYPE_CODE", field="meetingTypeCode", name="会议类型编号")
    private java.lang.String meetingTypeCode;

    /**
    * 会议类型
    */
    @ExcelProperty("会议类型")
    @Size(max = 128, message = "会议类型长度不能超过128个字符")
    @ApiModelProperty(value = "会议类型", name = "meetingTypeName")
    @FieldRemark(column="MEETING_TYPE_NAME", field="meetingTypeName", name="会议类型")
    private java.lang.String meetingTypeName;
    /**
    * 参会人员
    */
    @Size(max = 2000, message = "参会人员长度不能超过2000个字符")
    @ApiModelProperty(value = "参会人员", name = "attendeeIds")
    @FieldRemark(column="ATTENDEE_IDS", field="attendeeIds", name="参会人员", dataType="user")
    private java.lang.String attendeeIds;

    /**
    * 参会人员显示字段
    */
    @ExcelProperty("参会人员")
    private java.lang.String attendeeIdsAlias;

    /**
    * 参会人员职工号
    */
    @ExcelProperty("参会人员职工号")
    @Size(max = 2000, message = "参会人员职工号长度不能超过2000个字符")
    @ApiModelProperty(value = "参会人员职工号", name = "attendeeCodes")
    @FieldRemark(column="ATTENDEE_CODES", field="attendeeCodes", name="参会人员职工号")
    private java.lang.String attendeeCodes;

    /**
    * 参会人员姓名
    */
    @ExcelProperty("参会人员姓名")
    @Size(max = 2000, message = "参会人员姓名长度不能超过2000个字符")
    @ApiModelProperty(value = "参会人员姓名", name = "attendeeNames")
    @FieldRemark(column="ATTENDEE_NAMES", field="attendeeNames", name="参会人员姓名")
    private java.lang.String attendeeNames;

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
    private String dbid_; // 流程实例ID
    @ExcelProperty("流程当前步骤")
    private String activityalias_; // 节点中文名称
    private String activityname_; // 当前节点id
    @ExcelProperty("流程状态")
    private String businessstate_; // 流程当前状态
    private String currUserId; // 当前登录人ID
    private String bpmType;
    private String bpmState;
    @ExcelProperty("当前处理人")
    private String assigneenames_;

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

    public java.lang.String getMeetingRoomId(){
        return meetingRoomId;
    }

    public void setMeetingRoomId(java.lang.String meetingRoomId){
        this.meetingRoomId = meetingRoomId;
    }

    public java.lang.String getMeetingRoomIdAlias(){
        return meetingRoomIdAlias;
    }

    public void setMeetingRoomIdAlias(java.lang.String meetingRoomIdAlias){
        this.meetingRoomIdAlias = meetingRoomIdAlias;
    }

    public java.lang.String getMeetingRoomName(){
        return meetingRoomName;
    }

    public void setMeetingRoomName(java.lang.String meetingRoomName){
        this.meetingRoomName = meetingRoomName;
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

    public java.util.Date getPreStartTime(){
        return preStartTime;
    }

    public void setPreStartTime(java.util.Date preStartTime){
        this.preStartTime = preStartTime;
        super.handleNullValue();
    }

    public java.util.Date getPreStartTimeBegin(){
        return preStartTimeBegin;
    }

    public void setPreStartTimeBegin(java.util.Date preStartTimeBegin){
        this.preStartTimeBegin = preStartTimeBegin;
    }

    public java.util.Date getPreStartTimeEnd(){
        return preStartTimeEnd;
    }

    public void setPreStartTimeEnd(java.util.Date preStartTimeEnd){
        this.preStartTimeEnd = preStartTimeEnd;
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

    public java.lang.String getHostId(){
        return hostId;
    }

    public void setHostId(java.lang.String hostId){
        this.hostId = hostId;
    }

    public java.lang.String getHostIdAlias(){
        return hostIdAlias;
    }

    public void setHostIdAlias(java.lang.String hostIdAlias){
        this.hostIdAlias = hostIdAlias;
    }

    public java.lang.String getHostCode(){
        return hostCode;
    }

    public void setHostCode(java.lang.String hostCode){
        this.hostCode = hostCode;
    }

    public java.lang.String getHostName(){
        return hostName;
    }

    public void setHostName(java.lang.String hostName){
        this.hostName = hostName;
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

    public java.lang.String getContent(){
        return content;
    }

    public void setContent(java.lang.String content){
        this.content = content;
    }

    public java.lang.String getMeetingTypeId(){
        return meetingTypeId;
    }

    public void setMeetingTypeId(java.lang.String meetingTypeId){
        this.meetingTypeId = meetingTypeId;
    }

    public java.lang.String getMeetingTypeCode(){
        return meetingTypeCode;
    }

    public void setMeetingTypeCode(java.lang.String meetingTypeCode){
        this.meetingTypeCode = meetingTypeCode;
    }

    public java.lang.String getMeetingTypeName(){
        return meetingTypeName;
    }

    public void setMeetingTypeName(java.lang.String meetingTypeName){
        this.meetingTypeName = meetingTypeName;
    }

    public java.lang.String getAttendeeIds(){
        return attendeeIds;
    }

    public void setAttendeeIds(java.lang.String attendeeIds){
        this.attendeeIds = attendeeIds;
    }

    public java.lang.String getAttendeeIdsAlias(){
        return attendeeIdsAlias;
    }

    public void setAttendeeIdsAlias(java.lang.String attendeeIdsAlias){
        this.attendeeIdsAlias = attendeeIdsAlias;
    }

    public java.lang.String getAttendeeCodes(){
        return attendeeCodes;
    }

    public void setAttendeeCodes(java.lang.String attendeeCodes){
        this.attendeeCodes = attendeeCodes;
    }

    public java.lang.String getAttendeeNames(){
        return attendeeNames;
    }

    public void setAttendeeNames(java.lang.String attendeeNames){
        this.attendeeNames = attendeeNames;
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

    public String getDbid_() {
        return dbid_;
    }

    public void setDbid_(String dbid_) {
        this.dbid_ = dbid_;
    }

    public String getActivityalias_() {
        return activityalias_;
    }

    public void setActivityalias_(String activityalias_) {
        this.activityalias_ = activityalias_;
    }

    public String getActivityname_() {
        return activityname_;
    }

    public void setActivityname_(String activityname_) {
        this.activityname_ = activityname_;
    }

    public String getBusinessstate_() {
        return businessstate_;
    }

    public void setBusinessstate_(String businessstate_) {
        this.businessstate_ = businessstate_;
    }

    public String getCurrUserId() {
        return currUserId;
    }

    public void setCurrUserId(String currUserId) {
        this.currUserId = currUserId;
    }

    public String getBpmType() {
        return bpmType;
    }

    public void setBpmType(String bpmType) {
        this.bpmType = bpmType;
    }

    public String getBpmState() {
        return bpmState;
    }

    public void setBpmState(String bpmState) {
        this.bpmState = bpmState;
    }
    public String getAssigneenames_() {
        return assigneenames_;
    }

    public void setAssigneenames_(String assigneenames_) {
        this.assigneenames_ = assigneenames_;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "会议表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "会议表";
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
