import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ChangePassword extends JFrame {
	static ChangePassword frame;
	private JPanel contentPane;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JPasswordField newPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ChangePassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 public static boolean
	    isAllPresent(String str)
	    {
	        String regex = "^[A-Za-z_\\d]+$";       
	        Pattern p = Pattern.compile(regex);
	        if (str == null) {
	            System.out.println("No");
	            return false;
	        }
	        Matcher m = p.matcher(str);
	        if (m.matches())
	            return true;
	        else
	            return false;
	    }

	/**
	 * Create the frame.
	 */
	public ChangePassword() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUserLoginForm = new JLabel("Promena lozinke");
		lblUserLoginForm.setForeground(Color.GRAY);
		lblUserLoginForm.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblEnterName = new JLabel("Unesite ime:");
		
		JLabel lblEnterPassword = new JLabel("Unesite staru lozinku:");
		
		JLabel lblEnterNewPassword = new JLabel("Unesite novu lozinku:");
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		JButton btnChangePass = new JButton("Promeni lozinku");
		btnChangePass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String name=nameField.getText();
			String password=String.valueOf(passwordField.getPassword());
			String newpassword=String.valueOf(newPasswordField.getPassword());
			
			if(ChangePasswordDao.validate(name, password) && isAllPresent(newpassword)){
				ChangePasswordDao.save(name,password,newpassword);
				JOptionPane.showMessageDialog(ChangePassword.this,"Uspešno promenjena lozinka");
				frame.dispose();
				UserSucess.main(new String[]{});
			}
			else if(!isAllPresent(newpassword)) {
				JOptionPane.showMessageDialog(ChangePassword.this,"Lozinka može sadržati samo mala i velika slova i '_' ");
			
			}else{
			
				JOptionPane.showMessageDialog(ChangePassword.this, "Pogrešan username ili lozinka","Error!", JOptionPane.ERROR_MESSAGE);
				nameField.setText("");
				passwordField.setText("");
				newPasswordField.setText("");
			}
			}
		});
		
		JButton btnBack = new JButton("Nazad");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSucess.main(new String[]{});
				frame.dispose();
			}
		});
		
		passwordField = new JPasswordField();
		newPasswordField = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(124)
							.addComponent(lblUserLoginForm)
						)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEnterName)
								.addComponent(lblEnterPassword)
								.addComponent(lblEnterNewPassword))
							.addGap(47)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(newPasswordField)
								.addComponent(passwordField)
								.addComponent(nameField, GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))))
					.addContainerGap(107, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(187, Short.MAX_VALUE)
					.addComponent(btnChangePass, GroupLayout.DEFAULT_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(151)
					.addComponent(btnBack)
					.addGap(46))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblUserLoginForm)
					.addContainerGap(300, Short.MAX_VALUE)
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblEnterNewPassword)
							.addComponent(newPasswordField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnChangePass)
					.addContainerGap(80, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addGap(46))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
