/*
 * This is GUI of application 
 * author : Can Eldem
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.ws.handler.MessageContext.Scope;

public class searchGui extends JFrame{

	public JPanel contentPane;
	public JTextField textField;
	public JTextField textField_1;
	public JLabel lblNewLabel_2;
	public JButton btnNewButton;
	public JComboBox comboBox;
	public JLabel lblPath ;
	public JLabel lblCost ;
	public JLabel lblTime ;
	public JLabel lblExporedNodes;
	public JTextArea textArea;
	public JScrollPane scrollingArea;

	/**
	 * Instantiates a new search gui.
	 */
	searchGui(){


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(132, 17, 41, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(132, 48, 41, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel("start point");
		lblNewLabel.setBounds(24, 20, 68, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("end point ");
		lblNewLabel_1.setBounds(24, 51, 68, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("./map.jpg"));
		lblNewLabel_2.setBounds(10, 137, 588, 382);
		contentPane.add(lblNewLabel_2);
		btnNewButton = new JButton("Search");
		btnNewButton.setBounds(24, 72, 89, 23);
		contentPane.add(btnNewButton);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Breath First ", "Depth First ", "Uniform cost", "Greddy Best First", "A*"}));
		comboBox.setBounds(24, 106, 115, 20);
		contentPane.add(comboBox);

		lblPath = new JLabel("Path:");
		lblPath.setBounds(204, 20, 740, 14);
		contentPane.add(lblPath);

		lblCost = new JLabel("Cost:");
		lblCost.setBounds(204, 51, 740, 14);
		contentPane.add(lblCost);

		lblTime = new JLabel("Time:");
		lblTime.setBounds(204, 106, 740, 14);
		contentPane.add(lblTime);

		lblExporedNodes = new JLabel("Expored Nodes:");
		lblExporedNodes.setBounds(203, 76, 740, 14);
		contentPane.add(lblExporedNodes);

		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(608, 137, 46, 14);
		contentPane.add(lblSteps);

		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(608, 162, 270, 357);
		contentPane.add(textArea);

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(608, 162, 270, 357);
		contentPane.add(scrollPane);
	 

		addListener();

	}// end of constructor

	void addListener(){

		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int mthd=comboBox.getSelectedIndex();
				int start =Integer.parseInt(textField.getText().toString());
				int end =Integer.parseInt(textField_1.getText().toString());
				GeneralSearch general = new GeneralSearch(mthd,start-1,end-1,lblCost,lblPath,lblTime,textArea,lblExporedNodes);
				general.Search();
			}
		});
	}

	public static void main(String[] args) {
		searchGui d = new searchGui();
		d.setVisible(true);
	}


}
