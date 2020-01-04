package org.ironstudios.userssvc.service;

import org.ironstudios.userssvc.cryptography.CryptoEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;

@Service
public class EncryptionService {

    CryptoEngine cryptoEngine;

    @Autowired
    public EncryptionService(CryptoEngine cryptoEngine) {
        this.cryptoEngine = cryptoEngine;
    }

    public String getPasswordHash(String plainPassword){
        return cryptoEngine.encrypt(plainPassword);
    }

}
