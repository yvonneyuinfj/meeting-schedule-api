package avicit.myportal.meeting.meetingtype.service;

import avicit.platform6.core.context.ThreadContextHelper;
import avicit.platform6.core.domain.VueNode;
import avicit.platform6.core.exception.BusinessException;
import avicit.platform6.core.exception.DaoException;
import avicit.platform6.modules.system.syslog.service.SysLogUtil;
import avicit.platform6.core.properties.PlatformConstant.OpType;
import avicit.platform6.commons.utils.BusinessUtil;
import avicit.platform6.commons.utils.ComUtil;
import avicit.platform6.commons.utils.PojoUtil;
import org.apache.commons.lang3.StringUtils;
import avicit.platform6.api.system.ConvertColumnClient;
import avicit.platform6.api.system.impl.SystemConstant;
import avicit.myportal.meeting.meetingtype.dao.MeetingTypeDAO;
import avicit.myportal.meeting.meetingtype.dto.MeetingTypeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @金航数码科技有限责任公司
 * @作者：yxy
 * @邮箱：yxy@avic.com
 * @创建时间： 2024-01-15 15:21
 * @类说明：会议类型表Service
 * @修改记录：
 */
@Service
public class MeetingTypeService {

	private static final Logger logger = LoggerFactory.getLogger(MeetingTypeService.class);

	@Autowired
	private MeetingTypeDAO meetingTypeDAO;
    @Autowired
    private ConvertColumnClient convertColumnClient;

	private static final int ID_LIMIT = 500;

	/**
     * 按照父id查询数据
     * @param parentId    父节点ID
     * @param level       树等级
     * @param orgIdentity 组织id
     * @return List<VueNode>
     */
    public List<VueNode> getMeetingTypeByParentId(String parentId, int level, String orgIdentity) {
        List<VueNode> vueNodeList = new ArrayList<>();
        if(!"-1".equals(parentId)){
            List<MeetingTypeDTO> dtoList = meetingTypeDAO.findChildrenMeetingTypeById(parentId, orgIdentity);
            vueNodeList = transToTreeNodeData(dtoList);
            return vueNodeList;
        }else{
            vueNodeList = getMeetingTypeByTreeLevel(level, orgIdentity);
            if(vueNodeList.size() == 0){
                VueNode rootNode = insertRoot();
                vueNodeList.add(rootNode);
            }
        }
        return vueNodeList;
    }

    /**
     * 树层级查询
     * @param level       树等级
     * @param orgIdentity 组织id
     * @return List<VueNode>
     */
    private List<VueNode> getMeetingTypeByTreeLevel(int level, String orgIdentity) {
        List<MeetingTypeDTO> allNodes = meetingTypeDAO.findMeetingTypeByTreeLevel(level, orgIdentity);
        List<VueNode> tree = createTreeData(allNodes);
        return tree;
    }

	/**
	 * 查询树节点
	 * @param text    查询条件
	 * @param orgIdentity 组织id
	 * @return List<ZTreeNode>
	 */
	@Transactional(readOnly = true)
	public List<VueNode> searchMeetingType(String text, String orgIdentity) {
		List<VueNode> tree = new ArrayList<>();
		MeetingTypeDTO queryParams = new MeetingTypeDTO();
		queryParams.setTypeName(text);
		queryParams.setOrgIdentity(orgIdentity);
		List<MeetingTypeDTO> searchNodes = meetingTypeDAO.searchMeetingType(queryParams);
		if (!CollectionUtils.isEmpty(searchNodes)){
			Set<String> treeIdSet = reDuplicationByTreePath(searchNodes);
			int i = 0;
			//考虑mybatis foreach的限制，所以定义参数格式为list内还是list
			List<List<String>> idsList = new ArrayList<List<String>>();
			List<String> idList = new ArrayList<String>();
			for (String treeId : treeIdSet) {
				//当id个数超出限制后，则新new一个list来存放
				if(i % ID_LIMIT == 0 && i > 0){
					idsList.add(idList);
					idList = new ArrayList<String>();
				}
				idList.add(treeId);
				i++;
			}
			idsList.add(idList);
			List<MeetingTypeDTO> allNodes = meetingTypeDAO.searchMeetingTypeByIds(idsList);
			tree = createTreeData(allNodes);
		}
		return tree;
	}

