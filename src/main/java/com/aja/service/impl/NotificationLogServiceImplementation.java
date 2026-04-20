package com.aja.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aja.dto.NotificationLogRequestDto;
import com.aja.entity.NotificationLog;
import com.aja.repository.NotificationLogRepository;
import com.aja.service.NotificationLogService;

@Service
public class NotificationLogServiceImplementation implements NotificationLogService {
	
	private NotificationLogRepository NotificationLogRepo;

	@Override
	public NotificationLog createLog(NotificationLogRequestDto dto) {
		NotificationLog log=new NotificationLog();
		log.setUserId(dto.getUserId());
		log.setType(dto.getType());
		log.setMessage(dto.getMessage());
		log.setStatus(dto.getStatus()==null? "PENDING":dto.getStatus());
		return NotificationLogRepo.save(log);
	}

	@Override
	public List<NotificationLog> getAllLogs() {
		
		return NotificationLogRepo.findAll();
	}

}
