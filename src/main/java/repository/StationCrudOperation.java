package repository;

import model.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StationCrudOperation implements CrudOperation<Station>{
    private Connection connection;

    public StationCrudOperation(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Station> findAll() throws SQLException {
        List<Station> allStation = new ArrayList<>();
        String sql = "SELECT * FROM station";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
            while (resultSet.next()){
                Station station = new Station();
                station.setStationID(resultSet.getObject("station_id",UUID.class));
                station.setName(resultSet.getString("name"));
                station.setLongitude(resultSet.getString("longitude"));
                station.setLatitude(resultSet.getString("latitude"));
                station.setNumber_of_employees(resultSet.getInt("number_of_employees"));
                allStation.add(station);
            }
        }
        return allStation;
    }

    @Override
    public List<Station> findById(UUID id) throws SQLException {
        List<Station> allStationById = new ArrayList<>();
        String sql = "SELECT * FROM station WHERE station_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()){
           while (resultSet.next()){
               Station station = new Station();
               station.setStationID(resultSet.getObject("station_id",UUID.class));
               station.setName(resultSet.getString("name"));
               station.setLongitude(resultSet.getString("longitude"));
               station.setLatitude(resultSet.getString("latitude"));
               station.setNumber_of_employees(resultSet.getInt("number_of_employees"));
               allStationById.add(station);
           }
        }
        return allStationById;
    }

    @Override
    public Station save(Station toSave) throws SQLException {
        String sql = "INSERT INTO station (station_id, name, longitude, latitude, number_of_employees) VALUES (?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setObject(1, toSave.getStationID());
            preparedStatement.setString(2, toSave.getName());
            preparedStatement.setString(3, toSave.getLongitude());
            preparedStatement.setString(4, toSave.getLatitude());
            preparedStatement.setInt(5, toSave.getNumber_of_employees());
            preparedStatement.executeUpdate();
        }
        return toSave;
    }

    @Override
    public Station update(UUID id, Station toUpdate) throws SQLException {
        String sql = "UPDATE station SET name=?, longitude=?, latitude=?, number_of_employees=? WHERE station_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, toUpdate.getName());
            preparedStatement.setString(2, toUpdate.getLongitude());
            preparedStatement.setString(3, toUpdate.getLatitude());
            preparedStatement.setInt(4, toUpdate.getNumber_of_employees());
            preparedStatement.setObject(5, id);
            preparedStatement.executeUpdate();
        }
        return toUpdate;
    }

    @Override
    public void delete(UUID id) throws SQLException {
        String sql = "DELETE FROM station WHERE station_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
