import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static java.lang.Math.abs;

public class CandyCrushListener extends MouseAdapter {
    private Piece[][] grid;
    private boolean isSwap = false;
    private boolean isMoving = false;
    private int click = 0;
    private int posX, posY, x0, y0, x, y;
    private int tileSize = 54;
    private int offsetX = 48;
    private int offsetY = 24;


    public void mouseClicked(MouseEvent e) {
        // Xử lý khi chuột nhấp
    }

    public void mousePressed(MouseEvent mouse) {
        // Xử lý khi nhấn chuột
        if (mouse != null && mouse.getID() == MouseEvent.MOUSE_PRESSED) {
            if (mouse.getButton() == MouseEvent.BUTTON1) {
                if(!isSwap && !isMoving) {
                    click++;
                }
                posX = mouse.getX() - offsetX;
                posY = mouse.getY() - offsetY;

                if (click == 1) {
                    x0 = posX / tileSize + 1;
                    y0 = posY / tileSize + 1;
                }
                if (click == 2) {
                    x = posX / tileSize + 1;
                    y = posY / tileSize + 1;
                    if (abs(x - x0) + abs(y - y0) == 1) {
                        swap(grid[y0][x0], grid[y][x]);
                        isSwap = true;
                        click = 0;
                    } else {
                        click = 1;
                    }
                }
            }
            mouse = null;
        }
    }
    public void swap(Piece p1, Piece p2) {
        int rowAux = p1.row;
        p1.row = p2.row;
        p2.row = rowAux;

        int colAux = p1.col;
        p1.col = p2.col;
        p2.col = colAux;

        grid[p1.row][p1.col] = p1;
        grid[p2.row][p2.col] = p2;
    }
}