	/**
	 *  组建树形结构
	 * @param entityNodes 节点集合
	 * @return List<VueNode>
	 */
	private List<VueNode> createTreeData(List<MeetingTypeDTO> entityNodes) {
		List<VueNode> list = transToTreeNodeData(entityNodes);
		List<VueNode> tree = new ArrayList<>();
		for (VueNode vueNode : list) {
			//找到根节点
			if ("-1".equals(vueNode.getParentId())) {
				tree.add(vueNode);
			}
			List<VueNode> children = new ArrayList<>();
			//再次遍历list，找到子节点
			for (VueNode node : list) {
				if (vueNode.getId().equals(node.getParentId())) {
					children.add(node);
				}
			}
			if(!children.isEmpty()){
                vueNode.setChildren(children);
            }
		}
		return tree;
	}

    /**
     * 初始化根节点
     * @return
     */
    private VueNode insertRoot() {
        MeetingTypeDTO tree = new MeetingTypeDTO();
        tree.setParentId("-1");
        tree.setOrgIdentity(ThreadContextHelper.getOrgId());
        tree.setTypeName("根节点");
        tree.setSecretLevel("1");
        tree.setTreePath("-1");
        tree.setTreeSort(1L);
        insertMeetingType(tree);
        return transToTreeNodeData(tree);
    }

    /**
     *  将实体类集合转化成VueNode格式
     * @param entityNodes  实体类节点集合
     * @return List<VueNode>
     */
    private List<VueNode> transToTreeNodeData(List<MeetingTypeDTO> entityNodes) {
        List<VueNode> vueNodes = new ArrayList<>();
        for (MeetingTypeDTO entityNode : entityNodes) {
            VueNode vueNode = transToTreeNodeData(entityNode);
            vueNodes.add(vueNode);
        }
        return vueNodes;
    }

    /**
     *  将实体类转化成VueNode格式
     * @param entityNode  实体类节点集合
     * @return List<VueNode>
     */
    private VueNode transToTreeNodeData(MeetingTypeDTO entityNode) {
        VueNode vueNode = new VueNode();
        vueNode.setId(entityNode.getId());
        vueNode.setKey(entityNode.getId());
        vueNode.setTitle(entityNode.getTypeName() == null ? "" : entityNode.getTypeName());
        vueNode.setSelectable(true);
        vueNode.setDisabled(false);
        vueNode.setDisableCheckbox(false);
        vueNode.setParentId(entityNode.getParentId());
        HashMap<String,Object> attr = new HashMap<>();
        attr.put("treeLeaf", entityNode.getTreeLeaf());
        attr.put("treeLevel", entityNode.getTreeLevel());
        attr.put("treePath", entityNode.getTreePath());
        vueNode.setAttributes(attr);
        if ("Y".equals(entityNode.getTreeLeaf())){
            vueNode.setIsLeaf(true);
        } else{
            vueNode.setIsLeaf(false);
        }
        return vueNode;
    }

    /**
     * 根据treePath去重
     * @param entityNodes entityNodes
     */
    private Set<String> reDuplicationByTreePath(List<MeetingTypeDTO> entityNodes) {
        Set<String> treeIdSet = new HashSet<String>();
        for (MeetingTypeDTO dto : entityNodes) {
            String treePath = dto.getTreePath();
            treeIdSet.addAll(Arrays.asList(treePath.split("/")));
        }
        return treeIdSet;
    }

	/**
	 * 通过主键查询单条记录
	 *
	 * @param id 主键id
	 * @return MeetingTypeDTO
	 */
	@Transactional(readOnly = true)
	public MeetingTypeDTO getMeetingTypeDtoById(String id) {
        MeetingTypeDTO dto = meetingTypeDAO.findMeetingTypeById(id);
        valueConvert(Arrays.asList(dto));
        //记录日志
        if (dto != null) {
            SysLogUtil.log4Query(dto);
        }
        return dto;
	}

