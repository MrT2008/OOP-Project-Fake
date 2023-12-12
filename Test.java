import javax.swing.*; 

public class Test {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CandyCrushModel model = new CandyCrushModel();
            View3 view = new View3(model);
            CandyCrushController controller = new CandyCrushController(model, view);

            JFrame frame = new JFrame("Candy Crush");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            // frame.setSize(740, 480);
            frame.add(view);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            controller.start();
        });
    }
}
