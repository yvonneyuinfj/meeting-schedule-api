package avicit.myportal.meeting.meetingtype.api;

import avicit.platform6.core.domain.VueNode;
import avicit.platform6.core.restclient.RestClient;
import avicit.platform6.core.restclient.RestClientUtils;
import avicit.platform6.core.rest.msg.ResponseMsg;
import avicit.myportal.meeting.meetingtype.dto.MeetingTypeDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-15 15:21
 * @类说明：会议类型表Api
 * @修改记录：
 */
@Component
public class MeetingTypeApi {

	/**
	 * 服务编码
	 */
	private static final String SERVICE_CODE = "myportal";
	private static final String BASE_PATH = "/api/myportal/meeting/meetingtypes";

	@Autowired
	private RestClient restClient;

	/**
     * 加载树
     *
     * @param level 树的等级
     * @param id 父节点ID
     * @return List<VueNode>
     */
	public List<VueNode> getTree(int level, String id){
		String url = BASE_PATH+"/get-tree/" + level + "/" + id + "/v1";
		 ResponseMsg<List<VueNode>> responseMsg = restClient.doGet(SERVICE_CODE, url, new ParameterizedTypeReference<ResponseMsg<List<VueNode>>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
	}
	
	/**
	 * 查询树节点
     *
     * @param searchText 查询条件
     * @return List<VueNode>
     */
	public List<VueNode> searchMeetingType(String searchText){
		String url = BASE_PATH+"/search/v1";
		ResponseMsg<List<VueNode>> responseMsg = restClient.doPost(SERVICE_CODE, url, searchText, new ParameterizedTypeReference<ResponseMsg<List<VueNode>>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
	}
	
	/**
     * 初始化根节点 如果需要修改根节点数据通过前台页面设置
     *
     * @return String
     */
    public String insertRoot() {
        String url = BASE_PATH+"/insert-root/v1";
        ResponseMsg<String> responseMsg = restClient.doPost(SERVICE_CODE, url, "", new ParameterizedTypeReference<ResponseMsg<String>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }
	
	/**
	 * 通过ID获取单条记录
     *
	 * @param id 主键id
	 * @return MeetingTypeDTO
	 */
	public MeetingTypeDTO getMeetingTypeDtoById(String id){
		String url = BASE_PATH+"/get/"+ id + "/v1" ;
		 ResponseMsg<MeetingTypeDTO> responseMsg = restClient.doGet(SERVICE_CODE, url, new ParameterizedTypeReference<ResponseMsg<MeetingTypeDTO>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }

	/**
     * 新增对象
     *
     * @return String
     */
	public String save(MeetingTypeDTO dto) {
		String url = BASE_PATH+"/save/v1";
		 ResponseMsg<String> responseMsg = restClient.doPost(SERVICE_CODE, url, dto, new ParameterizedTypeReference<ResponseMsg<String>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }
	
	/**
     * 按主键单条删除
     *
     * @param id 主键id
     * @return Integer
     */
    public Integer deleteById(String id) {
        String url = BASE_PATH+"/delete-by-id/" + id + "/v1";
        ResponseMsg<Integer> responseMsg = restClient.doDelete(SERVICE_CODE, url, new ParameterizedTypeReference<ResponseMsg<Integer>>() {
        });
        return RestClientUtils.getResponseBody(responseMsg);
    }
}