    /**
     * 按id查询展开树
     * @param id id
     * @return VueNode
     */
    public List<VueNode> expandMeetingTypeById(String id) {
        MeetingTypeDTO meetingTypeDTO = meetingTypeDAO.findMeetingTypeById(id);
        if (meetingTypeDTO == null){
            throw new BusinessException("节点不存在");
        }
        List<String> ids = Arrays.asList(meetingTypeDTO.getTreePath().split("/"));
        MeetingTypeDTO rootDto = meetingTypeDAO.findMeetingTypeById(ids.get(0));
        VueNode rootVueNode = transToTreeNodeData(rootDto);
        VueNode tempVueNode = rootVueNode;
        for (String tid : ids.subList(1, ids.size())) {
            List<MeetingTypeDTO> dtoList = meetingTypeDAO.findChildrenMeetingTypeById(tempVueNode.getId(), tempVueNode.getOrgIdentity());
            List<VueNode> vueNodeList = transToTreeNodeData(dtoList);
            tempVueNode.setChildren(vueNodeList);
            for (VueNode vueNode : vueNodeList) {
                if (vueNode.getId().equals(tid)){
                    tempVueNode = vueNode;
                    break;
                }
            }
        }
        return Collections.singletonList(rootVueNode);
    }

    /**
     * 新增数据
     * @param dto 保存对象
     * @return String
     */
    @Transactional
    public String insertMeetingType(MeetingTypeDTO dto) {
        String id = ComUtil.getId();
        dto.setId(id);
        PojoUtil.setSysProperties(dto, OpType.insert);
        // 自动计算其他相关属性值
        setTreeProperties(dto, null, OpType.insert);
        meetingTypeDAO.insertMeetingType(dto);
        //记录日志
        SysLogUtil.log4Insert(dto);
        return id;
    }

	/**
	 * 更新数据
	 * @param dto 更新对象
	 * @return int
	 */
	@Transactional
	public boolean updateMeetingType(MeetingTypeDTO dto) {
		boolean flag = false;
		List<MeetingTypeDTO> updateChildrenList = null;
		MeetingTypeDTO old = meetingTypeDAO.findMeetingTypeById(dto.getId());
		if (isMoveToSelfChildNode(dto,old)){
			throw new BusinessException("不允许将节点移动到自身或者子节点下");
		}
		String oldParentId = old.getParentId();
		String orgIdentity = old.getOrgIdentity();
		if (isUpdateTreeProperties(dto, old)) {
			setTreeProperties(dto, old, OpType.update);
			updateChildrenList = updateChildrenTreeProperties(dto, old);
			flag = true;
		}
		int ret = meetingTypeDAO.updateMeetingTypeAll(getUpdateDto(dto));
		if (ret == 0) {
			throw new DaoException("数据失效，请重新更新");
		}
		if (!CollectionUtils.isEmpty(updateChildrenList)) {
		    meetingTypeDAO.updateMeetingTypeList(updateChildrenList);
		}
		// 新的父节点变更为非叶子节点
		if (!org.apache.commons.lang3.StringUtils.equals("-1", dto.getParentId())) {
			MeetingTypeDTO parentDTO = meetingTypeDAO.findMeetingTypeById(dto.getParentId());
			parentDTO.setTreeLeaf("N");
			meetingTypeDAO.updateMeetingTypeAll(parentDTO);
		}
		// 判断原节点是否还有子节点，否则改为叶子节点
		List<MeetingTypeDTO> childrenList = meetingTypeDAO.findChildrenMeetingTypeById(oldParentId, orgIdentity);
		if (CollectionUtils.isEmpty(childrenList)) {
			MeetingTypeDTO oldParentDTO = meetingTypeDAO.findMeetingTypeById(oldParentId);
			oldParentDTO.setTreeLeaf("Y");
			meetingTypeDAO.updateMeetingTypeAll(oldParentDTO);
		}
		return flag;
	}

	/**
	 * 判断是是否移动节点到自身或者子节点下
	 * @param dto 更新对象
	 * @param old 源对象
	 * @return boolean
	 */
	private boolean isMoveToSelfChildNode(MeetingTypeDTO dto, MeetingTypeDTO old) {
		if (!dto.getParentId().equals(old.getParentId())){
			//判断是否移动到自身下面
			if (dto.getParentId().equals(old.getId())){
				return true;
			}
			//判断是否移动到子节点下
			List<MeetingTypeDTO> children = meetingTypeDAO.findMeetingTypeByTreePath(old.getTreePath());
			for (MeetingTypeDTO child : children) {
				if (child.getId().equals(dto.getParentId())){
					return true;
				}
			}
		}
		return false;
	}

