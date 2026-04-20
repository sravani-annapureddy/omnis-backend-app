package com.aja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aja.entity.NotificationLog;

public interface NotificationLogRepository extends JpaRepository<NotificationLog, Long> {

}
