package in.ashokit.service;
import java.util.List;
import java.util.Map;
import in.ashokit.entity.Plan;

public interface PlanService {
	
	public Map<Integer,String> getPlanCategory();
	
	public boolean savePlan(Plan plan);
	
	public List<Plan> getAllPlan();
	
	public Plan getPlanById(Integer planId);
	
	public boolean updatePlan(Plan plan);
	
	public boolean deletePlanById(Integer planID);
	
	public boolean changePlanStatus(Integer planId, String status);	
}
