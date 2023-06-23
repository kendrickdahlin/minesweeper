//Kendrick Dahiln
import java.util.Random;

public class KendricksMineModel implements MineModel {

    private int rows, cols, mines, numFlags;
    private boolean start, end, won;
    private KendricksCell[][] allCells;
    private long startTime;

    KendricksMineModel(){
    }

    @Override
    public void newGame(int numRows, int numCols, int numMines) {
        rows = 0;
        cols = 0;
        end = false;
        won = false;
        rows = numRows;
        cols = numCols;
        mines = 0;
        start = true;
        numFlags = 0;
        startTime = System.currentTimeMillis();

        allCells = new KendricksCell[numRows][numCols];

        Random randNumber = new Random();


        for (int count1 = 0; count1<numRows; count1+=1){
            for (int count2 = 0; count2<numCols; count2+=1){
                allCells[count1][count2] = new KendricksCell(count1,count2, false, this);
            }
        }

        for (int count = 0;count<numMines;count+=0){
            int holdRow = randNumber.nextInt(numRows-1);
            int holdCol = randNumber.nextInt(numCols-1);

            if (!allCells[holdRow][holdCol].isMine()){
                allCells[holdRow][holdCol].makeMine();
                count+=1;
            }
        }
    }

    @Override
    public int getNumRows() {
        return rows;
    }


    @Override
    public int getNumCols() {
        return cols;
    }

    @Override
    public int getNumMines() {
        return mines;
    }

    @Override
    public int getNumFlags() {
        numFlags = 0;
        for (int count1 = 0; count1<rows; count1+=1) {
            for (int count2 = 0; count2 < cols; count2 += 1) {
                if (allCells[count1][count2].isFlagged()){
                        numFlags += 1;
                }
            }
        }
        return numFlags;
    }

    @Override
    public int getElapsedSeconds() {
        long currentTime = (System.currentTimeMillis() - startTime)/1000;
        return (int)currentTime;
    }

    @Override
    public Cell getCell(int row, int col) {
        return allCells[row][col];
    }

    @Override
    public void stepOnCell(int row, int col) {
        allCells[row][col].makeVisible();
        if (allCells[row][col].isMine()){
            end = true;
        }

        if (allCells[row][col].getNeighborMines()==0){

            for (int count1 = row - 1; count1 < row + 2; count1 += 1) {
                for (int count2 = col - 1; count2 < col + 2; count2 += 1) {
                    if (count1 >=0 && count1 <rows && count2 >=0 && count2<cols){
                        if (!allCells[count1][count2].isMine() && !allCells[count1][count2].isVisible()){
                            stepOnCell(count1,count2);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void placeOrRemoveFlagOnCell(int row, int col) {
        allCells[row][col].makeFlagged();
    }

    @Override
    public boolean isGameStarted() {
        return start;
    }

    @Override
    public boolean isGameOver() {
        if (won || end){
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayerDead() {
        if (end){
            for (int count1 = 0; count1<rows;count1+=1){
                for (int count2 =0; count2<cols;count2+=1){
                    if (allCells[count1][count2].isMine()) {
                        allCells[count1][count2].makeVisible();
                    }
                }
            }
        }
        return end;
    }

    @Override
    public boolean isGameWon() {
        for (int count1 = 0; count1<rows; count1+=1){
            for (int count2 = 0; count2<cols; count2+=1){
                if (!allCells[count1][count2].isMine()){
                    if (!allCells[count1][count2].isVisible()) {
                        return false;
                    }
                }

            }
        }
        return true;
    }
}

