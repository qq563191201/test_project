/**
 * 定义一个自动门，实现IDoor接口
 * 1、实现接口使用关键字:implements
 * 2、实现接口，必须实现接口中的所有方法
 */
package interfaces;

public class AutomaticDoor implements IDoor {

    @Override
    public void CloseDoor() {
        System.out.println("AutomaticDoor is closed.");

    }

    @Override
    public void OpenDoor() {
        System.out.println("AutomaticDoor is opened.");

    }

}