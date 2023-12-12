import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CandyCrushController implements Runnable, MouseListener {
    private CandyCrushModel model;
    private View3 view;
    private boolean isRunning;
    private Thread thread;
    private MouseEvent mouse;
    private boolean isSwap = false, isMoving = false; 
    private int click = 0;
    private int x0, y0, x, y;
    private int offsetX = 48, offsetY = 24;
    private int tileSize = 54;

    public CandyCrushController(CandyCrushModel model, View3 view) {
        this.model = model;
        this.view = view;
        view.addMouseListener(this);
    }
    
    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    @Override
    public void run() {
        try {
            while (isRunning) {
                model.update();
                view.draw();
                Thread.sleep(1000 / 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handInput() {
        if (mouse != null && mouse.getID() == MouseEvent.MOUSE_PRESSED) {
            if (mouse.getButton() == MouseEvent.BUTTON1) {
                if (!isSwap && !isMoving) {
                    click++;
                }
                int posX = mouse.getX() - offsetX;
                int posY = mouse.getY() - offsetY;

                if (click == 1) {
                    x0 = posX / tileSize + 1;
                    y0 = posY / tileSize + 1;
                }
                if (click == 2) {
                    x = posX / tileSize + 1;
                    y = posY / tileSize + 1;
                    if (Math.abs(x - x0) + Math.abs(y - y0) == 1) {
                        model.swapPieces(model.getGrid()[y0][x0], model.getGrid()[y][x]);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click events
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Handle mouse press events
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Handle mouse release events
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse enter events
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Handle mouse exit events
    }
}
