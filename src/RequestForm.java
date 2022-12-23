import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;

public class RequestForm extends JFrame {
	static RequestForm frame;
	private JPanel contentPane;
	private JTextField callnoField;
	private JTextField nameField;
	private JTextField usernameField;
	private JPasswordField passwordField;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RequestForm();
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
	public RequestForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 404);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblSendReq = new JLabel("Zahtev za knjigom");
		lblSendReq.setForeground(Color.GRAY);
		lblSendReq.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblCallNo = new JLabel("Oznaka Knjige:");
		
		JLabel lblName = new JLabel("Naziv knjige:");
		
		JLabel lblUserName = new JLabel("Korisnicko ime");
		JLabel lblPassword = new JLabel("Lozinka");
		

		
		callnoField = new JTextField();
		callnoField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		
		JButton btnRequestt = new JButton("Podnesi zahtev");
		btnRequestt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String callno=callnoField.getText();
			String name=nameField.getText();
			String username=usernameField.getText();
			String password=String.valueOf(passwordField.getPassword());
			
			
			if(callno.equals("") || username.equals("") || name.equals("") || password.equals("")) {
				JOptionPane.showMessageDialog(RequestForm.this,"Sva polja moraju biti popunjena!");
				
			}else if(!UserDao.validate(username, password))
			{
				JOptionPane.showMessageDialog(RequestForm.this,"Pogresno ime ili lozinka!");
			}
			
			else {
				int userid=UserDao.getid(username, password);
			if(RequestDao.checkBook(callno) && RequestDao.checkQuantity(callno)>0 &&
					!RequestDao.checkReq(callno, userid) && !RequestDao.CheckIssued(callno, userid)) {
			int i=RequestDao.save(callno, name, userid);
			if(i>0){
				JOptionPane.showMessageDialog(RequestForm.this,"Zahtev podnet!");
				UserSucess.main(new String[]{});
				frame.dispose();
				
			}else{
				JOptionPane.showMessageDialog(RequestForm.this,"Došlo je do greške!");
			}
			}
			else if(!RequestDao.checkBook(callno))
				JOptionPane.showMessageDialog(RequestForm.this,"Ne postoji knjiga sa unetom oznakom");
			else if(RequestDao.checkReq(callno, userid))
					JOptionPane.showMessageDialog(RequestForm.this,"Već ste podneli zahtev za ovu knjigu");
			else if(RequestDao.CheckIssued(callno, userid))
				JOptionPane.showMessageDialog(RequestForm.this,"Ovu knjigu ste već iznajmili");
			else		
				JOptionPane.showMessageDialog(RequestForm.this,"Željena knjiga je trenutno nedostupna na lageru");
				
			}
		}
		});
		
		JButton btnBack = new JButton("Nazad");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserSucess.main(new String[]{});
				frame.dispose();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(lblSendReq))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblName)
								.addComponent(lblCallNo)
								.addComponent(lblUserName)
							.addComponent(lblPassword))
							.addGap(47)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(nameField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
								.addComponent(callnoField, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(125, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(161)
					.addComponent(btnRequestt, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(162, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(359, Short.MAX_VALUE)
					.addComponent(btnBack)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblSendReq)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCallNo)
						.addComponent(callnoField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblName)
						.addComponent(nameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserName)
						.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addComponent(btnRequestt, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBack)
					.addContainerGap(53, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
