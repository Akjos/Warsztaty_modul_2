package com.coderslab.DAO;

import com.coderslab.Utils.DBUtil;
import com.coderslab.databaseModel.UsersGroups;

import java.sql.*;
import java.util.Arrays;

public class UsersGroupsDAO {
    public static final String CREATE_GROUP_QUERY = "insert into users_groups(name) values (?);";
    private static String READ_GROUP_QUERY = "select * from users_groups where id = ?";
    private static String UPDATE_GROUP_QUERY = "update users_groups set name = ? where id = ?";
    private static String DELETE_GROUP_QUERY = "delete from users_groups where id = ?";
    private static String FIND_ALL_GROUP_QUERY = "select * from users_groups";

    public UsersGroups created(UsersGroups usersGroups) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usersGroups.getName());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                usersGroups.setId(result.getInt(1));
            }
            return usersGroups;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsersGroups read(int groupUserId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement= connection.prepareStatement(READ_GROUP_QUERY)) {
            statement.setInt(1,groupUserId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                UsersGroups usersGroups = new UsersGroups();
                usersGroups.setId(resultSet.getInt("id"));
                usersGroups.setName(resultSet.getString("name"));
                return usersGroups;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UsersGroups usersGroups) {
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP_QUERY)) {
            statement.setString(1,usersGroups.getName());
            statement.setInt(2,usersGroups.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE_GROUP_QUERY)) {
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UsersGroups[] findAll() {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY)) {

            UsersGroups[] usersGroups = new UsersGroups[0];

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UsersGroups usersGroup = new UsersGroups();
                usersGroup.setId(resultSet.getInt("id"));
                usersGroup.setName(resultSet.getString("name"));
                usersGroups = addToArray(usersGroup, usersGroups);
            }
            return usersGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UsersGroups[] addToArray(UsersGroups g, UsersGroups[] usersGroups) {
        UsersGroups[] tmpUsers = Arrays.copyOf(usersGroups, usersGroups.length + 1);
        tmpUsers[usersGroups.length] = g;
        return tmpUsers;
    }

}
