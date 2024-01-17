package avicit.myportal.event.event.service;

import avicit.myportal.event.event.dao.EventDAO;
import avicit.myportal.event.event.dto.EventDTO;
import avicit.myportal.event.event.dto.EventImportDTO;
import avicit.platform6.api.system.ConvertColumnClient;
import avicit.platform6.api.system.impl.SystemConstant;
import avicit.platform6.api.system.OssClient;
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
* @创建时间： 2024-01-16 13:47
* @类说明：日程表Service
* @修改记录：
*/
@Service
public class EventService {

	private static final Logger logger = LoggerFactory.getLogger(EventService.class);

	@Autowired
	private EventDAO eventDAO;

	@Autowired
	private OssClient ossClient;


	@Autowired
	private ConvertColumnClient convertColumnClient;

	/**
	* 按条件分页查询
	* @param queryReqBean 查询条件
	* @return QueryRespBean<EventDTO>
	*/
	@Transactional(readOnly = true)
	public QueryRespBean<EventDTO> searchEventByPage(QueryReqBean<EventDTO> queryReqBean) {
		QueryRespBean<EventDTO> queryRespBean = new QueryRespBean<>();
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), EventDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            EventDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<EventDTO>() {
            });
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
        PageHelper.startPage(queryReqBean.getPageParameter());
		//文档密级
		List<String> wordSecret = ThreadContextHelper.getWordSecretList();

        Page<EventDTO> result = eventDAO.searchEventByPage(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret, queryReqBean.getSortExp(), queryReqBean.getKeyWord());
		valueConvert(result.getResult());
		queryRespBean.setResult(result);
		return queryRespBean;
	}

	/**
	* 按条件查询
	*
	* @param queryReqBean 查询条件
	* @return List<EventDTO>
	*/
	@Transactional(readOnly = true)
	public List<EventDTO> searchEvent(QueryReqBean<EventDTO> queryReqBean) {
		QueryRespBean<EventDTO> queryRespBean = new QueryRespBean<>();
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), EventDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            EventDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<EventDTO>() {
            });
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
		//文档密级
		List<String> wordSecret = ThreadContextHelper.getWordSecretList();

        List<EventDTO> dataList = eventDAO.searchEvent(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret);
		valueConvert(dataList);
		return dataList;
	}

	/**
	* 按条件导出
	*
	* @param queryReqBean 查询条件
	* @return List<EventDTO>
	*/
	@Transactional(readOnly = true)
	public List<EventDTO> searchEventForExportExcel(QueryReqBean<EventDTO> queryReqBean) {
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            EventDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<EventDTO>() {
            });
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
		List<EventDTO> result=eventDAO.searchEventForExportExcel(queryReqBean.getSearchParams(), ThreadContextHelper.getOrgId() , ThreadContextHelper.getWordSecretList(), queryReqBean.getSortExp(), queryReqBean.getKeyWord());
        valueConvert(result);
        return result;
	}

	/**
	* 通过主键查询单条记录
	*
	* @param id 主键id
	* @return EventDTO
	*/
	@Transactional(readOnly = true)
	public EventDTO queryEventByPrimaryKey(String id) {
		EventDTO eventDTO = eventDAO.findEventById(id);
		//记录日志
		if (eventDTO != null) {
            valueConvert(Arrays.asList(eventDTO));
			SysLogUtil.log4Query(eventDTO);
		}
		return eventDTO;
	}

	/**
	* 新增对象
	*
	* @param eventDTO 保存对象
	* @return String
	*/
	@Transactional
	public String insertEvent(EventDTO eventDTO) {
		eventDTO.setId(ComUtil.getId());
		PojoUtil.setSysProperties(eventDTO, OpType.insert);
		eventDAO.insertEvent(eventDTO);
		//记录日志
		SysLogUtil.log4Insert(eventDTO);
		return eventDTO.getId();
	}

	/**
	* 批量新增对象
	*
	* @param dtoList 保存对象集合
	* @return int
	*/
	@Transactional
	public int insertEventList(List<EventDTO> dtoList) {
		for (EventDTO eventDTO : dtoList) {
			eventDTO.setId(ComUtil.getId());
			PojoUtil.setSysProperties(eventDTO, OpType.insert);
		}
        ListUtils.partition(dtoList, 50).forEach(subList -> {
            eventDAO.insertEventList(subList);
        });
        // 记录日志
        SysLogUtil.log4Insert(dtoList);
		return dtoList.size();
	}

	/**
	* 修改对象全部字段
	*
	* @param eventDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateEvent(EventDTO eventDTO) {
        return updateDto(eventDTO, true);
	}

	/**
	* 修改对象部分字段
	*
	* @param eventDTO 修改对象
	* @return int
	*/
	@Transactional
	public int updateEventSensitive(EventDTO eventDTO) {
        return updateDto(eventDTO, false);
	}

	/**
	* 批量更新对象
	*
	* @param dtoList 修改对象集合
	* @return int
	*/
	@Transactional
	public int updateEventList(List<EventDTO> dtoList) {
		List<EventDTO> updateList = new ArrayList<>();
        List<EventDTO> oldList = new ArrayList<>();
		for (EventDTO dto : dtoList) {
            EventDTO old = findById(dto.getId());
            if (old == null) {
                throw new BusinessException("修改对象不存在!");
            }
            EventDTO updateDto = (EventDTO)old.clone();
            PojoUtil.setSysProperties(dto, OpType.update);
            PojoUtil.copyProperties(updateDto, dto, false);
			updateList.add(updateDto);
            oldList.add(old);
		}
		ListUtils.partition(updateList, 50).forEach(subList -> {
            eventDAO.updateEventList(subList);
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
	public int deleteEventById(String id) {
		if (StringUtils.isEmpty(id)) {
			throw new BusinessException("删除失败！传入的参数主键为null");
		}
		//记录日志
		EventDTO eventDTO = findById(id);
		if (eventDTO == null) {
			throw new BusinessException("删除失败！对象不存在");
		}
		SysLogUtil.log4Delete(eventDTO);
		//删除业务数据
		int count = eventDAO.deleteEventById(id);
		//删除附件
		ossClient.deleteAttachByFormId(id);
		return count;
	}

	/**
	 * 批量删除数据
	 *
	 * @param ids id的数组
	 * @return int
	 */
	@Transactional
	public int deleteEventByIds(String[] ids) {
		int result = 0;
		for (String id : ids) {
			deleteEventById(id);
			result++;
		}
		return result;
	}

	/**
	* 日志专用，内部方法，不再记录日志
	*
	* @param id 主键id
	* @return EventDTO
	*/
	private EventDTO findById(String id) {
		return eventDAO.findEventById(id);
	}
    /**
     * 内部方法，
     *
     * @param dto 修改对象
     * @param isUpdateAllProp 更新全部字段是为true，更新部分字段为false
     * @return
     */
    private int updateDto(EventDTO dto, Boolean isUpdateAllProp) {
        if (dto == null) {
            throw new BusinessException("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException("修改对象的id不能为空！");
        }
        EventDTO old = findById(dto.getId());
        if (old == null) {
            throw new BusinessException("修改对象不存在！");
        }
        EventDTO updateDto = (EventDTO)old.clone();
        PojoUtil.setSysProperties(dto, OpType.update);
        int ret = 0;
        if (isUpdateAllProp) {
            PojoUtil.copyProperties(updateDto, dto, false);
            ret = eventDAO.updateEventAll(updateDto);
        } else {
            PojoUtil.copyProperties(updateDto, dto, true);
            ret = eventDAO.updateEventSensitive(dto);
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
		List<EventDTO> eventSuccessList = new ArrayList<EventDTO>();
		List<EventImportDTO> eventErrList = new ArrayList<EventImportDTO>();
		Map<String, Object> formDataMap = new HashMap<String,Object>();
		if(StringUtils.isNotEmpty(formData)){
			formDataMap = JsonUtil.parseJSON2Map(formData);
		}
		if(!CollectionUtils.isEmpty(dataList)){
            Map<String, Map<String, String>> convertResultData = this.assembleData(dataList);
			for (Map<String, String> data:dataList) {
				if(StringUtils.isEmpty(data.get("errorInfo"))){
					//业务校验
					EventDTO dto = new EventDTO();
					Map2Bo.map2Pojo(data,dto);
					eventSuccessList.add(dto);
				} else {
					EventImportDTO dtoImp = new EventImportDTO();
					Map2Bo.map2Pojo(data,dtoImp);
					eventErrList.add(dtoImp);
				}
			}
            this.revValueConvert(eventSuccessList, convertResultData);
			this.insertEventList(eventSuccessList);
		}
		ExcelImportResult excelImport = ExcelUtil.createUploadHis(eventSuccessList,eventErrList,EventImportDTO.class,formDataMap);
		return excelImport;
	}
	/**
	 * 通过平台API将字段值转换为名称，包括通用选择组件、通用代码
	 *
	 * @param eventDTOList
	 */
	private void valueConvert(List<EventDTO> eventDTOList) {
		//循环组装请求数据
		Map<String, Set<String>> convertFormData = new HashMap<>();
		for (EventDTO event : eventDTOList) {
			BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, event.getAuthorId());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", event.getYnValid());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", event.getYnPublic());
			BusinessUtil.createConvertSet(convertFormData, "MYPORTAL_EVENT_TYPE", event.getType());
			BusinessUtil.createConvertSet(convertFormData, "MYPORTAL_REMIND_TYPE", event.getRemindType());
			BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, event.getSharedUserIds());
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", event.getSecretLevel());
		}
		if (convertFormData.size() > 0) {
			//获取请求结果
			Map<String, Map<String, String>> convertResultData = convertColumnClient.replace(convertFormData);
			//循环设置Alias或Name的值
			for (EventDTO event : eventDTOList) {
				event.setAuthorIdAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, event.getAuthorId()));
				event.setYnValidName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", event.getYnValid()));
				event.setYnPublicName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", event.getYnPublic()));
				event.setTypeName(BusinessUtil.convertFormat(convertResultData, "MYPORTAL_EVENT_TYPE", event.getType()));
				event.setRemindTypeName(BusinessUtil.convertFormat(convertResultData, "MYPORTAL_REMIND_TYPE", event.getRemindType()));
				event.setSharedUserIdsAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, event.getSharedUserIds()));
				event.setSecretLevelName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", event.getSecretLevel()));
			}
		}
	}

	/**
     * 通过平台API将字段名称转换为值，包括通用选择组件、通用代码
     * 基础数据中用户默认使用用户编号转换，部门、岗位、角色默认使用编码进行转换，群组使用名称进行转换
     *
     * @param eventDTOList
     */
    private void revValueConvert(List<EventDTO> eventDTOList, Map<String, Map<String, String>> convertResultData) {
        //循环设置Alias或Name的值
        for (EventDTO event : eventDTOList) {
            event.setAuthorId(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, event.getAuthorIdAlias()));
            event.setYnValid(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", event.getYnValidName()));
            event.setYnPublic(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", event.getYnPublicName()));
            event.setType(BusinessUtil.convertFormat(convertResultData, "MYPORTAL_EVENT_TYPE", event.getTypeName()));
            event.setRemindType(BusinessUtil.convertFormat(convertResultData, "MYPORTAL_REMIND_TYPE", event.getRemindTypeName()));
            event.setSharedUserIds(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, event.getSharedUserIdsAlias()));
            event.setSecretLevel(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", event.getSecretLevelName()));
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
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, data.get("authorIdAlias"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", data.get("ynValidName"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", data.get("ynPublicName"));
            BusinessUtil.createConvertSet(convertFormData, "MYPORTAL_EVENT_TYPE", data.get("typeName"));
            BusinessUtil.createConvertSet(convertFormData, "MYPORTAL_REMIND_TYPE", data.get("remindTypeName"));
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, data.get("sharedUserIdsAlias"));
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", data.get("secretLevelName"));
        }
		return  convertColumnClient.convertCodeToId(convertFormData);
    }
}

