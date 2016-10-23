package svc.managers;

import org.springframework.stereotype.Component;
import svc.data.clients.ClientDAO;
import svc.models.Client;
import svc.models.ClientNeed;

import java.util.List;

import javax.inject.Inject;

@Component
public class ClientManager {
	
	@Inject
	private ClientDAO clientDAO;
	
	public Client saveClient(Client client)
	{
		client = clientDAO.saveClient(client);
		return client;
	}
	
	public List<Client> getAllClients() {
		return clientDAO.getAllClients();
	}

    public Client GetClientById(int id) {
        return clientDAO.getClientById(id);
    }
    
    public void CreateNeed(ClientNeed client_need) {
    	clientDAO.CreateNeed(client_need);;
    }
    
    public void DeleteNeed(Integer need_id) {
    	clientDAO.DeleteNeed(need_id);;
    }

	public int getSpdatScore(Client client) {
		int score = 0;
		if(client.total_months_homeless >= 24 || client.times_house_then_homeless >= 4)
		{
			score += 1;
		}
		
		int interactions = client.times_er + client.police_interactions + client.times_ambulance
				+ client.times_used_crisis_service + client.times_hospitalized;
		if(interactions >= 4){
			score += 1;
		}
		
		if(client.attacked_since_homeless || client.threatened_harm) {
			score += 1;
		}
		
		if(client.pending_legal_action){
			score += 1;
		}
		
		if(client.is_forced_or_tricked || client.risky_activity || !client.is_primary_sleeping_location_shelter){
			score += 1;
		}
		
		if(client.owe_money || !client.has_regular_income || !client.money_covers_expenses){
			score += 1;
		}
		
		if(!client.has_planned_activities){
			score += 1;
		}
		
		if(client.has_disliked_friends || client.friends_negatively_influence){
			score += 1;
		}
		
		if(client.has_poor_hygiene){
			score += 1;
		}
		
		if(!client.has_regular_care){
			score += 1;
		}

		boolean has_other_medical_condition = false;
		
		if(client.has_kidney_disease){
			score += 1;
			has_other_medical_condition = true;
		}
		
		if(client.frostbite_history){
			score += 1;
			has_other_medical_condition = true;
		}
		
		if(client.has_liver_disease){
			score += 1;
			has_other_medical_condition = true;
		}
		
		if(client.aids){
			score += 1;
			has_other_medical_condition = true;
		}
		
		if(client.heatstroke_history || client.heart_disease || client.emphysema || client.diabetes ||
				client.asthma || client.cancer || client.hep_c || client.tuberculosis || client.signs_of_health_condition){
			has_other_medical_condition = true;
		}
		
		boolean has_substance_abuse_problem = false;
		if(client.problematic_drug_use || client.daily_alcohol_consumption || client.used_injection_drugs
				|| client.returned_to_drugs || client.non_beverage_alcohol || client.blacked_out || client.signs_of_drug_alcohol_abuse){
			has_substance_abuse_problem = true;
			score += 1;
		}
		
		boolean has_mental_health_problem = false;
		if(client.been_committed || client.er_for_mental_health || client.spoken_with_psychiatrist ||
				client.head_trauma || client.learning_disability || client.problems_concentrating || client.signs_of_mental_illness){
			has_mental_health_problem = true;
			score += 1;
		}
		
		if(has_mental_health_problem && has_other_medical_condition && has_substance_abuse_problem)
		{
			score += 1;
		}
		
		if(client.not_taking_medicine){
			score += 1;
		}
		
		if(client.homelessness_cause_by_abuse)
		{
			score += 1;
		}
		
		return score;
		
	}
}
