package avicit.myportal.meeting.meetinguser.service;


import avicit.platform6.core.exception.BusinessException;
import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import avicit.platform6.core.mybatis.pagehelper.PageHelper;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import avicit.platform6.commons.utils.StringUtils;
import avicit.platform6.commons.utils.BusinessUtil;
import avicit.platform6.commons.utils.JsonHelper;
import avicit.platform6.api.system.ConvertColumnClient;
import avicit.platform6.api.system.impl.SystemConstant;
import avicit.myportal.meeting.meetinguser.dao.MeetingUserDAO;
import avicit.myportal.meeting.meetinguser.dto.MeetingUserDTO;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-19 15:13
 * @类说明：
 * @修改记录：
 */
@Service
public class MeetingUserService  {

	 private static final Logger logger = LoggerFactory.getLogger(MeetingUserService.class);

    @Autowired
    private MeetingUserDAO meetingUserDAO;


    @Autowired
    private ConvertColumnClient convertColumnClient;
    /**
     * 按条件分页查询
     *
     * @param queryReqBean 查询条件
     * @return QueryRespBean<MeetingUserDTO>
     */
    @Transactional(readOnly = true)
    public QueryRespBean<MeetingUserDTO> searchMeetingUserByPage(QueryReqBean<MeetingUserDTO> queryReqBean) {
        QueryRespBean<MeetingUserDTO> queryRespBean = new QueryRespBean<>();
        if (StringUtils.isNotEmpty(queryReqBean.getSidx()) && StringUtils.isNotEmpty(queryReqBean.getSord())) {
            String sordExp = BusinessUtil.getSortExpColumnName(queryReqBean.getSidx(), queryReqBean.getSord(), MeetingUserDTO.class);
            if (StringUtils.isNotEmpty(sordExp)) {
                queryReqBean.setSortExp(sordExp);
            }
        }
        if (StringUtils.isNotEmpty(queryReqBean.getKeyWord())) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            MeetingUserDTO searchKeyWordParam = JsonHelper.getInstance().readValue(queryReqBean.getKeyWord(), dateFormat, new TypeReference<MeetingUserDTO>() {
            });
            searchKeyWordParam.setMeetingId(queryReqBean.getSearchParams().getMeetingId());
            queryReqBean.setSearchParams(searchKeyWordParam);
        }
        if (queryReqBean.getSearchParams() != null && StringUtils.isEmpty(queryReqBean.getSearchParams().getMeetingId())) {
            queryReqBean.getSearchParams().setMeetingId("-1");
        }
        PageHelper.startPage(queryReqBean.getPageParameter());
        //文档密级
        List<String> wordSecret = ThreadContextHelper.getWordSecretList();

