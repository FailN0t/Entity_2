import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        JFrame frame = new JFrame(Game.NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        game.attack.setBounds(0, 0, 64, 30);
        frame.add(game.attack);
        game.attackMonster.setBounds(200, 0, 90, 30);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == game.attack) {
                    System.out.println(game.hero.getHealth());
                    System.out.println(game.hero.getAttack());
                    System.out.println(game.monster.getHealth());
                    System.out.println(game.monster.getDefense());
                    game.hero.strike(game.monster);
                    System.out.println(game.monster.getHealth());
                    System.out.println();
                }
                if (e.getSource() == game.attackMonster) {
                    System.out.println(game.hero.getHealth());
                    System.out.println(game.hero.getDefense());
                    System.out.println(game.monster.getHealth());
                    System.out.println(game.monster.getAttack());
                    game.monster.strike(game.hero);
                    System.out.println(game.hero.getHealth());
                    System.out.println();
                }
                if (e.getSource() == game.toHeal) {
                    game.hero.toHeal();
                }
                if (e.getSource() == game.toHealMonster) {
                    game.monster.toHeal();
                }
            }
        };
        game.attack.addActionListener(actionListener);
        game.attackMonster.addActionListener(actionListener);
        game.toHeal.addActionListener(actionListener);
        game.toHealMonster.addActionListener(actionListener);
        frame.add(game.attackMonster);
        frame.add(game.toHeal);
        frame.add(game.toHealMonster);

        frame.add(game.lblHeroHealth);
        frame.add(game.lblMonsterHealth);
        frame.add(game.lblHeroAttack);
        frame.add(game.lblHeroDefense);
        frame.add(game.lblMonsterAttack);
        frame.add(game.lblMonsterDefense);
        frame.add(game.lblHeroTreat);
        frame.add(game.lblMonsterTreat);
        frame.add(game, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(true);
        frame.setVisible(true);
        game.start();
    }

}
