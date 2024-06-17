package Service;

import model.Station;
import repository.StationCrudOperation;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class StationService {
    private final StationCrudOperation stationCrudOperation;

    public StationService(StationCrudOperation stationCrudOperation) {
        this.stationCrudOperation = stationCrudOperation;
    }
    public List<Station> getAllStation() throws SQLException {
        return stationCrudOperation.findAll();
    }
    public Station createStation(Station toSave) throws SQLException {
        return stationCrudOperation.save(toSave);
    }
    public void updateStation(UUID id, Station toUpdate) throws SQLException {
        stationCrudOperation.update(id, toUpdate);
    }
    public void deleteStation(UUID id) throws SQLException {
        stationCrudOperation.delete(id);
    }
    public List<Station> getStationById(UUID id) throws SQLException {
        return stationCrudOperation.findById(id);
    }
}
