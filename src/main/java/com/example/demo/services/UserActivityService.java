package com.example.demo.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserActivityDAO;
import com.example.demo.dto.UserActivityDTO;
import com.example.demo.dto.UserActivityDTOFull;
import com.example.demo.entities.UserActivity;

@Service
public class UserActivityService extends MainService{

	@Autowired
	UserActivityDAO repo;
	
	@Autowired
	UsersService usersService;
	
	public void save(String username, int type) {
		UserActivity ua = new UserActivity();
		ua.setUserId(usersService.findByUsername(username).getId());
		ua.setActivityId(type);
		Date date=java.util.Calendar.getInstance().getTime();
		ua.setOccurrence(date);
		repo.save(ua);
	}
	
	public Page<UserActivityDTOFull> findAllFromUADTO(Pageable pageable, String key){
		List<UserActivityDTO> before = repo.findAllFromUADTO();
		List<UserActivityDTOFull> after = new ArrayList<UserActivityDTOFull>();
		for(UserActivityDTO u : before) {
			List<Date> o = findAllOccurrence(u.getUser_name(),u.getActivity_name());
			Collections.sort(o);
			UserActivityDTOFull f = new UserActivityDTOFull(u.getUser_name(),u.getActivity_name(),u.getAmount(),o.get(0),o.get(o.size()-1));
			System.out.println(f);
			after.add(f);
		}
		List<UserActivityDTOFull> key_search = new ArrayList<UserActivityDTOFull>();
		if(key.equals("")) {
			key_search = after;
		}
		else {
			for(UserActivityDTOFull e:after) {
				if(e.toString().toLowerCase().contains(key.toLowerCase())) {
					key_search.add(e);
				}
			}
		}
		List<UserActivityDTOFull> list = searchUsingKey(key_search, pageable);
		Page<UserActivityDTOFull> entityPage = new PageImpl<UserActivityDTOFull>(list, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()),key_search.size());

		return entityPage;
	}
	
	public List<Date> findAllOccurrence(String username, String activity) {
		return repo.findAllOccurrence(username, activity);
	}
	
}
