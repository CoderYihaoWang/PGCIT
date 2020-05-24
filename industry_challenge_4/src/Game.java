import javax.swing.*;

// represents the game JFrame
public class Game extends JFrame {
    public Game() {
        getContentPane().add(new Panel());
        setTitle("Tic Tac Toe");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Game().setVisible(true));
    }
}
