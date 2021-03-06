import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ExamScorerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private final JComboBox<Integer> comboBox = new JComboBox<Integer>();
	private JTextField curveCorrect;
	private JTextField curveTotal;
	// private JLabel output = new JLabel("");
	JCheckBox chckbxCurve;
	JToggleButton fileSaver;
	private int pointValue = 1;
	private boolean curve = false;
	static String fileName = "";
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel output;
	private static Font font;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					// If Nimbus is not available, you can set the GUI to another look and feel.
				}
				try {
					ExamScorerGUI frame = new ExamScorerGUI();
					// frame.setSize(new Dimension(600, 450));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ExamScorerGUI() {
		setTitle("Exam Scores Calculator");
		setResizable(false);
		setForeground(Color.WHITE);
		// setBackground(Color.DARK_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblStudentsScore = new JLabel("Number Correct:");
		lblStudentsScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStudentsScore.setBounds(39, 66, 101, 27);
		contentPane.add(lblStudentsScore);

		textField = new JTextField();
		textField.setBounds(168, 62, 54, 31);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblTotalScore = new JLabel("Possible Correct:");
		lblTotalScore.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalScore.setBounds(39, 107, 101, 14);
		contentPane.add(lblTotalScore);

		textField_1 = new JTextField();
		textField_1.setBounds(168, 99, 54, 31);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCorrectCurve = new JLabel("Add Correct:");
		lblCorrectCurve.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCorrectCurve.setBounds(39, 240, 101, 14);
		// contentPane.add(lblCorrectCurve);

		curveCorrect = new JTextField();
		curveCorrect.setBounds(168, 233, 54, 31);
		// contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblTotalCurve = new JLabel("Add Total:");
		lblTotalCurve.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTotalCurve.setBounds(39, 270, 101, 14);
		// contentPane.add(lblCorrectCurve);

		curveTotal = new JTextField();
		curveTotal.setBounds(168, 263, 54, 31);
		// contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel = new JLabel(new ImageIcon(("chemistry.png")));
		lblNewLabel.setBounds(291, 62, 143, 110);
		contentPane.add(lblNewLabel);

		chckbxCurve = new JCheckBox("Curve Scores");
		chckbxCurve.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxCurve.setBounds(20, 174, 135, 23);
		contentPane.add(chckbxCurve);
		comboBox.setModel(new DefaultComboBoxModel(new Integer[] { 1, 2, 3, 4, 5 }));
		comboBox.setToolTipText("");
		comboBox.setBounds(172, 135, 46, 30);
		contentPane.add(comboBox);
		/* Number Listener */
		ActionListener comboBoxListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pointValue = (int) comboBox.getSelectedItem();

			}
		};
		comboBox.addActionListener(comboBoxListener);

		JLabel lblNewLabel_2 = new JLabel("Points Per Question:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(20, 140, 120, 14);
		contentPane.add(lblNewLabel_2);

		output = new JLabel("");
		output.setHorizontalAlignment(SwingConstants.LEADING);
		output.setBounds(94, 250, 25500, 84);
		// output.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(output);

		JLabel fileNameLabel = new JLabel("File Name:");
		fileNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		fileNameLabel.setBounds(-36, 205, 101, 14);
		contentPane.add(fileNameLabel);

		fileSaver = new JToggleButton("Store In Doc");
		fileSaver.setHorizontalAlignment(SwingConstants.CENTER);
		fileSaver.setBounds(291, 174, 143, 23);
		fileSaver.setSelected(true);
		contentPane.add(fileSaver);

		JButton btnCalculateScore = new JButton("Calculate Score");
		btnCalculateScore.setBounds(94, 236, 255, 50);
		contentPane.add(btnCalculateScore);

		textField_2 = new JTextField();//
		textField_2.setBounds(70, 200, 126, 27);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblStudentName = new JLabel("Student Name:");//
		lblStudentName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblStudentName.setBounds(205, 205, 86, 14);
		contentPane.add(lblStudentName);

		textField_3 = new JTextField();//
		textField_3.setBounds(301, 200, 133, 27);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Novi Exam Scorer");
		File font_file = new File("schoolfont.ttf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, font_file);
			font = font.deriveFont(30.0f);
		} catch (FontFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblNewLabel_4.setForeground(new Color(39, 102, 29));
		lblNewLabel_4.setFont(font);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(65, -65, 300, 200);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("by Joshua Huang");
		lblNewLabel_3.setFont(new Font("", Font.PLAIN, 9));
		lblNewLabel_3.setBounds(353, 28, 81, 14);
		contentPane.add(lblNewLabel_3);

		btnCalculateScore.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					output.setText(calculate() + "% Correct                 " + (score(curve, pointValue)));
					generateFile();
					
					// updateCalc();
					// System.out.println(calculate() + "% Correct");
					// System.out.println(score(curve, pointValue));
				} catch (NumberFormatException exc) {
					output.setText(("                    Please Enter Numbers"));
				}
			}
		});
		ActionListener modeListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (chckbxCurve.isSelected() && fileSaver.isSelected()) {
					setBounds(100, 100, 450, 400);
					btnCalculateScore.setBounds(94, 306, 255, 50);
					fileNameLabel.setText("File Name:");
					lblStudentName.setText("Student Name:");
					contentPane.add(lblCorrectCurve);
					contentPane.add(curveCorrect);
					contentPane.add(lblTotalCurve);
					contentPane.add(curveTotal);
					curve = true;
					output.setBounds(94, 320, 25500, 84);
					textField_2.setVisible(true);
					textField_3.setVisible(true);
				} else if (fileSaver.isSelected()) {
					setBounds(100, 100, 450, 330);
					btnCalculateScore.setBounds(94, 236, 255, 50);
					contentPane.remove(lblCorrectCurve);
					contentPane.remove(curveCorrect);
					contentPane.remove(lblTotalCurve);
					contentPane.remove(curveTotal);
					fileNameLabel.setText("File Name:");
					lblStudentName.setText("Student Name:");
					curve = false;
					output.setBounds(94, 250, 25500, 84);
					textField_2.setVisible(true);
					textField_3.setVisible(true);
				} else if (chckbxCurve.isSelected()) {
					setBounds(100, 100, 450, 400);
					btnCalculateScore.setBounds(94, 306, 255, 50);
					contentPane.add(lblCorrectCurve);
					contentPane.add(curveCorrect);
					contentPane.add(lblTotalCurve);
					contentPane.add(curveTotal);
					fileNameLabel.setText("");
					lblStudentName.setText("");
					curve = true;
					output.setBounds(94, 320, 25500, 84);
					textField_2.setVisible(false);
					textField_3.setVisible(false);
				} else {
					setBounds(100, 100, 450, 330);
					btnCalculateScore.setBounds(94, 236, 255, 50);
					contentPane.remove(lblCorrectCurve);
					contentPane.remove(curveCorrect);
					contentPane.remove(lblTotalCurve);
					contentPane.remove(curveTotal);
					fileNameLabel.setText("");
					lblStudentName.setText("");
					curve = false;
					output.setBounds(94, 250, 25500, 84);
					textField_2.setVisible(false);
					textField_3.setVisible(false);
				}
			}
		};
		chckbxCurve.addActionListener(modeListener);
		fileSaver.addActionListener(modeListener);
	}

	private double calculate() {
		double curveC = 0;
		double curveT = 0;
		if (chckbxCurve.isSelected()) {
			curveC = Double.parseDouble(curveCorrect.getText());
			curveT = Double.parseDouble(curveTotal.getText());
		}
		DecimalFormat numberFormat = new DecimalFormat("#.0");
		double correctsP = Double.parseDouble(textField.getText()) * (int) comboBox.getSelectedItem() + curveC;
		double totalP = Double.parseDouble(textField_1.getText()) * (int) comboBox.getSelectedItem() + curveT;
		double percent = correctsP / totalP * 100;
		return Double.parseDouble(numberFormat.format(percent));
	}

	private String score(boolean curve, int pointValue) {
		double corrects = Double.parseDouble(textField.getText());
		double total = Double.parseDouble(textField_1.getText());
		if (curve) {
			double curveC = Double.parseDouble(curveCorrect.getText());
			double curveT = Double.parseDouble(curveTotal.getText());
			return "Score: " + (corrects * pointValue + curveC) + " out of " + (total * pointValue + curveT);
		} else {
			return "Score: " + corrects * pointValue + " out of " + total * pointValue;
		}

	}

	private void generateFile() {
		if (fileSaver.isSelected()&&!textField_3.getText().isEmpty()) {
			String s = System.lineSeparator();			
			try {
				Files.write(Paths.get("recordedLogs/"+textField_2.getText()+".txt"), s.getBytes(), StandardOpenOption.APPEND);
			    Files.write(Paths.get("recordedLogs/"+textField_2.getText()+".txt"), (textField_3.getText()+": "+output.getText()).getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
				File dir = new File("recordedLogs");
				dir.mkdirs();
				@SuppressWarnings("unused")
				File file = new File(dir, textField_2.getText()+".txt");
				try {
					PrintWriter display = new PrintWriter("recordedLogs/"+textField_2.getText()+".txt");
					display.print(textField_3.getText()+": "+output.getText());
					display.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
//	private double getAverage() {
//		nameSize = textField_3.getText().length();
//		try {
//			Files.readAllLines(Paths.get("recordedLogs/"+textField_2.getText()+".txt"));
//		    Files.write(Paths.get("recordedLogs/"+textField_2.getText()+".txt"), (textField_3.getText()+": "+output.getText()).getBytes(), StandardOpenOption.APPEND);
//		}catch (IOException e) {
//			File dir = new File("recordedLogs");
//			dir.mkdirs();
//			File file = new File(dir, textField_2.getText()+".txt");
//			try {
//				PrintWriter display = new PrintWriter("recordedLogs/"+textField_2.getText()+".txt");
//				display.print(textField_3.getText()+": "+output.getText());
//				display.close();
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//		}
//	}
}
