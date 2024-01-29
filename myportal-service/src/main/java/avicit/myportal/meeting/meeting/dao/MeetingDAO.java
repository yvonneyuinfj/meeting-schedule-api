package avicit.myportal.meeting.meeting.dao;

import avicit.myportal.meeting.meeting.dto.MeetingDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-19 16:06
* @类说明：会议日程信息表Dao
* @修改记录：
*/
public interface MeetingDAO {

	/**
	 * 分页查询
	 * @param meetingDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<MeetingDTO>
	 */
	public Page<MeetingDTO> searchMeetingByPage(@Param("bean") MeetingDTO meetingDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 不分页查询
	 * @param meetingDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @return List<MeetingDTO>
	 */
	public List<MeetingDTO> searchMeeting(@Param("bean") MeetingDTO meetingDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret);
	public List<MeetingDTO> searchMeetingsByUserId(@Param("bean") MeetingDTO meetingDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 按条件导出查询
	 * @param meetingDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<MeetingDTO>
	 */
	public List<MeetingDTO> searchMeetingForExportExcel(@Param("bean") MeetingDTO meetingDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 主键查询
	 * @param id 主键id
	 * @return MeetingDTO
	 */
	public MeetingDTO findMeetingById(String id);

	/**
	 * 新增
	 * @param meetingDTO 保存对象
	 * @return int
	 */
	public int insertMeeting(MeetingDTO meetingDTO);

	/**
	 * 批量新增
	 * @param dtoList 保存对象集合
	 * @return int
	 */
	public int insertMeetingList(@Param("dtoList") List<MeetingDTO> dtoList);

	/**
	 * 更新部分对象
	 * @param meetingDTO 更新对象
	 * @return int
	 */
	public int updateMeetingSensitive(MeetingDTO meetingDTO);

	/**
	 * 更新全部对象
	 * @param meetingDTO 更新对象
	 * @return int
	 */
	public int updateMeetingAll(MeetingDTO meetingDTO);

	/**
	 * 批量更新对象
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateMeetingList(@Param("dtoList") List<MeetingDTO> dtoList);

	/**
	 * 按主键删除
	 * @param id 主键id
	 * @return int
	 */
	public int deleteMeetingById(String id);
}