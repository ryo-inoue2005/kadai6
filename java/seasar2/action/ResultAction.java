package seasar2.action;

import org.seasar.struts.annotation.Execute;

public class ResultAction {
	
	@Execute(validator = false)
	public String index() {
		return "resultView.jsp";
	}
}
