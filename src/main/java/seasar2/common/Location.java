package seasar2.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Location {

	public Location() {
		results = new ArrayList<>();
	}

	private String message;

	private List<Map<String, String>> results;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Map<String, String>> getResults() {
		return results;
	}

	public void setResults(List<Map<String, String>> results) {
		this.results = results;
	}

}
