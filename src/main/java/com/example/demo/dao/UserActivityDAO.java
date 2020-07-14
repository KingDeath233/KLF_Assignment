package com.example.demo.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.dto.UserActivityDTO;
import com.example.demo.entities.UserActivity;

public interface UserActivityDAO extends JpaRepository<UserActivity, Integer>{

	@Query("select new com.example.demo.dto.UserActivityDTO((select u1.username from Users as u1 where u1.id = ua.userId),"
			+ "(select a1.name from Activity as a1 where a1.id = ua.activityId),"
			+ "count(ua.activityId)) "
			+ "from UserActivity as ua, Users as u, Activity as a "
			+ "where ua.userId = u.id and ua.activityId = a.id and occurrence BETWEEN '2019-10-01 00:00:00' AND '2020-10-31 23:59:59'"
			+ "group by ua.activityId, ua.userId ")
	public List<UserActivityDTO> findAllFromUADTO();
	
	@Query("select occurrence from UserActivity where occurrence BETWEEN '2019-10-01 00:00:00' AND '2020-10-31 23:59:59' and "
			+ "userId = (select id from Users as u where u.username = ?1) and "
			+ "activityId = (select id from Activity as a where a.name like ?2)")
	public List<Date> findAllOccurrence(String username, String activity);
	
}
