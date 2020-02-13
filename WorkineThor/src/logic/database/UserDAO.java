/**
 *  DAO class for Users entity, it will take care of the persistence, by connecting  
 *  to the DB through the Singleton instance dDDBHandle
 */
package logic.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.bean.UserBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDAO {

	private DBhandle dbHandler = DBhandle.getDBhandleInstance();
	private PreparedStatement pst;

	/**
	 * prepares the statement and then sends it to the DB
	 * 
	 * @param username
	 * @param password
	 * @throws SQLException
	 */
	public boolean insert(UserBean user) throws SQLException {
		String insert = "INSERT INTO users(username,password)" + "VALUES (?,?)";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(insert);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, user.getUsername());
		pst.setString(2, user.getPassword());

		pst.executeUpdate();
		return true;

	}

	public UserBean getUser(UserBean userIN) throws SQLException {
		UserBean userOut = new UserBean();
		String getUser = "SELECT * from users where username=? and password=?";

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getUser);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		pst.setString(1, userIN.getUsername());
		pst.setString(2, userIN.getPassword());
		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no user with the correct username or password
			userOut.setUsername("");
			userOut.setPassword("");
		} else {
			userOut.setUsername(rs.getString("username"));
			userOut.setPassword(rs.getString("password"));
		}
		return userOut;
	}

	public ObservableList<String> getAllUsers() throws SQLException { 
		String getAllUsers = "SELECT * from users";
		ObservableList<String> allUsers = FXCollections.observableArrayList();

		Connection dbConnection = dbHandler.getConnection();

		try {

			pst = dbConnection.prepareStatement(getAllUsers);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		ResultSet rs = pst.executeQuery();

		if (!rs.first()) { // rs empty no user with the correct username

		} else {
			while (rs.next())
				allUsers.addAll(rs.getString("username"));
		}
		return allUsers;
	}

}