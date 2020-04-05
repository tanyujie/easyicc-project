package org.easymis.easyicc.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.CommonLanguage;
import org.easymis.easyicc.domain.entity.CommonLanguageCategory;
import org.easymis.easyicc.domain.vo.CommonLanguageTreeVo;
import org.easymis.easyicc.mybatis.mapper.CommonLanguageMapper;
import org.easymis.easyicc.service.CommonLanguageCategoryService;
import org.easymis.easyicc.service.CommonLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class CommonLanguageServiceImpl implements CommonLanguageService {
	@Autowired
	private CommonLanguageMapper mapper;
	@Autowired
	private CommonLanguageCategoryService commonLanguageCategoryService;
	@Override
	public boolean save(CommonLanguage bean) {
		bean.setId(UUID.randomUUID().toString().replace("-", ""));
		return mapper.save(bean);
	}

	@Override
	public boolean update(CommonLanguage bean) {
		// TODO Auto-generated method stub
		return mapper.update(bean);
	}

	@Override
	public CommonLanguage findById(String id) {
		// TODO Auto-generated method stub
		return mapper.findById(id);
	}

	@Override
	public RestResult deleteByIds(String ids) {
		List<String> idsList = Arrays.asList(ids.split(","));
		mapper.deleteBatch(idsList);
		return RestResult.buildSuccess();
	}

	@Override
	public PageInfo<?> find(CommonLanguage bean, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<CommonLanguage> dataList = mapper.getList(bean);
		PageInfo<CommonLanguage> p = new PageInfo<CommonLanguage>(dataList);
		return p;
	}



	@Override
	public RestResult getListByTree(String orgId) {
		if (StringUtils.isEmpty(orgId))
			return RestResult.buildError("组织机构不能为空");
		// 部门树
		List<CommonLanguageCategory> commonLanguageTreeList = commonLanguageCategoryService.findByOrgId(orgId);
		// 查询所有员工信息
		List<CommonLanguage> commonLanguageList = mapper.findByOrgId(orgId);
		List<CommonLanguageTreeVo> treeList = new ArrayList<CommonLanguageTreeVo>();
		for (int i = 0; i < commonLanguageTreeList.size(); i++) {
			CommonLanguageCategory category = commonLanguageTreeList.get(i);
			CommonLanguageTreeVo treeVo = new CommonLanguageTreeVo();
			treeVo.setId(category.getId());
			treeVo.setName(category.getName());
			treeVo.setLanguageList(makeCommonLanguageList(category.getId(), commonLanguageList));
			treeList.add(treeVo);
		}
		return RestResult.buildSuccess(treeList);
	}
	private List<CommonLanguage> makeCommonLanguageList(String categoryId, List<CommonLanguage> commonLanguageList) {
		List<CommonLanguage> list = new ArrayList<CommonLanguage>();
		for (int i = 0; i < commonLanguageList.size(); i++) {
			if (categoryId.equals(commonLanguageList.get(i).getCategoryId())) {
				list.add(commonLanguageList.get(i));
			}

		}
		return list;

	}

	@Override
	public List<CommonLanguage> findList(CommonLanguage bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommonLanguage> findByStaffId(String staffId) {
		// TODO Auto-generated method stub
		return mapper.findByStaffId(staffId);
	}


}
