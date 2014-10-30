/**
 * 
 */
package de.tum.in.dss;

import java.util.Map;
import java.util.TreeMap;


public class UserAuthentication {
	
	private Map<String,User> userMap;

	/**
	 * 
	 */
	public UserAuthentication() {
		if(userMap==null){
			userMap = new TreeMap<String, User>();
		}
	}

	/**
	 * @param userMap
	 */
	public UserAuthentication(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	public void addUser(User user) {
		userMap.put(user.getUserName(), user);
	}

	public void deleteUser(User user) {
		userMap.remove(user.getUserName());
	}

	public void updateUser(User user) {
		userMap.put(user.getUserName(), user);
	}

	public User getUser(User user) {
		return userMap.get(user.getUserName());

	}

	/**
	 * @return the userMap
	 */
	public Map<String, User> getUserMap() {
		return userMap;
	}

	/**
	 * @param userMap the userMap to set
	 */
	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}
	
}
