package avicit.myportal.meeting.meetingroom.service;

import avicit.myportal.meeting.meetingroom.dao.MeetingRoomDAO;
import avicit.myportal.meeting.meetingroom.dto.MeetingRoomDTO;
import avicit.myportal.meeting.meetingroom.dto.MeetingRoomImportDTO;
import avicit.platform6.api.system.ConvertColumnClient;
import avicit.platform6.api.system.impl.SystemConstant;
import avicit.platform6.commons.utils.*;
import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.core.excel.ExcelUtil;
import avicit.platform6.core.excel.Map2Bo;
import avicit.platform6.core.excel.imp.entity.ExcelImportResult;
import avicit.platform6.core.exception.BusinessException;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.Page;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-15 15:35
* @类说明：会议室台账表Service
* @修改记录：
*/
@Service
public class MeetingRoomService {

	private static final Logger logger = LoggerFactory.getLogger(MeetingRoomService.class);

	@Autowired
	private MeetingRoomDAO meetingRoomDAO;



	@Autowired
	private ConvertColumnClient convertColumnClient;

	/**
	* 按条件分页查询
	* @param queryReqBean 查询条件
	* @return QueryRespBean<MeetingRoomDTO>
	*/
	@Transactional(readOnly = true)
	public QueryRespBean<MeetingRoomDTO> searchMeetingRoomByPage(QueryReqBean<MeetingRoomDTO> queryReqBean) {
		QueryRespBean<MeetingRoomDTO> queryRespBean = new QueryRespBean<>();
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), MeetingRoomDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingRoomDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingRoomDTO>() {
            });
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
        PageHelper.startPage(queryReqBean.getPageParameter());
		//文档密级
		List<String> wordSecret = ThreadContextHelper.getWordSecretList();

        Page<MeetingRoomDTO> result = meetingRoomDAO.searchMeetingRoomByPage(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret, queryReqBean.getSortExp(), queryReqBean.getKeyWord());
		valueConvert(result.getResult());
		queryRespBean.setResult(result);
		return queryRespBean;
	}

	/**
	* 按条件查询
	*
	* @param queryReqBean 查询条件
	* @return List<MeetingRoomDTO>
	*/
	@Transactional(readOnly = true)
	public List<MeetingRoomDTO> searchMeetingRoom(QueryReqBean<MeetingRoomDTO> queryReqBean) {
		QueryRespBean<MeetingRoomDTO> queryRespBean = new QueryRespBean<>();
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), MeetingRoomDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingRoomDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingRoomDTO>() {
            });
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
		//文档密级
		List<String> wordSecret = ThreadContextHelper.getWordSecretList();

        List<MeetingRoomDTO> dataList = meetingRoomDAO.searchMeetingRoom(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret);
		valueConvert(dataList);
		return dataList;
	}

	/**
	* 按条件导出
	*
	* @param queryReqBean 查询条件
	* @return List<MeetingRoomDTO>
	*/
	@Transactional(readOnly = true)
	public List<MeetingRoomDTO> searchMeetingRoomForExportExcel(QueryReqBean<MeetingRoomDTO> queryReqBean) {
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingRoomDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingRoomDTO>() {
            });
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
		List<MeetingRoomDTO> result=meetingRoomDAO.searchMeetingRoomForExportExcel(queryReqBean.getSearchParams(), ThreadContextHelper.getOrgId() , ThreadContextHelper.getWordSecretList(), queryReqBean.getSortExp(), queryReqBean.getKeyWord());
        valueConvert(result);
        return result;
	}

	/**
	* 通过主键查询单条记录
	*
	* @param id 主键id
	* @return MeetingRoomDTO
	*/
	@Transactional(readOnly = true)
	public MeetingRoomDTO queryMeetingRoomByPrimaryKey(String id) {
		MeetingRoomDTO meetingRoomDTO = meetingRoomDAO.findMeetingRoomById(id);
		//记录日志
		if (meetingRoomDTO != null) {
            valueConvert(Arrays.asList(meetingRoomDTO));
			SysLogUtil.log4Query(meetingRoomDTO);
		}
		return meetingRoomDTO;
	}

	/**
	* 新增对象
	*
	* @param meetingRoomDTO 保存对象
	* @return String
	*/
	@Transactional
	public String insertMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
		meetingRoomDTO.setId(ComUtil.getId());
		PojoUtil.setSysProperties(meetingRoomDTO, OpType.insert);
		meetingRoomDAO.insertMeetingRoom(meetingRoomDTO);
		//记录日志
		SysLogUtil.log4Insert(meetingRoomDTO);
		return meetingRoomDTO.getId();
	}

	/**
	* 批量新增对象
	*
	* @param dtoList 保存对象集合
	* @return int
	*/
	@Transactional
	public int insertMeetingRoomList(List<MeetingRoomDTO> dtoList) {
		for (MeetingRoomDTO meetingRoomDTO : dtoList) {
			meetingRoomDTO.setId(ComUtil.getId());
			PojoUtil.setSysProperties(meetingRoomDTO, OpType.insert);
		}
        ListUtils.partition(dtoList, 50).forEach(subList -> {
            meetingRoomDAO.insertMeetingRoomList(subList);
        });
        // 记录日志
        SysLogUtil.log4Insert(dtoList);
		return dtoList.size();
	}

	/**
	* 修改对象全部字段
	*
	* @param meetingRoomDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateMeetingRoom(MeetingRoomDTO meetingRoomDTO) {
        return updateDto(meetingRoomDTO, true);
	}

	/**
	* 修改对象部分字段
	*
	* @param meetingRoomDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateMeetingRoomSensitive(MeetingRoomDTO meetingRoomDTO) {
        return updateDto(meetingRoomDTO, false);
	}

	/**
	* 批量更新对象
	*
	* @param dtoList 修改对象集合
	* @return int
	*/
	@Transactional
	public int updateMeetingRoomList(List<MeetingRoomDTO> dtoList) {
		List<MeetingRoomDTO> updateList = new ArrayList<>();
        List<MeetingRoomDTO> oldList = new ArrayList<>();
		for (MeetingRoomDTO dto : dtoList) {
            MeetingRoomDTO old = findById(dto.getId());
            if (old == null) {
                throw new BusinessException("修改对象不存在!");
            }
            MeetingRoomDTO updateDto = (MeetingRoomDTO)old.clone();
            PojoUtil.setSysProperties(dto, OpType.update);
            PojoUtil.copyProperties(updateDto, dto, false);
			updateList.add(updateDto);
            oldList.add(old);
		}
		ListUtils.partition(updateList, 50).forEach(subList -> {
            meetingRoomDAO.updateMeetingRoomList(subList);
        });
		// 记录日志
        SysLogUtil.log4Update(updateList, oldList);
		return updateList.size();
	}

	/**
	* 按主键单条删除
	*
	* @param id 主键id
	* @return int
	*/
	@Transactional
	public int deleteMeetingRoomById(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new BusinessException("删除失败！传入的参数主键为null");
		}
		//记录日志
		MeetingRoomDTO meetingRoomDTO = findById(id);
		if (meetingRoomDTO == null) {
			throw new BusinessException("删除失败！对象不存在");
		}
		SysLogUtil.log4Delete(meetingRoomDTO);
		//删除业务数据
		int count = meetingRoomDAO.deleteMeetingRoomById(id);
		return count;
	}

	/**
	 * 批量删除数据
	 *
	 * @param ids id的数组
	 * @return int
	 */
	@Transactional
	public int deleteMeetingRoomByIds(String[] ids) {
		int result = 0;
		for (String id : ids) {
			deleteMeetingRoomById(id);
			result++;
		}
		return result;
	}

	/**
	* 日志专用，内部方法，不再记录日志
	*
	* @param id 主键id
	* @return MeetingRoomDTO
	*/
	private MeetingRoomDTO findById(String id) {
		return meetingRoomDAO.findMeetingRoomById(id);
	}
    /**
     * 内部方法，
     *
     * @param dto 修改对象
     * @param isUpdateAllProp 更新全部字段是为true，更新部分字段为false
     * @return
     */
    private int updateDto(MeetingRoomDTO dto, Boolean isUpdateAllProp) {
        if (dto == null) {
            throw new BusinessException("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException("修改对象的id不能为空！");
        }
        MeetingRoomDTO old = findById(dto.getId());
        if (old == null) {
            throw new BusinessException("修改对象不存在！");
        }
        MeetingRoomDTO updateDto = (MeetingRoomDTO)old.clone();
        PojoUtil.setSysProperties(dto, OpType.update);
        int ret = 0;
        if (isUpdateAllProp) {
            PojoUtil.copyProperties(updateDto, dto, false);
            ret = meetingRoomDAO.updateMeetingRoomAll(updateDto);
        } else {
            PojoUtil.copyProperties(updateDto, dto, true);
            ret = meetingRoomDAO.updateMeetingRoomSensitive(dto);
        }
        if (ret == 0) {
            throw new BusinessException("数据失效，请重新更新");
        }
        SysLogUtil.log4Update(updateDto, old);
        return ret;
    }
    /**
	 * 批量导入
	 * @return
	 */
	@Transactional
	public ExcelImportResult importData(List<Map<String, String>> dataList, String formData) {
		List<MeetingRoomDTO> meetingRoomSuccessList = new ArrayList<MeetingRoomDTO>();
		List<MeetingRoomImportDTO> meetingRoomErrList = new ArrayList<MeetingRoomImportDTO>();
		Map<String, Object> formDataMap = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(formData)){
			formDataMap = JsonUtil.parseJSON2Map(formData);
		}
		if(!CollectionUtils.isEmpty(dataList)){
            Map<String, Map<String, String>> convertResultData = this.assembleData(dataList);
			for (Map<String, String> data:dataList) {
				if(StringUtils.isEmpty(data.get("errorInfo"))){
					//业务校验
					MeetingRoomDTO dto = new MeetingRoomDTO();
					Map2Bo.map2Pojo(data,dto);
					meetingRoomSuccessList.add(dto);
				} else {
					MeetingRoomImportDTO dtoImp = new MeetingRoomImportDTO();
					Map2Bo.map2Pojo(data,dtoImp);
					meetingRoomErrList.add(dtoImp);
				}
			}
            this.revValueConvert(meetingRoomSuccessList, convertResultData);
			this.insertMeetingRoomList(meetingRoomSuccessList);
		}
		ExcelImportResult excelImport = ExcelUtil.createUploadHis(meetingRoomSuccessList,meetingRoomErrList,MeetingRoomImportDTO.class,formDataMap);
		return excelImport;
	}
	/**
	 * 通过平台API将字段值转换为名称，包括通用选择组件、通用代码
	 *
	 * @param meetingRoomDTOList
	 */
	private void valueConvert(List<MeetingRoomDTO> meetingRoomDTOList) {
		//循环组装请求数据
		Map<String, Set<String>> convertFormData = new HashMap<>();
		for (MeetingRoomDTO meetingRoom : meetingRoomDTOList) {
			BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, meetingRoom.getAdminId());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnPublic());
			BusinessUtil.createConvertSet(convertFormData, SystemConstant.DEPT, meetingRoom.getOwnerDeptId());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnApprove());
			BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, meetingRoom.getApprovalIds());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnValid());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", meetingRoom.getSecretLevel());
		}
		if (convertFormData.size() > 0) {
			//获取请求结果
			Map<String, Map<String, String>> convertResultData = convertColumnClient.replace(convertFormData);
			//循环设置Alias或Name的值
			for (MeetingRoomDTO meetingRoom : meetingRoomDTOList) {
				meetingRoom.setAdminIdAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meetingRoom.getAdminId()));
				meetingRoom.setYnPublicName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnPublic()));
				meetingRoom.setOwnerDeptIdAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.DEPT, meetingRoom.getOwnerDeptId()));
				meetingRoom.setYnApproveName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnApprove()));
				meetingRoom.setApprovalIdsAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meetingRoom.getApprovalIds()));
				meetingRoom.setYnValidName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnValid()));
				meetingRoom.setSecretLevelName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", meetingRoom.getSecretLevel()));
			}
		}
	}

	/**
     * 通过平台API将字段名称转换为值，包括通用选择组件、通用代码
     * 基础数据中用户默认使用用户编号转换，部门、岗位、角色默认使用编码进行转换，群组使用名称进行转换
     *
     * @param meetingRoomDTOList
     */
    private void revValueConvert(List<MeetingRoomDTO> meetingRoomDTOList, Map<String, Map<String, String>> convertResultData) {
        //循环设置Alias或Name的值
        for (MeetingRoomDTO meetingRoom : meetingRoomDTOList) {
            meetingRoom.setAdminId(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meetingRoom.getAdminIdAlias()));
            meetingRoom.setYnPublic(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnPublicName()));
            meetingRoom.setOwnerDeptId(BusinessUtil.convertFormat(convertResultData, SystemConstant.DEPT, meetingRoom.getOwnerDeptIdAlias()));
            meetingRoom.setYnApprove(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnApproveName()));
            meetingRoom.setApprovalIds(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meetingRoom.getApprovalIdsAlias()));
            meetingRoom.setYnValid(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meetingRoom.getYnValidName()));
            meetingRoom.setSecretLevel(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", meetingRoom.getSecretLevelName()));
        }
    }
    /**
     * 通过平台API将字段名称转换为值，包括通用选择组件、通用代码提前组装数据
     *
     * @param dataList
     */
    private  Map<String, Map<String, String>> assembleData(List<Map<String, String>> dataList) {
        //循环组装请求数据
        Map<String, Set<String>> convertFormData = new HashMap<>();
        for (Map<String, String> data : dataList) {
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, data.get("adminIdAlias"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", data.get("ynPublicName"));
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.DEPT,data.get("ownerDeptIdAlias"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", data.get("ynApproveName"));
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, data.get("approvalIdsAlias"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", data.get("ynValidName"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", data.get("secretLevelName"));
        }
		return  convertColumnClient.convertCodeToId(convertFormData);
    }
}

