package test.Map.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapModel implements Serializable{
	
	private List<Datas> list = new ArrayList<Datas>();
	public static MapModel FactoryMapModel(){
		return new MapModel();
	}
	public static Datas FactoryDatas(){
		return new Datas();
	}
	public static class Datas{
		private String id;
		private String name;
		private String time;
		private String pinying;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getPinying() {
			return pinying;
		}
		public void setPinying(String pinying) {
			this.pinying = pinying;
		}
	}
	public List<Datas> getList() {
		return list;
	}
	public void setList(List<Datas> list) {
		this.list = list;
	}
	
}
