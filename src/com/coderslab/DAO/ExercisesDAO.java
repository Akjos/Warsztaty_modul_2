package com.coderslab.DAO;

import com.coderslab.utils.DBUtil;
import com.coderslab.databaseModel.Exercise;

import java.sql.*;
import java.util.Arrays;

public class ExercisesDAO {
    private static final String CREATE_EXERCISES_QUERY = "INSERT INTO exercises(title, description) VALUES (?, ?)";
    private static final String READ_EXERCISES_QUERY = "SELECT * FROM exercises where id = ?";
    private static final String UPDATE_EXERCISES_QUERY = "UPDATE exercises SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISES_QUERY = "DELETE FROM exercises WHERE id = ?";
    private static final String FIND_ALL_EXERCISES_QUERY = "SELECT * FROM exercises";

    public Exercise create(Exercise exercise) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_EXERCISES_QUERY, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.executeUpdate();
            ResultSet result = statement.getGeneratedKeys();
            if (result.next()) {
                exercise.setId(result.getInt(1));
            }
            return exercise;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Exercise read(int exerciseId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_EXERCISES_QUERY)) {
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                return exercise;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Exercise exercise) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISES_QUERY)) {
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int exerciseId) {
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISES_QUERY)) {
            statement.setInt(1, exerciseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Exercise[] findAll() {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(FIND_ALL_EXERCISES_QUERY)) {

            Exercise[] exercises = new Exercise[0];

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getInt("id"));
                exercise.setTitle(resultSet.getString("title"));
                exercise.setDescription(resultSet.getString("description"));
                exercises = addToArray(exercise, exercises);
            }
            return exercises;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Exercise[] addToArray(Exercise exercise, Exercise[] exercises) {
        Exercise[] tempExercises = Arrays.copyOf(exercises, exercises.length + 1);
        tempExercises[exercises.length] = exercise;
        return tempExercises;
    }
}
