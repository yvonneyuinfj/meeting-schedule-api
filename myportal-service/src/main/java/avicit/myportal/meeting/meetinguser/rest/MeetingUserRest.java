package avicit.myportal.meeting.meetinguser.rest;

import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.core.domain.ValidationList;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.commons.utils.StringUtils;
import avicit.platform6.core.annotation.RequiresPermissions;
import avicit.myportal.meeting.meetinguser.dto.MeetingUserDTO;
import avicit.myportal.meeting.meetinguser.service.MeetingUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-19 15:13
 * @类说明：会议与参会人关系表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "meetingUser", description = "会议与参会人关系表")
@RequestMapping("/api/myportal/meeting/meetingusers")
public class MeetingUserRest {

    private static final Logger logger = LoggerFactory.getLogger(MeetingUserRest.class);

    @Autowired
    private MeetingUserService meetingUserService;

    /**
     * 按条件分页查询子表数据
     *
     * @param queryReqBean 查询条件
     * @return ResponseMsg<QueryRespBean<MeetingUserDTO>>
     */
    @PostMapping("/search-by-page/v1")
    @ApiOperation(value = "按条件分页查询子表数据")
    @RequiresPermissions("meetingUser:view")
    public ResponseMsg<QueryRespBean<MeetingUserDTO>> searchByPage(@ApiParam(value = "分页", name = "queryReqBean") @RequestBody QueryReqBean<MeetingUserDTO> queryReqBean) {
        ResponseMsg<QueryRespBean<MeetingUserDTO>> responseMsg = new ResponseMsg<>();
        QueryRespBean<MeetingUserDTO> result = meetingUserService.searchMeetingUserByPage(queryReqBean);
        responseMsg.setResponseBody(result);
        return responseMsg;
    }

    /**
     * 通过主表主键查询子表数据
     *
     * @param pid 父id
     * @return ResponseMsg<List<MeetingUserDTO>>
     */
    @GetMapping("/get-by-pid/{pid}/v1")
    @ApiOperation(value = "通过主表主键查询子表数据")
    public ResponseMsg<List<MeetingUserDTO>> getByPid(@ApiParam(value = "父id", name = "pid") @PathVariable("pid") String pid) {
        ResponseMsg<List<MeetingUserDTO>> responseMsg = new ResponseMsg<>();
        List<MeetingUserDTO> result = meetingUserService.queryMeetingUserByPid(pid);
        responseMsg.setResponseBody(result);
        return responseMsg;
    }

    /**
     * 通过子表主键查询数据
     *
     * @param id 主键id
     * @return ResponseMsg<MeetingUserDTO>
     */
    @GetMapping("/get/{id}/v1")
    @ApiOperation(value = "通过子表主键查询数据")
    @RequiresPermissions("meetingUser:edit")
    public ResponseMsg<MeetingUserDTO> getById(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
        ResponseMsg<MeetingUserDTO> responseMsg = new ResponseMsg<>();
        MeetingUserDTO meetingUserDTO = meetingUserService.queryMeetingUserByPrimaryKey(id);
        responseMsg.setResponseBody(meetingUserDTO);
        return responseMsg;
    }

    /**
     * 新增/编辑子表对象
     *
     * @param meetingUserDTOList 保存对象
     * @return ResponseMsg<String>
     */
    @PostMapping("/save/v1")
    @ApiOperation(value = "新增/编辑子表对象")
    public ResponseMsg<String> save(@ApiParam(value = "保存对象", name = "meetingUserDTO") @Validated @RequestBody ValidationList<MeetingUserDTO> meetingUserDTOList) {
        ResponseMsg<String> responseMsg = new ResponseMsg<>();
        for(MeetingUserDTO meetingUserDTODTO : meetingUserDTOList){
            if (StringUtils.isEmpty(meetingUserDTODTO.getId())) {
                meetingUserDTODTO.setOrgIdentity(ThreadContextHelper.getOrgId());
                responseMsg.setResponseBody(meetingUserService.insertMeetingUser(meetingUserDTODTO));
            } else {
                responseMsg.setResponseBody(String.valueOf(meetingUserService.updateMeetingUser(meetingUserDTODTO)));
            }
        }
        return responseMsg;
    }

    /**
     * 修改子表部分对象字段
     *
     * @param meetingUserDTO 更新对象
     * @return ResponseMsg<Integer>
     */
    @PutMapping("/update/v1")
    @ApiOperation(value = "修改子表部分对象字段")
    public ResponseMsg<Integer> updateSensitive(@ApiParam(value = "更新对象", name = "meetingUserDTO")  @RequestBody MeetingUserDTO meetingUserDTO) {
        ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
        responseMsg.setResponseBody(meetingUserService.updateMeetingUserSensitive(meetingUserDTO));
        return responseMsg;
    }

    /**
     * 修改子表全部对象字段
     *
     * @param meetingUserDTO 更新对象
     * @return ResponseMsg<Integer>
     */
    @PutMapping("/update-all/v1")
    @ApiOperation(value = "修改子表全部对象字段")
    public ResponseMsg<Integer> updateAll(@ApiParam(value = "更新对象", name = "meetingUserDTO") @Validated @RequestBody MeetingUserDTO meetingUserDTO) {
        ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
        responseMsg.setResponseBody(meetingUserService.updateMeetingUser(meetingUserDTO));
        return responseMsg;
    }

    /**
     * 按主键单条删除
     *
     * @param id 主键id
     * @return ResponseMsg<Integer>
     */
    @DeleteMapping("/delete-by-id/{id}/v1")
    @ApiOperation(value = "按主键单条删除")
    public ResponseMsg<Integer> deleteById(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
        ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
        responseMsg.setResponseBody(meetingUserService.deleteMeetingUserById(id));
        return responseMsg;
    }

    /**
     * 子表数据批量删除
     *
     * @param ids 主键id串
     * @return ResponseMsg<Integer>
     */
    @DeleteMapping("/delete-by-ids/v1")
    @ApiOperation(value = "子表数据批量删除")
    @RequiresPermissions("meetingUser:del")
    public ResponseMsg<Integer> deleteByIds(@ApiParam(value = "id数组", name = "ids") @RequestBody String[] ids) {
        ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
        responseMsg.setResponseBody(meetingUserService.deleteMeetingUserByIds(ids));
        return responseMsg;
    }
}