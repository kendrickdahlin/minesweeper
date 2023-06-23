//Kendrick Dahlin
public class KendricksCell implements Cell {

    private int row,col,neighborMines;
    private boolean visible, mine, flag;
    private MineModel myMineModel;

    public KendricksCell(int row, int col,boolean mine, MineModel input){
        this.row = row;
        this.col = col;
        this.mine = mine;
        this.visible = false;
        this.flag = false;
        this.myMineModel = input;
        this.neighborMines = 0;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public boolean isMine() {
        return mine;
    }

    public void makeMine(){
        mine = true;
    }

    @Override
    public boolean isFlagged() {
        return flag;
    }

    @Override
    public int getNeighborMines() {
        neighborMines = 0;
        for (int count1 = row - 1; count1 < row + 2; count1 += 1) {
            for (int count2 = col - 1; count2 < col + 2; count2 += 1) {
                if (count1 >=0 && count1 <myMineModel.getNumRows() && count2 >=0 && count2<myMineModel.getNumCols()){
                    if (myMineModel.getCell(count1, count2).isMine()) {
                        neighborMines += 1;
                    }
                }
            }
        }
        return neighborMines;
    }

    void makeVisible(){
        visible = true;
    }

    void makeFlagged(){
        if (flag){
            flag = false;
        }
        else if (!flag){
            flag = true;
        }
    }

}
