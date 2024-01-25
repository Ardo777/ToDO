package com.example.mytodoproject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    private int id;
    private String title;
    private Date createdDate;
    private Date finishDate;
    private Status status;
    private User user;

}
