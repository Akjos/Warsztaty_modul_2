package com.coderslab.DAO;

import com.coderslab.utils.DBUtil;
import com.coderslab.databaseModel.Solutions;

import java.sql.*;
import java.util.Arrays;

public class SolutionsDAO {
    private static final String CREATE_SOLUTIONS_QUERY = "INSERT INTO solutions(created, description, exercises_id, user_id) VALUES (now(), ?, ?, ?)";
    private static final String READ_SOLUTIONS_QUERY = "SELECT * FROM solutions where id = ?";
    private static final String UPDATE_SOLUTIONS_QUERY = "UPDATE solutions SET description = ?, exercises_id = ?, user_id = ?, updated = ? where id = ?";
    private static final String DELETE_SOLUTIONS_QUERY = "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY = "SELECT * FROM solutions";
    private static final String FIND_ALL_BY_USERS_ID_QUERY = "SELECT * FROM solutions WHERE user_id = ?";
    private static final String FIND_ALL_BY_EXERCISES_ID_QUERY = "SELECT * FROM solutions WHERE exercises_id = ? ORDER BY created DESC";

    public Solutions created(Solutions solutions) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTIONS_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(solutions, statement);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                solutions.setId(result.getInt(1));
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Solutions read(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_SOLUTIONS_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solutions solutions = new Solutions();
                setSolution(resultSet, solutions);
                return solutions;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Solutions solutions) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SOLUTIONS_QUERY)) {
            solutions.setUpdate(new Date(System.currentTimeMillis()));
            setStatement(solutions, statement);
            statement.setDate(4, solutions.getUpdate());
            statement.setInt(5, solutions.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SOLUTIONS_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Solutions[] findAll() {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY)) {
            Solutions[] solutions = new Solutions[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solutions solution = new Solutions();
                setSolution(resultSet, solution);
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solutions[] findAllByUserId(int id) {
        return findAllById(id, FIND_ALL_BY_USERS_ID_QUERY);
    }

    public Solutions[] findAllByExerciseId(int id) {
        return findAllById(id, FIND_ALL_BY_EXERCISES_ID_QUERY);
    }

    private Solutions[] findAllById(int id, String findAllByUsersIdQuery) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllByUsersIdQuery)) {
            Solutions[] solutions = new Solutions[0];
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solutions solution = new Solutions();
                setSolution(resultSet, solution);
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Solutions[] addToArray(Solutions solution, Solutions[] solutions) {
        Solutions[] tempSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tempSolutions[solutions.length] = solution;
        return tempSolutions;
    }

    protected void setStatement(Solutions solution, PreparedStatement statement) throws SQLException {
        statement.setString(1, solution.getDescription());
        statement.setInt(2, solution.getExercise_id());
        statement.setInt(3, solution.getUser_id());
    }

    protected void setSolution(ResultSet resultSet, Solutions solution) throws SQLException {
        solution.setId(resultSet.getInt("id"));
        solution.setCreated(resultSet.getDate("created"));
        solution.setUpdate(resultSet.getDate("updated"));
        solution.setDescription(resultSet.getString("description"));
        solution.setExercise_id(resultSet.getInt("exercises_id"));
        solution.setUser_id(resultSet.getInt("user_id"));
    }
}
