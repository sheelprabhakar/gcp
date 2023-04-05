package com.c4c.gcp.gsm.rest.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResource {
    private UUID id;
    private String name;
    private String email;
    private boolean enabled;
    private String password;
}
