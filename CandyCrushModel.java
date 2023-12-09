import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class CandyCrushModel {
    private Piece[][] grid;
    private MouseEvent mouse;
    private boolean isSwap, isMoving;
    private int click;
    private int posX, posY;
    private int offsetX, offsetY;
    private int x0, y0, x, y;
    private int titleSize;
    private int speedSwapAnimation = 4;
    private int WIDTH = 740;
    private int HEIGHT = 480;
    private BufferedImage Main, gems, cursor;
    private BufferedImage view;

    public CandyCrushModel(MouseEvent mouse) {
        grid = new Piece[8][8];
        this.mouse = mouse;
        this.isSwap = false;
        this.isMoving = false;
        this.click = 0;
        this.offsetX = 48;
        this.offsetY = 24;
        this.titleSize = 54;
        this.speedSwapAnimation = 4;
        // start();
    }

    public void start () {
        try {
            view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
            grid = new Piece[10][10];

            Main = ImageIO.read(getClass().getResource("/background.png"));
            gems = ImageIO.read(getClass().getResource("/gems.png"));
            cursor = ImageIO.read(getClass().getResource("/cursor.png"));

            for(int i = 0;i < 10;i++) {
                for(int j = 0;j < 10;j++) {
                    grid[i][j] = new Piece();
                }
            }

            for (int i = 1;i <=8;i++) {
                for(int j = 1;j <= 8;j++) {
                    grid[i][j].kind = (new Random().nextInt(7));
                    grid[i][j].row = i;
                    grid[i][j].col = j;
                    grid[i][j].x = j * titleSize;
                    grid[i][j].y = i * titleSize;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCandies() {
        if(mouse != null && mouse.getID() == MouseEvent.MOUSE_PRESSED) {
            if(mouse.getButton() == MouseEvent.BUTTON1) {
                if(!isSwap && !isMoving) {
                    click++;
                }
                posX = mouse.getX() - offsetX;
                posY = mouse.getY() - offsetY;

                if(click == 1) {
                    x0 = posY / titleSize + 1;
                    y0 = posY / titleSize + 1;
                }
                if(click == 2) {
                    x = posX / titleSize + 1;
                    y = posY / titleSize + 1;
                    if(Math.abs(x - x0) + Math.abs(y - y0) == 1) {
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

        // Match finding
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                if(grid[i][j].kind == grid[i + 1][j].kind) {
                    if(grid[i][j].kind == grid[i - 1][j].kind) {
                        for(int n = -1; n <= 1; n++) {
                            grid[i + n][j].match++;
                        }
                    }
                }
                if(grid[i][j].kind == grid[i][j + 1].kind) {
                    if (grid[i][j].kind == grid[i][j - 1].kind) {
                        for(int n = -1;n <= 1;n++) {
                            grid[i][j + n].match++;
                        }
                    }
                }
            }
        }

        int score = 0;
        for(int i = 1; i <= 8; i++) {
            for(int j = 1; j <= 8; j++) {
                score += grid[i][j].match;
            }
        }

        // Second swap if no matching in the first time
        if(isSwap && !isMoving) {
            if(score == 0) {
                swap(grid[y0][x0], grid[y][x]);
            }
            isSwap = false;     /*Sau khi swap xong trở về false */
        }

        if(!isMoving) {
            for(int i = 8; i > 0; i--) {
                for(int j = 1; j <= 8; j++) {
                    if(grid[i][j].match != 0) {
                        for(int n = i; n > 0; n--) {
                            if(grid[n][j].match == 0) {
                                swap(grid[n][j], grid[i][j]);
                                break;
                            }
                        }
                    }
                }
            }
            for(int j = 1;j <= 8;j++) {
                for(int i = 8, n = 0;i > 0; i--) {
                    if(grid[i][j].match != 0) {
                        grid[i][j].kind = new Random().nextInt(7);
                        grid[i][j].y = -titleSize * n++;
                        grid[i][j].match = 0;
                    }
                }
            }
        }
    }
}
