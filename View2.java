import javax.imageio.ImageIO;
import javax.swing.*;
import java.lang.Runnable;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;

public class View2 extends JFrame {
    private final int WIDTH = 740;
    private final int HEIGHT = 480;
    private Thread thread;
    private boolean isRunning;
    private BufferedImage background, gems, cursor;

    private Piece[][] grid;
    private boolean isSwap = false;
    private boolean isMoving = false;
    private int speedSwapAnimation = 4;
    private int tileSize = 54;

    // private static final Image Main = null;
    private int click;
    private int offsetX = 48, offsetY = 24;
    private int titleSize = 54;
    private int x0, y0;
    private BufferedImage view;

    // Khai báo Menu
    private JMenu menu, submenu;
    private JMenuItem i1, i2, i3, i4, i5;
    private JMenuBar mb = new JMenuBar();
    private JLabel jLabel;
    public JPopupMenu jPopupMenu;
    private CandyCrushController controler;

    public View2() {
        this.setTitle("Minh Trieu");
        // this.setSize(750, 540);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Set Icon 
        URL url_icon = CandyCrushView.class.getResource("icondog.png");       
        Image img = Toolkit.getDefaultToolkit().createImage(url_icon);
        this.setIconImage(img);


        // JMenuBar
        menu = new JMenu("Menu");
        submenu = new JMenu("Sub Menu");
        i1 = new JMenuItem("New");
        i2 = new JMenuItem("Save");
        i3 = new JMenuItem("Save as");
        i4 = new JMenuItem("Exit");
        i5 = new JMenuItem("Test");
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        submenu.add(i4);
        submenu.add(i5);
        menu.add(submenu);
        mb.add(menu);

        // JLabel
        jLabel = new JLabel();
        // jLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(CandyCrushView.class.getResource("Main.png"))));

        jPopupMenu = new JPopupMenu();
        
        JMenu jPopupMenuFont = new JMenu("Font");
        JMenuItem jMenuItemType = new JMenuItem("Type");
        jMenuItemType.addActionListener(controler);
        JMenuItem jMenuItemSize = new JMenuItem("Size");
        // jMenuItemSize.addActionListener(controler);
        jPopupMenuFont.add(jMenuItemType);
        jPopupMenuFont.add(jMenuItemSize);

        jPopupMenu.add(jPopupMenuFont);
        this.add(jPopupMenu);
        addNotify();
        // run();
        draw();

        // Tạo Label hiển thị
        Font font = new Font("Arial", Font.BOLD, 50);
        this.setJMenuBar(mb);
        this.add(jLabel, BorderLayout.CENTER);
        this.setVisible(true);
        
        
    }

    // public void updateView() {

    // }

    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(new Runnable() {
                public void run() {
                    draw();
                    try {
                        view = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
                        grid = new Piece[10][10];

                        background = ImageIO.read(getClass().getResource("/Main.png"));
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
                        grid[i][j].x = j * tileSize;
                        grid[i][j].y = i * tileSize;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        });
        isRunning = true;
        thread.start();
        }
    }

   

    public void movingAnimation() {
        isMoving = false;
        for(int i = 1;i <= 8;i++) {
            for (int j = 1;j <= 8;j++) {
                Piece p = grid[i][j];
                int dx = 0, dy = 0;
                for(int n = 0;n < speedSwapAnimation;n++) {
                    dx = p.x - p.col * tileSize;
                    dy = p.y - p.row * tileSize;
                    if (dx != 0) {
                        p.x -= dx / Math.abs(dx);
                    }
                    if (dy != 0) {
                        p.y -= dy / Math.abs(dy);
                    }
                }
                if (dx != 0 || dy != 0) {
                    isMoving = true;
                }
            }
        }
    }

    public void draw () {
        Graphics2D g2 = (Graphics2D) getGraphics();
        g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null);

        for (int i = 1;i <= 8;i++) {
            for (int j = 1;j <= 8;j++) {
                g2.drawImage(
                        gems.getSubimage(grid[i][j].kind * 49, 0, 49, 49),
                        grid[i][j].x + (offsetX - titleSize),
                        grid[i][j].y + (offsetY - titleSize),
                        49,
                        49,
                        null
                );
                //Show cursor
                if(click == 1) {
                    if(x0 == j && y0 == i) {
                        g2.drawImage(
                                cursor,
                                grid[i][j].x + (offsetX - titleSize),
                                grid[i][j].y + (offsetY - titleSize),
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
}