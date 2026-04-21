package com.aja.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aja.dto.NotificationLogRequestDto;
import com.aja.entity.NotificationLog;
import com.aja.repository.NotificationLogRepository;
import com.aja.service.NotificationLogService;

@Service
public class NotificationLogServiceImplementation implements NotificationLogService {
	
	private NotificationLogRepository NotificationLogRepo;
	private SmsServiceImplementation smsServiceImpl;

	public NotificationLogServiceImplementation(NotificationLogRepository notificationLogRepo,
			SmsServiceImplementation smsServiceImpl) {
		super();
		NotificationLogRepo = notificationLogRepo;
		smsServiceImpl = smsServiceImpl;
	}

	@Override
	public NotificationLog createLog(NotificationLogRequestDto dto) {
		NotificationLog log=new NotificationLog();
		log.setUserId(dto.getUserId());
		log.setType(dto.getType());
		log.setMessage(dto.getMessage());
		log.setSentAt(LocalDateTime.now());
		
		try {
			if("SMS".equalsIgnoreCase(dto.getType())) {
				String phone="+918106893303";
				smsServiceImpl.sendSms(phone,dto.getMessage());
			}
			log.setStatus("SENT");
		}catch(Exception e) {
			log.setStatus("FAILED");
		}
		return NotificationLogRepo.save(log);
	}

	@Override
	public List<NotificationLog> getAllLogs() {
		
		return NotificationLogRepo.findAll();
	}

}
