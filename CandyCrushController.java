import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CandyCrushController implements Runnable, MouseListener {
    private CandyCrushModel model;
    private View3 view;
    private boolean isRunning = true;
    private Thread thread;
    private MouseEvent mouse;
    private boolean isSwap = false, isMoving = false; 
    public  int click = 0;
    private int x0, y0, x, y;
    private int offsetX = 48, offsetY = 24;
    private int tileSize = 54;

    public CandyCrushController(CandyCrushModel model, View3 view) {
        this.model = model;
        this.view = view;
        view.addMouseListener(this); 
    }

    // public int getClick() {
    //     return this.click;
    // }
    
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
                handInput();
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
            // Check if the left mouse button is pressed
            if (mouse.getButton() == MouseEvent.BUTTON1) {
                // Check if swapping or moving is not in progress
//                if (!isSwap && !isMoving) {
                    //click++;
                    // handleFirstClick();

//                } else {
                    // Reset click count if swapping or moving is in progress
                // click = 0;
//                }
            }

            // Reset mouse to null after processing the event
            mouse = null;
        }
    }

    private void handleFirstClick() {
        System.out.println("Click Count: " + click);
        
        int posX = mouse.getX() - offsetX;
        int posY = mouse.getY() - offsetY;
    
        // Calculate grid coordinates based on mouse position
        int gridX = posX / tileSize + 1;
        int gridY = posY / tileSize + 1;
    
        // Handle first click
        if (click == 1) {
            handleFirstClickAction(gridX, gridY);
        } else if (click == 2) {
            handleSecondClickAction(gridX, gridY);
        }
    }

    private void handleFirstClickAction(int x, int y) {
        // Store coordinates of the first click
        x0 = x;
        y0 = y;
        click++;
    }

    private void handleSecondClickAction(int x, int y) {
        // Store coordinates of the second click
        this.x = x;
        this.y = y;
    
        // Check if the selected pieces are adjacent
        if (Math.abs(x - x0) + Math.abs(y - y0) == 1) {
            // Swap pieces if adjacent
            model.swapPieces(model.getGrid()[y0][x0], model.getGrid()[y][x]);
            isSwap = true;
            click = 0;
        } else {
            // Reset to the first click if pieces are not adjacent
            click = 1;
        }
    }

    public void resetClick() {
        click = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse click events
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouse = e;

        // :)
        int magicNumber = 54;

        double squareX = Math.floor((e.getX() - offsetX) / magicNumber);
        double squareY = Math.floor((e.getY() - offsetY) / magicNumber);

        // System.out.printf("x = %f, y = %f\n", squareX, squareY);
        view.toggleFirstClick();
        view.setX0((int) squareX);
        view.setY0((int) squareY);
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
