package avicit.myportal.meeting.meetingtype.rest;

import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.core.domain.VueNode;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.commons.utils.StringUtils;
import avicit.platform6.core.annotation.RequiresPermissions;
import avicit.myportal.meeting.meetingtype.dto.MeetingTypeDTO;
import avicit.myportal.meeting.meetingtype.service.MeetingTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.validation.annotation.Validated;

import java.util.*;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-15 15:21
 * @类说明：会议类型表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "meetingType", description = "会议类型表")
@RequestMapping("/api/myportal/meeting/meetingtypes")
public class MeetingTypeRest {

    private static final Logger logger = LoggerFactory.getLogger(MeetingTypeRest.class);

    @Autowired
    private MeetingTypeService meetingTypeService;

    /**
     * 加载树
     * @param level 树的等级
     * @param id    父节点ID
     * @return ResponseMsg<List<VueNode>>
     */
    @GetMapping("/get-tree/{level}/{id}/v1")
    @ApiOperation(value = "加载树")
    @RequiresPermissions("meetingType:view")
    public ResponseMsg<List<VueNode>> getTree(@ApiParam(value = "展开的树的层级", name = "level") @PathVariable("level") int level, @ApiParam(value = "父节点ID", name = "id") @PathVariable("id") String id) {
        ResponseMsg<List<VueNode>> responseMsg = new ResponseMsg<>();
        if (id == null) {
            id = "-1";
        }
        if (level == 0) {
            level = 1;
        }
        responseMsg.setResponseBody(meetingTypeService.getMeetingTypeByParentId(id, level, ThreadContextHelper.getOrgId()));
        return responseMsg;
    }

    /**
     * 查询树节点
     * @param paramMap 查询条件
     * @return ResponseMsg<List<VueNode>>
     */
    @PostMapping("/search/v1")
    @ApiOperation(value = "查询树节点")
    public ResponseMsg<List<VueNode>> searchMeetingType(@ApiParam(value = "查询条件", name = "paramMap") @RequestBody Map<String, String> paramMap) {
        String searchText = paramMap.get("searchText");
        List<VueNode> res = meetingTypeService.searchMeetingType(searchText, ThreadContextHelper.getOrgId());
        ResponseMsg<List<VueNode>> responseMsg = new ResponseMsg<>();
        responseMsg.setResponseBody(res);
        return responseMsg;
    }

    /**
     * 通过树主键获取单条记录
     *
     * @param id 主键id
     * @return ResponseMsg<MeetingTypeDTO>
     */
    @GetMapping("/get/{id}/v1")
    @ApiOperation(value = "通过树主键获取单条记录")
    @RequiresPermissions("meetingType:edit")
    public ResponseMsg<MeetingTypeDTO> getMeetingTypeDtoById(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
        ResponseMsg<MeetingTypeDTO> responseMsg = new ResponseMsg<>();
        MeetingTypeDTO meetingType = meetingTypeService.getMeetingTypeDtoById(id);
        responseMsg.setResponseBody(meetingType);
        return responseMsg;
    }

    /**
     * 加载至指定节点
     * @param id id
     * @return ResponseMsg<VueNode>
     */
    @GetMapping("/expand-tree/{id}/v1")
    @ApiOperation(value = "加载至指定节点")
    public ResponseMsg<List<VueNode>> expandMeetingTypeById(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
        ResponseMsg<List<VueNode>> responseMsg = new ResponseMsg<>();
        List<VueNode> vueNodes = meetingTypeService.expandMeetingTypeById(id);
        responseMsg.setResponseBody(vueNodes);
        return responseMsg;
    }
    /**
     * 新增树对象
     *
     * @param meetingType 插入对象
     * @return ResponseMsg<VueNode>
     */
    @PostMapping("/save/v1")
    @ApiOperation(value = "新增树对象")
    @RequiresPermissions(value={"meetingType:edit","meetingType:add"},logical= Logical.OR)
    public ResponseMsg<VueNode> saveMain(@ApiParam(value = "插入对象", name = "meetingType") @Validated @RequestBody  MeetingTypeDTO meetingType) {
        ResponseMsg<VueNode> responseMsg = new ResponseMsg<>();
        VueNode node = new VueNode();
        if (StringUtils.isEmpty(meetingType.getId())) {
            meetingType.setOrgIdentity(ThreadContextHelper.getOrgId());
            meetingTypeService.insertMeetingType(meetingType);
            node.setSelectable(true);
            node.setDisabled(false);
            node.setDisableCheckbox(false);
            node.setIsLeaf(true);
        } else {
            meetingTypeService.updateMeetingType(meetingType);
        }
        node.setId(meetingType.getId());
        node.setKey(meetingType.getId());
        node.setTitle(meetingType.getTypeName());
        node.setParentId(meetingType.getParentId());
        responseMsg.setResponseBody(node);
        return responseMsg;
    }

    /**
     * 按树主键单条删除
     *
     * @param id 主键id
     * @return ResponseMsg<Integer>
     */
    @DeleteMapping("/delete-by-id/{id}/v1")
    @ApiOperation(value = "按树主键单条删除")
    @RequiresPermissions("meetingType:del")
    public ResponseMsg<Integer> deleteMainById(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) throws Exception {
        ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
        responseMsg.setResponseBody(meetingTypeService.deleteMeetingTypeById(id));
        return responseMsg;
    }
}
