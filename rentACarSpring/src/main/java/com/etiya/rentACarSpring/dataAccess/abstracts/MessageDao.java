package com.etiya.rentACarSpring.dataAccess.abstracts;

import com.etiya.rentACarSpring.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MessageDao extends JpaRepository<Message,Integer> {

    boolean existsByLanguage_LanguageId(int languageId);

    @Query(value = "select m.message_content from messages as m inner join message_keys as mk on m.message_key_id=mk.message_key_id where m.language_id = ?1 and mk.message_key = ?2",nativeQuery = true)
    String getMessageByLanguageIdAndKey(int languageId,String messageKey);

    @Query(value = "select l.language_id from languages l where l.language_id=?1",nativeQuery = true)
    List<Integer> getLanguageById(int id);

    @Query(value = "select mk.message_key_id from message_keys mk where mk.message_key_id=?1",nativeQuery = true)
    List<Integer> getMessageKeyById(int id);

}
