package avicit.myportal.meeting.meeting.api;

import avicit.platform6.core.restclient.RestClient;
import avicit.platform6.core.restclient.RestClientUtils;
import avicit.platform6.core.rest.msg.QueryReqBean;
import avicit.platform6.core.rest.msg.QueryRespBean;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.platform6.api.bpm.dto.ProcessParameter;
import avicit.platform6.bpmreform.bpmbusiness.dto.StartResultBean;
import avicit.myportal.meeting.meeting.dto.MeetingDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-19 16:06
 * @类说明：会议日程信息表Api
 * @修改记录：
 */
@Component
public class MeetingApi{

    /** 服务编码  */
    private static final String SERVICE_CODE = "myportal";
    private static final String BASE_PATH = "/api/myportal/meeting/meetings";

    @Autowired
    private RestClient restClient;

    /**
     * 按条件分页查询主表数据
     *
     * @param queryReqBean 查询条件
     * @return QueryRespBean<MeetingDTO>
     */
    public QueryRespBean<MeetingDTO> searchByPage(QueryReqBean<MeetingDTO> queryReqBean) {
        String url = BASE_PATH+"/search-by-page/v1";
        ResponseMsg<QueryRespBean<MeetingDTO>> responseMsg = restClient.doPost(SERVICE_CODE, url, queryReqBean, new ParameterizedTypeReference<ResponseMsg<QueryRespBean<MeetingDTO>>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 按条件不分页查询主表数据
     *
     * @param queryReqBean 查询条件
     * @return List<MeetingDTO>
     */
    public List<MeetingDTO> search(QueryReqBean<MeetingDTO> queryReqBean) {
        String url = BASE_PATH+"/search/v1";
        ResponseMsg<List<MeetingDTO>> responseMsg = restClient.doPost(SERVICE_CODE, url, queryReqBean, new ParameterizedTypeReference<ResponseMsg<List<MeetingDTO>>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 通过主键查询主表单条记录
     *
     * @param id 主键id
     * @return MeetingDTO
     */
    public MeetingDTO get(String id) {
        String url = BASE_PATH+"/get/" + id + "/v1";
        ResponseMsg<MeetingDTO> responseMsg = restClient.doGet(SERVICE_CODE, url, new ParameterizedTypeReference<ResponseMsg<MeetingDTO>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 新增主表对象
     *
     * @param meetingDTO 保存对象
     * @return String
     */
    public String save(MeetingDTO meetingDTO) {
        String url = BASE_PATH+"/save/v1";
        ResponseMsg<String> responseMsg = restClient.doPost(SERVICE_CODE, url, meetingDTO, new ParameterizedTypeReference<ResponseMsg<String>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 修改主表部分对象字段
     *
     * @param meetingDTO 修改对象
     * @return Integer
     */
    public Integer updateSensitive(MeetingDTO meetingDTO) {
        String url = BASE_PATH+"/update-sensitive/v1";
        ResponseMsg<Integer> responseMsg = restClient.doPut(SERVICE_CODE, url, meetingDTO, new ParameterizedTypeReference<ResponseMsg<Integer>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 修改主表全部对象字段
     *
     * @param meetingDTO 修改对象
     * @return Integer
     */
    public Integer updateAll(MeetingDTO meetingDTO) {
        String url = BASE_PATH+"/update-all/v1";
        ResponseMsg<Integer> responseMsg = restClient.doPut(SERVICE_CODE, url, meetingDTO, new ParameterizedTypeReference<ResponseMsg<Integer>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 按主表主键单条删除主表数据
     *
     * @param id 主键id
     * @return Integer
     */
    public Integer deleteById(String id) {
        String url = BASE_PATH+"/delete-by-id/"+ id +"/v1";
        ResponseMsg<Integer> responseMsg = restClient.doDelete(SERVICE_CODE, url, new ParameterizedTypeReference<ResponseMsg<Integer>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 批量删除主表数据
     *
     * @param ids 逗号分隔的id串
     * @return Integer
     */
    public Integer deleteByIds(String[] ids) {
        String url = BASE_PATH+"/delete-by-ids/v1";
        ResponseMsg<Integer> responseMsg = restClient.doDelete(SERVICE_CODE, url, ids, new ParameterizedTypeReference<ResponseMsg<Integer>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

    /**
     * 新增并启动流程
     *
     * @param processParam 参数对象
     * @return StartResultBean
     */
    public StartResultBean saveAndStartProcess(ProcessParameter<MeetingDTO> processParam){
        String url = BASE_PATH + "/save-and-start-process/v1";
        ResponseMsg<StartResultBean> responseMsg = restClient.doPost(SERVICE_CODE, url, processParam, new ParameterizedTypeReference<ResponseMsg<StartResultBean>>(){});
        return RestClientUtils.getResponseBody(responseMsg);
    }
}