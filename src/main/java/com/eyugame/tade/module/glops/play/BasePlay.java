package com.eyugame.tade.module.glops.play;  
  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.HashSet;  
import java.util.List;  
import java.util.Random;  
import java.util.Set;  
  
import com.eyugame.tade.module.glops.constant.Color;  
import com.eyugame.tade.module.glops.exception.NearCellException;  
import com.eyugame.tade.module.glops.exception.NoSpoilageException;  
import com.eyugame.tade.module.glops.model.Cell;  
import com.eyugame.tade.module.glops.model.RemoveScaleResult;  
  
/** 
 *  
 *  
 * @author pengwei 
 */  
public class BasePlay {  
  
    private final static String LINK = "_";  
    /** 
     * 地图 
     */  
    private Cell[][] maps;  
  
    /** 
     * 横轴单元格数量 
     */  
    private int xSize;  
  
    /** 
     * 竖轴单元格数量 
     */  
    private int ySize;  
    /** 
     * 随机数 
     */  
    private Random random;  
    /** 
     * 可以供随机的颜色 
     */  
    private List<String> liveColorList = new ArrayList<String>();  
  
    /** 
     * 一次移动的一组（可能多次消除和生成） 
     */  
    private List<RemoveScaleResult> removeScaleResultList;  
  
    /** 
     * 要移除的位置 
     */  
    private Set<String> removeCellSet = new HashSet<String>();  
  
    /** 
     * 构造方法 
     *  
     * @param xSize 
     * @param ySize 
     */  
    public BasePlay(int xSize, int ySize) {  
        super();  
        this.xSize = xSize;  
        this.ySize = ySize;  
        this.maps = new Cell[xSize][ySize];  
        random = new Random();  
        this.initMaps();  
        while (this.isDieMap()) {  
            this.initMaps();  
        }  
    }  
  
    /** 
     * 初始化地图,给地图上色 
     */  
    private void initMaps() {  
        this.initLiveColor();  
        for (int i = 0; i < this.xSize; i++) {  
            for (int j = 0; j < this.ySize; j++) {  
                // 可供选择的颜色  
                int liveSize = liveColorList.size();  
                // 判断该位置是否有可供选择的颜色  
                if (liveSize > 0) {  
                    // 随机颜色  
                    int tem = random.nextInt(liveSize);  
                    Cell cell = new Cell();  
                    String liveColor = liveColorList.get(tem);  
                    // 给格子上坐标跟颜色  
                    cell.setX(i);  
                    cell.setY(j);  
                    cell.setColor(Color.valueOf(liveColor));  
                    // 放进地图  
                    maps[i][j] = cell;  
                    // 判断该格子是否有3个连在一起  
                    if (this.isLine(i, j)) {  
                        // 如果是有颜色重叠,从供选择的颜色中去掉该颜色，并重新随机颜色  
                        j = j - 1;  
                        liveColorList.remove(liveColor);  
                    } else {  
                        // 如果颜色没有3个重复，则初始化可供选择颜色  
                        this.initLiveColor();  
                    }  
                } else {  
                    // 如果没有可以选择的颜色，初始化地图  
                    this.maps = new Cell[xSize][ySize];  
                    this.initMaps();  
                    return;  
                }  
            }  
        }  
    }  
  
    /** 
     * 初始化随机颜色 
     */  
    private void initLiveColor() {  
        liveColorList = new ArrayList<String>();  
        Color[] colors = Color.values();  
        for (Color color : colors) {  
            liveColorList.add(new String(color.toString()));  
        }  
    }  
  
