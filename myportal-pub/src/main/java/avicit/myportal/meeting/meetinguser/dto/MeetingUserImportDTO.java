package avicit.myportal.meeting.meetinguser.dto;

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
* @创建时间： 2024-01-19 08:50
* @类说明：会议与参会人关系表导入DTO
* @修改记录：
*/
@ExcelIgnoreUnannotated
@Data
public class MeetingUserImportDTO{
private static final long serialVersionUID = 1L;
    /**
    * MEETING_ID
    */
    @NotBlank(message = "MEETING_ID不能为空")
    @ExcelProperty("MEETING_ID")
    @Size(max = 50, message = "MEETING_ID长度不能超过50个字符")
    private String meetingId;
    /**
    * USER_ID
    */
    @NotBlank(message = "USER_ID不能为空")
    @ExcelProperty("USER_ID")
    @Size(max = 50, message = "USER_ID长度不能超过50个字符")
    private String userId;
    /**
    * ATTRIBUTE_01
    */
    @Size(max = 256, message = "ATTRIBUTE_01长度不能超过256个字符")
    private String attribute01;
    /**
    * ATTRIBUTE_02
    */
    @Size(max = 256, message = "ATTRIBUTE_02长度不能超过256个字符")
    private String attribute02;
    /**
    * ATTRIBUTE_03
    */
    @Size(max = 256, message = "ATTRIBUTE_03长度不能超过256个字符")
    private String attribute03;
    /**
    * ATTRIBUTE_04
    */
    @Size(max = 256, message = "ATTRIBUTE_04长度不能超过256个字符")
    private String attribute04;
    /**
    * ATTRIBUTE_05
    */
    @Size(max = 256, message = "ATTRIBUTE_05长度不能超过256个字符")
    private String attribute05;
    /**
    * ATTRIBUTE_06
    */
    @Size(max = 256, message = "ATTRIBUTE_06长度不能超过256个字符")
    private String attribute06;
    /**
    * ATTRIBUTE_07
    */
    @Size(max = 256, message = "ATTRIBUTE_07长度不能超过256个字符")
    private String attribute07;
    /**
    * ATTRIBUTE_08
    */
    @Size(max = 256, message = "ATTRIBUTE_08长度不能超过256个字符")
    private String attribute08;
    /**
    * ATTRIBUTE_09
    */

    @DateTimeStr(format ="yyyy/MM/dd", message = "【ATTRIBUTE_09】格式错误，正确格式为：yyyy/MM/dd")
    private String attribute09;
    /**
    * ATTRIBUTE_10
    */

    @DateTimeStr(format ="yyyy/MM/dd", message = "【ATTRIBUTE_10】格式错误，正确格式为：yyyy/MM/dd")
    private String attribute10;
    @ExcelProperty("SECRET_LEVEL")
    private String secretLevelName;

    @ColumnWidth(30)
    @ContentFontStyle(color=10)
    @ExcelProperty("错误信息")
    private String errorInfo;
}
