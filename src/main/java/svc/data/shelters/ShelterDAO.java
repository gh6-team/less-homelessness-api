package svc.data.shelters;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import svc.data.jdbc.BaseJdbcDao;
import svc.location.LatLng;
import svc.logging.LogSystem;
import svc.models.Shelter;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShelterDAO extends BaseJdbcDao {
    public Shelter getByShelterId(int shelter_id) {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("shelter_id", shelter_id);
            String sql = "SELECT * FROM shelters WHERE id = :shelter_id";
            Shelter shelter = jdbcTemplate.queryForObject(sql, parameterMap, new ShelterSQLMapper());
            return shelter;
        } catch (Exception e) {
            LogSystem.LogDBException(e);
            return null;
        }
    }

    public List<Shelter> getByLocation(LatLng location) {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("latitude", location.latitude);
            parameterMap.put("longitude", location.longitude);
            String sql = "SELECT * FROM shelters"; //TODO do actual geo query
            return jdbcTemplate.query(sql, parameterMap, new ShelterSQLMapper());
        } catch (Exception e) {
            LogSystem.LogDBException(e);
            return null;
        }
    }

    public List<Shelter> getAllShelters() {
        try {
            Map<String, Object> parameterMap = new HashMap<String, Object>();
            String sql = "SELECT * FROM shelters";
            return jdbcTemplate.query(sql, parameterMap, new ShelterSQLMapper());
        } catch (Exception e) {
            LogSystem.LogDBException(e);
            return null;
        }
    }

    private class ShelterSQLMapper implements RowMapper<Shelter> {
        public Shelter mapRow(ResultSet rs, int i) {
            Shelter shelter = new Shelter();
            try {
                shelter.id = rs.getInt("id");
                shelter.name = rs.getString("name");
                shelter.address = rs.getString("address");
                shelter.state = rs.getString("state");
                shelter.zip_code = rs.getString("zip_code");
                shelter.phone_number = rs.getString("phone_number");
                shelter.location = rs.getString("location");
                shelter.allows_men = rs.getInt("allows_men");
                shelter.allows_women = rs.getInt("allows_women");
                shelter.allows_children = rs.getInt("allows_children");

            } catch (Exception e) {
                LogSystem.LogDBException(e);
                return null;
            }

            return shelter;
        }
    }

}
