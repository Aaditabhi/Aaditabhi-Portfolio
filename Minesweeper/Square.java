public class Square {
  private boolean isClicked;
  private boolean isFlagged;
  private boolean isNotClicked;

  public Square(){
    isClicked = false;
    isFlagged = false;
  }
  public void modClicked(){  
    isClicked = true;
    isFlagged = false;
  }
  public String getTexture() {
    if(isFlagged){
      return "🚩";
    }
    return "📜";
  }
  public boolean getIsClicked(){
    return isClicked;
  }
  public boolean getIsFlagged(){
    return isFlagged;
  }
  public boolean getIsNotClicked(){
    return isNotClicked;
  }
  public void modFlagged(){
    isFlagged = !isFlagged;
  }
}