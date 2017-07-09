package my;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ErrorDialog {
	ErrorDialog(String error){
		JOptionPane.showMessageDialog(new JFrame(), error,"Error",JOptionPane.ERROR_MESSAGE);
	}
}
