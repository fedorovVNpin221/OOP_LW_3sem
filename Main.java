import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class Main extends JPanel {

    private double angle = 0;
    private final double angleIncrement = Math.PI / 90;
    private int positionX = 0;
    private final int speedX = 1;
    private final int squareSize = 100;

    public Main() {
        Timer animationTimer = new Timer(10, e -> update());
        animationTimer.start();
    }

    private void update() {
        angle += angleIncrement;
        positionX += speedX;

        // Проверка границ окна
        if (positionX > getWidth() - squareSize || positionX < 0) {
            positionX = Math.max(0, Math.min(getWidth() - squareSize, positionX)); // Ограничиваем позицию
            positionX = -positionX; // Меняем направление
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Перемещение и поворот
        g2d.translate(positionX, getHeight() / 2);
        g2d.rotate(angle);

        // Рисование квадрата
        drawSquare(g2d);
    }

    private void drawSquare(Graphics2D g2d) {
        Rectangle2D square = new Rectangle2D.Double(-squareSize / 2, -squareSize / 2, squareSize, squareSize);
        g2d.draw(square);
    }

    public static void main(String[] args) {
        JFrame window = createWindow();
        window.setVisible(true);
    }

    private static JFrame createWindow() {
        JFrame frame = new JFrame("Вращающийся квадрат");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new Main(), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Центрируем окно
        return frame;
    }
}