    /**
     * 内部方法，获取修改的dto对象
     * @param dto
     * @return
     */
    private MeetingTypeDTO getUpdateDto(MeetingTypeDTO dto) {
		MeetingTypeDTO oldDTO = findById(dto.getId());
        if (oldDTO == null) {
            throw new BusinessException("数据不存在");
        }
        //记录日志
        SysLogUtil.log4Update(dto, oldDTO);
        PojoUtil.setSysProperties(dto, OpType.update);
        if (StringUtils.isEmpty(dto.getTreePath())) {
            dto.setTreePath(oldDTO.getTreePath());
        }
        if (dto.getTreeLevel() == null) {
            dto.setTreeLevel(oldDTO.getTreeLevel());
        }
        if (StringUtils.isEmpty(dto.getTreeLeaf())) {
            dto.setTreeLeaf(oldDTO.getTreeLeaf());
        }
        if (StringUtils.isEmpty(dto.getTreeSorts())) {
            dto.setTreeSorts(oldDTO.getTreeSorts());
        }
        PojoUtil.copyProperties(oldDTO, dto, false);
        return oldDTO;
    }

    /**
     * 根据主键删除数据
     * @param id 主键id
     * @return int
     */
    @Transactional
    public int deleteMeetingTypeById(String id) throws Exception {
        if (org.apache.commons.lang3.StringUtils.isEmpty(id)) {
            throw new Exception("删除失败！传入的参数主键为null");
        }
        // 记录日志
        MeetingTypeDTO dto = meetingTypeDAO.findMeetingTypeById(id);
        if (dto == null){
            throw new BusinessException("当前选中节点已被删除！");
        }
        if (!dto.getTreeLeaf().equals("Y")){
            throw new BusinessException("当前选中节点含有子节点，请先删除子节点！");
        }
        SysLogUtil.log4Delete(dto);
        int count = meetingTypeDAO.deleteMeetingTypeById(id);
        setTreeProperties(dto, null, OpType.delete);
        return count;
    }

    /**
     * 日志专用，内部方法，不再记录日志
     * @param id 主键id
     * @return MeetingTypeDTO
     */
    private MeetingTypeDTO findById(String id) {
        return meetingTypeDAO.findMeetingTypeById(id);
    }

    /**
     * 自动计算树形结构其他属性值
     * @param dto 修改后的对象
     * @param old 原对象（更新方法使用）
     * @return
     */
    private void setTreeProperties(MeetingTypeDTO dto, MeetingTypeDTO old, OpType opType) {
        // 获取其父级节点
        MeetingTypeDTO parentDTO = findParentMeetingTypeById(dto.getParentId());
        // 非初始化根节点情况
        if (parentDTO != null) {
            // 判断操作类型
            switch (opType) {
                case insert: // 新增
                    // 设置当前树路经
                    dto.setTreePath(parentDTO.getTreePath() + "/" + dto.getId());
                    // 设置当前树全路径排序
                    Long treeSort = dto.getTreeSort();
                    String treeSorts = String.format("%06d", treeSort);
                    dto.setTreeSorts(parentDTO.getTreeSorts() + "/" + treeSorts);
                    // 设置当前是否为叶子节点，新增全部为叶子节点
                    dto.setTreeLeaf("Y");
                    // 设置当前节点级别
                    dto.setTreeLevel(parentDTO.getTreeLevel() + 1);
                    // 判断当前父节点是否为叶子节点，如果是，则更改为非叶子节点
                    if ("Y".equals(parentDTO.getTreeLeaf())) {
                        parentDTO.setTreeLeaf("N");
                        meetingTypeDAO.updateMeetingTypeAll(parentDTO);
                    }
                    break;
                case update: // 修改
                    // 主要针对于排序字段的修改
                    if (isUpdateTreeProperties(dto, old)) { // 排序字段被修改，则更改整体排序字段
                        // 当前新的本级排序字段
                        String currentTreeSorts = String.format("%06d", dto.getTreeSort());
                        // 当前新的总体排序字段
                        String newTreeSorts = parentDTO.getTreeSorts() + "/" + currentTreeSorts;
                        // 赋值
                        dto.setTreeSorts(newTreeSorts);
                        // 重新生成当前节点tree_path路径
                        String currentTreePath = parentDTO.getTreePath() + "/" + dto.getId();
                        Long currentTreeLevel = parentDTO.getTreeLevel() + 1;
                        // 赋值
                        dto.setTreePath(currentTreePath);
                        dto.setTreeLevel(currentTreeLevel);
                    }
                    break;
                case delete: // 删除
                    // 判断当前父节点下还是否有子节点，如果没有，则需要把父节点置为叶子节点
                    List<MeetingTypeDTO> childrenList = findChildrenMeetingTypeById(dto.getParentId(), dto.getOrgIdentity());
                    if (CollectionUtils.isEmpty(childrenList)) {
                        parentDTO.setTreeLeaf("Y");
                        meetingTypeDAO.updateMeetingTypeAll(parentDTO);
                    }
                    break;
                default:
                    break;
            }
        } else { // 初始化树根节点
            dto.setTreePath(dto.getId());
            // 设置当前树全路径排序
            Long treeSort = dto.getTreeSort();
            String treeSorts = String.format("%06d", treeSort);
            dto.setTreeSorts(treeSorts);
            //设置当前是否为叶子节点，新增全部为叶子节点
            if (opType == OpType.insert) {
                dto.setTreeLeaf("Y");
            }
            // 设置当前节点级别.
            dto.setTreeLevel(1L);
        }
    }

