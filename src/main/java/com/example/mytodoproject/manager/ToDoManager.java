package com.example.mytodoproject.manager;

import com.example.mytodoproject.db.DBConnectionProvider;
import com.example.mytodoproject.model.Status;
import com.example.mytodoproject.model.ToDo;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;


public class ToDoManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final UserManager userManager = new UserManager();

    public void addToDo(ToDo toDo) {
        String sql = "INSERT INTO todo(title, created_date, finish_date, user_id, status) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setDate(2, new Date(toDo.getCreatedDate().getTime()));
            preparedStatement.setDate(3, new Date(toDo.getFinishDate().getTime()));
            preparedStatement.setInt(4, toDo.getUser().getId());
            preparedStatement.setString(5, toDo.getStatus().toString());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                toDo.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteToDo(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateToDo(ToDo toDo) {
        String sql = "UPDATE todo SET title = ?,  finish_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, toDo.getTitle());
            preparedStatement.setDate(2, new Date(toDo.getFinishDate().getTime()));
            preparedStatement.setString(3, toDo.getStatus().toString());
            preparedStatement.setInt(4, toDo.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ToDo> getToDoByDate(int userId) {
        List<ToDo> toDos = new ArrayList<>();
        String query = "SELECT * FROM todo WHERE user_id = '" + userId + "' ORDER BY finish_date";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                toDos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return toDos;
    }

    public List<ToDo> getToDoByUser(int userId) {
        List<ToDo> toDos = new ArrayList<>();
        String query = "SELECT * FROM todo WHERE user_id = '" + userId + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                toDos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return toDos;
    }


    public ToDo getToDoById(int id) {
        String query = "SELECT * FROM todo WHERE id =" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next()) {
                return (ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userManager.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
