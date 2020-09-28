import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonHandler implements ActionListener {
    private int row, col;
    private Grid grid;
    private int gridValue;

    public ButtonHandler(int x, int y, int value) {
        row = x;
        col = y;
        gridValue = value;
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Button Pressed: " + row + "," + col);
		JButton button = (JButton)e.getSource();
		button.setEnabled(false);
		if (gridValue == -1)
			button.setText("X");
		else
			button.setText(Integer.toString(gridValue));
	}
}