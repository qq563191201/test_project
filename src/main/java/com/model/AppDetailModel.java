package com.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonSerialize(include=Inclusion.NON_NULL)
public class AppDetailModel{
	
	private List<Datas> data = new ArrayList<Datas>();
	
	@JsonSerialize(include=Inclusion.NON_NULL)
	public static class Datas{
		private String apkName;//软件名
		private String name;//软件包名称
		private String apkpath;//app软件包下载地址
		private String mtypeCode;//1-应用 2-游戏
		private String typeCode;//类别id
		private String clientDownloadUrl;//开发商网址
		private String discription;//描述
		private String id;//ID
		private String downloadTotalNum;//下载次数
		private String smallIcon;//小图
		private String meduimIcon;//中图
		private String largeIcon;//大图标
		private String virusIcon;//卡巴杀毒图标
		private String appId;//应用ID
		private String apkSize;//应用大小
		private String url;//截图url地址
		private String picPath;//截图地址
		private String detail;//摘要
		private String system;//支持系统
		private String versionCode;//版本号
		private String version;//版本
		private String isVirus;//卡巴杀毒结果：100 未扫描，0无毒
		private String safeType;//0未知,1安全软件,2病毒软件,3中风险软件,4低风险软件
		private String searchTag;//搜索关键字，逗号分隔
		private String pubCountry;//发布国家
		private String linkEmail;//联系人邮件
		private String linkPhone;//联系人电话
		private String starNum;//应用星级
		private List<Image> images = new ArrayList<Image>();
		
		public String getApkName() {
			return apkName;
		}
		public void setApkName(String apkName) {
			this.apkName = apkName;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getApkpath() {
			return apkpath;
		}
		public void setApkpath(String apkpath) {
			this.apkpath = apkpath;
		}
		public String getMtypeCode() {
			return mtypeCode;
		}
		public void setMtypeCode(String mtypeCode) {
			this.mtypeCode = mtypeCode;
		}
		public String getTypeCode() {
			return typeCode;
		}
		public void setTypeCode(String typeCode) {
			this.typeCode = typeCode;
		}
		public String getClientDownloadUrl() {
			return clientDownloadUrl;
		}
		public void setClientDownloadUrl(String clientDownloadUrl) {
			this.clientDownloadUrl = clientDownloadUrl;
		}
		public String getDiscription() {
			return discription;
		}
		public void setDiscription(String discription) {
			this.discription = discription;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getDownloadTotalNum() {
			return downloadTotalNum;
		}
		public void setDownloadTotalNum(String downloadTotalNum) {
			this.downloadTotalNum = downloadTotalNum;
		}
		public String getSmallIcon() {
			return smallIcon;
		}
		public void setSmallIcon(String smallIcon) {
			this.smallIcon = smallIcon;
		}
		public String getMeduimIcon() {
			return meduimIcon;
		}
		public void setMeduimIcon(String meduimIcon) {
			this.meduimIcon = meduimIcon;
		}
		public String getLargeIcon() {
			return largeIcon;
		}
		public void setLargeIcon(String largeIcon) {
			this.largeIcon = largeIcon;
		}
		public String getVirusIcon() {
			return virusIcon;
		}
		public void setVirusIcon(String virusIcon) {
			this.virusIcon = virusIcon;
		}
		public String getAppId() {
			return appId;
		}
		public void setAppId(String appId) {
			this.appId = appId;
		}
		public String getApkSize() {
			return apkSize;
		}
		public void setApkSize(String apkSize) {
			this.apkSize = apkSize;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getPicPath() {
			return picPath;
		}
		public void setPicPath(String picPath) {
			this.picPath = picPath;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getSystem() {
			return system;
		}
		public void setSystem(String system) {
			this.system = system;
		}
		public String getVersionCode() {
			return versionCode;
		}
		public void setVersionCode(String versionCode) {
			this.versionCode = versionCode;
		}
		public String getVersion() {
			return version;
		}
		public void setVersion(String version) {
			this.version = version;
		}
		public String getIsVirus() {
			return isVirus;
		}
		public void setIsVirus(String isVirus) {
			this.isVirus = isVirus;
		}
		public String getSafeType() {
			return safeType;
		}
		public void setSafeType(String safeType) {
			this.safeType = safeType;
		}
		public String getSearchTag() {
			return searchTag;
		}
		public void setSearchTag(String searchTag) {
			this.searchTag = searchTag;
		}
		public String getPubCountry() {
			return pubCountry;
		}
		public void setPubCountry(String pubCountry) {
			this.pubCountry = pubCountry;
		}
		public String getLinkEmail() {
			return linkEmail;
		}
		public void setLinkEmail(String linkEmail) {
			this.linkEmail = linkEmail;
		}
		public String getLinkPhone() {
			return linkPhone;
		}
		public void setLinkPhone(String linkPhone) {
			this.linkPhone = linkPhone;
		}
		public String getStarNum() {
			return starNum;
		}
		public void setStarNum(String starNum) {
			this.starNum = starNum;
		}
		public List<Image> getImages() {
			return images;
		}
		public void setImages(List<Image> images) {
			this.images = images;
		}
	}
	
	public static class Image{
		private String id;
		private String url;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	}

	public List<Datas> getData() {
		return data;
	}

	public void setData(List<Datas> data) {
		this.data = data;
	}

	public static AppDetailModel FactoryAppDetailModel(){
		return new AppDetailModel();
	}
	
	public static Datas FactoryDatas(){
		return new Datas();
	}
	
	public static Image FactoryImage(){
		return new Image();
	}
}
