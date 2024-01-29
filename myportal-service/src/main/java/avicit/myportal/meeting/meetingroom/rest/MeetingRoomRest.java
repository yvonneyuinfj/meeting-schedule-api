package avicit.myportal.meeting.meetingroom.rest;

import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.core.excel.ExcelUtil;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.core.annotation.RequiresPermissions;
import avicit.platform6.commons.utils.StringUtils;
import avicit.myportal.meeting.meetingroom.dto.MeetingRoomDTO;
import avicit.myportal.meeting.meetingroom.dto.MeetingRoomImportDTO;
import avicit.platform6.core.excel.imp.entity.ExcelImportResult;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import avicit.myportal.meeting.meetingroom.service.MeetingRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.shiro.authz.annotation.Logical;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-15 15:35
 * @类说明：会议室台账表Rest
 * @修改记录：
 */
@RestController
@Api(tags = "meetingRoom", description = "会议室台账表")
@RequestMapping("/api/myportal/meeting/meetingrooms")
	public class MeetingRoomRest {

	private static final Logger logger = LoggerFactory.getLogger(MeetingRoomRest.class);

	@Autowired
	private MeetingRoomService meetingRoomService;


	/**
	 * 按条件分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<QueryRespBean<MeetingRoomDTO>>
	 */
	@PostMapping("/search-by-page/v1")
	@ApiOperation(value = "按条件分页查询")
	@RequiresPermissions("meetingRoom:view")
	public ResponseMsg<QueryRespBean<MeetingRoomDTO>> searchByPage(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<MeetingRoomDTO> queryReqBean) {
		ResponseMsg<QueryRespBean<MeetingRoomDTO>> responseMsg = new ResponseMsg<>();
		QueryRespBean<MeetingRoomDTO> result = meetingRoomService.searchMeetingRoomByPage(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	/**
	 * 按条件不分页查询
	 *
	 * @param queryReqBean 查询条件
	 * @return ResponseMsg<List<MeetingRoomDTO>>
	 */
	@PostMapping("/search/v1")
	@ApiOperation(value = "按条件不分页查询")
	public ResponseMsg<List<MeetingRoomDTO>> search(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<MeetingRoomDTO> queryReqBean) {
		ResponseMsg<List<MeetingRoomDTO>> responseMsg = new ResponseMsg<>();
		List<MeetingRoomDTO> result = meetingRoomService.searchMeetingRoom(queryReqBean);
		responseMsg.setResponseBody(result);
		return responseMsg;
	}

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id 主键id
	 * @return ResponseMsg<MeetingRoomDTO>
	 */
	@GetMapping("/get/{id}/v1")
	@ApiOperation(value = "通过主键查询单条记录")
	@RequiresPermissions("meetingRoom:edit")
	public ResponseMsg<MeetingRoomDTO> get(@ApiParam(value = "主键id", name = "id") @PathVariable("id") String id) {
		ResponseMsg<MeetingRoomDTO> responseMsg = new ResponseMsg<>();
		MeetingRoomDTO meetingRoom = meetingRoomService.queryMeetingRoomByPrimaryKey(id);
		responseMsg.setResponseBody(meetingRoom);
		return responseMsg;
	}

	/**
	 * 保存对象
	 *
	 * @param meetingRoom 保存对象
	 * @return ResponseMsg<String>
	 */
	@PostMapping("/save/v1")
	@ApiOperation(value = "新增对象")
	@RequiresPermissions(value={"meetingRoom:edit","meetingRoom:add"},logical= Logical.OR)
	public ResponseMsg<String> save(@ApiParam(value = "保存对象", name = "meetingRoom") @Validated @RequestBody MeetingRoomDTO meetingRoom) {
		ResponseMsg<String> responseMsg = new ResponseMsg<>();
		if (StringUtils.isEmpty(meetingRoom.getId())) {
			meetingRoom.setOrgIdentity(ThreadContextHelper.getOrgId());
			responseMsg.setResponseBody(meetingRoomService.insertMeetingRoom(meetingRoom));
		}else{
			responseMsg.setResponseBody(String.valueOf(meetingRoomService.updateMeetingRoom(meetingRoom)));
		}
		return responseMsg;
	}

    /**
	 * 修改部分对象字段
	 *
	 * @param meetingRoom 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-sensitive/v1")
	@ApiOperation(value = "修改部分对象字段")
	public ResponseMsg<Integer> updateSensitive(@ApiParam(value = "修改对象", name = "meetingRoom")  @RequestBody MeetingRoomDTO meetingRoom) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(meetingRoomService.updateMeetingRoomSensitive(meetingRoom));
		return responseMsg;
	}

	/**
	 * 修改全部对象字段
	 *
	 * @param meetingRoom 修改对象
	 * @return ResponseMsg<Integer>
	 */
	@PutMapping("/update-all/v1")
	@ApiOperation(value = "修改全部对象字段")
	public ResponseMsg<Integer> updateAll(@ApiParam(value = "修改对象", name = "meetingRoom") @Validated @RequestBody MeetingRoomDTO meetingRoom) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(meetingRoomService.updateMeetingRoom(meetingRoom));
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
		responseMsg.setResponseBody(meetingRoomService.deleteMeetingRoomById(id));
		return responseMsg;
	}

	/**
	 * 批量删除
	 *
	 * @param ids 逗号分隔的id串
	 * @return ResponseMsg<Integer>
	 */
	@DeleteMapping("/delete-by-ids/v1")
	@ApiOperation(value = "批量删除")
	@RequiresPermissions("meetingRoom:del")
	public ResponseMsg<Integer> deleteByIds(@ApiParam(value = "id数组", name = "ids") @RequestBody String[] ids) {
		ResponseMsg<Integer> responseMsg = new ResponseMsg<>();
		responseMsg.setResponseBody(meetingRoomService.deleteMeetingRoomByIds(ids));
		return responseMsg;
	}

    @GetMapping("/downloadTemplate/v1")
	@ApiOperation(value = "模板下载")
	public void downloadTemplate(HttpServletResponse response) throws IOException {
		// 输出
		ExcelUtil.downloadTemplate(response, MeetingRoomImportDTO.class,"导入模板");
	}

	/**
	 * 导入数据
	 * @param multipartFile 导入文件
	 * @return
	 */
	@PostMapping(value = "/importData/v1", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiOperation(value = "导入文件")
	@RequiresPermissions("meetingRoom:import")
	public ResponseMsg<Object> importDataV2(@ApiParam(value = "Excel 文件", name = "impExcelFile") @RequestPart("impExcelFile") MultipartFile multipartFile,@RequestParam("formData") String formData) throws Exception {
		ResponseMsg<Object> responseMsg = new ResponseMsg<>();
		List<Map<String, String>> excelList = ExcelUtil.importExcel(multipartFile.getInputStream(), MeetingRoomImportDTO.class);
		ExcelImportResult excelImportResult = meetingRoomService.importData(excelList,formData);
		responseMsg.setResponseBody(excelImportResult);
		return responseMsg;
	}

	/**
	 * 按条件导出所有
	 * @param queryReqBean 查询条件
	 * @return
	 */
	@PostMapping("/exportData/v1")
	@ApiOperation(value = "按条件导出所有")
	@RequiresPermissions("meetingRoom:export")
	public void exportData(@ApiParam(value = "查询条件", name = "queryReqBean") @RequestBody QueryReqBean<MeetingRoomDTO> queryReqBean, HttpServletResponse response) throws IOException {
        List<MeetingRoomDTO> list = meetingRoomService.searchMeetingRoomForExportExcel(queryReqBean);
		// 输出
		ExcelUtil.exportExcel(response, list, MeetingRoomDTO.class,"会议室数据导出", "会议室信息" );
	}
}
