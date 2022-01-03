package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LanguageDao extends JpaRepository<Language,Integer> {
   List<Language> getByMessagesIsNotNull();
}
