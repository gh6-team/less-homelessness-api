package svc.data.shelters;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import svc.data.jdbc.BaseJdbcDao;
import svc.location.LatLng;
import svc.logging.LogSystem;
import svc.models.Shelter;
import svc.models.ShelterBedAssignment;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ShelterDAO extends BaseJdbcDao {
	private SimpleJdbcInsert shelterBedAssignmentInsert;

	@Override
	protected void createSimpleJdbcInserts(DataSource dataSource) {
		shelterBedAssignmentInsert = new SimpleJdbcInsert(dataSource).withTableName("shelter_bed_assignments")
				.usingGeneratedKeyColumns("id")
				.usingColumns("shelter_bed_id", "assigned_to_client_id", "assignment_date", "assigned_by_id");
	}

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
			String sql = "SELECT * FROM shelters"; // TODO do actual geo query
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

	public List<ShelterBedAssignment> getBedAssignmentsForShelter(int shelter_id) {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("shelter_id", shelter_id);
			String sql = "SELECT * FROM shelter_beds LEFT JOIN shelter_bed_assignments "
					+ "ON shelter_beds.id = shelter_bed_assignments.shelter_bed_id "
					+ "WHERE shelter_beds.shelter_id = :shelter_id";
			return jdbcTemplate.query(sql, parameterMap, new ShelterBedAssignmentSQLMapper());
		} catch (Exception e) {
			LogSystem.LogDBException(e);
			return null;
		}
	}

	public ShelterBedAssignment assignBed(ShelterBedAssignment shelterBedAssignment) {
		final SqlParameterSource parameterSource = new MapSqlParameterSource()
				.addValue("shelter_bed_id", shelterBedAssignment.shelter_bed_id)
				.addValue("assigned_to_client_id", shelterBedAssignment.assigned_to_client_id)
				.addValue("assignment_date", "2016-10-23")
				.addValue("assigned_by_id", shelterBedAssignment.assigned_by_id);

		try {
			Number key = shelterBedAssignmentInsert.executeAndReturnKey(parameterSource);
			shelterBedAssignment.id = key.intValue();
			return shelterBedAssignment;
		} catch (Exception e) {
			LogSystem.LogDBException(e);
			return null;
		}
	}

	public long getAvailableBedCount(int shelter_id) {
		long count = 0;

		List<ShelterBedAssignment> beds = getBedAssignmentsForShelter(shelter_id);
		for (ShelterBedAssignment bed : beds) {
			if (bed.assigned_to_client_id == 0) {
				++count;
			}
		}

		return count;
	}

	public boolean unassignBed(int shelter_bed_assignment_id) {
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("shelter_bed_assignment_id", shelter_bed_assignment_id);
			String sql = "DELETE FROM shelter_bed_assignments WHERE id = :shelter_bed_assignment_id";
			jdbcTemplate.update(sql, parameterMap);
			return true;
		} catch (Exception e) {
			LogSystem.LogDBException(e);
			return false;
		}
	}

	private class ShelterSQLMapper implements RowMapper<Shelter> {
		public Shelter mapRow(ResultSet rs, int i) {
			Shelter shelter = new Shelter();
			try {
				shelter.id = rs.getInt("id");
				shelter.name = rs.getString("name");
				shelter.address = rs.getString("address");
				shelter.city = rs.getString("city");
				shelter.state = rs.getString("state");
				shelter.zip_code = rs.getString("zip_code");
				shelter.phone_number = rs.getString("phone_number");
				Double latitude = rs.getDouble("latitude");
				Double longitude = rs.getDouble("longitude");
				if (latitude != null && longitude != null) {
					shelter.location = new LatLng(latitude, longitude);
				}
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

	private class ShelterBedAssignmentSQLMapper implements RowMapper<ShelterBedAssignment> {
		public ShelterBedAssignment mapRow(ResultSet rs, int i) {
			ShelterBedAssignment shelterBedAssignment = new ShelterBedAssignment();
			try {
				shelterBedAssignment.id = rs.getInt("shelter_bed_assignments.id");
				shelterBedAssignment.shelter_id = rs.getInt("shelter_beds.shelter_id");
				shelterBedAssignment.bed_name = rs.getString("shelter_beds.bed_name");
				shelterBedAssignment.shelter_bed_id = rs.getInt("shelter_beds.id");
				shelterBedAssignment.assigned_to_client_id = rs.getInt("shelter_bed_assignments.assigned_to_client_id");
				shelterBedAssignment.assignment_date = rs.getDate("shelter_bed_assignments.assignment_date");
				shelterBedAssignment.assigned_by_id = rs.getInt("shelter_bed_assignments.assigned_by_id");
			} catch (Exception e) {
				LogSystem.LogDBException(e);
				return null;
			}

			return shelterBedAssignment;
		}
	}

}
