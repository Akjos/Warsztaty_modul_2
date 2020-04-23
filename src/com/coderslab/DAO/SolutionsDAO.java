package com.coderslab.DAO;

import com.coderslab.utils.DBUtil;
import com.coderslab.databaseModel.Solution;

import java.sql.*;
import java.util.Arrays;

public class SolutionsDAO {
    private static final String CREATE_SOLUTIONS_QUERY = "INSERT INTO solutions(created, exercises_id, user_id) VALUES (now(), ?, ?)";
    private static final String READ_SOLUTIONS_QUERY = "SELECT * FROM solutions where id = ?";
    private static final String UPDATE_SOLUTIONS_QUERY = "UPDATE solutions SET description = ?, updated = now() where id = ?";
    private static final String DELETE_SOLUTIONS_QUERY = "DELETE FROM solutions WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY = "SELECT * FROM solutions";
    private static final String FIND_ALL_BY_USERS_ID_QUERY = "SELECT * FROM solutions WHERE user_id = ?";
    private static final String FIND_ALL_BY_EXERCISES_ID_QUERY = "SELECT * FROM solutions WHERE exercises_id = ? ORDER BY created DESC";
    private static final String FIND_ALL_UNDONE_BY_USER_ID_QUERY = "SELECT * FROM solutions WHERE user_id = ? and description is null ORDER BY created DESC";

    public Solution created(Solution solution) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTIONS_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            setStatement(solution, statement);
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                solution.setId(result.getInt(1));
            }
            return solution;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Solution read(int id) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_SOLUTIONS_QUERY)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Solution solution = new Solution();
                setSolution(resultSet, solution);
                return solution;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void update(Solution solution) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SOLUTIONS_QUERY)) {
            statement.setString(1, solution.getDescription());
            statement.setInt(2, solution.getId());
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

    public Solution[] findAll() {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_SOLUTIONS_QUERY)) {
            Solution[] solutions = new Solution[0];
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                setSolution(resultSet, solution);
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Solution[] findAllByUserId(int id) {
        return findAllById(id, FIND_ALL_BY_USERS_ID_QUERY);
    }

    public Solution[] findAllByExerciseId(int id) {
        return findAllById(id, FIND_ALL_BY_EXERCISES_ID_QUERY);
    }

    public Solution[] findAllUndoneByUserId(int id) {
        return findAllById(id, FIND_ALL_UNDONE_BY_USER_ID_QUERY);
    }

    private Solution[] findAllById(int id, String findAllByUsersIdQuery) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(findAllByUsersIdQuery)) {
            Solution[] solutions = new Solution[0];
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Solution solution = new Solution();
                setSolution(resultSet, solution);
                solutions = addToArray(solution, solutions);
            }
            return solutions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private Solution[] addToArray(Solution solution, Solution[] solutions) {
        Solution[] tempSolutions = Arrays.copyOf(solutions, solutions.length + 1);
        tempSolutions[solutions.length] = solution;
        return tempSolutions;
    }

    protected void setStatement(Solution solution, PreparedStatement statement) throws SQLException {
        statement.setInt(1, solution.getExercise_id());
        statement.setInt(2, solution.getUser_id());
    }

    protected void setSolution(ResultSet resultSet, Solution solution) throws SQLException {
        solution.setId(resultSet.getInt("id"));
        solution.setCreated(resultSet.getDate("created"));
        solution.setUpdate(resultSet.getDate("updated"));
        solution.setDescription(resultSet.getString("description"));
        solution.setExercise_id(resultSet.getInt("exercises_id"));
        solution.setUser_id(resultSet.getInt("user_id"));
    }
}