    /** 
     * 填充地图 不允许3格一排或者一列 
     *  
     * @param x 
     *            填充格子的x轴 
     * @param y 
     *            填充格子的y轴 
     * @return 是否填充成功 
     */  
    private boolean isLine(int x, int y) {  
        boolean lx1 = x - 1 > -1;  
        boolean lx2 = x - 2 > -1;  
        boolean bx1 = x + 1 < this.xSize;  
        boolean bx2 = x + 2 < this.xSize;  
        boolean ly1 = y - 1 > -1;  
        boolean ly2 = y - 2 > -1;  
        boolean by1 = y + 1 < this.ySize;  
        boolean by2 = y + 2 < this.ySize;  
        if (ly1 && by1) {  
            if (isCellColorEqual(maps[x][y - 1], maps[x][y], maps[x][y + 1])) {  
                return true;  
            }  
        }  
        if (lx1 && bx1) {  
            if (isCellColorEqual(maps[x - 1][y], maps[x][y], maps[x + 1][y])) {  
                return true;  
            }  
        }  
        if (ly2) {  
            if (isCellColorEqual(maps[x][y], maps[x][y - 1], maps[x][y - 2])) {  
                return true;  
            }  
        }  
        if (by2) {  
            if (isCellColorEqual(maps[x][y], maps[x][y + 1], maps[x][y + 2])) {  
                return true;  
            }  
        }  
  
        if (lx2) {  
            if (isCellColorEqual(maps[x][y], maps[x - 1][y], maps[x - 2][y])) {  
                return true;  
            }  
        }  
        if (bx2) {  
            if (isCellColorEqual(maps[x][y], maps[x + 1][y], maps[x + 2][y])) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 相邻3个格子是否同一颜色 
     *  
     * @param cell1 
     *            格子1 
     * @param cell2 
     *            格子2 
     * @param cell3 
     *            格子3 
     * @return 统一颜色为true，不同为false 
     */  
    private boolean isCellColorEqual(Cell cell1, Cell cell2, Cell cell3) {  
        if (cell1 != null && cell2 != null && cell3 != null) {  
            Color color1 = cell1.color;  
            Color color2 = cell2.color;  
            Color color3 = cell3.color;  
            if (color1 != null && color2 != null && color3 != null) {  
                return (color1 == color2 && color1 == color3);  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 在补图要添加的格子中相邻3个格子是否同一颜色 
     *  
     * @param cell1 
     *            格子1 
     * @param cell2 
     *            格子2 
     * @param cell3 
     *            格子3 
     * @return 统一颜色为true，不同为false 
     */  
    private boolean isCellColorEqualInAddCell(Cell cell1, Cell cell2, Cell cell3, Set<Cell> set) {  
        if (cell1 != null && cell2 != null && cell3 != null) {  
            if (set.contains(cell1) && set.contains(cell2) && set.contains(cell3)) {  
                Color color1 = cell1.color;  
                Color color2 = cell2.color;  
                Color color3 = cell3.color;  
                if (color1 != null && color2 != null && color3 != null) {  
                    return (color1 == color2 && color1 == color3);  
                }  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 右边颜色一样的格子 
     */  
    private void isCellColorEqualRight(int x, int y, Color color, Set<String> set) {  
        set.add(this.getKey(x, y));  
        int newX = x + 1;  
        if (newX < this.xSize) {  
            if (maps[newX][y] != null && maps[newX][y].color == color) {  
                this.isCellColorEqualRight(newX, y, color, set);  
            }  
        }  
  
    }  
  
    /** 
     * 左边颜色一样的格子 
     */  
    private void isCellColorEqualLeft(int x, int y, Color color, Set<String> set) {  
        set.add(this.getKey(x, y));  
        int newX = x - 1;  
        if (newX >= 0) {  
            if (maps[newX][y] != null && maps[newX][y].color == color) {  
                this.isCellColorEqualLeft(newX, y, color, set);  
            }  
        }  
  
    }  
  
    /** 
     * 主键生成 
     *  
     * @param x 
     *            x坐标 
     * @param y 
     *            y坐标 
     * @return 
     */  
    private String getKey(int x, int y) {  
        return x + BasePlay.LINK + y;  
    }  
  
    /** 
     * 上边颜色一样的格子 
     */  
    private void isCellColorEqualUp(int x, int y, Color color, Set<String> set) {  
        set.add(this.getKey(x, y));  
        int newY = y - 1;  
        if (newY >= 0) {  
            if (maps[x][newY] != null && maps[x][newY].color == color) {  
                this.isCellColorEqualUp(x, newY, color, set);  
            }  
        }  
    }  
  
    /** 
     * 下边颜色一样的格子 
     */  
    private void isCellColorEqualDown(int x, int y, Color color, Set<String> set) {  
        set.add(this.getKey(x, y));  
        int newY = y + 1;  
        if (newY < this.ySize) {  
            if (maps[x][newY] != null && maps[x][newY].color == color) {  
                this.isCellColorEqualDown(x, newY, color, set);  
  
            }  
        }  
    }  
  
    /** 
     * 在删除的节点中，找到相邻的相同颜色的格子 
     *  
     * @param x 
     * @param y 
     * @param color 
     * @param set 
     * @param cSet 
     */  
    private void nearAdd(int x, int y, Color color, Set<String> set, Set<String> cSet) {  
        if (!cSet.isEmpty()) {  
            String nKey = this.getKey(x, y);  
            cSet.remove(nKey);  
            set.add(nKey);  
            if (x - 1 > -1) {  
                String key = this.getKey(x - 1, y);  
                if (removeCellSet.contains(key) && !set.contains(key) && maps[x - 1][y].color == color) {  
                    this.nearAdd(x - 1, y, color, set, cSet);  
                }  
            }  
            if (x + 1 < this.xSize) {  
                String key = this.getKey(x + 1, y);  
                if (removeCellSet.contains(key) && !set.contains(key) && maps[x + 1][y].color == color) {  
                    this.nearAdd(x + 1, y, color, set, cSet);  
                }  
            }  
            if (y - 1 > -1) {  
                String key = this.getKey(x, y - 1);  
                if (removeCellSet.contains(key) && !set.contains(key) && maps[x][y - 1].color == color) {  
                    this.nearAdd(x, y - 1, color, set, cSet);  
                }  
            }  
            if (y + 1 < this.ySize) {  
                String key = this.getKey(x, y + 1);  
                if (removeCellSet.contains(key) && !set.contains(key) && maps[x][y + 1].color == color) {  
                    this.nearAdd(x, y + 1, color, set, cSet);  
                }  
            }  
        }  
    }  
  
  
    /** 
     * 移动 将source 移动至target 
     *  
     * @param source 
     * @param target 
     * @throws Exception 
     */  
    public List<RemoveScaleResult> move(Cell source, Cell target) {  
        if (source != null && target != null) {  
            if (this.near(source, target)) {  
                Color targetColor = maps[target.X][target.Y].color;  
                Color sourceColor = maps[source.X][source.Y].color;  
                maps[source.X][source.Y].color = targetColor;  
                maps[target.X][target.Y].color = sourceColor;  
                if (!this.isLine(source.X, source.Y) && !this.isLine(target.X, target.Y)) {  
                    maps[source.X][source.Y].color = sourceColor;  
                    maps[target.X][target.Y].color = targetColor;  
                    throw new NoSpoilageException("这次移动没有可消除的格子");  
                } else {  
                    removeScaleResultList = new ArrayList<RemoveScaleResult>();  
                    this.fadeCircle();  
                }  
            } else {  
                throw new NearCellException("目标不在起点旁边");  
            }  
        } else {  
            throw new NullPointerException("起点或者目标为空");  
        }  
        return removeScaleResultList;  
    }  
  
    /** 
     * 起点跟目标点是否相邻 
     *  
     * @param source 
     * @param target 
     * @return 
     */  
    private boolean near(Cell source, Cell target) {  
        if (this.isInMap(source) && this.isInMap(target) && source.nearCell(target)) {  
            return true;  
        }  
        return false;  
    }  
  
    /** 
     * 判断该点是否超界 
     *  
     * @param cell 
     * @return 
     */  
    private boolean isInMap(Cell cell) {  
        if (cell.X > -1 && cell.X < this.xSize && cell.Y > -1 && cell.Y < this.ySize) {  
            return true;  
        }  
        return false;  
    }  
  
    /** 
     * 补图 随机添加格子 
     *  
     * @return 
     */  
    private Set<Cell> addCell(RemoveScaleResult result) {  
        Set<Cell> addCellSet = this.getNonePoint();  
        if (!addCellSet.isEmpty()) {  
            this.addCell(addCellSet, result);  
        }  
        return addCellSet;  
    }  
  
    /** 
     * 补图 
     *  
     * @param addCellSet 
     */  
  
    private void addCell(Set<Cell> addCellSet, RemoveScaleResult result) {  
        List<Cell> list = new ArrayList<Cell>();  
        this.initLiveColor();  
        for (Cell cell : addCellSet) {  
            while (true) {  
                if (!this.liveColorList.isEmpty()) {  
                    int tem = random.nextInt(liveColorList.size());  
                    String liveColor = liveColorList.get(tem);  
                    cell.setColor(Color.valueOf(liveColor));  
                    if (!this.isLineOnAddCell(cell, addCellSet)) {  
                        maps[cell.X][cell.Y] = cell;  
                        list.add(cell);  
                        break;  
                    } else {  
                        liveColorList.remove(liveColor);  
                    }  
                } else {  
                    this.addCell(addCellSet, result);  
                    return;  
                }  
            }  
        }  
          
        if (this.isDieMap()) {  
            this.addCell(addCellSet, result);  
        } else {  
            if (!list.isEmpty()) {  
                result.setNewCellList(list);  
            }  
        }  
    }  
  
      
    /** 
     * 判断在补图要添加的给子中是否有3个连线 
     *  
     * @param x 
     * @param y 
     * @param set 
     * @return 
     */  
    private boolean isLineOnAddCell(Cell cell, Set<Cell> set) {  
        int x=cell.X;  
        int y=cell.Y;  
        boolean lx1 = x - 1 > -1;  
        boolean lx2 = x - 2 > -1;  
        boolean bx1 = x + 1 < this.xSize;  
        boolean bx2 = x + 2 < this.xSize;  
        boolean ly1 = y - 1 > -1;  
        boolean ly2 = y - 2 > -1;  
        boolean by1 = y + 1 < this.ySize;  
        boolean by2 = y + 2 < this.ySize;  
        if (ly1 && by1) {  
            if (isCellColorEqualInAddCell(maps[x][y - 1], cell, maps[x][y + 1], set)) {  
                return true;  
            }  
        }  
        if (lx1 && bx1) {  
            if (isCellColorEqualInAddCell(maps[x - 1][y], cell, maps[x + 1][y], set)) {  
                return true;  
            }  
        }  
        if (ly2) {  
            if (isCellColorEqualInAddCell(cell, maps[x][y - 1], maps[x][y - 2], set)) {  
                return true;  
            }  
        }  
        if (by2) {  
            if (isCellColorEqualInAddCell(cell, maps[x][y + 1], maps[x][y + 2], set)) {  
                return true;  
            }  
        }  
  
        if (lx2) {  
            if (isCellColorEqualInAddCell(cell, maps[x - 1][y], maps[x - 2][y], set)) {  
                return true;  
            }  
        }  
        if (bx2) {  
            if (isCellColorEqualInAddCell(cell, maps[x + 1][y], maps[x + 2][y], set)) {  
                return true;  
            }  
        }  
        return false;  
    }  
  
    /** 
     * 3消 
     */  
    private void fadeCircle() {  
        removeCellSet = new HashSet<String>();  
        RemoveScaleResult result = new RemoveScaleResult();  
        List<List<Cell>> removeCellList = new ArrayList<List<Cell>>();  
        // 判断选出要消除的格子  
        this.createRemoveCell();  
        // 给要消除的给子分块  
        this.blockRemoveCell(removeCellList);  
        // 消除格子，并且降落  
        this.removeCellAndDown();  
        if (!removeCellList.isEmpty()) {  
            result.setRemoveCellList(removeCellList);  
        }  
        // 添加格子  
        if (!removeCellSet.isEmpty()) {  
            this.addCell(result);  
            removeScaleResultList.add(result);  
            // 添加格子后再消除格子  
            this.fadeCircle();  
        }  
    }  
  
    /** 
     * 生成要消掉的节点 同颜色同列或者同行超过3个的都要消掉 
     */  
    private void createRemoveCell() {  
        for (int i = 0; i < this.xSize; i++) {  
            for (int j = 0; j < this.ySize; j++) {  
                Cell source = maps[i][j];  
                String cellKey = this.getKey(i, j);  
                if (source != null && !removeCellSet.contains(cellKey)) {  
                    source.setX(i);  
                    source.setY(j);  
                    Set<String> rowSet = new HashSet<String>();  
                    Set<String> colSet = new HashSet<String>();  
                    this.isCellColorEqualLeft(i, j, source.color, rowSet);  
                    this.isCellColorEqualRight(i, j, source.color, rowSet);  
                    this.isCellColorEqualUp(i, j, source.color, colSet);  
                    this.isCellColorEqualDown(i, j, source.color, colSet);  
                    if (rowSet.size() > 2) {  
                        for (String key : rowSet) {  
                            removeCellSet.add(key);  
                        }  
                    }  
                    if (colSet.size() > 2) {  
                        for (String key : colSet) {  
                            removeCellSet.add(key);  
                        }  
                    }  
                }  
            }  
        }  
    }  
  
    /** 
     * 给要消除的给子分区域 
     */  
    private void blockRemoveCell(List<List<Cell>> removeCellList) {  
        // 复制一份要消掉的格子的集合  
        Set<String> cSet = new HashSet<String>(removeCellSet);  
        for (String key : removeCellSet) {  
            // 不在cSet里面的格子说明被归某一区域了，不需要在分区域了  
            if (!cSet.isEmpty() && cSet.contains(key)) {  
                String[] xy = key.split(BasePlay.LINK);  
                int x = Integer.parseInt(xy[0]);  
                int y = Integer.parseInt(xy[1]);  
                Set<String> set = new HashSet<String>();  
                // 为该格子相邻的格子迭代扩张，并从cSet中移除掉  
                this.nearAdd(x, y, maps[x][y].color, set, cSet);  
                if (!set.isEmpty()) {  
                    List<Cell> list = new ArrayList<Cell>();  
                    for (String key2 : set) {  
                        String[] xy2 = key2.split(BasePlay.LINK);  
                        int x2 = Integer.parseInt(xy2[0]);  
                        int y2 = Integer.parseInt(xy2[1]);  
                        maps[x2][y2].X = x2;  
                        maps[x2][y2].Y = y2;  
                        list.add(maps[x2][y2]);  
                    }  
                    // 对同属于同一区域的要消除的格子排序  
                    Collections.sort(list, new Comparator<Cell>() {  
                        @Override  
                        public int compare(Cell o1, Cell o2) {  
                            if (o1.Y == o2.Y) {  
                                return 0;  
                            } else if (o1.Y > o2.Y) {  
                                return -1;  
                            } else {  
                                return 1;  
                            }  
                        }  
                    });  
                    removeCellList.add(list);  
                }  
            }  
        }  
    }  
  
    /** 
     * 消除要消除的格子跟并且地图格子下降 
     */  
    private void removeCellAndDown() {  
        Set<Integer> set = new HashSet<Integer>();  
        for (String key : removeCellSet) {  
            String[] xy = key.split(BasePlay.LINK);  
            int x = Integer.parseInt(xy[0]);  
            int y = Integer.parseInt(xy[1]);  
            maps[x][y] = null;  
            if (!set.contains(x)) {  
                set.add(x);  
            }  
        }  
        for (Integer x : set) {  
            List<Cell> list = new ArrayList<Cell>();  
            for (int j = this.ySize - 1; j > -1; j--) {  
                Cell cell = maps[x][j];  
                if (cell != null) {  
                    cell.setX(x);  
                    cell.setY(j);  
                    list.add(cell.clone());  
                    maps[x][j] = null;  
                }  
            }  
            int j = this.ySize - 1;  
            for (Cell cell : list) {  
                cell.setX(x);  
                maps[x][j] = cell;  
                j--;  
            }  
        }  
    }  
  
    /** 
     * 获取空的节点 
     *  
     * @return 
     */  
    private Set<Cell> getNonePoint() {  
        Set<Cell> set = new HashSet<Cell>();  
        for (int i = 0; i < this.xSize; i++) {  
            for (int j = 0; j < this.ySize; j++) {  
                if (maps[i][j] == null) {  
                    Cell cell = new Cell();  
                    cell.setX(i);  
                    cell.setY(j);  
                    set.add(cell);  
                }  
            }  
        }  
        return set;  
    }  
  
    /** 
     * 是否为死图 
     *  
     * @return 
     */  
    private boolean isDieMap() {  
        for (int i = 0; i < this.xSize; i++) {  
            for (int j = 0; j < this.ySize; j++) {  
                maps[i][j].X = i;  
                maps[i][j].Y = j;  
                if (isDie(i, j) == false) {  
                    return false;  
                }  
            }  
        }  
        return true;  
    }  
  
    /** 
     * 判断该格子是否为死格子 
     *  
     * @param x 
     *            格子的x坐标 
     * @param y 
     *            格子的y坐标 
     * @return 
     */  
    private boolean isDie(int x, int y) {  
        boolean lx1 = x - 1 > -1;  
        boolean lx2 = x - 2 > -1;  
        boolean lx3 = x - 3 > -1;  
        boolean bx1 = x + 1 < this.xSize;  
        boolean bx2 = x + 2 < this.xSize;  
        boolean bx3 = x + 3 < this.xSize;  
        boolean ly1 = y - 1 > -1;  
        boolean ly2 = y - 2 > -1;  
        boolean ly3 = y - 3 > -1;  
        boolean by1 = y + 1 < this.ySize;  
        boolean by2 = y + 2 < this.ySize;  
        boolean by3 = y + 3 < this.ySize;  
        Color color = maps[x][y].color;  
        if (bx1) {  
            if (maps[x + 1][y].color == color) {  
                if (bx3) {  
                    if (maps[x + 3][y].color == color) {  
                        return false;  
                    }  
                }  
                if (bx2 && by1) {  
                    if (maps[x + 2][y + 1].color == color) {  
                        return false;  
                    }  
                }  
                if (bx2 && ly1) {  
                    if (maps[x + 2][y - 1].color == color) {  
                        return false;  
                    }  
                }  
                if (lx2) {  
                    if (maps[x - 2][y].color == color) {  
                        return false;  
                    }  
                }  
                if (lx1 && ly1) {  
                    if (maps[x - 1][y - 1].color == color) {  
                        return false;  
                    }  
                }  
                if (lx1 && by1) {  
                    if (maps[x - 1][y + 1].color == color) {  
                        return false;  
                    }  
                }  
            }  
            if (ly1 && by1) {  
                if (maps[x + 1][y - 1].color == color && maps[x + 1][y + 1].color == color) {  
                    return false;  
                }  
            }  
        }  
        if (lx1) {  
            if (maps[x - 1][y].color == color) {  
                if (lx3) {  
                    if (maps[x - 3][y].color == color) {  
                        return false;  
                    }  
                }  
                if (lx2 && by1) {  
                    if (maps[x - 2][y + 1].color == color) {  
                        return false;  
                    }  
                }  
                if (lx2 && ly1) {  
                    if (maps[x - 2][y - 1].color == color) {  
                        return false;  
                    }  
                }  
                if (bx2) {  
                    if (maps[x + 2][y].color == color) {  
                        return false;  
                    }  
                }  
                if (bx1 && ly1) {  
                    if (maps[x + 1][y - 1].color == color) {  
                        return false;  
                    }  
                }  
                if (bx1 && by1) {  
                    if (maps[x + 1][y + 1].color == color) {  
                        return false;  
                    }  
                }  
            }  
            if (ly1 && by1) {  
                if (maps[x - 1][y - 1].color == color && maps[x - 1][y + 1].color == color) {  
                    return false;  
                }  
            }  
        }  
        if (by1) {  
            if (maps[x][y + 1].color == color) {  
                if (by3) {  
                    if (maps[x][y + 3].color == color) {  
                        return false;  
                    }  
                }  
                if (lx1 && by2) {  
                    if (maps[x - 1][y + 2].color == color) {  
                        return false;  
                    }  
                }  
                if (bx1 && by2) {  
                    if (maps[x + 1][y + 2].color == color) {  
                        return false;  
                    }  
                }  
                if (ly2) {  
                    if (maps[x][y - 2].color == color) {  
                        return false;  
                    }  
                }  
                if (bx1 && ly1) {  
                    if (maps[x + 1][y - 1].color == color) {  
                        return false;  
                    }  
                }  
                if (lx1 && ly1) {  
                    if (maps[x - 1][y - 1].color == color) {  
                        return false;  
                    }  
                }  
            }  
            if (lx1 && bx1) {  
                if (maps[x - 1][y + 1].color == color && maps[x + 1][y + 1].color == color) {  
                    return false;  
                }  
            }  
        }  
        if (ly1) {  
            if (maps[x][y - 1].color == color) {  
                if (ly3) {  
                    if (maps[x][y - 3].color == color) {  
                        return false;  
                    }  
                }  
                if (lx1 && ly2) {  
                    if (maps[x - 1][y - 2].color == color) {  
                        return false;  
                    }  
                }  
                if (bx1 && ly2) {  
                    if (maps[x + 1][y - 2].color == color) {  
                        return false;  
                    }  
                }  
                if (by2) {  
                    if (maps[x][y + 2].color == color) {  
                        return false;  
                    }  
                }  
                if (bx1 && by1) {  
                    if (maps[x + 1][y + 1].color == color) {  
                        return false;  
                    }  
                }  
                if (lx1 && by1) {  
                    if (maps[x - 1][y + 1].color == color) {  
                        return false;  
                    }  
                }  
            }  
            if (lx1 && bx1) {  
                if (maps[x - 1][y - 1].color == color && maps[x + 1][y - 1].color == color) {  
                    return false;  
                }  
            }  
        }  
        return true;  
    }  
  
    public Cell[][] getMaps() {  
        return maps;  
    }  
  
    public void setMaps(Cell[][] maps) {  
        this.maps = maps;  
    }  
  
    public int getxSize() {  
        return xSize;  
    }  
  
    public void setxSize(int xSize) {  
        this.xSize = xSize;  
    }  
  
    public int getySize() {  
        return ySize;  
    }  
  
    public void setySize(int ySize) {  
        this.ySize = ySize;  
    }  
  
}