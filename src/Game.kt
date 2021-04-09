public class Game {

  //This is the Game class.  It hold the current state of the game
  //with the help of the Cell class.
  
  public boolean finished = false;
  public boolean draw = false;
  public int gridSize;
  
  private Cell[] grid;
  
  //constructor.  takes integer and generates a new Game with given size
  public Game(int size) {
  
    this.gridSize = size;
    grid = new Cell[gridSize * gridSize];
    
    for (int i = 0; i < grid.length; i++) {
      grid[i] = new Cell();
    }
  }
  
  //checks to see if a win condition has been met and
  //outputs the current game map to the console 
  public String output() {
    checkForTicTacToe();
    return drawMap();
  }
  
  //places an X or an O in a cell ont he game map
  public boolean setCell(int index){
  
    if (grid[index].empty) {
    
      grid[index].placeMark();
      return true;
      
    } else {
      return false;
    }
  }
  
  //checks to see if a win condition has been met
  private boolean checkForTicTacToe() {
  
    boolean gridFilled;
    boolean rowWin;
    boolean columnWin;
    boolean diagonalWin;
    
    Cell[][] rows = new Cell[gridSize][gridSize];
    Cell[][] columns = new Cell[gridSize][gridSize];
    Cell[][] diagonals = new Cell[2][gridSize]; //there are only ever two diagonals which complete a tictactoe in a square
    
    //if every cell is filled, end the game
    gridFilled = true;
    for(int i = 0; i < gridSize * gridSize; i++){
    
      if(grid[i].empty){
        gridFilled = false;
      }
    }
    
    if(gridFilled){
      finished = true;
      draw = true;
    }
    
    for(int i = 0; i < gridSize; i++){
      for(int j = 0; j < gridSize; j++){
      
        rows[i][j] = grid[gridSize*i+j];
      }
    }
    
    for(int i = 0; i < gridSize; i++){
      for(int j = 0; j < gridSize; j++){
      
        columns[i][j] = grid[i+gridSize*j];
      }
    }
    
    for(int i = 0; i < 2; i++){
      if(i == 0){
        for(int j = 0; j < gridSize; j++){
        
          diagonals[i][j] = grid[(gridSize + 1) * j];
          
        }
      } else {
        for(int j = 0; j < gridSize; j++){
        
          diagonals[i][j] = grid[(gridSize - 1) * (j + 1)];
        }
      }
    }
    
    //if a row has all the same content and isnt empty
    //then the game is over
    for (Cell[] row : rows) {
    
      //if the row elements are all the same and not empty
      //set finished to true
      rowWin = true;
      for(int i = 0; i < row.length - 1; i++) {
        if(row[i].output()!=row[i + 1].output()){
        
          rowWin = false;
        }
        for(int j = 0; j < row.length - 1; j++){
          if(row[i].empty){
          
            rowWin = false;
          }
        }
      }
      
      if(rowWin){
        finished = true;
        draw = false;
      }
    }
    
    //if a column has all the same content and isnt empty
    //then the game is over
    for (Cell[] column : columns) {
    
      //if the column elements are all the same and not empty
      //set finished to true
      columnWin = true;
      for(int i = 0; i < column.length - 1; i++) {
        if(column[i].output()!=column[i + 1].output()) {
        
          columnWin = false;
        }
        
        for(int j = 0; j < column.length - 1; j++) {
          if(column[i].empty){
          
            columnWin = false;
          }
        }
      }
      
      if(columnWin){
        finished = true;
        draw = false;
      }
    }
    
    //if a diagonal has all the same content and isnt empty
    //then the game is over
    for (Cell[] diagonal : diagonals) {
    
      //if the diagonal elements are all the same and not empty
      //set finished to true
      
      diagonalWin = true;
      for(int i = 0; i < diagonal.length - 1; i++) {
        if(diagonal[i].output()!=diagonal[i + 1].output()) {
        
          diagonalWin = false;
        }
        
        for(int j = 0; j < diagonal.length - 1; j++) {
          if(diagonal[i].empty){
          
            diagonalWin = false;
          }
        }
      }
      
      if(diagonalWin){
        finished = true;
        draw = false;
      }
    }
    
    return finished;
  }
  
  //draws the current game state in perfect proportion
  //
  private String drawMap() {
  
  String top = "\t\t  ";
  String fill = "\t\t    ";
  String divider = "\t\t ---";
  String meat = "\t\t";
  String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  String map = "\n";
  
    for(int i = 1; i < gridSize; i++) {
    
      top += i + "  ";
      
      if(i<9){
        top += " ";
      }
      
      fill += "|   ";
      divider += "+---";
    }
    
    top += gridSize + " \n";
    fill += "\n";
    divider += "\n";
    map += top + fill;
    
    for(int row = 1; row < 2; row++){
      for(int column = 1; column < 2; column++){
      
        meat += alphabet.substring(row - 1, row) + " " + grid[3 * (row - 1) + (column - 1)].output();
        
        for(int i = 2; i < gridSize + 1; i++){
        
          meat += " | " + grid[3 * (row - 1) + (i - 1)].output();
        }
      }
      
      meat += "\n";
    }
    
    map += meat + fill;
    
    for (int row = 2; row < gridSize + 1; row++){
    
      map += divider;
      map += fill;
      
      for(int column = 1; column < 2; column++){
      
        meat = "\t\t" + alphabet.substring(row - 1, row) + " " + grid[gridSize * (row - 1) + (column - 1)].output();
        
        for(int i = column + 1; i < gridSize + 1; i++){
        
          meat += " | " + grid[gridSize * (row - 1) + (i - 1)].output();
        }
      }
      
      map += meat + "\n" + fill;
    }
    
    return map;
  }
}