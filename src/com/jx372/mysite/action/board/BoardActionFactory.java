package com.jx372.mysite.action.board;

import com.jx372.mysite.action.gusetbook.DeleteFormAction;
import com.jx372.web.action.Action;
import com.jx372.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		// TODO Auto-generated method stub
		
		Action action = null;
		
		if("writeForm".equals(actionName)){
			
			action = new WriteFormAction();
			
		}else if("write".equals(actionName)){
		
			action = new WriteAction();
		
		}else if("view".equals(actionName)){
		
			action = new ViewAction();
		
		}
		else if("modify".equals(actionName)){
			
			action = new ModifyAction();
		
		}else if("modifyForm".equals(actionName)){
			
			action = new ModifyFormAction();
		
		}
		else if("delete".equals(actionName)){
			
			action = new DeleteAction();
		
		}
		else if("replyForm".equals(actionName)){
			
			action = new replyFormAction();
		
		}
		else{
			
			action = new ListAction();
			
			
		}
		
		return action;
	}

}
