package com.aja.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aja.dto.NotificationLogRequestDto;
import com.aja.entity.NotificationLog;
import com.aja.service.NotificationLogService;

@RestController
@RequestMapping("/notificationlog")
public class NotificationLogController {
	
	private NotificationLogService service;
	
	@PostMapping("/create")
	public ResponseEntity<NotificationLog> create(@RequestBody NotificationLogRequestDto  dto){
		return ResponseEntity.ok(service.createLog(dto));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<NotificationLog>> getAll(){
		return ResponseEntity.ok(service.getAllLogs());
		
	}

}
