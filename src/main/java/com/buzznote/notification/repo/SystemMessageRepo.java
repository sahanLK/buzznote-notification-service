package com.buzznote.notification.repo;

import com.buzznote.notification.model.SystemMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemMessageRepo extends JpaRepository<SystemMessage, Integer> {
}
