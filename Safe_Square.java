public class Safe_Square extends Square{
  
  private int numBombRadius;
  private boolean markedAsFlagged;

  public Safe_Square(){
    super();
    numBombRadius = 0;
    markedAsFlagged = false;
  }
  public Safe_Square(boolean clicked){
    super();
    if(clicked){
      modClicked();
    }
   
  }
  
  public String getTexture(){
    if(getIsClicked() && numBombRadius > 0){
      if(numBombRadius == 1){
        return "1️⃣ ";
      } else if (numBombRadius == 2){
        return "2️⃣ ";
      } else if (numBombRadius == 3){
        return "3️⃣ ";
      } else if (numBombRadius == 4){
        return "4️⃣ ";
      } else if (numBombRadius == 5){
        return "5️⃣ ";
      } else if (numBombRadius == 6){
        return "6️⃣ ";
      } else if (numBombRadius == 7){
        return "7️⃣ ";
      } else if (numBombRadius == 8){
        return "8️⃣ ";
      } else {
        return "9️⃣ ";
      }
      
    } else if (getIsClicked() && !getIsFlagged()){
      return "🔵";
    } else if (getIsFlagged()){
      return "🚩";
    }
    return "📜";
  }
  public void setBombRadius(int n){
    numBombRadius = n;
  }
  public int getNumBombs(){
    return numBombRadius;
  }
  public void setMarkedAsFlagged(){
    markedAsFlagged = true;
  }
  public boolean getMarkedAsFlagged(){
    return markedAsFlagged;
  }
  
}