package com.eyugame.tade.module.glops.model;  
  
import com.eyugame.tade.module.glops.constant.Color;  
  
/** 
 * 单元格 
 *  
 * @author k60 
 */  
public class Cell {  
  
    /** 
     * x坐标 
     */  
    public int X;  
  
    /** 
     * y坐标 
     */  
    public int Y;  
  
    /** 
     * 颜色 
     */  
    public Color color;  
  
    public Cell() {  
        super();  
    }  
  
    public Cell(int x, int y) {  
        super();  
        X = x;  
        Y = y;  
    }  
  
    public int getX() {  
        return X;  
    }  
  
    public void setX(int x) {  
        X = x;  
    }  
  
    public int getY() {  
        return Y;  
    }  
  
    public void setY(int y) {  
        Y = y;  
    }  
  
    public Color getColor() {  
        return color;  
    }  
  
    public void setColor(Color color) {  
        this.color = color;  
    }  
  
    public boolean nearCell(Cell cell) {  
        if (cell != null) {  
            if (this.X == cell.X && this.Y == (cell.Y + 1)) {  
                return true;  
            } else if (this.X == cell.X && this.Y == (cell.Y - 1)) {  
                return true;  
            } else if (this.X == (cell.X + 1) && this.Y == cell.Y) {  
                return true;  
            } else if (this.X == (cell.X - 1) && this.Y == cell.Y) {  
                return true;  
            }  
        }  
        return false;  
    }  
      
    @Override  
    public String toString() {  
        return this.X+"_"+this.Y+":"+this.color;  
                  
    }  
  
    @Override  
    public int hashCode() {  
        final int prime = 31;  
        int result = 1;  
        result = prime * result + X;  
        result = prime * result + Y;  
        return result;  
    }  
  
    @Override  
    public boolean equals(Object obj) {  
        if (this == obj)  
            return true;  
        if (obj == null)  
            return false;  
        if (getClass() != obj.getClass())  
            return false;  
        Cell other = (Cell) obj;  
        if (X != other.X)  
            return false;  
        if (Y != other.Y)  
            return false;  
        return true;  
    }  
      
    public Cell clone(){  
        Cell cell=new Cell();  
        cell.setX(this.X);  
        cell.setY(this.Y);  
        cell.setColor(this.color);  
        return cell;  
    }  
      
      
}