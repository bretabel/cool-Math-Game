package horsegame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import processing.core.PApplet;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JPasswordField pwdPassword;
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		//titles window
		setTitle("To Be Determined");
		//basic setup
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("Password");
		pwdPassword.setBounds(173, 145, 160, 30);
		contentPane.add(pwdPassword);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 145, 160, 30);
		contentPane.add(lblPassword);
		
		JLabel lblUsername = new JLabel("Login");
		lblUsername.setBounds(218, 10, 45, 30);
		contentPane.add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		txtUsername.setBounds(174, 90, 160, 30);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel label_1 = new JLabel("Username");
		label_1.setBounds(30, 90, 160, 30);
		contentPane.add(label_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(173, 215, 117, 29);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.print("This button has been clicked");

			
		}
		
	}
