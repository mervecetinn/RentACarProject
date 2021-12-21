package com.etiya.rentACarSpring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="message_name")
    private String messageName;

    @Column(name = "message_content")
    private String messageContent;

    @Column(name = "language_id")
    private int languageId;
}