        Page<MeetingUserDTO> result = meetingUserDAO.searchMeetingUserByPage(queryReqBean.getSearchParams(),  wordSecret, queryReqBean.getSortExp(), queryReqBean.getKeyWord());
        valueConvert(result.getResult());
        queryRespBean.setResult(result);
        return queryRespBean;
    }

    /**
     * 按条件导出
     *
     * @param queryReqBean 查询条件
     * @param wordSecret   文档密级
     * @param orderBy      排序
     * @return List<MeetingUserDTO>
     */
    @Transactional(readOnly = true)
    public List<MeetingUserDTO> searchMeetingUserForExportExcel(QueryReqBean<MeetingUserDTO> queryReqBean, List<String> wordSecret, String orderBy) {
        return meetingUserDAO.searchMeetingUserForExportExcel(queryReqBean.getSearchParams(), wordSecret, orderBy, queryReqBean.getKeyWord());
    }

    /**
     * 通过主键查询单条记录
     *
     * @param id 主键id
     * @return MeetingUserDTO
     */
    @Transactional(readOnly = true)
    public MeetingUserDTO queryMeetingUserByPrimaryKey(String id) {
        MeetingUserDTO meetingUserDTO = meetingUserDAO.findMeetingUserById(id);
        //记录日志
        if (meetingUserDTO != null) {
            valueConvert(Arrays.asList(meetingUserDTO));
            SysLogUtil.log4Query(meetingUserDTO);
        }
        return meetingUserDTO;
    }

    /**
     * 通过父键查询单条记录
     *
     * @param pid 父id
     * @return List<MeetingUserDTO>
     */
    @Transactional(readOnly = true)
    public List<MeetingUserDTO> queryMeetingUserByPid(String pid) {
        List<MeetingUserDTO> dtoList = meetingUserDAO.findMeetingUserByPid(pid);
        return dtoList;
    }
    /**
     * 新增对象
     *
     * @param meetingUserDTO 保存对象
     * @return String
     */
    @Transactional
    public String insertMeetingUser(MeetingUserDTO meetingUserDTO) {
        meetingUserDTO.setId(ComUtil.getId());
        PojoUtil.setSysProperties(meetingUserDTO, OpType.insert);
        meetingUserDAO.insertMeetingUser(meetingUserDTO);
        //记录日志
        SysLogUtil.log4Insert(meetingUserDTO);
        return meetingUserDTO.getId();
    }

    /**
     * 批量新增对象
     *
     * @param dtoList 保存对象集合
     * @return int
     */
    @Transactional
    public int insertMeetingUserList(List<MeetingUserDTO> dtoList) {
        for (MeetingUserDTO meetingUserDTO : dtoList) {
            meetingUserDTO.setId(ComUtil.getId());
            PojoUtil.setSysProperties(meetingUserDTO, OpType.insert);
        }
        ListUtils.partition(dtoList, 50).forEach(subList -> {
            meetingUserDAO.insertMeetingUserList(subList);
        });
        // 记录日志
        SysLogUtil.log4Insert(dtoList);
        return dtoList.size();
    }

    /**
     * 修改对象全部字段
     *
     * @param meetingUserDTO 修改对象
     * @return int
     */
    @Transactional
    public int updateMeetingUser(MeetingUserDTO meetingUserDTO) {
        return updateDto(meetingUserDTO, true);
    }

    /**
     * 修改对象部分字段
     *
     * @param meetingUserDTO 修改对象
     * @return int
     */
    @Transactional
    public int updateMeetingUserSensitive(MeetingUserDTO meetingUserDTO) {
        return updateDto(meetingUserDTO, false);
    }

    /**
     * 批量更新对象
     *
     * @param dtoList 修改对象集合
     * @return int
     */
    @Transactional
    public int updateMeetingUserList(List<MeetingUserDTO> dtoList) {
        List<MeetingUserDTO> updateList = new ArrayList<>();
        List<MeetingUserDTO> oldList = new ArrayList<>();
        for (MeetingUserDTO dto : dtoList) {
            MeetingUserDTO old = findById(dto.getId());
            if (old == null) {
                throw new BusinessException("修改对象不存在!");
            }
            MeetingUserDTO updateDto = (MeetingUserDTO)old.clone();
            PojoUtil.setSysProperties(dto, OpType.update);
            PojoUtil.copyProperties(updateDto, dto, false);
            updateList.add(updateDto);
            oldList.add(old);
        }
        ListUtils.partition(updateList, 50).forEach(subList -> {
            meetingUserDAO.updateMeetingUserList(subList);
        });
        // 记录日志
        SysLogUtil.log4Update(updateList, oldList);
        return updateList.size();
    }

    /**
     * 内部方法，获取修改的dto对象
     *
     * @param meetingUserDTO
     * @return
     */
    private MeetingUserDTO getUpdateSubDto(MeetingUserDTO meetingUserDTO) {
        MeetingUserDTO oldDTO = findById(meetingUserDTO.getId());
        if (oldDTO == null) {
            throw new BusinessException("数据不存在");
        }
        String cid = oldDTO.getMeetingId();
        //记录日志
        SysLogUtil.log4Update(meetingUserDTO, oldDTO);
        PojoUtil.setSysProperties(meetingUserDTO, OpType.update);
        PojoUtil.copyProperties(oldDTO, meetingUserDTO, false);
        oldDTO.setMeetingId(cid);
        return oldDTO;
    }

    /**
     * 按主键单条删除
     *
     * @param id 主键id
     * @return int
     */
    @Transactional
    public int deleteMeetingUserById(String id) {
        if (StringUtils.isEmpty(id)) {
            throw new BusinessException("删除失败！传入的参数主键为null");
        }
        //记录日志
        MeetingUserDTO meetingUserDTO = findById(id);
        if (meetingUserDTO == null) {
            throw new BusinessException("删除失败！对象不存在");
        }
        SysLogUtil.log4Delete(meetingUserDTO);
        //删除业务数据
        int count = meetingUserDAO.deleteMeetingUserById(id);
        return count;
    }

    /**
     * 按条件删除数据
     *
     * @param meetingUserDTO 对象条件
     * @return int
     */
    public int deleteMeetingUser(MeetingUserDTO meetingUserDTO) {
        //记录日志
        if (meetingUserDTO == null) {
            throw new BusinessException("删除失败！对象不存在");
        }
        SysLogUtil.log4Delete(meetingUserDTO);
        return meetingUserDAO.deleteMeetingUserById(meetingUserDTO.getId());
    }

    /**
     * 批量删除数据
     *
     * @param ids 逗号分隔的id串
     * @return int
     */
    @Transactional
    public int deleteMeetingUserByIds(String[] ids) {
        int result = 0;
        for (String id : ids) {
            deleteMeetingUserById(id);
            result++;
        }
        return result;
    }

    /**
     * 日志专用，内部方法，不再记录日志
     *
     * @param id 主键id
     * @return meetingUserDTO
     */
    private MeetingUserDTO findById(String id) {
        return meetingUserDAO.findMeetingUserById(id);
    }
    /**
     * 内部方法，
     *
     * @param dto 修改对象
     * @param isUpdateAllProp 更新全部字段是为true，更新部分字段为false
     * @return
     */
    private int updateDto(MeetingUserDTO dto, boolean isUpdateAllProp) {
        if (dto == null) {
            throw new BusinessException("修改对象不能为空！");
        }
        if (StringUtils.isEmpty(dto.getId())) {
            throw new BusinessException("修改对象的id不能为空！");
        }
        MeetingUserDTO old = findById(dto.getId());
        if (old == null) {
            throw new BusinessException("修改对象不存在！");
        }
        MeetingUserDTO updateDto = (MeetingUserDTO)old.clone();
        PojoUtil.setSysProperties(dto, OpType.update);
        int ret = 0;
        if (isUpdateAllProp) {
            PojoUtil.copyProperties(updateDto, dto, false);
            ret = meetingUserDAO.updateMeetingUserAll(updateDto);
        } else {
            PojoUtil.copyProperties(updateDto, dto, true);
            ret = meetingUserDAO.updateMeetingUserSensitive(dto);
        }
        if (ret == 0) {
            throw new BusinessException("数据失效，请重新更新");
        }
        SysLogUtil.log4Update(updateDto, old);
        return ret;
    }

    /**
     * 通过平台API将字段值转换为名称，包括通用选择组件、通用代码
     *
     * @param meetingUserDTOList
     */
    private void valueConvert(List<MeetingUserDTO> meetingUserDTOList) {
        //循环组装请求数据
        Map<String, Set<String>> convertFormData = new HashMap<>();
        for (MeetingUserDTO meetingUser : meetingUserDTOList) {
            BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", meetingUser.getSecretLevel());
        }
        if (convertFormData.size() > 0) {
            //获取请求结果
            Map<String, Map<String, String>> convertResultData = convertColumnClient.replace(convertFormData);
            //循环设置Alias或Name的值
            for (MeetingUserDTO meetingUser : meetingUserDTOList) {
                meetingUser.setSecretLevelName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", meetingUser.getSecretLevel()));
            }
        }
    }
}

