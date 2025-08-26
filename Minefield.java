public class Minefield {
  
  private Square[][] field; //Grid which represents the field
  private int fieldX; //Length of the grid's x axis or how many columns
  private int fieldY; //Length of the grid's y axis or how many rows 
  private int numBombs; //Amount of bombs based on the difficulty, Easy - 10 Bombs, Medium - 40 bombs, Hard - 99 bombs
  private int level; //User level selected, either 1, 2, or 3, controls dimensions of field and number of bombs
  private int numFlags; // Current flags the player has
  //Precondition: 0 < l < 4
  public Minefield(int l){
    //Level easy
    if(l == 1){
      level = l;
      fieldX = 10;
      fieldY = 8;
      numBombs = 10;
      field = new Square[fieldY][fieldX];
    //Level medium
    } else if(l == 2){
      level = l;
      fieldX = 18;
      fieldY = 14;
      numBombs = 40;
      field = new Square[fieldY][fieldX];
    //Level hard
    } else {
      level = l;
      fieldX = 24;
      fieldY = 20;
      numBombs = 99;
      field = new Square[fieldY][fieldX];
    }
    numFlags = numBombs; //numBombs is final after constructor, numFlags changes on user input
    
  }
  //2D array Field getter method
  public Square[][] getField(){ 
    return field;
  }
  //Fills each index in field[][] with Safe_Square
  public void createBaseField(){ 
    for (int r = 0; r < field.length; r ++){
      for(int c = 0; c < field[0].length; c++){
        Square s = new Safe_Square();
        field[r][c] = s;
      }
    }
  }
  //Randomly generates area for the user's first move 
  //@param int x - user selected x coordinate
  //@param int y = user selected y coordinate
  public void firstInput(int x, int y){ 
    //Randomly generates the width and length of the area based off of the level and the dimensions set from them 
    int len = (int)(Math.random() * fieldY/2) + 2;
    int wid = (int)(Math.random() * fieldX/2) + 2;
    
    //Randomizes further so that the area isn't perfectly rectangular and has varying length for each column and row 
    for(int r = y-1 - len/2 + (int)(Math.random()*3) - 1; r < y + len/2 + (int)(Math.random()*3) - 1; r ++){
      for(int c = x-1 - wid/2 + (int)(Math.random()*3) -1 ; c < x + wid/2 + (int)(Math.random()*3) - 1; c ++){
        try { //If the index goes out of bounds, do nothing, fill in the rest that are in bounds
          
          field[r][c].modClicked();
          
        } catch(Exception e ){}
      }
    }
    fillBombs(numBombs); //Fills in bombs around the area
    fillSafes(x-1,y-1); //Fills in squares that have a bomb radius of zero, in order to make games quicker and reduce tedious inputs from the user
    checkBombRadius(); //Checks each square for the number of bombs around it, modifies instance variable which is used to determine texture
    drawField(); //Draws the grid
    
    
  }
  //Clears the screen and prints out grid field
  
  public void drawField(){
    clearScreen();
    //Prints numbers at the top
    for(int i = 0; i <= fieldX; i ++){
      if(i < 10){
        System.out.print("0");
      }
      System.out.print(i + "  ");
    }
    System.out.println();
    System.out.print("--");
    //Dashes to seperate the numbers from the grid
    for(int i = 0; i < fieldX; i ++){
      System.out.print("  " + "--");
    }
    System.out.println();
    for(int r = 0; r < fieldY; r++){
      if(r+1 < 10){
        System.out.print("0");
      }
      System.out.print(r+1 + "|");
      //Prints out the square based off its instance variables
      System.out.print(" " + field[r][0].getTexture());
      for(int c = 1; c < fieldX; c++){
        System.out.print("  " + field[r][c].getTexture());
      }
      System.out.println("\n");

      
      
    }
    
  }
  //Randomly fills in bombs for non-clicked Safe_Squares
  public void fillBombs(int bombs){
    int rand;
    while(bombs > 0){
      //Traverses through array
      for(int r = 0; r < fieldY; r++){
        for(int c = 0; c < fieldX; c++){
          if(bombs == 0){
            break;
          }
          //Generates a number between 0 and the field length
          rand = (int)(Math.random() * fieldX);
          //If the number is zero, isn't already ckicked, and hasn't already been filled in for a mine square
          if(rand == 0 && !(field[r][c] instanceof Mine_Square) && !(field[r][c].getIsClicked())){
            Square m = new Mine_Square();
            field[r][c] = m;
            bombs--;
          }
        }
      }
    }
  }
  
  public void checkBombRadius(){
    for(int r = 0; r < field.length; r++){
      for(int c = 0; c < field[0].length; c++){
        checkSquareBombRadius(r, c);
        }
        }
    }
  public void fillSafes(){
    for (int r = 0; r<field.length; r++){
      for (int c = 0; c<field[0].length; c++){
        if(field[r][c] instanceof Safe_Square){
          if(((Safe_Square)field[r][c]).getNumBombs() == 0 && ((Safe_Square)field[r][c]).getIsClicked()){
            for(int r2 = -1; r2<2; r2++){
              for(int c2 = -1; c2<2; c2++){
                try{
                  field[r + r2][c + c2].modClicked();
                } catch (Exception e){}
              }
            }
          }
        }
      }
    }
  }
  public void fillSafes(int x, int y){
    //int c = x-1, r = y - 1;
    int c = x, r = y; // c = 18, r = 16, 15, r = 

    //If index goes out of bounds
    if(r<0|| c<0){
      return;
    }
    if(r>=field.length || c>=field[0].length){
      return;
    }

    //If index has already been filled safe, return, otherwise will result in infinite recursion
    if(((Safe_Square)field[r][c]).getMarkedAsFlagged()){
      return;
    }
    /*if(field[r][c] instanceof Safe_Square){
      if(!(field[r][c].getIsClicked()) && ((Safe_Square)field[r][c]).getNumBombs() != 0){
        return;
      }
    }*/
    
    
    field[r][c].modClicked(); //Fill in safe square
    ((Safe_Square)field[r][c]).setMarkedAsFlagged(); //Boolean instance variable so infinite recursion will not occur
    checkSquareBombRadius(r, c); //Sets numBombsinRadius instance variable so the texture can be printed

    //If the square is a safe square recur for all squares around it, fill them in 
    if(((Safe_Square)field[r][c]).getNumBombs() == 0){
      
      fillSafes(c-1,r-1);
      fillSafes(c-1,r);
      fillSafes(c-1,r+1);
      fillSafes(c,r-1);
      fillSafes(c,r+1);
      fillSafes(c+1,r-1);
      fillSafes(c+1,r);
      fillSafes(c+1,r+1);
      
    } 
  }
  /*public void fillSafes(int x, int y){
    int c = x-1, r = y-1;
    //If index is out of bounds, stop immediately
    if(r<0 || c<0){
      return;
    }
    if(r>=fieldY || c>=fieldX){
      return;
    }
    field[r][c].modClicked();
    //If index is a safe square that has been clicked and has a zero bomb radius
    if(field[r][c] instanceof Safe_Square){
      if(((Safe_Square)field[r][c]).getTexture().equals("🔵")){
        fillSafes(r-1,c-1);
        fillSafes(r-1,c);
        fillSafes(r-1,c+1);
        fillSafes(r,c-1);
        fillSafes(r,c+1);
        fillSafes(r+1,c-1);
        fillSafes(r+1,c);
        fillSafes(r+1,c+1);
      }
    }
    
  }*/

  //Sets the Safe_Square instance variable numBombRadius so that the texture can be printed correctly
  public void checkSquareBombRadius(int r, int c){
    //If the square is safe and has been clicked
    if(field[r][c] instanceof Safe_Square && field[r][c].getIsClicked()){
          int bombsInRadius = 0;
          //Check the squares around the safe square
          for(int r2 = -1; r2 < 2; r2++){
            for(int c2 = -1; c2 < 2; c2++){
              //Try because index may go out of bounds
              try { 
                //If there is a bomb in the radius of the parameter square increase the amount of bombsInRadius, variable for bombRadius to be set equal to 
                if(field[r + r2][c + c2] instanceof Mine_Square){
                  bombsInRadius++;
                }
              } catch (Exception e){} //Catch if indexOutOfBoundsError is thrown, do nothing
            }
          }
          ((Safe_Square)field[r][c]).setBombRadius(bombsInRadius);
        }
  }
  //Runs the player's move based off of input parameters
  public void playerMoveDig(int x, int y){     
    //If player clicks a bomb, end the game
    if(field[y-1][x-1] instanceof Mine_Square){
      field[y-1][x-1].modClicked();
    } 
    else {
      //Checks if the square has a bomb radius of zero
      checkSquareBombRadius(y-1, x-1);
      if(((Safe_Square)field[y-1][x-1]).getNumBombs() == 0){
        fillSafes(x-1, y-1); //If bomb radius is zero, fill in all safe squares around it until the latest filled in square is adjacent to a bomb
      } 
      else {
        field[y-1][x-1].modClicked(); //Otherwise, just select the square
      }
      drawField(); //Print out field
      playerWin(); //Checks if the user clicked all squares and has one
    }
  }
  //Runs the player move based off of a flag input
  public void playerMoveFlag(int x, int y){
    
    field[y-1][x-1].modFlagged(); //Flags the current square
    if(field[y-1][x-1].getIsFlagged()){ //Decreases or increases the remaining flags label to be printed
      numFlags--;
    } else {
      numFlags++;
    }
    drawField(); // Print out field
    
  }
  //Checks if a player has clicked all the safe squares, and has won
  public boolean playerWin(){
    for(int r = 0; r < field.length; r ++){
      for(int c = 0; c < field[0].length; c++){
        if(field[r][c] instanceof Square && !(field[r][c] instanceof Safe_Square) && !(field[r][c] instanceof Mine_Square)){
          
          return false;
        }
        if(field[r][c] instanceof Safe_Square){
          if(field[r][c].getIsClicked() == false){
            return false;
          }
        }
      }
    }
    System.out.println("Congrats, You won!");
    System.exit(0);
    return true;
  }
  public int getFieldX(){
    return fieldX;
  }
  public int getFieldY(){
    return fieldY;
  }
  public int getNumFlags(){
    return numFlags;
  }
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }  
  public void enableHackerMode(){
    for(int r = 0; r < fieldY; r++){
      for(int c = 0; c <fieldX; c++){
        if(field[r][c] instanceof Mine_Square){
          ((Mine_Square)field[r][c]).setShowBomb();
        }
      }
    }
    drawField();
  }
}

