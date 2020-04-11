package org.easymis.easyicc.domain.entity;

import java.util.Date;

public class Member {
	private String id;
    private String memberId; 
    //member编号",column="member_no",length=40,isnull=false) 
    private String memberNo; 
    private String orgId;
    //性别",column="sex",length=1,isnull=true) 
    private String sex; 
    //年龄",column="age",isnull=true) 
    private Integer age; 
    //公司名称",column="company_name",length=1024,isnull=true) 
    private String companyName; 
    //部门",column="department",length=1024,isnull=true) 
    private String department; 
    //职位",column="position",length=1024,isnull=true) 
    private String position; 
    //密码",column="password",length=60,isnull=true) 
    private String password; 
    //头像地址",column="head_url",length=1024,isnull=true) 
    private String headUrl; 
    //电话号码/手机号码",column="mobile_no",length=32,isnull=false) 
    private String mobileNo; 
    //邮箱",column="email",length=1024,isnull=true) 
    private String email; 
    //修改时间",column="modify_time",isnull=true) 
    private Date modifyTime; 
    //创建时间",column="create_time",isnull=true) 
    private Date createTime; 
    //名字",column="name",length=128,isnull=true) 
    private String name; 
    //启用/禁用",column="enabled",isnull=true) 
    private Boolean enabled; 


	/**
	 * 用户名，账号，慕信号
	 */
	private String username;

	/**
	 * 我的头像，如果没有默认给一张
	 */
	private String faceImage;

	private String faceImageBig;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 新用户注册后默认后台生成二维码，并且上传到fastdfs
	 */
	private String qrcode;

	private String cid;

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 *
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * 获取用户名，账号，慕信号
	 *
	 * @return username - 用户名，账号，慕信号
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置用户名，账号，慕信号
	 *
	 * @param username
	 *            用户名，账号，慕信号
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取密码
	 *
	 * @return password - 密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置密码
	 *
	 * @param password
	 *            密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取我的头像，如果没有默认给一张
	 *
	 * @return face_image - 我的头像，如果没有默认给一张
	 */
	public String getFaceImage() {
		return faceImage;
	}

	/**
	 * 设置我的头像，如果没有默认给一张
	 *
	 * @param faceImage
	 *            我的头像，如果没有默认给一张
	 */
	public void setFaceImage(String faceImage) {
		this.faceImage = faceImage;
	}

	/**
	 * @return face_image_big
	 */
	public String getFaceImageBig() {
		return faceImageBig;
	}

	/**
	 *
	 */
	public void setFaceImageBig(String faceImageBig) {
		this.faceImageBig = faceImageBig;
	}

	/**
	 * 获取昵称
	 *
	 * @return nickname - 昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置昵称
	 *
	 * @param nickname
	 *            昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取新用户注册后默认后台生成二维码，并且上传到fastdfs
	 *
	 * @return qrcode - 新用户注册后默认后台生成二维码，并且上传到fastdfs
	 */
	public String getQrcode() {
		return qrcode;
	}

	/**
	 * 设置新用户注册后默认后台生成二维码，并且上传到fastdfs
	 *
	 * @param qrcode
	 *            新用户注册后默认后台生成二维码，并且上传到fastdfs
	 */
	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	/**
	 * @return cid
	 */
	public String getCid() {
		return cid;
	}

	/**
	 *
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
