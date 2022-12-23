import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Library extends JFrame {
	static Library frame;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame= new Library();
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
	public Library() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblLibraryManagement = new JLabel("Biblioteka Sveti Sava");
		lblLibraryManagement.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLibraryManagement.setForeground(Color.BLACK);
		
		JButton btnAdminLogin = new JButton("Admin Login");
		btnAdminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AdminLogin.main(new String[]{});
			frame.dispose();
			}
		});
		btnAdminLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnLibrarianLogin = new JButton("Bibliotekar Login");
		btnLibrarianLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LibrarianLogin.main(new String[]{});
				frame.dispose();
			}
		});
		btnLibrarianLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnRegisterUser = new JButton("Registruj se");
		btnRegisterUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
			User.main(new String[]{});
			frame.dispose();
			}
		});
		
		
		btnRegisterUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnUserLogin = new JButton("Korisnicki login");
		btnUserLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e12) {
			UserLogin.main(new String[]{});
			frame.dispose();
			}
		});
		btnUserLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnGuest = new JButton("Sekcija za gosta");
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e12) {
			GuestSession.main(new String[]{});
			frame.dispose();
			}
		});
		btnGuest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		JButton btnContact = new JButton("Kontakt");
		btnContact.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
			ContactForm.main(new String[]{});
			frame.dispose();
			}
		});
		
		
		btnRegisterUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.CENTER)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.CENTER)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(134)
							.addComponent(lblLibraryManagement))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(140)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnLibrarianLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnAdminLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								.addComponent(btnUserLogin, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								
								.addComponent(btnGuest, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								.addComponent(btnRegisterUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
								
								.addComponent(btnContact, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))))
					.addContainerGap(130, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLibraryManagement)
					.addGap(32)
					.addComponent(btnAdminLogin, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLibrarianLogin, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE)
					.addComponent(btnUserLogin, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE)
					.addComponent(btnGuest, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(130, Short.MAX_VALUE)
					.addComponent(btnRegisterUser, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(130, Short.MAX_VALUE)
					
				.addComponent(btnContact, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
				.addContainerGap(130, Short.MAX_VALUE))
				
		);
		contentPane.setLayout(gl_contentPane);
	}
}
