package avicit.myportal.meeting.meeting.dto;

import avicit.platform6.core.annotation.log.FieldRemark;
import avicit.platform6.core.annotation.log.PojoRemark;
import avicit.platform6.core.annotation.log.Id;
import avicit.platform6.core.annotation.log.LogField;
import avicit.platform6.core.domain.BeanDTO;
import avicit.myportal.meeting.meetinguser.dto.MeetingUserDTO;
import avicit.platform6.core.domain.ValidationList;
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
* @创建时间： 2024-01-19 16:06
* @类说明：会议日程信息表DTO
* @修改记录：
*/
@ApiModel(value = "MeetingDTO", description = "会议日程信息表")
@PojoRemark(table="MEETING", object="MeetingDTO", name="会议日程信息表")
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
    private String id;

    /**
    * 会议名称
    */
    @LogField
    @NotBlank(message = "会议名称不能为空")
    @ExcelProperty("会议名称")
    @Size(max = 128, message = "会议名称长度不能超过128个字符")
    @ApiModelProperty(value = "会议名称", name = "name")
    @FieldRemark(column="NAME", field="name", name="会议名称")
    private String name;
    /**
    * 创建人
    */
    @Size(max = 64, message = "创建人长度不能超过64个字符")
    @ApiModelProperty(value = "创建人", name = "authorId")
    @FieldRemark(column="AUTHOR_ID", field="authorId", name="创建人", dataType="user")
    private String authorId;

    /**
    * 创建人显示字段
    */
    @ExcelProperty("创建人")
    private String authorIdAlias;

    /**
    * 作者代码
    */
    @Size(max = 64, message = "作者代码长度不能超过64个字符")
    @ApiModelProperty(value = "作者代码", name = "authorCode")
    @FieldRemark(column="AUTHOR_CODE", field="authorCode", name="作者代码")
    private String authorCode;

    /**
    * 作者姓名
    */
    @Size(max = 64, message = "作者姓名长度不能超过64个字符")
    @ApiModelProperty(value = "作者姓名", name = "authorName")
    @FieldRemark(column="AUTHOR_NAME", field="authorName", name="作者姓名")
    private String authorName;

    /**
    * 会议室ID
    */
    @Size(max = 64, message = "会议室ID长度不能超过64个字符")
    @ApiModelProperty(value = "会议室ID", name = "meetingRoomId")
    @FieldRemark(column="MEETING_ROOM_ID", field="meetingRoomId", name="会议室ID")
    private String meetingRoomId;

    /**
    * 会议室ID显示字段
    */
    @ExcelProperty("会议室")
    private String meetingRoomIdAlias;

    /**
    * 会议室名称
    */
    @Size(max = 128, message = "会议室名称长度不能超过128个字符")
    @ApiModelProperty(value = "会议室名称", name = "meetingRoomName")
    @FieldRemark(column="MEETING_ROOM_NAME", field="meetingRoomName", name="会议室名称")
    private String meetingRoomName;

    /**
    * 地点ID
    */
    @Size(max = 64, message = "地点ID长度不能超过64个字符")
    @ApiModelProperty(value = "地点ID", name = "placeId")
    @FieldRemark(column="PLACE_ID", field="placeId", name="地点ID")
    private String placeId;

    /**
    * 地点名称
    */
    @Size(max = 128, message = "地点名称长度不能超过128个字符")
    @ApiModelProperty(value = "地点名称", name = "placeName")
    @FieldRemark(column="PLACE_NAME", field="placeName", name="地点名称")
    private String placeName;

    /**
    * 是否需要审批
    */
    @ApiModelProperty(value = "是否需要审批", name = "ynApprove")
    @FieldRemark(column="YN_APPROVE", field="ynApprove", name="是否需要审批", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private String ynApprove;
    @ExcelProperty("是否需要审批")
    private String ynApproveName;

    /**
    * 审批人ID
    */
    @Size(max = 2000, message = "审批人ID长度不能超过2000个字符")
    @ApiModelProperty(value = "审批人ID", name = "approvalIds")
    @FieldRemark(column="APPROVAL_IDS", field="approvalIds", name="审批人ID", dataType="user")
    private String approvalIds;

    /**
    * 审批人ID显示字段
    */
    private String approvalIdsAlias;

    /**
    * 审批人代码
    */
    @Size(max = 2000, message = "审批人代码长度不能超过2000个字符")
    @ApiModelProperty(value = "审批人代码", name = "approvalCodes")
    @FieldRemark(column="APPROVAL_CODES", field="approvalCodes", name="审批人代码")
    private String approvalCodes;

    /**
    * 审批人名称
    */
    @ExcelProperty("审批人")
    @Size(max = 2000, message = "审批人名称长度不能超过2000个字符")
    @ApiModelProperty(value = "审批人名称", name = "approvalNames")
    @FieldRemark(column="APPROVAL_NAMES", field="approvalNames", name="审批人名称")
    private String approvalNames;

    /**
    * 审批状态
    */
    @Size(max = 2000, message = "审批状态长度不能超过2000个字符")
    @ApiModelProperty(value = "审批状态", name = "approvalState")
    @FieldRemark(column="APPROVAL_STATE", field="approvalState", name="审批状态")
    private String approvalState;
    /**
    * 占用开始时间
    */
    @ExcelProperty("占用开始时间")
    @DateTimeFormat("yyyy/MM/dd HH:mm")
    @ColumnWidth(20)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    @ApiModelProperty(value = "占用开始时间", name = "preStartTime")
    @FieldRemark(column="PRE_START_TIME", field="preStartTime", name="占用开始时间")
    private java.util.Date preStartTime;

    /**
    * 占用开始时间起
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private java.util.Date preStartTimeBegin;

    /**
    * 占用开始时间止
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private java.util.Date preStartTimeEnd;
    /**
    * 开始时间
    */
    @ExcelProperty("会议开始时间")
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
    @ExcelProperty("预计结束时间")
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
    * 主持人
    */
    @Size(max = 64, message = "主持人长度不能超过64个字符")
    @ApiModelProperty(value = "主持人", name = "hostId")
    @FieldRemark(column="HOST_ID", field="hostId", name="主持人", dataType="user")
    private String hostId;

    /**
    * 主持人显示字段
    */
    private String hostIdAlias;

    /**
    * 主持人代码
    */
    @Size(max = 64, message = "主持人代码长度不能超过64个字符")
    @ApiModelProperty(value = "主持人代码", name = "hostCode")
    @FieldRemark(column="HOST_CODE", field="hostCode", name="主持人代码")
    private String hostCode;

    /**
    * 主持人姓名
    */
    @ExcelProperty("主持人")
    @Size(max = 128, message = "主持人姓名长度不能超过128个字符")
    @ApiModelProperty(value = "主持人姓名", name = "hostName")
    @FieldRemark(column="HOST_NAME", field="hostName", name="主持人姓名")
    private String hostName;

    /**
    * 组织部门
    */
    @Size(max = 64, message = "组织部门长度不能超过64个字符")
    @ApiModelProperty(value = "组织部门", name = "orgDeptId")
    @FieldRemark(column="ORG_DEPT_ID", field="orgDeptId", name="组织部门", dataType="dept")
    private String orgDeptId;

    /**
    * 组织部门显示字段
    */
    private String orgDeptIdAlias;

    /**
    * 组织部门代码
    */
    @Size(max = 64, message = "组织部门代码长度不能超过64个字符")
    @ApiModelProperty(value = "组织部门代码", name = "orgDeptCode")
    @FieldRemark(column="ORG_DEPT_CODE", field="orgDeptCode", name="组织部门代码")
    private String orgDeptCode;

    /**
    * 组织部门名称
    */
    @ExcelProperty("组织部门")
    @Size(max = 64, message = "组织部门名称长度不能超过64个字符")
    @ApiModelProperty(value = "组织部门名称", name = "orgDeptName")
    @FieldRemark(column="ORG_DEPT_NAME", field="orgDeptName", name="组织部门名称")
    private String orgDeptName;

    /**
    * 是否有效
    */
    @ApiModelProperty(value = "是否有效", name = "ynValid")
    @FieldRemark(column="YN_VALID", field="ynValid", name="是否有效", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private String ynValid;
    private String ynValidName;


    /**
    * 是否公开
    */
    @ApiModelProperty(value = "是否公开", name = "ynPublic")
    @FieldRemark(column="YN_PUBLIC", field="ynPublic", name="是否公开", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private String ynPublic;
    @ExcelProperty("是否公开")
    private String ynPublicName;


    /**
    * 是否新地点
    */
    @ApiModelProperty(value = "是否新地点", name = "ynNewPlace")
    @FieldRemark(column="YN_NEW_PLACE", field="ynNewPlace", name="是否新地点", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private String ynNewPlace;
    private String ynNewPlaceName;


    /**
    * 是否显示日程
    */
    @ApiModelProperty(value = "是否显示日程", name = "ynShowEvent")
    @FieldRemark(column="YN_SHOW_EVENT", field="ynShowEvent", name="是否显示日程", dataType="lookup", lookupType="PLATFORM_YES_NO_FLAG")
    private String ynShowEvent;
    @ExcelProperty("是否显示日程")
    private String ynShowEventName;


    /**
    * 联系电话
    */
    @ExcelProperty("联系电话")
    @Size(max = 50, message = "联系电话长度不能超过50个字符")
    @ApiModelProperty(value = "联系电话", name = "phoneNumber")
    @FieldRemark(column="PHONE_NUMBER", field="phoneNumber", name="联系电话")
    private String phoneNumber;

    /**
    * 会议内容
    */
    @ExcelProperty("会议内容")
    @Size(max = 512, message = "会议内容长度不能超过512个字符")
    @ApiModelProperty(value = "会议内容", name = "content")
    @FieldRemark(column="CONTENT", field="content", name="会议内容")
    private String content;

    /**
    * 会议类型
    */

    @Size(max = 64, message = "会议类型长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型", name = "meetingTypeId")
    @FieldRemark(column="MEETING_TYPE_ID", field="meetingTypeId", name="会议类型")
    private String meetingTypeId;

    /**
    * 会议类型代码
    */
    @Size(max = 64, message = "会议类型代码长度不能超过64个字符")
    @ApiModelProperty(value = "会议类型代码", name = "meetingTypeCode")
    @FieldRemark(column="MEETING_TYPE_CODE", field="meetingTypeCode", name="会议类型代码")
    private String meetingTypeCode;

    /**
    * 会议类型名称
    */
    @ExcelProperty("会议类型")
    @Size(max = 128, message = "会议类型名称长度不能超过128个字符")
    @ApiModelProperty(value = "会议类型名称", name = "meetingTypeName")
    @FieldRemark(column="MEETING_TYPE_NAME", field="meetingTypeName", name="会议类型名称")
    private String meetingTypeName;
    /**
    * 参会人员IDs
    */
    @Size(max = 2000, message = "参会人员IDs长度不能超过2000个字符")
    @ApiModelProperty(value = "参会人员IDs", name = "attendeeIds")
    @FieldRemark(column="ATTENDEE_IDS", field="attendeeIds", name="参会人员IDs", dataType="user")
    private String attendeeIds;

    /**
    * 参会人员IDs显示字段
    */
    private String attendeeIdsAlias;

    /**
    * 参会人员代码
    */
    @Size(max = 2000, message = "参会人员代码长度不能超过2000个字符")
    @ApiModelProperty(value = "参会人员代码", name = "attendeeCodes")
    @FieldRemark(column="ATTENDEE_CODES", field="attendeeCodes", name="参会人员代码")
    private String attendeeCodes;

    /**
    * 参会人员名称
    */
    @ExcelProperty("参会人员")
    @Size(max = 2000, message = "参会人员名称长度不能超过2000个字符")
    @ApiModelProperty(value = "参会人员名称", name = "attendeeNames")
    @FieldRemark(column="ATTENDEE_NAMES", field="attendeeNames", name="参会人员名称")
    private String attendeeNames;

    /**
    * 日程类型
    */
    @ApiModelProperty(value = "日程类型", name = "eventType")
    @FieldRemark(column="EVENT_TYPE", field="eventType", name="日程类型", dataType="lookup", lookupType="MYPORTAL_EVENT_TYPE")
    private String eventType;
    @ExcelProperty("日程类型")
    private String eventTypeName;


    /**
    * 待办提醒
    */
    @ApiModelProperty(value = "待办提醒", name = "remindType")
    @FieldRemark(column="REMIND_TYPE", field="remindType", name="待办提醒", dataType="lookup", lookupType="MYPORTAL_REMIND_TYPE")
    private String remindType;
    @ExcelProperty("待办提醒")
    private String remindTypeName;

    /**
    * 提醒天数
    */
    @ExcelProperty("提醒天数")
    @ColumnWidth(15)
    @NumberFormat("0")
    @Digits(integer = 2, fraction = 0, message = "提醒天数格式不正确")
    @ApiModelProperty(value = "提醒天数", name = "remindDuration")
    @FieldRemark(column="REMIND_DURATION", field="remindDuration", name="提醒天数")
    private Long remindDuration;

    /**
    * 背景颜色
    */
    @Size(max = 10, message = "背景颜色长度不能超过10个字符")
    @ApiModelProperty(value = "背景颜色", name = "bgColor")
    @FieldRemark(column="BG_COLOR", field="bgColor", name="背景颜色")
    private String bgColor;

    /**
    * 备注
    */
    @Size(max = 256, message = "备注长度不能超过256个字符")
    @ApiModelProperty(value = "备注", name = "note")
    @FieldRemark(column="NOTE", field="note", name="备注")
    private String note;

    /**
    * 扩展属性01
    */
    @Size(max = 256, message = "扩展属性01长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性01", name = "attribute01")
    @FieldRemark(column="ATTRIBUTE_01", field="attribute01", name="扩展属性01")
    private String attribute01;

    /**
    * 扩展属性02
    */
    @Size(max = 256, message = "扩展属性02长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性02", name = "attribute02")
    @FieldRemark(column="ATTRIBUTE_02", field="attribute02", name="扩展属性02")
    private String attribute02;

    /**
    * 扩展属性03
    */
    @Size(max = 256, message = "扩展属性03长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性03", name = "attribute03")
    @FieldRemark(column="ATTRIBUTE_03", field="attribute03", name="扩展属性03")
    private String attribute03;

    /**
    * 扩展属性04
    */
    @Size(max = 256, message = "扩展属性04长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性04", name = "attribute04")
    @FieldRemark(column="ATTRIBUTE_04", field="attribute04", name="扩展属性04")
    private String attribute04;

    /**
    * 扩展属性05
    */
    @Size(max = 256, message = "扩展属性05长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性05", name = "attribute05")
    @FieldRemark(column="ATTRIBUTE_05", field="attribute05", name="扩展属性05")
    private String attribute05;

    /**
    * 扩展属性06
    */
    @Size(max = 256, message = "扩展属性06长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性06", name = "attribute06")
    @FieldRemark(column="ATTRIBUTE_06", field="attribute06", name="扩展属性06")
    private String attribute06;

    /**
    * 扩展属性07
    */
    @Size(max = 256, message = "扩展属性07长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性07", name = "attribute07")
    @FieldRemark(column="ATTRIBUTE_07", field="attribute07", name="扩展属性07")
    private String attribute07;

    /**
    * 扩展属性08
    */
    @Size(max = 256, message = "扩展属性08长度不能超过256个字符")
    @ApiModelProperty(value = "扩展属性08", name = "attribute08")
    @FieldRemark(column="ATTRIBUTE_08", field="attribute08", name="扩展属性08")
    private String attribute08;
    /**
    * 扩展属性09
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "扩展属性09", name = "attribute09")
    @FieldRemark(column="ATTRIBUTE_09", field="attribute09", name="扩展属性09")
    private java.util.Date attribute09;

    /**
    * 扩展属性09起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute09Begin;

    /**
    * 扩展属性09止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute09End;
    /**
    * 扩展属性10
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "扩展属性10", name = "attribute10")
    @FieldRemark(column="ATTRIBUTE_10", field="attribute10", name="扩展属性10")
    private java.util.Date attribute10;

    /**
    * 扩展属性10起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute10Begin;

    /**
    * 扩展属性10止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date attribute10End;

    /**
    * 密级
    */
    @FieldRemark(column="SECRET_LEVEL", field="secretLevel", name="密级", dataType="lookup", lookupType="PLATFORM_FILE_SECRET_LEVEL")
    private String secretLevel;
    @ExcelProperty("密级")
    private String secretLevelName;

    /**
    * 创建日期起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date creationDateBegin;

    /**
    * 创建日期止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date creationDateEnd;

    /**
    * 最后更新日期起
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date lastUpdateDateBegin;

    /**
    * 最后更新日期止
    */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private java.util.Date lastUpdateDateEnd;
    /**
     * 子表对象集合
     */
    private ValidationList<MeetingUserDTO> meetingUserList;
    private String dbid_; // 流程实例ID
//    @ExcelProperty("流程当前步骤")
    private String activityalias_; // 节点中文名称
    private String activityname_; // 当前节点id
//    @ExcelProperty("流程状态")
    private String businessstate_; // 流程当前状态
    private String currUserId; // 当前登录人ID
    private String bpmType;
    private String bpmState;
//    @ExcelProperty("当前处理人")
    private String assigneenames_;

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthorId(){
        return authorId;
    }

    public void setAuthorId(String authorId){
        this.authorId = authorId;
    }

    public String getAuthorIdAlias(){
        return authorIdAlias;
    }

    public void setAuthorIdAlias(String authorIdAlias){
        this.authorIdAlias = authorIdAlias;
    }

    public String getAuthorCode(){
        return authorCode;
    }

    public void setAuthorCode(String authorCode){
        this.authorCode = authorCode;
    }

    public String getAuthorName(){
        return authorName;
    }

    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public String getMeetingRoomId(){
        return meetingRoomId;
    }

    public void setMeetingRoomId(String meetingRoomId){
        this.meetingRoomId = meetingRoomId;
    }

    public String getMeetingRoomIdAlias(){
        return meetingRoomIdAlias;
    }

    public void setMeetingRoomIdAlias(String meetingRoomIdAlias){
        this.meetingRoomIdAlias = meetingRoomIdAlias;
    }

    public String getMeetingRoomName(){
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName){
        this.meetingRoomName = meetingRoomName;
    }

    public String getPlaceId(){
        return placeId;
    }

    public void setPlaceId(String placeId){
        this.placeId = placeId;
    }

    public String getPlaceName(){
        return placeName;
    }

    public void setPlaceName(String placeName){
        this.placeName = placeName;
    }
    public String getYnApprove(){
        return ynApprove;
    }

    public void setYnApprove(String ynApprove){
        this.ynApprove = ynApprove;
    }

    public String getYnApproveName(){
        return ynApproveName;
    }

    public void setYnApproveName(String ynApproveName){
        this.ynApproveName = ynApproveName;
    }

    public String getApprovalIds(){
        return approvalIds;
    }

    public void setApprovalIds(String approvalIds){
        this.approvalIds = approvalIds;
    }

    public String getApprovalIdsAlias(){
        return approvalIdsAlias;
    }

    public void setApprovalIdsAlias(String approvalIdsAlias){
        this.approvalIdsAlias = approvalIdsAlias;
    }

    public String getApprovalCodes(){
        return approvalCodes;
    }

    public void setApprovalCodes(String approvalCodes){
        this.approvalCodes = approvalCodes;
    }

    public String getApprovalNames(){
        return approvalNames;
    }

    public void setApprovalNames(String approvalNames){
        this.approvalNames = approvalNames;
    }

    public String getApprovalState(){
        return approvalState;
    }

    public void setApprovalState(String approvalState){
        this.approvalState = approvalState;
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

    public String getHostId(){
        return hostId;
    }

    public void setHostId(String hostId){
        this.hostId = hostId;
    }

    public String getHostIdAlias(){
        return hostIdAlias;
    }

    public void setHostIdAlias(String hostIdAlias){
        this.hostIdAlias = hostIdAlias;
    }

    public String getHostCode(){
        return hostCode;
    }

    public void setHostCode(String hostCode){
        this.hostCode = hostCode;
    }

    public String getHostName(){
        return hostName;
    }

    public void setHostName(String hostName){
        this.hostName = hostName;
    }

    public String getOrgDeptId(){
        return orgDeptId;
    }

    public void setOrgDeptId(String orgDeptId){
        this.orgDeptId = orgDeptId;
    }

    public String getOrgDeptIdAlias(){
        return orgDeptIdAlias;
    }

    public void setOrgDeptIdAlias(String orgDeptIdAlias){
        this.orgDeptIdAlias = orgDeptIdAlias;
    }

    public String getOrgDeptCode(){
        return orgDeptCode;
    }

    public void setOrgDeptCode(String orgDeptCode){
        this.orgDeptCode = orgDeptCode;
    }

    public String getOrgDeptName(){
        return orgDeptName;
    }

    public void setOrgDeptName(String orgDeptName){
        this.orgDeptName = orgDeptName;
    }
    public String getYnValid(){
        return ynValid;
    }

    public void setYnValid(String ynValid){
        this.ynValid = ynValid;
    }

    public String getYnValidName(){
        return ynValidName;
    }

    public void setYnValidName(String ynValidName){
        this.ynValidName = ynValidName;
    }
    public String getYnPublic(){
        return ynPublic;
    }

    public void setYnPublic(String ynPublic){
        this.ynPublic = ynPublic;
    }

    public String getYnPublicName(){
        return ynPublicName;
    }

    public void setYnPublicName(String ynPublicName){
        this.ynPublicName = ynPublicName;
    }
    public String getYnNewPlace(){
        return ynNewPlace;
    }

    public void setYnNewPlace(String ynNewPlace){
        this.ynNewPlace = ynNewPlace;
    }

    public String getYnNewPlaceName(){
        return ynNewPlaceName;
    }

    public void setYnNewPlaceName(String ynNewPlaceName){
        this.ynNewPlaceName = ynNewPlaceName;
    }
    public String getYnShowEvent(){
        return ynShowEvent;
    }

    public void setYnShowEvent(String ynShowEvent){
        this.ynShowEvent = ynShowEvent;
    }

    public String getYnShowEventName(){
        return ynShowEventName;
    }

    public void setYnShowEventName(String ynShowEventName){
        this.ynShowEventName = ynShowEventName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getMeetingTypeId(){
        return meetingTypeId;
    }

    public void setMeetingTypeId(String meetingTypeId){
        this.meetingTypeId = meetingTypeId;
    }

    public String getMeetingTypeCode(){
        return meetingTypeCode;
    }

    public void setMeetingTypeCode(String meetingTypeCode){
        this.meetingTypeCode = meetingTypeCode;
    }

    public String getMeetingTypeName(){
        return meetingTypeName;
    }

    public void setMeetingTypeName(String meetingTypeName){
        this.meetingTypeName = meetingTypeName;
    }

    public String getAttendeeIds(){
        return attendeeIds;
    }

    public void setAttendeeIds(String attendeeIds){
        this.attendeeIds = attendeeIds;
    }

    public String getAttendeeIdsAlias(){
        return attendeeIdsAlias;
    }

    public void setAttendeeIdsAlias(String attendeeIdsAlias){
        this.attendeeIdsAlias = attendeeIdsAlias;
    }

    public String getAttendeeCodes(){
        return attendeeCodes;
    }

    public void setAttendeeCodes(String attendeeCodes){
        this.attendeeCodes = attendeeCodes;
    }

    public String getAttendeeNames(){
        return attendeeNames;
    }

    public void setAttendeeNames(String attendeeNames){
        this.attendeeNames = attendeeNames;
    }
    public String getEventType(){
        return eventType;
    }

    public void setEventType(String eventType){
        this.eventType = eventType;
    }

    public String getEventTypeName(){
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName){
        this.eventTypeName = eventTypeName;
    }
    public String getRemindType(){
        return remindType;
    }

    public void setRemindType(String remindType){
        this.remindType = remindType;
    }

    public String getRemindTypeName(){
        return remindTypeName;
    }

    public void setRemindTypeName(String remindTypeName){
        this.remindTypeName = remindTypeName;
    }

    public Long getRemindDuration(){
        return remindDuration;
    }

    public void setRemindDuration(Long remindDuration){
        this.remindDuration = remindDuration;
        super.handleNullValue();
    }

    public String getBgColor(){
        return bgColor;
    }

    public void setBgColor(String bgColor){
        this.bgColor = bgColor;
    }

    public String getNote(){
        return note;
    }

    public void setNote(String note){
        this.note = note;
    }

    public String getAttribute01(){
        return attribute01;
    }

    public void setAttribute01(String attribute01){
        this.attribute01 = attribute01;
    }

    public String getAttribute02(){
        return attribute02;
    }

    public void setAttribute02(String attribute02){
        this.attribute02 = attribute02;
    }

    public String getAttribute03(){
        return attribute03;
    }

    public void setAttribute03(String attribute03){
        this.attribute03 = attribute03;
    }

    public String getAttribute04(){
        return attribute04;
    }

    public void setAttribute04(String attribute04){
        this.attribute04 = attribute04;
    }

    public String getAttribute05(){
        return attribute05;
    }

    public void setAttribute05(String attribute05){
        this.attribute05 = attribute05;
    }

    public String getAttribute06(){
        return attribute06;
    }

    public void setAttribute06(String attribute06){
        this.attribute06 = attribute06;
    }

    public String getAttribute07(){
        return attribute07;
    }

    public void setAttribute07(String attribute07){
        this.attribute07 = attribute07;
    }

    public String getAttribute08(){
        return attribute08;
    }

    public void setAttribute08(String attribute08){
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
    public String getSecretLevel(){
        return secretLevel;
    }

    public void setSecretLevel(String secretLevel){
        this.secretLevel = secretLevel;
    }

    public String getSecretLevelName(){
        return secretLevelName;
    }

    public void setSecretLevelName(String secretLevelName){
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

    public ValidationList<MeetingUserDTO> getMeetingUserList() {
        return meetingUserList;
    }

    public void setMeetingUserList(ValidationList<MeetingUserDTO> meetingUserList) {
        this.meetingUserList = meetingUserList;
    }

    @Override
    public String getLogFormName() {
        if (StringUtils.isEmpty(super.logFormName)) {
            return "会议日程信息表";
        } else {
            return super.logFormName;
        }
    }

    @Override
    public String getLogTitle() {
        if (StringUtils.isEmpty(super.logTitle)) {
            return "会议日程信息表";
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
