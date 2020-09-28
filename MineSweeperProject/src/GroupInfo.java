import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

public class GroupInfo {

    private JFrame description = new JFrame("About");

    public GroupInfo()  {
        JLabel groupInfo = new JLabel("Game created by some comp sci kids");
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new PressOK());
        groupInfo.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        okButton.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        GroupLayout layout = new GroupLayout(description.getContentPane());
        description.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(LEADING).addComponent(groupInfo)).addGroup(layout.createParallelGroup(LEADING).addComponent(okButton)));
        layout.linkSize(SwingConstants.HORIZONTAL,okButton);
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(BASELINE).addComponent(groupInfo)).addGroup(layout.createParallelGroup(BASELINE).addComponent(okButton)));
        description.pack();
        description.setVisible(true);
        description.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    private class PressOK implements ActionListener  {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Closed About");
            description.dispose();
        }
    }
}
