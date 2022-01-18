public class MazeTraversal {


    public MazeTraversal(){
    }

    /**Maze traversal function uses BackTracking Recursion to solve the maze and record the direct route
     *
     * @param maze maze that will be solved
     * @param x current location x direction
     * @param y current location y direction
     * @return boolean whether or not the end is reachable following that recursive path
     */
    public static boolean mazeTraversalFunction(char[][] maze,int x,int y){
            maze[x][y] = 'X';
                if (dotChecker(x + 1, y, maze)) {
                    x++;
                    maze[x][y] = 'X';
                    print2dArray(maze);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    if(x!=maze.length-1) {
                        return mazeTraversalFunction(maze, x, y);
                    }
                    else{
                        return true;
                    }
                }
                if (dotChecker(x, y + 1, maze)) {
                    y++;
                    maze[x][y] = 'X';
                    print2dArray(maze);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    if(y!=maze[x].length-1) {
                        return mazeTraversalFunction(maze, x, y);
                    }
                    else{
                        return true;
                    }
                }
                if (dotChecker(x - 1, y, maze)) {
                    x--;
                    maze[x][y] = 'X';
                    print2dArray(maze);
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    if(x!=0) {
                        return mazeTraversalFunction(maze, x, y);
                    }
                    else{
                        return true;
                    }
                }
                if (dotChecker(x, y - 1, maze)) {
                    y--;
                    maze[x][y] = 'X';
                    print2dArray(maze);
                    System.out.println();
                    System.out.println();
                    if (y != 0) {
                        return mazeTraversalFunction(maze, x, y);
                    }
                    else{
                        return true;
                    }
                }
        return false;
    }

    /**
     * Prints 2D java Arrays
     * @param array array to be printed
     */
    public static void print2dArray(char[][] array){
        int x=0;
        int y=0;
        for(y=0;y<array.length;y++){
            for(x=0; x<array.length;x++){
                System.out.print(array[x][y]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     * dotChecker checks the maze for "dots" or a position that the program can move through in the maze
     * @param x location X in the maze that is being checked
     * @param y location Y in the maze that is being checked
     * @param maze maze being checked
     * @return
     */
    public static boolean dotChecker(int x, int y, char[][] maze){
        boolean returnVal=true;
        if(x<0 || x>maze.length-1){
            returnVal=false;
        }
        if(y<0 || y>maze[x].length-1){
            returnVal= false;
        }
        if(maze[x][y]!='.'){
            returnVal=false;
        }
        return returnVal;
    }
}
