package com.c4c.gcp.gsm.core.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DbCred{
    private String userName;
    private String password;
    private boolean active;
}