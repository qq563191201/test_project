package test.Map;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ListSortUtilTest extends TestCase {

	private List<TestUser> list = null;

	/**
	 * 打印清单
	 * 
	 * @author Ken_xu
	 */
	private void printListEg() {
		List<TestUser> TestUserList = new ArrayList<TestUser>();
		for (int i = 0, maxIdx = list.size(); i < maxIdx; i++) {
			TestUser tm = list.get(i);
			String showTxt = String.format("%d)\t modelName=%s;\t\t extendsModel=%s;", i, tm.getModelName(), tm.getExtendsModels());
			System.out.println(showTxt);
			if (i == (maxIdx - 1)) {
				showTxt = String.format("...................总共：%d条", list.size());
				System.out.println(showTxt);
			}
			TestUserList.add(tm);
		}
		list = TestUserList;
	}

	/**
	 * 初始化测试用例
	 */
	protected void setUp() throws Exception {
		super.setUp();

		List<TestUser> TestUserList = new ArrayList<TestUser>();
		long rowNum = 0l;
		for (int i = 0; i < 10; i++) {
			TestUser tm = new TestUser();
			if (i % 2 == 0) {
				// 每两个对象的modelName相同
				rowNum = Math.round(Math.random() * 100);
			}
			tm.setModelName("AAA_TEST_" + rowNum);
			tm.setExtendsModels("BBBBBBB" + i);
			TestUserList.add(tm);
		}
		list = TestUserList;
	}

	/**
	 * 测试排序
	 * 
	 * @author Ken_xu
	 */
	@SuppressWarnings("unchecked")
	public void testSort() {
		ListSortUtil<TestUser> sortUtil = new ListSortUtil(TestUser.class);
		try {
			sortUtil.addDesc("modelName");
//			sortUtil.addAsc("extendsModels");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		System.out.println("打印排序前结果");
		printListEg();
		sortUtil.sortList(list);
		System.out.println("打印排序后结果");
		printListEg();
	}

	class TestUser {
		String modelName, extendsModels;

		public String getModelName() {
			return modelName;
		}

		public void setModelName(String modelName) {
			this.modelName = modelName;
		}

		public String getExtendsModels() {
			return extendsModels;
		}

		public void setExtendsModels(String extendsModels) {
			this.extendsModels = extendsModels;
		}
	}
}