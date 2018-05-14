/**
 * 定义一个安全门，实现IDoor接口
 * 1、实现接口使用关键字:implements
 * 2、实现接口，必须实现接口中的所有方法
 */
package interfaces;

import java.util.Stack;

public class SafeDoor implements IDoor {

    @Override
    public void CloseDoor() {
        System.out.println("SaftDoor is closed.");

    }

    @Override
    public void OpenDoor() {
        System.out.println("SafeDoor is opened.");

    }

    public static void main(String args[]){
    	IDoor d = new AutomaticDoor();
    	d.CloseDoor();
    	Stack s = new Stack();
    	
    }
}