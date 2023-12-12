import javax.imageio.ImageIO; 
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class View3 extends JPanel {
    private BufferedImage view;
    private CandyCrushModel model;
    private BufferedImage main, gems, cursor;
    private Piece[][] grid;
    private int tileSize = 54;
    private int offsetX = 48, offsetY = 24;
    private int x0, y0;
    private int click = 0;
    private final int WIDTH = 740;
    private final int HEIGHT = 480;

    public View3(CandyCrushModel model) {
        this.model = model;
        setPreferredSize(new Dimension(740, 480));
         try {
            main = ImageIO.read(getClass().getResource("/Main.png"));
            gems = ImageIO.read(getClass().getResource("/gems.png"));
            cursor = ImageIO.read(getClass().getResource("/cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        view = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) view.getGraphics();
        g2.drawImage(main, 0, 0, WIDTH, HEIGHT, null);

        for (int i = 1;i <= 8;i++) {
            for (int j = 1;j <= 8;j++) {
                g2.drawImage(
                        gems.getSubimage(grid[i][j].kind * 49, 0, 49, 49),
                        grid[i][j].x + (offsetX - tileSize),
                        grid[i][j].y + (offsetY - tileSize),
                        49,
                        49,
                        null
                );

                //Show cursor
                if(click == 1) {
                    if(x0 == j && y0 == i) {
                        g2.drawImage(
                                cursor,
                                grid[i][j].x + (offsetX - tileSize),
                                grid[i][j].y + (offsetY - tileSize),
                                cursor.getWidth(),
                                cursor.getHeight(),
                                null
                        );
                    }
                }
            }
        }

        Graphics g = getGraphics();
        g.drawImage(view, 0, 0, WIDTH, HEIGHT, null);
        g.dispose();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(view, 0, 0, getWidth(), getHeight(), null);
        // draw();
    }
}