    /**
     * 查询父节点信息
     * @param parentId
     * @return MeetingTypeDTO
     * @throws DaoException
     */
    public MeetingTypeDTO findParentMeetingTypeById(String parentId) {
        MeetingTypeDTO dto = meetingTypeDAO.findMeetingTypeById(parentId);
        // 记录日志
        if (dto != null) {
            SysLogUtil.log4Query(dto);
        }
        return dto;
    }

    private boolean isUpdateTreeProperties(MeetingTypeDTO dto, MeetingTypeDTO old) {
        boolean flag = false;
        if (!old.getTreeSort().equals(dto.getTreeSort()) || !old.getParentId().equals(dto.getParentId())) {
            flag = true;
        }
        return flag;
    }

    private List<MeetingTypeDTO> findChildrenMeetingTypeById(String id, String orgIdentity) {
        return meetingTypeDAO.findChildrenMeetingTypeById(id, orgIdentity);
    }

    /**
     * 更新下属所有节点的tree_path或者是tree_sorts
     * @param parent
     * @param oldParent
     * @return
     */
    private List<MeetingTypeDTO> updateChildrenTreeProperties(MeetingTypeDTO parent, MeetingTypeDTO oldParent) {
        // 原来的排序字段和树路径字段
        String oldTreeSorts = oldParent.getTreeSorts();
        String oldTreePath = oldParent.getTreePath();
        // 现在的排序字段
        String currentTreeSorts = parent.getTreeSorts();
        String currentTreePath = parent.getTreePath();
        // 查找当前节点下的所有子节点
        List<MeetingTypeDTO> list = meetingTypeDAO.findMeetingTypeByTreePath(oldTreePath);
        for (MeetingTypeDTO dto : list) {
            dto.setTreePath(dto.getTreePath().replace(oldTreePath, currentTreePath));
            dto.setTreeSorts(dto.getTreeSorts().substring(0, oldTreeSorts.length()).replace(oldTreeSorts, currentTreeSorts)
                + dto.getTreeSorts().substring(oldTreeSorts.length()));
            dto.setTreeLevel(Long.valueOf(dto.getTreePath().split("/").length));
        }
        return list;
    }

	/**
	 * 通过平台API将字段值转换为名称，包括通用选择组件、通用代码
	 *
	 * @param meetingTypeDTOList
	 */
	private void valueConvert(List<MeetingTypeDTO> meetingTypeDTOList) {
		//循环组装请求数据
		Map<String, Set<String>> convertFormData = new HashMap<>();
		for (MeetingTypeDTO meetingType : meetingTypeDTOList) {
			BusinessUtil.createConvertSet(convertFormData, "PLATFORM_FILE_SECRET_LEVEL", meetingType.getSecretLevel());
		}
		if (convertFormData.size() > 0) {
			//获取请求结果
			Map<String, Map<String, String>> convertResultData = convertColumnClient.replace(convertFormData);
			//循环设置Alias或Name的值
			for (MeetingTypeDTO meetingType : meetingTypeDTOList) {
				meetingType.setSecretLevelName(BusinessUtil.convertFormat(convertResultData, "PLATFORM_FILE_SECRET_LEVEL", meetingType.getSecretLevel()));
			}
		}
	}
}
