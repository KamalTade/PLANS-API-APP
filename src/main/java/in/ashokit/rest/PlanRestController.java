package in.ashokit.rest;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.ashokit.entity.Plan;
import in.ashokit.service.PlanService;
import io.swagger.models.Response;


@RestController
public class PlanRestController {
	
	@Autowired
	private PlanService planservice;
	
    @GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> plancategies()
	{
		Map<Integer,String> categories= planservice.getPlanCategory();
		
		return new  ResponseEntity<>(categories,HttpStatus.OK);
	}

    @PostMapping("/plan")
    public ResponseEntity<String> savePlan(@RequestBody Plan plan)
    {
    	String responseMsg="--";
    	boolean isSaved = planservice.savePlan(plan);
    	
    	if(isSaved)
    	{
    		responseMsg="Plan saved";
    	}
    	else
    	{
    		responseMsg="plan not saved";
    	}
    	
    	return new ResponseEntity<String>(responseMsg,HttpStatus.CREATED);
    }
    
    @GetMapping("/plans")
    public ResponseEntity<List<Plan>> plans(){
    	List<Plan> allplans=planservice.getAllPlan();
    	
    return new ResponseEntity<>(allplans,HttpStatus.OK);

    }
    
    @GetMapping("plan/{planId}")
    public ResponseEntity<Plan> editPlan(@PathVariable Integer planId)
    {
    	Plan plan=planservice.getPlanById(planId);
    	return new ResponseEntity<>(plan,HttpStatus.OK);
    }
    
    
    @PutMapping("/plan")
    public ResponseEntity<String> updatePlan(@RequestBody Plan plan)
    {
    	boolean isUpdated = planservice.updatePlan(plan);
    	
    	String msg="";
    	
    	if(isUpdated)
    	{
    		msg="plan updated";
    	}
    	else
    	{
    		msg="plan not updated";
    	}
    	
    	return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
    
    
    @DeleteMapping("plan/{planId}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer planId)
    {
    	boolean isDeleted = planservice.deletePlanById(planId);
    	
    	String msg="";
    	
    	if(isDeleted)
    	{
    		msg="plan deleted";
    	}
    	else
    	{
    		msg="plan not deleted";
    	}
    	return new ResponseEntity<>(msg,HttpStatus.OK);
    }
    
    @PutMapping("/status-change/{planId}/{status}")
    public ResponseEntity<String> statuschange(@PathVariable Integer planId,
    		                                   @PathVariable String status){
    	String msg="";
    	
		boolean isstatuschange= planservice.changePlanStatus(planId, status);
		
		if(isstatuschange)
		{
			msg="sttaus changed";
		}
		else
		{
			msg="status not changed";
		}
		return new ResponseEntity<String>(msg,HttpStatus.OK);
    }
}







