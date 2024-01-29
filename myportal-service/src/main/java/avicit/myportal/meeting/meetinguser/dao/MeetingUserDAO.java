package avicit.myportal.meeting.meetinguser.dao;

import avicit.myportal.meeting.meetinguser.dto.MeetingUserDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-19 15:13
 * @类说明：会议与参会人关系表Dao
 * @修改记录：
 */
public interface MeetingUserDAO {

	/**
	 * 分页查询
	 * @param meetingUserDTO 查询对象
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<MeetingUserDTO>
	 */
	public Page<MeetingUserDTO> searchMeetingUserByPage(@Param("bean") MeetingUserDTO meetingUserDTO, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 不分页查询
	 * @param meetingUserDTO 查询对象
	 * @return List<MeetingUserDTO>
	 */
	public List<MeetingUserDTO> searchMeetingUser(@Param("bean") MeetingUserDTO meetingUserDTO);

	/**
	 * 按条件导出查询
	 * @param meetingUserDTO 查询对象
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<MeetingUserDTO>
	 */
	public List<MeetingUserDTO> searchMeetingUserForExportExcel(@Param("bean") MeetingUserDTO meetingUserDTO,  @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 主键查询
	 * @param id 主键id
	 * @return MeetingUserDTO
	 */
	public MeetingUserDTO findMeetingUserById(String id);

	/**
     * 根据pid查询对象
     * @param pid 父id
     * @return List<MeetingUserDTO>
     */
    public List<MeetingUserDTO> findMeetingUserByPid(String pid);

	/**
	 * 新增
	 * @param meetingUserDTO 保存对象
	 * @return int
	 */
	public int insertMeetingUser(MeetingUserDTO meetingUserDTO);

	/**
	 * 批量新增
	 * @param dtoList 保存对象集合
	 * @return int
	 */
	public int insertMeetingUserList(@Param("dtoList") List<MeetingUserDTO> dtoList);

	/**
	 * 更新部分对象
	 * @param meetingUserDTO 更新对象
	 * @return int
	 */
	public int updateMeetingUserSensitive(MeetingUserDTO meetingUserDTO);

	/**
	 * 更新全部对象
	 * @param meetingUserDTO 更新对象
	 * @return int
	 */
	public int updateMeetingUserAll(MeetingUserDTO meetingUserDTO);

	/**
	 * 批量更新对象
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateMeetingUserList(@Param("dtoList") List<MeetingUserDTO> dtoList);

	/**
	 * 按主键删除
	 * @param id 主键id
	 * @return int
	 */
	public int deleteMeetingUserById(String id);
}