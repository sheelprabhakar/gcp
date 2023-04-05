package com.c4c.gcp.gsm.core.service;

import com.c4c.gcp.gsm.core.service.api.SecretReaderService;
import com.c4c.gcp.gsm.core.dto.DbCred;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.secretmanager.v1.AccessSecretVersionResponse;
import com.google.cloud.secretmanager.v1.SecretManagerServiceClient;
import com.google.cloud.secretmanager.v1.SecretVersionName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SecretReaderServiceImpl implements SecretReaderService
{
    private final static Logger LOGGER = LoggerFactory.getLogger(SecretReaderServiceImpl.class);
    private final String GCP_PROJECT_ID = "481630069970";
    private final String DB_SECRETS_ID = "dual-user-poc-db-cred";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public DbCred getActiveDbCred() throws IOException {
        List<DbCred> dbCredList = accessSecretVersion(GCP_PROJECT_ID, DB_SECRETS_ID);
        for (DbCred cred: dbCredList ) {
            if(cred.isActive())
                return cred;
        }
        return null;
    }
    private DbCred getActiveDbCred(String projectId, String secretId) throws IOException {
        List<DbCred> dbCredList = accessSecretVersion(projectId, secretId);
        for (DbCred cred: dbCredList ) {
            if(cred.isActive())
                return cred;
        }
        return null;
    }
    private List<DbCred> accessSecretVersion(String projectId, String secretId) throws IOException {
        return this.accessSecretVersion(projectId, secretId, "latest");
    }
    private List<DbCred> accessSecretVersion(String projectId, String secretId, String versionId) throws IOException {
        try (SecretManagerServiceClient client = SecretManagerServiceClient.create()) {
            SecretVersionName secretVersionName = SecretVersionName.of(projectId, secretId, versionId);

            // Access the secret version.
            AccessSecretVersionResponse response = client.accessSecretVersion(secretVersionName);

            return OBJECT_MAPPER.readValue(response.getPayload().getData().toByteArray(), OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, DbCred.class));
        }
    }

}
