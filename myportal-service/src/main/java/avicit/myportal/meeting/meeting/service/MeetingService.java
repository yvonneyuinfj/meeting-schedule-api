package avicit.myportal.meeting.meeting.service;

import avicit.myportal.meeting.meeting.dao.MeetingDAO;
import avicit.myportal.meeting.meeting.dto.MeetingDTO;
import avicit.myportal.meeting.meetinguser.dto.MeetingUserDTO;
import avicit.myportal.meeting.meetinguser.service.MeetingUserService;
import avicit.platform6.api.system.OssClient;
import avicit.platform6.api.bpm.BpmOperateClient;
import avicit.platform6.api.system.ConvertColumnClient;
import avicit.platform6.api.system.impl.SystemConstant;
import avicit.platform6.bpmreform.bpmbusiness.dto.StartBean;
import avicit.platform6.bpmreform.bpmbusiness.dto.StartResultBean;
import avicit.platform6.commons.utils.*;
import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.core.domain.ValidationList;
import avicit.platform6.core.exception.BusinessException;
import avicit.platform6.core.exception.UtilsException;
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
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-19 16:06
 * @类说明：会议日程信息表Service
 * @修改记录：
 */
@Service
public class MeetingService {

    private static final Logger logger = LoggerFactory.getLogger(MeetingService.class);

    @Autowired
    private MeetingDAO meetingDAO;

    @Autowired
    private OssClient ossClient;

    @Autowired
    private ConvertColumnClient convertColumnClient;

    @Autowired
    private BpmOperateClient bpmOperateClient;

    @Autowired
    private MeetingUserService meetingUserService;

