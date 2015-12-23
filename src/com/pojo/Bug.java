package com.pojo;

import java.util.List;

public class Bug {
	private String bugid;
	private String title;
	private String description;
	private String createuserid;
	private String createtime;
	private String processuserid;
	private String projectid;
	private String status;
	private String occurtime;
	private String leavel;
	
	
	private List<String> images;//录入bug时候缓存图片路径的
	
	
	private List<Images> image;//查询该bug下的所有上传的图片
	private User user;
	private User processedUser;
	private String commentNum;
	private List<Comment> comment;
	private Project project;
	
	
	public String getBugid() {
		return bugid;
	}
	public void setBugid(String bugid) {
		this.bugid = bugid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getProcessuserid() {
		return processuserid;
	}
	public void setProcessuserid(String processuserid) {
		this.processuserid = processuserid;
	}
	public String getProjectid() {
		return projectid;
	}
	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOccurtime() {
		return occurtime;
	}
	public void setOccurtime(String occurtime) {
		this.occurtime = occurtime;
	}
	public String getLeavel() {
		return leavel;
	}
	public void setLeavel(String leavel) {
		this.leavel = leavel;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<Images> getImage() {
		return image;
	}
	public void setImage(List<Images> image) {
		this.image = image;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getProcessedUser() {
		return processedUser;
	}
	public void setProcessedUser(User processedUser) {
		this.processedUser = processedUser;
	}
	public String getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(String commentNum) {
		this.commentNum = commentNum;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	@Override
	public String toString() {
		return "Bug [bugid=" + bugid + ", title=" + title + ", description="
				+ description + ", createuserid=" + createuserid
				+ ", createtime=" + createtime + ", processuserid="
				+ processuserid + ", projectid=" + projectid + ", status="
				+ status + ", occurtime=" + occurtime + ", leavel=" + leavel
				+ ", images=" + images + ", image=" + image + ", user=" + user
				+ ", processedUser=" + processedUser + ", commentNum="
				+ commentNum + ", comment=" + comment + ", project=" + project
				+ "]";
	}
	
	
	
}
