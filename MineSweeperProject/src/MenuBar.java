import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar {
    private JMenuBar menuBar;
    private JMenu game, help;
    private JMenuItem newGame, settings, exit, about;
    private Settings settingsClass;
    public JMenuBar createMenuBar()   {

        menuBar = new JMenuBar();

        game = new JMenu("Game");
        menuBar.add(game);

        newGame = new JMenuItem("New Game");
        game.add(newGame);
        newGame.addActionListener(new PressNewGame());

        settings = new JMenuItem("Settings");
        game.add(settings);
        settings.addActionListener(new PressSettings());

        exit = new JMenuItem("Exit");
        game.add(exit);
        exit.addActionListener(new PressExit());


        help = new JMenu("Help");
        menuBar.add(help);
        about = new JMenuItem("About");
        help.add(about);
        about.addActionListener(new PressAbout());

        return menuBar;
    }

    private class PressSettings implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Opened Settings");
            settingsClass = new Settings();
        }
    }



    private class PressExit implements  ActionListener   {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Pressed exit");
            System.exit(0);
        }
    }

    private class PressAbout implements ActionListener   {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Opened About");
            GroupInfo info = new GroupInfo();
        }
    }


}
class PressNewGame implements ActionListener    {

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Started new Game");
        new Game(0, 0, false);
    }
}

