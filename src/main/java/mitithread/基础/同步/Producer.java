package mitithread.基础.同步;

/**
 * 生产者
 * @title
 * @description 
 * @author Xuxiaobing
 * @date 2015-1-4 下午05:40:08 
 * @version v1.0
 */
public class Producer implements Runnable{
	private Info info = null;
	 
	Producer(Info info) {
		this.info = info;
	}
	 
	public void run() {
		boolean flag = false;
		for (int i = 0; i < 25; ++i) {
			if (flag) {
				this.info.set("20Rollen", 20);
				flag = false;
			} else {
				this.info.set("100ChunGe", 100);
				flag = true;
			}
		}
	}
}
