package com.laptrinhjavaweb.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;

public class UserRepository extends SimpleJpaRepository<UserEntity> implements IUserRepository {

	@Override
	public UserEntity save(UserEntity userEntity) {
		String sql="insert into user(username,password, fullname, status) values (?,?,?,?)";
		Connection connection = null;
		PreparedStatement statement = null;
		//ResultSet resultSet = null;
		try {
			//Long id = null;
			connection = EntityManagerFactory.getConnection();
			connection.setAutoCommit(false);
		//	statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		//	setParameter(statement, parameters);
			statement = connection.prepareStatement(sql);
			statement.setString(1, userEntity.getUserName());
			statement.setString(2, userEntity.getPassWord());
			statement.setString(3, userEntity.getFullName());
			statement.setInt(4, userEntity.getStatus());
			statement.executeUpdate();
			
			/*resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}*/
			connection.commit();
			return 	userEntity;

		} catch (SQLException e) {
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				/*if (resultSet != null) {
					resultSet.close();
				}*/
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return new UserEntity();
	}
	

}
