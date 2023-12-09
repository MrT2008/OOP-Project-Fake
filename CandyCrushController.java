import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.text.View;

public class CandyCrushController implements ActionListener{
    private CandyCrushModel model;
    private CandyCrushView view;

    public CandyCrushController(CandyCrushModel model, CandyCrushView view) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        String button = e.getActionCommand();
    }
}
   

    


      

    


    // if(button.equals("New")) {
        //     this.menuExView.setTextJLabel("Click New");
        // } else if(button.equals("Save")) {
        //     this.menuExView.setTextJLabel("Click Save");
        // } else if(button.equals("Save as")) {
        //     this.menuExView.setTextJLabel("Click Save as");
        // } else if(button.equals("Exit")) {
        //     System.exit(0);;
        // } else if(button.equals("Test")) {
        //     this.menuExView.setTextJLabel("Click Test");
        // }
     // public CandyCrushController(View menuExView) {
    //     this.menuExView = menuExView;
    // }

    //   if(button.equals("Exit")) {
    //         System.exit(0);
    //     } else {
    //         this.menuExView.setTextJLabel("Click " +button);
    //     }
