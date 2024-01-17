package avicit.myportal.event.event.dao;

import avicit.myportal.event.event.dto.EventDTO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @金航数码科技有限责任公司
* @作者：yxy
* @邮箱：yxy@avic.com
* @创建时间： 2024-01-16 13:47
* @类说明：日程表Dao
* @修改记录：
*/
public interface EventDAO {

	/**
	 * 分页查询
	 * @param eventDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<EventDTO>
	 */
	public Page<EventDTO> searchEventByPage(@Param("bean") EventDTO eventDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 不分页查询
	 * @param eventDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @return List<EventDTO>
	 */
	public List<EventDTO> searchEvent(@Param("bean") EventDTO eventDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret);

	/**
	 * 按条件导出查询
	 * @param eventDTO 查询对象
	 * @param orgIdentity       组织id
	 * @param wordSecret        文档密级
	 * @param orderBy           排序条件
	 * @param keyWords          查询关键字
	 * @return Page<EventDTO>
	 */
	public List<EventDTO> searchEventForExportExcel(@Param("bean") EventDTO eventDTO, @Param("orgIdentity") String orgIdentity, @Param("wordSecret") List<String> wordSecret, @Param("orderBy") String orderBy, @Param("keyWords") String keyWords);

	/**
	 * 主键查询
	 * @param id 主键id
	 * @return EventDTO
	 */
	public EventDTO findEventById(String id);

	/**
	 * 新增
	 * @param eventDTO 保存对象
	 * @return int
	 */
	public int insertEvent(EventDTO eventDTO);

	/**
	 * 批量新增
	 * @param dtoList 保存对象集合
	 * @return int
	 */
	public int insertEventList(@Param("dtoList") List<EventDTO> dtoList);

	/**
	 * 更新部分对象
	 * @param eventDTO 更新对象
	 * @return int
	 */
	public int updateEventSensitive(EventDTO eventDTO);

	/**
	 * 更新全部对象
	 * @param eventDTO 更新对象
	 * @return int
	 */
	public int updateEventAll(EventDTO eventDTO);

	/**
	 * 批量更新对象
	 * @param dtoList 批量更新对象集合
	 * @return int
	 */
	public int updateEventList(@Param("dtoList") List<EventDTO> dtoList);

	/**
	 * 按主键删除
	 * @param id 主键id
	 * @return int
	 */
	public int deleteEventById(String id);
}