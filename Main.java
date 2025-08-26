import java.util.Scanner;

class Main {
  static Main main = new Main();
  static Minefield mf;
  
  static Scanner scan = new Scanner(System.in);
  static int numInput, numInput2, numInput3;
  
  public static void main(String[] args) {
    
    System.out.println("Hello and Welcome to Minesweeper");
    System.out.println("\nChose your difficulty level:\n1\n2\n3\n");
    System.out.println("Note: You may have to increase the console size with higher difficulty levels\n");
    numInput = scan.nextInt();
    while(numInput < 1 || numInput > 3){
      System.out.println("That's not a valid level");
      numInput = scan.nextInt();
    }
    mf = new Minefield(numInput);
    mf.createBaseField();
    mf.drawField();
    System.out.println("Type in the X Coordinate of the point:");
    numInput = scan.nextInt();
    while(numInput < 0 || numInput > mf.getFieldX()){
      System.out.println("That's not a valid coordinate");
      numInput = scan.nextInt();
    }
    System.out.println("\nType in the Y Coordinate of the point:");
    numInput2 = scan.nextInt();
    while(numInput2 < 0 || numInput2 > mf.getFieldY()){
      System.out.println("That's not a valid coordinate");
      numInput2 = scan.nextInt();
    }
    mf.firstInput(numInput,numInput2);
    while(!mf.playerWin()){
      System.out.println("You have: " + mf.getNumFlags() + " flags left.");
      System.out.println("Type in the X Coordinate of the point:");
      numInput = scan.nextInt();
      System.out.println("\nType in the Y Coordinate of the point:");
      numInput2 = scan.nextInt();
      System.out.println("\nType:\n1. Dig\t2. Flag\t3. Enable Hacker Mode\t4. Cancel");
      numInput3 = scan.nextInt();
      main.runPlayerMove(numInput, numInput2, numInput3);
    }
    
  }
  public void runPlayerMove(int x, int y, int moveType){
    //If moveType is of type dig
    if(moveType == 1){
      if(mf.getField()[y-1][x-1].getIsClicked() == false){
      mf.playerMoveDig(x,y);
      } else {
        System.out.println("\nThe square has already been clicked, choose another\n");
      }
    } else if (moveType == 2){
        if(mf.getField()[y-1][x-1].getIsClicked() == false){
          mf.playerMoveFlag(x,y);
        } else {
          System.out.println("\nThe square has already been clicked, choose another\n");
        }
    } else if (moveType == 3){
      mf.enableHackerMode();
    }
    mf.playerWin();
    
      
    
  }
}