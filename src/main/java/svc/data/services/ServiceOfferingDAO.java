package svc.data.services;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import svc.data.jdbc.BaseJdbcDao;
import svc.logging.LogSystem;
import svc.models.ServiceOffering;

@Repository
public class ServiceOfferingDAO extends BaseJdbcDao {
	private SimpleJdbcInsert serviceInsert;
	private SimpleJdbcInsert serviceNeedMatchInsert;
	
    @Override
	protected void createSimpleJdbcInserts(DataSource dataSource) {
    	serviceInsert = new SimpleJdbcInsert(dataSource)
    			.withTableName("service_offering")
    			.usingGeneratedKeyColumns("id")
    			.usingColumns("max_availability",
    					"organization_id",
    					"service");
    	
    	serviceNeedMatchInsert = new SimpleJdbcInsert(dataSource)
    			.withTableName("service_need_match")
    			.usingColumns("client_need_id",
    					"service_offering_id");
    }
    
	public void Assign(int service_id, int client_id)
	{
		try {
			final SqlParameterSource parameterSource = new MapSqlParameterSource()
					.addValue("service_offering_id", service_id)
					.addValue("client_need_id", client_id);

			serviceNeedMatchInsert.execute(parameterSource);
		} catch (Exception e) {
			LogSystem.LogDBException(e);
		}
	}
	
	public void Unassign(int service_id, int client_id)
	{
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("service_id", service_id);
			parameterMap.put("client_id", client_id);
			String sql = "DELETE FROM service_need_match WHERE service_offering_id = :service_id AND client_need_id = :client_id";
			jdbcTemplate.update(sql, parameterMap);
		} catch (Exception e) {
			LogSystem.LogDBException(e);
		}
	}
	
	public void Update(ServiceOffering serviceOffering)
	{
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("id", serviceOffering.id);
			parameterMap.put("max_availability", serviceOffering.max_availability);
			parameterMap.put("organization_id", serviceOffering.organization_id);
			parameterMap.put("service", serviceOffering.service);
			String sql = "UPDATE service_offering SET max_availability = :max_availability, organization_id = :organization_id, service = :service WHERE id = :id";
			jdbcTemplate.update(sql, parameterMap);
		} catch (Exception e) {
			LogSystem.LogDBException(e);
		}
	}
    
	public void Create(ServiceOffering serviceOffering)
	{
		MapSqlParameterSource parameterSource = new MapSqlParameterSource()
		        .addValue("max_availability", serviceOffering.max_availability)
		        .addValue("organization_id", serviceOffering.organization_id)
		        .addValue("service", serviceOffering.service);
		Number key = serviceInsert.executeAndReturnKey(parameterSource);
		serviceOffering.id = key.intValue();
	}
	
	public void Delete(int service_id)
	{
		try {
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("service_id", service_id);
			String sql = "DELETE FROM service_offering WHERE id = :service_id";
			jdbcTemplate.update(sql, parameterMap);
		} catch (Exception e) {
			LogSystem.LogDBException(e);
		}
	}
}
