package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDao extends JpaRepository<Message,Integer> {
    List<Message> findMessagesByLanguageIdEquals(int languageId);
}
