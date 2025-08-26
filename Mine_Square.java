public class Mine_Square extends Square{
  private boolean showBomb;
  public Mine_Square(){
    super();
    showBomb = false;
  }

  public void modClicked(){
    super.modClicked();
    System.out.println("WHOOPS, that was a bomb\nGAME OVER");
    System.exit(0);
  }
  public void setShowBomb(){
    showBomb = !showBomb;
  }
  public String getTexture(){
    if(showBomb){
      return "🔥";
    }
    if(getIsFlagged()){
      return "🚩";
    } else if (getIsClicked()){
      return "*";
    }
    return "📜";
  }
}