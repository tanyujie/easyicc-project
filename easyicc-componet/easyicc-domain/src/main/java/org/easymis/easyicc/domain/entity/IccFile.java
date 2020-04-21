package org.easymis.easyicc.domain.entity;
import java.io.Serializable;
import java.util.Date;

import lombok.Data; 
 
  
  
@Data
public class IccFile implements Serializable {

	private String id;
	// 上级目录", column = "parent_id", length = 40, isnull = true)
	private String parentId;
	// 组织id", column = "org_id", length = 40, isnull = true)
	private String orgId;
	// 文件路径", column = "file_path", length = 255, isnull = true)
	private String filePath;
	// 文件名", column = "file_name", length = 255, isnull = true)
	private String fileName;
	// 备注", column = "depict", length = 255, isnull = true)
	private String depict;
	// 大小", column = "file_size", isnull = true)
	private Integer fileSize;
	// 图片长度", column = "picture_length", isnull = true)
	private Integer pictureLength;
	// 图片宽度", column = "picture_width", isnull = true)
	private Integer pictureWidth;
	// 上传日期", column = "create_time", isnull = true)
	private Date createTime;
	// 1文件0文件夹", column = "folder_flag", isnull = true)
	private Boolean folderFlag;

}