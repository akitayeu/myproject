package com.samsolutions.kitayeu.myproject.services.impl;


import com.samsolutions.kitayeu.myproject.dtos.EmployeeDto;
import com.samsolutions.kitayeu.myproject.services.KeycloakService;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;

@Service
public class KeycloakServiceImpl implements KeycloakService {

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Autowired
    private Keycloak keycloakAccount;

    @Override
    public EmployeeDto createKeycloakUser(EmployeeDto employeeDto) {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(employeeDto.getUserDto().getUserName());
        userRepresentation.setFirstName(employeeDto.getFirstname());
        userRepresentation.setLastName(employeeDto.getLastname());
        userRepresentation.setEmail(employeeDto.getUserDto().getUserMail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        RealmResource realmResource = keycloakAccount.realm(keycloakRealm);
        Response response = realmResource.users().create(userRepresentation);

        String keycloakId = CreatedResponseUtil.getCreatedId(response);
        employeeDto.getUserDto().setKeycloakId(keycloakId);

        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType("password");
        passwordCred.setValue(employeeDto.getUserDto().getUserPassword());

        UserResource userResource = realmResource.users().get(keycloakId);
        userResource.resetPassword(passwordCred);

        return employeeDto;

    }
}