package avicit.myportal.meeting.meetingroom.dao;

import avicit.myportal.meeting.meetingroom.dto.MeetingRoomDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-15 15:35
* @类说明：会议室台账表Dao
* @修改记录：
*/
public interface MeetingRoomDAO {

	/**
	 * 分页查询
	 * @param meetingRoomDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<MeetingRoomDTO>
	 */
	public Page<MeetingRoomDTO> searchMeetingRoomByPage(@Param("bean") MeetingRoomDTO meetingRoomDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 不分页查询
	 * @param meetingRoomDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @return List<MeetingRoomDTO>
	 */
	public List<MeetingRoomDTO> searchMeetingRoom(@Param("bean") MeetingRoomDTO meetingRoomDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret);

	/**
	 * 按条件导出查询
	 * @param meetingRoomDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<MeetingRoomDTO>
	 */
	public List<MeetingRoomDTO> searchMeetingRoomForExportExcel(@Param("bean") MeetingRoomDTO meetingRoomDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 主键查询
	 * @param id 主键id
	 * @return MeetingRoomDTO
	 */
	public MeetingRoomDTO findMeetingRoomById(String id);

	/**
	 * 新增
	 * @param meetingRoomDTO 保存对象
	 * @return int
	 */
	public int insertMeetingRoom(MeetingRoomDTO meetingRoomDTO);

	/**
	 * 批量新增
	 * @param dtoList 保存对象集合
	 * @return int
	 */
	public int insertMeetingRoomList(@Param("dtoList") List<MeetingRoomDTO> dtoList);

	/**
	 * 更新部分对象
	 * @param meetingRoomDTO 更新对象
	 * @return int
	 */
	public int updateMeetingRoomSensitive(MeetingRoomDTO meetingRoomDTO);

	/**
	 * 更新全部对象
	 * @param meetingRoomDTO 更新对象
	 * @return int
	 */
	public int updateMeetingRoomAll(MeetingRoomDTO meetingRoomDTO);

	/**
	 * 批量更新对象
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateMeetingRoomList(@Param("dtoList") List<MeetingRoomDTO> dtoList);

	/**
	 * 按主键删除
	 * @param id 主键id
	 * @return int
	 */
	public int deleteMeetingRoomById(String id);
}