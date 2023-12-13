import java.util.Random;
import static java.lang.Math.abs; 

public class CandyCrushModel {
    private Piece[][] grid;
    private int tileSize = 54;
    private int speedSwapAnimation = 4;
    private int score;
    private boolean isSwap = false, isMoving = false;
    private int x0, y0, x, y;

    public CandyCrushModel() {
        // grid = new Piece[10][10];
        initializeGrid();
    }

    public Piece[][] getGrid() {
        return grid;
    }

    public void initializeGrid() {
        grid = new Piece[10][10];
        for(int i = 0;i < 10;i++) {
            for(int j = 0;j < 10;j++) {
                grid[i][j] = new Piece();
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Piece();
                grid[i][j].kind = new Random().nextInt(7);
                grid[i][j].row = i;
                grid[i][j].col = j;
                grid[i][j].x = j * tileSize;
                grid[i][j].y = i * tileSize;
            }
        }
    }

    public void swapPieces(Piece p1, Piece p2) {
        int rowAux = p1.row;
        p1.row = p2.row;
        p2.row = rowAux;

        int colAux = p1.col;
        p1.col = p2.col;
        p2.col = colAux;

        grid[p1.row][p1.col] = p1;
        grid[p2.row][p2.col] = p2;
    }

    public void update() {
        matching();
        moving();
        getScore();
        secondSwap();
        updateGrid();
    }

    public void matching() {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (grid[i][j].kind == grid[i + 1][j].kind) {
                    if (grid[i][j].kind == grid[i - 1][j].kind) {
                        for (int n = -1; n <= 1; n++) {
                            grid[i + n][j].match++;
                        }
                    }
                }
                if (grid[i][j].kind == grid[i][j + 1].kind) {
                    if (grid[i][j].kind == grid[i][j - 1].kind) {
                        for (int n = -1; n <= 1; n++) {
                            grid[i][j + n].match++;
                        }
                    }
                }
            }
        }
    }

    public void moving() {
        isMoving = false;
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                Piece p = grid[i][j];
                int dx = 0, dy = 0;
                for (int n = 0; n < speedSwapAnimation; n++) {
                    dx = p.x - p.col * tileSize;
                    dy = p.y - p.row * tileSize;
                    if (dx != 0) {
                        p.x -= dx / abs(dx);
                    }
                    if (dy != 0) {
                        p.y -= dy / abs(dy);
                    }
                }
                if (dx != 0 || dy != 0) {
                    isMoving = true;
                }
            }
        }
    }

    public void getScore() {
        score = 0;
        for(int i = 1;i <= 8;i++) {
            for(int j = 1;j <= 8;j++) {
                score += grid[i][j].match;
            }
        }
    }

    public void secondSwap() {
        if(isSwap && !isMoving) {
            // if(score == 0) {
                swapPieces(grid[y0][x0], grid[y][x]);
            // }
            isSwap = false;
        }
    }

    public void updateGrid() {
        if (!isMoving) {
            for (int i = 8;i > 0;i--) {
                for (int j = 1;j <= 8;j++) {
                    if (grid[i][j].match != 0) {
                        for (int n = i;n > 0;n--) {
                            if (grid[n][j].match == 0) {
                                swapPieces(grid[n][j], grid[i][j]);
                                break;
                            }
                        }
                    }
                }
            }
            for(int j = 1;j <= 8;j++) {
                for(int i = 8, n = 0;i > 0;i--) {
                    if(grid[i][j].match != 0) {
                        grid[i][j].kind = new Random().nextInt(7);
                        grid[i][j].y = -tileSize * n++;
                        grid[i][j].match = 0;
                    }
                }
            }
        }
    }

    // Thêm các phương thức khác cần thiết cho Model
}