    /**
     * 按条件分页查询
     *
     * @param queryReqBean 查询条件
     * @return QueryRespBean<MeetingDTO>
     */
    @Transactional(readOnly = true)
    public QueryRespBean<MeetingDTO> searchMeetingByPage(QueryReqBean<MeetingDTO> queryReqBean) {
        QueryRespBean<MeetingDTO> queryRespBean = new QueryRespBean<>();
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), MeetingDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingDTO>() {
            });
            searchKeyWordParam.setBpmState(queryReqBean.getSearchParams().getBpmState());
            searchKeyWordParam.setBpmType(queryReqBean.getSearchParams().getBpmType());
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
        //设置流程查询条件
        MeetingDTO searchParams = queryReqBean.getSearchParams();
        if (searchParams == null) {
            searchParams = new MeetingDTO();
        }
        searchParams.setCurrUserId(ThreadContextHelper.getUserId());
        if (StringUtils.isEmpty(searchParams.getBpmType())) {
            searchParams.setBpmType("my");
        }
        queryReqBean.setSearchParams(searchParams);
        PageHelper.startPage(queryReqBean.getPageParameter());
        //文档密级
        List<String> wordSecret = ThreadContextHelper.getWordSecretList();

        Page<MeetingDTO> result = meetingDAO.searchMeetingByPage(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret, queryReqBean.getSortExp(), queryReqBean.getKeyWord());
        valueConvert(result.getResult());
        queryRespBean.setResult(result);
        return queryRespBean;
    }

    /**
     * 按条件查询
     *
     * @param queryReqBean 查询条件
     * @return List<MeetingDTO>
     */
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchMeeting(QueryReqBean<MeetingDTO> queryReqBean) {
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), MeetingDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingDTO>() {
            });
            searchKeyWordParam.setBpmState(queryReqBean.getSearchParams().getBpmState());
            searchKeyWordParam.setBpmType(queryReqBean.getSearchParams().getBpmType());
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
        //设置流程查询条件
        MeetingDTO searchParams = queryReqBean.getSearchParams();
        if (searchParams == null) {
            searchParams = new MeetingDTO();
        }
        searchParams.setCurrUserId(ThreadContextHelper.getUserId());
        if (StringUtils.isEmpty(searchParams.getBpmType())) {
            searchParams.setBpmType("my");
        }
        queryReqBean.setSearchParams(searchParams);
        //文档密级
        List<String> wordSecret = ThreadContextHelper.getWordSecretList();
        List<MeetingDTO> dataList = meetingDAO.searchMeeting(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret);
        valueConvert(dataList);
        return dataList;
    }

    /**
     * 按条件导出
     *
     * @param queryReqBean 查询条件
     * @param orgIdentity  组织id
     * @param wordSecret   文档密级
     * @param orderBy      排序
     * @return List<MeetingDTO>
     */
    @Transactional(readOnly = true)
    public List<MeetingDTO> searchMeetingForExportExcel(QueryReqBean<MeetingDTO> queryReqBean, String orgIdentity, List<String> wordSecret, String orderBy) {
        MeetingDTO searchParams = queryReqBean.getSearchParams();
        if (searchParams == null) {
            searchParams = new MeetingDTO();
        }
        searchParams.setCurrUserId(ThreadContextHelper.getUserId());
        List<MeetingDTO> resultList = meetingDAO.searchMeetingForExportExcel(queryReqBean.getSearchParams(), orgIdentity, wordSecret, orderBy, queryReqBean.getKeyWord());
        valueConvert(resultList);
        return resultList;
    }

    /**
     * 通过主键查询单条记录
     *
     * @param id 主键id
     * @return MeetingDTO
     */
    @Transactional(readOnly = true)
    public MeetingDTO queryMeetingByPrimaryKey(String id) {
        MeetingDTO meetingDTO = meetingDAO.findMeetingById(id);
        //记录日志
        if (meetingDTO != null) {
            valueConvert(Arrays.asList(meetingDTO));
            SysLogUtil.log4Query(meetingDTO);
        }
        return meetingDTO;
    }

    /**
     * 新增对象
     *
     * @param meetingDTO 保存对象
     * @return String
     */
    @Transactional
    public String insertMeeting(MeetingDTO meetingDTO) {
        if (StringUtils.isEmpty(meetingDTO.getId())) {
            meetingDTO.setId(ComUtil.getId());
        }
        PojoUtil.setSysProperties(meetingDTO, OpType.insert);
        meetingDAO.insertMeeting(meetingDTO);
        // 新增或修改子表对象集合
//        insertOrUpdateMeetingUser(meetingDTO);
        // 新增子表对象集合
        this.insertMeetingUser(meetingDTO);

        //记录日志
        SysLogUtil.log4Insert(meetingDTO);
        return meetingDTO.getId();
    }

    /**
     * 保存表单并启动流程
     *
     * @param meetingDTO 表单对象
     * @param parameter  启动流程所需要的参数
     * @return StartResultBean
     */
    @Transactional
    public StartResultBean insertMeetingAndStartProcess(MeetingDTO meetingDTO, Map<String, String> parameter) {
        Assert.notNull(parameter, "启动流程失败，请传递流程启动参数！");
        String processDefId = parameter.get("processDefId");
        String formCode = parameter.get("formCode");
        String jsonString = parameter.get("jsonString");
        String userId = parameter.get("userId");
        String deptId = parameter.get("deptId");
        Assert.hasText(processDefId, "启动流程失败，请传递流程启动参数！");
        //业务操作
        this.insertMeeting(meetingDTO);
        //变量集合
        Map<String, Object> variables = new HashMap<>();
        //web表单传递过来(除表单对象外)的变量，可以为空
        if (jsonString != null && !"".equals(jsonString)) {
            Map<String, Object> extVariables = JsonUtil.parseJSON2Map(jsonString);
            variables.putAll(extVariables);
        }
        //把表单对象转换成map,传递给流程变量
        Map<String, Object> pojoMap = null;
        try {
            pojoMap = (Map<String, Object>) PojoUtil.toMap(meetingDTO);
        } catch (Exception e) {
            throw new UtilsException("PojoUtil.toMap转换失败", e);
        }
        variables.putAll(pojoMap);
        // 构造启动流程的对象
        StartBean startBean = new StartBean();
        startBean.setProcessDefinitionId(processDefId);
        startBean.setFormCode(formCode);
        startBean.setStartUserId(userId);
        startBean.setStartDeptId(deptId);
        startBean.setVariables(variables);
        StartResultBean startResultBean = bpmOperateClient.startProcessInstance(startBean);//启动流程
        // 返回流程启动的结果
        return startResultBean;
    }

    /**
     * 批量新增对象
     *
     * @param dtoList 保存对象集合
     * @return int
     */
    @Transactional
    public int insertMeetingList(List<MeetingDTO> dtoList) {
        for (MeetingDTO meetingDTO : dtoList) {
            meetingDTO.setId(ComUtil.getId());
            PojoUtil.setSysProperties(meetingDTO, OpType.insert);
        }
        ListUtils.partition(dtoList, 50).forEach(subList -> {
            meetingDAO.insertMeetingList(subList);
        });
        // 记录日志
        SysLogUtil.log4Insert(dtoList);
        return dtoList.size();
    }

    /**
     * 修改对象全部字段
     *
     * @param meetingDTO 修改对象
     * @return int
     */
    @Transactional
    public int updateMeeting(MeetingDTO meetingDTO) {
        return updateDto(meetingDTO, true);
    }

    /**
     * 修改对象部分字段
     *
     * @param meetingDTO 修改对象
     * @return int
     */
    @Transactional
    public int updateMeetingSensitive(MeetingDTO meetingDTO) {
        return updateDto(meetingDTO, false);
    }

    /**
     * 内部方法，获取修改的dto对象
     *
     * @param meetingDTO
     * @return
     */
    private MeetingDTO getUpdateMainDto(MeetingDTO meetingDTO) {
        MeetingDTO oldDTO = findById(meetingDTO.getId());
        if (oldDTO == null) {
            throw new BusinessException("数据不存在");
        }
        //记录日志
        SysLogUtil.log4Update(meetingDTO, oldDTO);
        PojoUtil.setSysProperties(meetingDTO, OpType.update);
        PojoUtil.copyProperties(oldDTO, meetingDTO, false);
        return oldDTO;
    }

    /**
     * 批量更新对象
     *
     * @param dtoList 修改对象集合
     * @return int
     */
    @Transactional
    public int updateMeetingList(List<MeetingDTO> dtoList) {
        List<MeetingDTO> updateList = new ArrayList<>();
        List<MeetingDTO> oldList = new ArrayList<>();
        for (MeetingDTO dto : dtoList) {
            MeetingDTO old = findById(dto.getId());
            if (old == null) {
                throw new BusinessException("修改对象不存在!");
            }
            MeetingDTO updateDto = (MeetingDTO) old.clone();
            PojoUtil.setSysProperties(dto, OpType.update);
            PojoUtil.copyProperties(updateDto, dto, false);
            updateList.add(updateDto);
            oldList.add(old);
        }
        ListUtils.partition(updateList, 50).forEach(subList -> {
            meetingDAO.updateMeetingList(subList);
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
    public int deleteMeetingById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException("删除失败！传入的参数主键为null");
        }
        //记录日志
        MeetingDTO meetingDTO = findById(id);
        if (meetingDTO == null) {
            throw new BusinessException("删除失败！对象不存在");
        }
        SysLogUtil.log4Delete(meetingDTO);
        //删除子表
        for (MeetingUserDTO meetingUserDTO : meetingUserService.queryMeetingUserByPid(meetingDTO.getId())) {
            meetingUserService.deleteMeetingUser(meetingUserDTO);
        }
        //删除业务数据
        int count = meetingDAO.deleteMeetingById(id);
        //删除关联流程实例
        bpmOperateClient.deleteProcessInstanceByFormId(id);
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
    public int deleteMeetingByIds(String[] ids) {
        int result = 0;
        for (String id : ids) {
            deleteMeetingById(id);
            result++;
        }
        return result;
    }

    /**
     * 流程服务回调方法（按主表主键单条删除业务数据）
     *
     * @param id 主键id
     * @return int
     */
    @Transactional
    public int deleteDataMeetingById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException("删除失败！传入的参数主键为null");
        }
        //记录日志
        MeetingDTO meetingDTO = findById(id);
        if (meetingDTO == null) {
            throw new BusinessException("删除失败！对象不存在");
        }
        SysLogUtil.log4Delete(meetingDTO);
        //删除子表
        for (MeetingUserDTO meetingUserDTO : meetingUserService.queryMeetingUserByPid(meetingDTO.getId())) {
            meetingUserService.deleteMeetingUser(meetingUserDTO);
        }
        //删除业务数据
        int count = meetingDAO.deleteMeetingById(id);
        //删除附件
        ossClient.deleteAttachByFormId(id);
        return count;
    }

    /**
     * 新增或修改子表对象集合(deprecated)
     *
     * @param meetingDTO 新增对象
     */
    private void insertOrUpdateMeetingUser(MeetingDTO meetingDTO) {
        ValidationList<MeetingUserDTO> meetingUserList = meetingDTO.getMeetingUserList();
        if (!CollectionUtils.isEmpty(meetingUserList)) {
            for (MeetingUserDTO meetingUserDTO : meetingUserList) {
                if (StringUtils.equals(OpType.delete.toString(), meetingUserDTO.getOperationType_())) {
                    meetingUserService.deleteMeetingUserById(meetingUserDTO.getId());
                } else if (StringUtils.equals(OpType.update.toString(), meetingUserDTO.getOperationType_())) {
                    meetingUserService.updateMeetingUserSensitive(meetingUserDTO);
                } else {
                    meetingUserDTO.setMeetingId(meetingDTO.getId());
                    meetingUserDTO.setOrgIdentity(meetingDTO.getOrgIdentity());
                    meetingUserService.insertMeetingUser(meetingUserDTO);
                }
            }
        }
    }

    /**
     * 新增子表对象集合
     *
     * @param meetingDTO 新增对象
     */
    private void insertMeetingUser(MeetingDTO meetingDTO) {
        Set<String> userIdList = new HashSet<>();
        // 将创建人加入参会人列表
        userIdList.add(meetingDTO.getCreatedBy());
        // 如果有主持人，将主持人加入参会人列表
        if (StringUtils.isNotEmpty(meetingDTO.getHostId())) {
            userIdList.add(meetingDTO.getHostId());
        }
        // 如果有参会人，加入参会人列表
        if (StringUtils.isNotEmpty(meetingDTO.getAttendeeIds())) {
            // 将分号分隔的字符串转换为列表
            String[] attendeeIdList = meetingDTO.getAttendeeIds().split(";");
            userIdList.addAll(Arrays.asList(attendeeIdList));
        }

        for (String userId : userIdList) {
            // 创建参会人相关数据对象
            MeetingUserDTO meetingUserDTO = new MeetingUserDTO();
            meetingUserDTO.setMeetingId(meetingDTO.getId());
            meetingUserDTO.setUserId(userId);
            meetingUserService.insertMeetingUser(meetingUserDTO);
        }
    }

    /**
     * 日志专用，内部方法，不再记录日志
     *
     * @param id 主键id
     * @return MeetingDTO
     */
    private MeetingDTO findById(String id) {
        return meetingDAO.findMeetingById(id);
    }

    /**
     * 内部方法，
     *
     * @param dto             修改对象
     * @param isUpdateAllProp 更新全部字段是为true，更新部分字段为false
     * @return
     */
    private int updateDto(MeetingDTO dto, boolean isUpdateAllProp) {
        if (dto == null) {
            throw new BusinessException("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException("修改对象的id不能为空！");
        }
        MeetingDTO old = findById(dto.getId());
        if (old == null) {
            throw new BusinessException("修改对象不存在！");
        }
        MeetingDTO updateDto = (MeetingDTO) old.clone();
        PojoUtil.setSysProperties(dto, OpType.update);
        int ret = 0;
        if (isUpdateAllProp) {
            PojoUtil.copyProperties(updateDto, dto, false);
            ret = meetingDAO.updateMeetingAll(updateDto);
        } else {
            PojoUtil.copyProperties(updateDto, dto, true);
            ret = meetingDAO.updateMeetingSensitive(dto);
        }
        if (ret == 0) {
            throw new BusinessException("数据失效，请重新更新");
        }
        // 新增或修改子表对象集合
//        insertOrUpdateMeetingUser(dto);
        // 更新子表
        for (MeetingUserDTO meetingUserDTO : meetingUserService.queryMeetingUserByPid(dto.getId())) {
            meetingUserService.deleteMeetingUser(meetingUserDTO);
        }
        this.insertMeetingUser(dto);
        try {
            //更新流程变量
            Map<String, Object> variables = new HashMap<String, Object>();
            variables.putAll((Map<String, Object>) PojoUtil.toMap(updateDto));
            bpmOperateClient.setVariablesByFormId(dto.getId(), variables);
        } catch (Exception e) {
            throw new UtilsException("流程变量更新失败", e);
        }
        SysLogUtil.log4Update(updateDto, old);
        return ret;
    }

    /**
     * 通过平台API将字段值转换为名称，包括通用选择组件、通用代码
     *
     * @param meetingDTOList
     */
    private void valueConvert(List<MeetingDTO> meetingDTOList) {
        //循环组装请求数据
        Map<String, Set<String>> convertFormData = new HashMap<>();
        for (MeetingDTO meeting : meetingDTOList) {
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, meeting.getAuthorId());
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meeting.getYnApprove());
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, meeting.getApprovalIds());
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, meeting.getHostId());
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.DEPT, meeting.getOrgDeptId());
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meeting.getYnValid());
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meeting.getYnPublic());
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meeting.getYnNewPlace());
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_YES_NO_FLAG", meeting.getYnShowEvent());
            BusinessUtil.createConvertSet(convertFormData, SystemConstant.USER, meeting.getAttendeeIds());
            BusinessUtil.createConvertSet(convertFormData, "MYPORTAL_EVENT_TYPE", meeting.getEventType());
            BusinessUtil.createConvertSet(convertFormData, "MYPORTAL_REMIND_TYPE", meeting.getRemindType());
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", meeting.getSecretLevel());
        }
        if (convertFormData.size() > 0) {
            //获取请求结果
            Map<String, Map<String, String>> convertResultData = convertColumnClient.replace(convertFormData);
            //循环设置Alias或Name的值
            for (MeetingDTO meeting : meetingDTOList) {
                meeting.setAuthorIdAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meeting.getAuthorId()));
                meeting.setYnApproveName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meeting.getYnApprove()));
                meeting.setApprovalIdsAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meeting.getApprovalIds()));
                meeting.setHostIdAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meeting.getHostId()));
                meeting.setOrgDeptIdAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.DEPT, meeting.getOrgDeptId()));
                meeting.setYnValidName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meeting.getYnValid()));
                meeting.setYnPublicName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meeting.getYnPublic()));
                meeting.setYnNewPlaceName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meeting.getYnNewPlace()));
                meeting.setYnShowEventName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_YES_NO_FLAG", meeting.getYnShowEvent()));
                meeting.setAttendeeIdsAlias(BusinessUtil.convertFormat(convertResultData, SystemConstant.USER, meeting.getAttendeeIds()));
                meeting.setEventTypeName(BusinessUtil.convertFormat(convertResultData, "MYPORTAL_EVENT_TYPE", meeting.getEventType()));
                meeting.setRemindTypeName(BusinessUtil.convertFormat(convertResultData, "MYPORTAL_REMIND_TYPE", meeting.getRemindType()));
                meeting.setSecretLevelName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", meeting.getSecretLevel()));
                //流程的编码转换成名称
                meeting.setBpmState(meeting.getBusinessstate_());
                meeting.setBusinessstate_(BusinessUtil.processStateCode2StateName(meeting.getBusinessstate_()));
            }
        }
    }

    /**
     * 按条件查询
     *
     * @param queryReqBean 查询条件
     * @return List<MeetingDTO>
     */
    @Transactional(readOnly = true)
    public List<MeetingDTO> getMeetingList(QueryReqBean<MeetingDTO> queryReqBean) {
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), MeetingDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingDTO>() {
            });
//            searchKeyWordParam.setBpmState(queryReqBean.getSearchParams().getBpmState());
//            searchKeyWordParam.setBpmType(queryReqBean.getSearchParams().getBpmType());
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
        //设置流程查询条件
        MeetingDTO searchParams = queryReqBean.getSearchParams();
        if (searchParams == null) {
            searchParams = new MeetingDTO();
        }
        searchParams.setCurrUserId(ThreadContextHelper.getUserId());
        if (StringUtils.isEmpty(searchParams.getBpmType())) {
            searchParams.setBpmType("my");
        }
        queryReqBean.setSearchParams(searchParams);
        //文档密级
        List<String> wordSecret = ThreadContextHelper.getWordSecretList();
        List<MeetingDTO> dataList = meetingDAO.searchMeetingsByUserId(queryReqBean.getSearchParams(), queryReqBean.getOrgIdentity(), wordSecret, queryReqBean.getSortExp(), queryReqBean.getKeyWord());
        valueConvert(dataList);
        return dataList;
    }
}

