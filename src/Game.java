
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    private boolean running;

    public static int WIDTH = 600;
    public static int HEIGHT = 300;
    public static String NAME = "GAME";

    private boolean leftPressed = false;
    private boolean rightPressed = false;

    private boolean upPressed = false;
    private boolean downPressed = false;

    public Entity hero = new Entity();
    public Entity monster = new Entity();
    private static double x = 50.0;
    private static double y = 100.0;

    public Button attack = new Button("Attack");
    public Button attackMonster = new Button("Attack Монстр");

    public Button toHeal = new Button("Heal");
    public Button toHealMonster = new Button("Heal Monster");

    public Label lblHeroHealth = new Label();
    public Label lblHeroAttack = new Label();
    public Label lblHeroDefense = new Label();
    public Label lblMonsterHealth = new Label();
    public Label lblMonsterAttack = new Label();
    public Label lblMonsterDefense = new Label();

    public Label lblHeroTreat = new Label();
    public Label lblMonsterTreat = new Label();


    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void run() {
        long lastTime = System.currentTimeMillis();
        long delta;

        init();

        while (running) {
            delta = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            render();
            update(delta);
        }
    }

    public class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == attack) {
                System.out.println("dsfsf");
            }
        }
    }

    public void init() {
        addKeyListener(new KeyInputHandler());
        hero.setSprite(getSprite("man.png"));
        monster.setSprite(getSprite("monster.png"));
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
        lblHeroHealth.setText("Heath = " + hero.getHealth().toString());
        lblHeroHealth.setBounds(70, 90, 80, 10);
        lblHeroAttack.setText("Attack = " + hero.getAttack().toString());
        lblHeroAttack.setBounds(70, 70, 80, 10);
        lblHeroDefense.setText("Defense = " + hero.getDefense().toString());
        lblHeroDefense.setBounds(70, 50, 80, 10);
        lblMonsterHealth.setText("Heath = " + monster.getHealth().toString());
        lblMonsterHealth.setBounds(230, 90, 80, 10);
        lblMonsterAttack.setText("Attack = " + monster.getAttack().toString());
        lblMonsterAttack.setBounds(230, 70, 80, 10);
        lblMonsterDefense.setText("Defense = " + monster.getDefense().toString());
        lblMonsterDefense.setBounds(230, 50, 80, 10);
        lblHeroTreat.setText("Treat = " + hero.getTreatment().toString());
        lblHeroTreat.setBounds(70, 40, 80, 10);
        lblMonsterTreat.setText("Treat = " + monster.getTreatment().toString());
        lblMonsterTreat.setBounds(230, 40, 80, 10);
        toHeal.setBounds(65,0,64, 30);
        toHealMonster.setBounds(291, 0, 90, 30);
        hero.getSprite().draw(g, (int) x, (int) y);
        monster.getSprite().draw(g, 200, 100, 100, 100);

        g.dispose();
        bs.show();
    }

    public void update(long delta) {
        if (leftPressed == true) {
            x -= 0.01;
        }
        if (rightPressed == true) {
            x += 0.01;
        }
        if (upPressed == true) {
            y -= 0.01;
        }
        if (downPressed == true) {
            y += 0.01;
        }
    }

    public Sprite getSprite(String path) {
        BufferedImage sourceImage = null;

        try {
            URL url = this.getClass().getClassLoader().getResource(path);
            sourceImage = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));

        return sprite;
    }


    private class KeyInputHandler extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = true;
            }
        }

        public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                leftPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                rightPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                upPressed = false;
            }
            if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                downPressed = false;
            }
        }
    }
}