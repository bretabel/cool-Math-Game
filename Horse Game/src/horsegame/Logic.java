package horsegame;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Logic {
	public static int isValid(String name, String password) {
		String res;
		DB newDB = new DB();
		try {
			//res not empty login
			//res = newDB.checkLogin(username.getText(), password.getText());
			res = newDB.checkLogin(name, password);
			//JOptionPane.showMessageDialog(null, res);
			if(!res.isEmpty()) {
				return 1;
			}else {
				return 2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			return 3;
		}
	
	}
	public static void main(String[] args) {
		
		
	}
}