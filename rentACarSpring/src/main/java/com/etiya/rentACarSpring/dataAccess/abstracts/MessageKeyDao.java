package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.MessageKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageKeyDao extends JpaRepository<MessageKey,Integer> {

    List<MessageKey> getByMessagesIsNotNull();
}
