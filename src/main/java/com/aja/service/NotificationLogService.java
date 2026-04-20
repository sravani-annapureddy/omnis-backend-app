package com.aja.service;

import java.util.List;

import com.aja.dto.NotificationLogRequestDto;
import com.aja.entity.NotificationLog;

public interface NotificationLogService {
	
	NotificationLog createLog(NotificationLogRequestDto dto);
	List<NotificationLog> getAllLogs();

}
