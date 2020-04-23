package com.coderslab.DAO;

import com.coderslab.utils.DBUtil;
import com.coderslab.databaseModel.UsersGroup;

import java.sql.*;
import java.util.Arrays;

public class UsersGroupsDAO {
    public static final String CREATE_GROUP_QUERY = "insert into users_groups(name) values (?);";
    private static String READ_GROUP_QUERY = "select * from users_groups where id = ?";
    private static String UPDATE_GROUP_QUERY = "update users_groups set name = ? where id = ?";
    private static String DELETE_GROUP_QUERY = "delete from users_groups where id = ?";
    private static String FIND_ALL_GROUP_QUERY = "select * from users_groups";

    public UsersGroup create(UsersGroup usersGroup) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, usersGroup.getName());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                usersGroup.setId(result.getInt(1));
            }
            return usersGroup;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UsersGroup read(int groupUserId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement= connection.prepareStatement(READ_GROUP_QUERY)) {
            statement.setInt(1,groupUserId);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                UsersGroup usersGroup = new UsersGroup();
                usersGroup.setId(resultSet.getInt("id"));
                usersGroup.setName(resultSet.getString("name"));
                return usersGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UsersGroup usersGroup) {
        try(Connection connection = DBUtil.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_GROUP_QUERY)) {
            statement.setString(1, usersGroup.getName());
            statement.setInt(2, usersGroup.getId());
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

    public UsersGroup[] findAll() {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_GROUP_QUERY)) {

            UsersGroup[] usersGroups = new UsersGroup[0];

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UsersGroup usersGroup = new UsersGroup();
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

    private UsersGroup[] addToArray(UsersGroup g, UsersGroup[] usersGroups) {
        UsersGroup[] tmpUsers = Arrays.copyOf(usersGroups, usersGroups.length + 1);
        tmpUsers[usersGroups.length] = g;
        return tmpUsers;
    }

}
