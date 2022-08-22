package in.ashokit.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.entity.PalnCategory;
import in.ashokit.entity.Plan;
import in.ashokit.repository.PlanCategoryRepo;
import in.ashokit.repository.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planrepo;
	
	@Autowired
	private PlanCategoryRepo plancategoryrepo;
	
	@Override
	public Map<Integer, String> getPlanCategory() {
		
	    List<PalnCategory> categories = plancategoryrepo.findAll();
	    
	    Map<Integer,String> categorymap = new HashMap<>();
	    
	    categories.forEach(category->{
	    	categorymap.put(category.getCategoryId(), category.getCategoryName());
	    });
	    
		return categorymap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		
		Plan saved = planrepo.save(plan);
		
		/*if(saved.getPlanId() != null)
		{
			return true;
		}
		else
		{
		return false;
		}*/
		
		//return saved.getPlanId()!=null ? true : false;
		
		return saved.getPlanId()!=null;
	}

	@Override
	public List<Plan> getAllPlan() {
		
		return  planrepo.findAll();
	}

	@Override
	public Plan getPlanById(Integer planId) {
		
		Optional<Plan> findById = planrepo.findById(planId);
		
		if(findById.isPresent())
		{
			return findById.get();
		}
		else
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
	     planrepo.save(plan);//upsert method
		
		return plan.getPlanId()!=null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		boolean status=false;
		try
		{
		   planrepo.deleteById(planId);
		   status=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public boolean changePlanStatus(Integer planId, String status) {
		
		Optional<Plan> findById = planrepo.findById(planId);
		
		if(findById.isPresent()) {
		 Plan plan=findById.get();
		plan.setActiveSW(status);
		planrepo.save(plan);
		return true;
		}
		return false;
	}
	

}
