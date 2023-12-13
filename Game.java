import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JPanel;


public class Game extends JPanel implements Runnable, MouseListener {
    private int WIDTH = 740;
    private int HEIGHT = 480;

    private Thread thread;
    private BufferedImage view;
    private Piece[][] grid;

    BufferedImage background, gems, cursor;

    private int titleSize = 54;
    private int offsetX = 48, offsetY = 24;
    private int x0, y0, x, y;
    private int click = 0;
    private int posX, poxY;
    int speedSwapAnimation = 4;
    private boolean isSwap = false, isMoving = false;
    
    public Game() {
        setPreferredSize((new Dimension(WIDTH, HEIGHT)));
        addMouseListener(this);
    }

    public void addNotify() {
        super.addNotify();
        if(thread == null) {
            thread = new Thread(this);
            boolean isRunning = true;
            thread.start();
        }
    }

    public void run() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
