package com.c4c.gcp.gsm.core.service.api;

import com.c4c.gcp.gsm.core.dto.DbCred;

import java.io.IOException;

public interface SecretReaderService {
    DbCred getActiveDbCred() throws IOException;
}
