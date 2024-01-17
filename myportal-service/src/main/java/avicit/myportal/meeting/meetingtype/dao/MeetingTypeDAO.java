package avicit.myportal.meeting.meetingtype.dao;

import avicit.myportal.meeting.meetingtype.dto.MeetingTypeDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-15 15:21
 * @类说明：会议类型表Dao
 * @修改记录：
 */
public interface MeetingTypeDAO {

	/**
	 * 按主键查询
	 * @param id 主键id
	 * @return MeetingTypeDTO
	 */
	public MeetingTypeDTO findMeetingTypeById(String id);

	/**
	 * 按照父id查询子节点
	 * @param id 父id
	 * @param orgIdentity 组织id
	 * @return List<MeetingTypeDTO>
	 */
	public List<MeetingTypeDTO> findChildrenMeetingTypeById(@Param("id") String id, @Param("orgIdentity") String orgIdentity);

    /**
     * 树层级查询
     * @param treeLevel
     * @return List<MeetingTypeDTO>
     */
    public List<MeetingTypeDTO> findMeetingTypeByTreeLevel(@Param("treeLevel") int treeLevel, @Param("orgIdentity") String orgIdentity);

	/**
	 * 按treePath查询
	 * @param treePath
	 * @return List<MeetingTypeDTO>
	 */
	public List<MeetingTypeDTO> findMeetingTypeByTreePath(@Param("treePath") String treePath);

	/**
	 * 根据ids获取所有的节点
	 * @param idsList ids
	 * @return List<MeetingTypeDTO>
	 */
	List<MeetingTypeDTO> searchMeetingTypeByIds(@Param("ids")List<List<String>> idsList);

	/**
	 * 根据条件查询节点
	 * @param meetingTypeDTO 查询条件
	 * @return List<MeetingTypeDTO>
	 */
	public List<MeetingTypeDTO> searchMeetingType(MeetingTypeDTO meetingTypeDTO);

	/**
	 * 新增
	 * @param tree 保存对象
	 * @return int
	 */
	public int insertMeetingType(MeetingTypeDTO tree);

	/**
	 * 更新对象
	 * @param tree 更新对象
	 * @return int
	 */
	public int updateMeetingTypeSensitive(MeetingTypeDTO tree);

	/**
	* 更新对象
	* @param tree 更新对象
	* @return int
	*/
	public int updateMeetingTypeAll(MeetingTypeDTO tree);

	/**
	 * 批量更新对象
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateMeetingTypeList(@Param("dtoList") List<MeetingTypeDTO> dtoList);

	/**
	 * 按主键删除
	 * @param id 主键id
	 * @return int
	 */
	public int deleteMeetingTypeById(String id);
}