import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class CandyCrushView extends JFrame {
    private JMenu menu, submenu;
    private JMenuItem i1, i2, i3, i4, i5;
    private JMenuBar mb = new JMenuBar();
    private JButton jButton;
    private JLabel jLabel;
    public JPopupMenu jPopupMenu;
    private CandyCrushController controler;

    public CandyCrushView() {
        this.setTitle("Minh Trieu");
        this.setSize(750, 540);
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
        jLabel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(CandyCrushView.class.getResource("Main.png"))));

        //JButton
        // jButton = new JButton("Test Button");
        // jButton.setFont(new Font("Arial", Font.BOLD, 50));
        // jButton.setSize(50, 50);   

        jPopupMenu = new JPopupMenu();
        
        JMenu jPopupMenuFont = new JMenu("Font");
        JMenuItem jMenuItemType = new JMenuItem("Type");
        // jMenuItemType.addActionListener(controler);
        JMenuItem jMenuItemSize = new JMenuItem("Size");
        // jMenuItemSize.addActionListener(controler);
        jPopupMenuFont.add(jMenuItemType);
        jPopupMenuFont.add(jMenuItemSize);

        jPopupMenu.add(jPopupMenuFont);
        this.add(jPopupMenu);

        // Thêm sự kiện phải chuột
        // MenuExampleMouseListener menuExampleMouseListener = new MenuExampleMouseListener(this);

        // Tạo Label hiển thị
        Font font = new Font("Arial", Font.BOLD, 50);
        this.setJMenuBar(mb);
        this.add(jLabel, BorderLayout.CENTER);
        // this.add(jButton, BorderLayout.SOUTH);
        this.setVisible(true);
    }


}