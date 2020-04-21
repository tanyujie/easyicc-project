package org.easymis.easyicc.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.easymis.easyicc.domain.entity.IccFile;

public interface IccFileMapper {
	@Select("select * from icc_file")
	public List<IccFile> getList(IccFile params);

	@Insert("insert into icc_file(id,parent_id,org_id,file_path,file_name,depict,file_size,picture_length,picture_width,create_time,folder_flag)values(#{id},#{parentId},#{orgId},#{filePath},#{fileName},#{depict},#{fileSize},#{pictureLength},#{pictureWidth},#{createTime},#{folderFlag})")
	public boolean save(IccFile bean);

	@Insert("insert into icc_file(id,parent_id,org_id,file_path,file_name,depict,file_size,picture_length,picture_width,create_time,folder_flag)values(#{id},#{parentId},#{orgId},#{filePath},#{fileName},#{depict},#{fileSize},#{pictureLength},#{pictureWidth},#{createTime},#{folderFlag})")
	public void saveBatch(List<IccFile> beans);

	@Update("UPDATE icc_file SET id= #{id},parent_id= #{parentId},org_id= #{orgId},file_path= #{filePath},file_name= #{fileName},depict= #{depict},file_size= #{fileSize},picture_length= #{pictureLength},picture_width= #{pictureWidth},create_time= #{createTime},folder_flag= #{folderFlag} WHERE id= #{id}")
	public boolean update(IccFile bean);

	@Delete(" DELETE FROM icc_file WHERE id = #{id}")
	public void delete(String id);

	public void removeBatch(List<String> list);

	public void restoreBatch(List<String> list);

	public void deleteBatch(List<String> list);

	@Select("select * from icc_file t WHERE t.id = #{id}")
	@Results(value = { @Result(property = "id", column = "id"), @Result(property = "parentId", column = "parent_id"),
			@Result(property = "orgId", column = "org_id"), @Result(property = "filePath", column = "file_path"),
			@Result(property = "fileName", column = "file_name"), @Result(property = "depict", column = "depict"),
			@Result(property = "fileSize", column = "file_size"),
			@Result(property = "pictureLength", column = "picture_length"),
			@Result(property = "pictureWidth", column = "picture_width"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "folderFlag", column = "folder_flag") })
	public IccFile findById(String id);

	@Select(" SELECT t.* FROM icc_file t }")
	public List<IccFile> findByIds(List<String> list);
}