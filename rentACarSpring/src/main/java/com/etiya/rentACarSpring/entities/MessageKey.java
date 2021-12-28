package com.etiya.rentACarSpring.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="message_keys")
public class MessageKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="message_key_id")
    private int messageKeyId;

    @Column(name="message_key")
    private String messageKey;

    @OneToMany(mappedBy = "messageKey")
    List<Message> messages;
}
