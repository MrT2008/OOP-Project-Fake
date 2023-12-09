import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuExampleMouseListener implements MouseListener {
    private CandyCrushView menuExView;

    public MenuExampleMouseListener(CandyCrushView menuExView) {
        this.menuExView = menuExView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.isPopupTrigger()) {
            this.menuExView.jPopupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
