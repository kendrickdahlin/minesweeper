class MineSweeper
{
  public static void main(String[] args) 
  {
    MineModel mineModel = new KendricksMineModel();
    
    MineView mineView = new MineView(mineModel, 1000, 800);
  }
}
