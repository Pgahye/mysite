package com.jx372.mysite.action.gusetbook;


import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory {
	
	
	public Action getAction(String actionName){
		
		
		Action action =null;

		if("deleteform".equals(actionName)){
			
			action = new DeleteFormAction();
			
			
		}else if("delete".equals(actionName)){
			
			
			action = new DeleteAction();
			
		}else if("add".equals(actionName)){
		
			
			action = new AddAction();
		}
		else{
			
			action = new ListAction();
			
		}
		return action;
	}

}
