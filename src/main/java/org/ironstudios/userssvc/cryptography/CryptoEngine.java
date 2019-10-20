package org.ironstudios.userssvc.cryptography;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.proto.KeyTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

/**
 * @author  Arjun Vijayvargiya
 * @version 1.0
 * Crypto Engine is an engine which provides encryption service to the user-svc application
 */
@Component
public class CryptoEngine {

    Aead aead;
    String SECRET_KEY = "IRONBAR65478658562024710741074832657213583698563056";

    public CryptoEngine() throws GeneralSecurityException {

        // Initialize Aead through registration
        AeadConfig.register();
        // Get the KeyTemplate [AES256_EAX]
        KeyTemplate keyTemplate = AeadKeyTemplates.AES256_EAX;

        // Generate KeySetHandle
        KeysetHandle keysetHandle = KeysetHandle.generateNew(keyTemplate);

        // Obtain the primitive Aead
        aead = AeadFactory.getPrimitive(keysetHandle);
    }

    public String encrypt(String plainText) throws GeneralSecurityException {

        System.out.println("Before Encryption: " + plainText);

        // Encrypting the plaintext
        byte[] cipherText = aead.encrypt(plainText.getBytes(StandardCharsets.UTF_8), SECRET_KEY.getBytes());
        String outputStr = new String(Base64.getEncoder().encode(cipherText));
        return outputStr;
    }

    public String decrypt(String cipherText) throws GeneralSecurityException {

        // Decrypting the plaintext
        byte[] decrypted = aead.decrypt(Base64.getDecoder().decode(cipherText),SECRET_KEY.getBytes() );
        String decryptedCipherText = new String(decrypted, StandardCharsets.UTF_8);
        return decryptedCipherText;
    }

}
