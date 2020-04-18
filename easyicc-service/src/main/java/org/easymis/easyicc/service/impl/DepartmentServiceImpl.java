package org.easymis.easyicc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.easymis.easyicc.common.result.RestResult;
import org.easymis.easyicc.domain.entity.Department;
import org.easymis.easyicc.domain.vo.DepartmentTreeVo;
import org.easymis.easyicc.mybatis.mapper.DepartmentMapper;
import org.easymis.easyicc.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentMapper mapper;
	@Override
	public boolean save(Department bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Department bean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Department findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageInfo<?> find(Department bean, Integer pageNum, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestResult deleteByIds(String ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DepartmentTreeVo> getTree(String orgId) {
		List<Department> departmentList = mapper.findByOrgId(orgId);
		List<DepartmentTreeVo> firstList = new ArrayList<DepartmentTreeVo>();
		for (int i = 0; i < departmentList.size(); i++) {
			if (StringUtils.isEmpty(departmentList.get(i).getParentId())) {
				DepartmentTreeVo firstDepartment = new DepartmentTreeVo();
				BeanUtils.copyProperties(departmentList.get(i), firstDepartment);
				List<DepartmentTreeVo> secondList = new ArrayList<DepartmentTreeVo>();
				for (int j = 0; j < departmentList.size(); j++) {
					if (departmentList.get(j).getParentId() != null
							&& departmentList.get(j).getParentId().equals(firstDepartment.getId())) {
						DepartmentTreeVo secondDepartment = new DepartmentTreeVo();
						BeanUtils.copyProperties(departmentList.get(j), secondDepartment);
						List<DepartmentTreeVo> thirdList = new ArrayList<DepartmentTreeVo>();
						for (int k = 0; k < departmentList.size(); k++) {
							if (departmentList.get(k).getParentId() != null
									&& departmentList.get(k).getParentId().equals(secondDepartment.getId())) {
								DepartmentTreeVo thirdDepartment = new DepartmentTreeVo();
								BeanUtils.copyProperties(departmentList.get(k), thirdDepartment);
								thirdDepartment.setDepartmentList(new ArrayList<DepartmentTreeVo>());
								thirdList.add(thirdDepartment);
							}

						}
						secondDepartment.setDepartmentList(thirdList);
						secondList.add(secondDepartment);
					}
				

				}
				firstDepartment.setDepartmentList(secondList);
				firstList.add(firstDepartment);
			}
		}
		return firstList;
	}

	@Override
	public List<Department> findByOrgId(String orgId) {
		// TODO Auto-generated method stub
		return mapper.findByOrgId(orgId);
	}

}
