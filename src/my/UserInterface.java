package my;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;

public class UserInterface extends JFrame {
	
	private JTextField nameField;
	private JTextField IDField;
	private JTextArea statusArea;
	
	public UserInterface() {
		setTitle("Shelter");
		getContentPane().setLayout(null);
		
		String[] animals ={"Dog","Cat"};
		JComboBox comboBox = new JComboBox(animals);
		comboBox.setBounds(10, 11, 87, 20);
		getContentPane().add(comboBox);
		
		
		nameField = new JTextField();
		nameField.setBounds(114, 11, 86, 20);
		getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(nameField.getText().isEmpty())
						throw new Exception();
					UserBashInterfaceLogic.add(comboBox.getSelectedItem().toString(), nameField.getText());
					nameField.setText("");
				}catch (Exception ex) {
					new ErrorDialog("Name cannot be empty");
					
				}
			}
		});
		btnAdd.setBounds(222, 10, 104, 23);
		getContentPane().add(btnAdd);
		
		IDField = new JTextField();
		IDField.setBounds(11, 53, 86, 20);
		getContentPane().add(IDField);
		IDField.setColumns(10);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(IDField.getText().isEmpty())
						throw new Exception();
					UserBashInterfaceLogic.remove(IDField.getText());
					IDField.setText("");
				}catch (Exception ex) {
					new ErrorDialog("ID cannot be empty");
					
				}
			}
		});
		
		btnRemove.setBounds(114, 52, 86, 23);
		getContentPane().add(btnRemove);
		
		JButton btnStatus = new JButton("Status");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusArea.setText(UserBashInterfaceLogic.getStatus());
				
			}
		});
		btnStatus.setBounds(10, 92, 89, 23);
		getContentPane().add(btnStatus);
		
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBashInterfaceLogic.save();
			}
		});
		btnSave.setBounds(111, 92, 89, 23);
		getContentPane().add(btnSave);
		
		
		
		statusArea = new JTextArea();
		statusArea.setEditable(false);
		statusArea.setBounds(10, 126, 375, 124);
		statusArea.setLineWrap(true);
		//getContentPane().add(statusArea);
		statusArea.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane(statusArea);
		scrollPane.setBounds(10, 126, 426, 124);
		getContentPane().add(scrollPane);
		
		JButton btnSaveToCsv = new JButton("Save to CSV");
		btnSaveToCsv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBashInterfaceLogic.writeToCSV();
			}
		});
		btnSaveToCsv.setBounds(222, 92, 104, 23);
		getContentPane().add(btnSaveToCsv);
		
		JButton btnSaveToPdf = new JButton("Save to PDF");
		btnSaveToPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserBashInterfaceLogic.exportToPDF();
			}
		});
		btnSaveToPdf.setBounds(347, 92, 89, 23);
		getContentPane().add(btnSaveToPdf);
		
		addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				String ObjButtons[] = {"Yes","No"};
			    int PromptResult = JOptionPane.showOptionDialog(null, 
			        "Are you sure you want to exit?", "Closing", 
			        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
			        ObjButtons,ObjButtons[1]);
			    if(PromptResult==0)
			    {
			      System.exit(0);          
			    }
			}
		});
		setSize(462, 300);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		UserBashInterfaceLogic userBashInterfaceLogic = new UserBashInterfaceLogic();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UserInterface();
            }
       });
	}
}
