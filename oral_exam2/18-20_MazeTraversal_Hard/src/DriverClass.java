public class DriverClass {
    public static void main(String[] args) {
                char[][] bookMaze = new char[][]{

                        {'#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
                        {'#', '.', '.', '#', '.', '#', '.', '#', '.', '#', '.', '#'},
                        {'#', '.', '#', '#', '.', '#', '.', '.', '.', '#', '.', '#'},
                        {'#', '.', '.', '.', '.', '#', '#', '#', '.', '#', '.', '#'},
                        {'#', '#', '#', '#', '.', '.', '.', '.', '.', '#', '.', '#'},
                        {'#', '.', '.', '.', '#', '#', '#', '#', '.', '#', '.', '#'},
                        {'#', '.', '#', '.', '#', '.', '.', '.', '.', '.', '.', '#'},
                        {'#', '.', '#', '.', '#', '#', '#', '#', '.', '#', '#', '#'},
                        {'#', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '#'},
                        {'#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#'},
                        {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
                        {'#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#'},
        };
                MazeTraversal.mazeTraversalFunction(bookMaze,0,2);
    }
}

