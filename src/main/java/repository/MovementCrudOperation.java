package repository;

import lombok.AllArgsConstructor;
import model.MovementStock;
import model.MovementType;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
@AllArgsConstructor
public class MovementCrudOperation implements CrudOperation<MovementStock>{
    private Connection connection;

    @Override
    public List<MovementStock> findAll() throws SQLException {
        List<MovementStock> allMovementStock = new ArrayList<>();
        String sql = "SELECT * FROM Movement_Stock";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allMovementStock.add(new MovementStock(
                        resultSet.getInt("id"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("stock_datetime").toInstant(),
                        MovementType.valueOf(resultSet.getString("type")),
                        resultSet.getInt("product_id")
                ));
            }
        }
        return allMovementStock;
    }

    @Override
    public List<MovementStock> findById(UUID id) throws SQLException {
        List<MovementStock> allMovementStock = new ArrayList<>();
        String sql = "SELECT * FROM Movement_Stock WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allMovementStock.add(new MovementStock(
                        resultSet.getInt("id"),
                        resultSet.getInt("quantity"),
                        resultSet.getTimestamp("stock_datetime").toInstant(),
                        MovementType.valueOf(resultSet.getString("type")),
                        resultSet.getInt("product_id")
                ));
            }
        }
        return null;
    }

    @Override
    public MovementStock save(MovementStock toSave) throws SQLException {
        String sql = "INSERT INTO Movement_Stock (quantity, stock_datetime, type, product_id) VALUES (?, ?, ?, ?)";
        try(PreparedStatement insertStatement = connection.prepareStatement(sql)) {
            insertStatement.setInt(1, toSave.getQuantity());
            insertStatement.setTimestamp(2, Timestamp.from(toSave.getStockDatetime()));
            insertStatement.setObject(3, toSave.getType());
            insertStatement.setInt(4, toSave.getProductId());
            insertStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public MovementStock update(UUID id, MovementStock toUpdate) throws SQLException {
        String sql = "UPDATE Mouvement_Stock SET quantity = ?, stock_datetime = ? WHERE product_id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, toUpdate.getQuantity());
            preparedStatement.setTimestamp(2, Timestamp.from(toUpdate.getStockDatetime()));
            preparedStatement.setObject(3, toUpdate.getProductId());
            preparedStatement.executeUpdate();
        }
        return null;
    }

    @Override
    public void delete(UUID id) throws SQLException {

    }
}
