package com.example.auth;

import io.jsonwebtoken.Jwts;
import jakarta.xml.bind.DatatypeConverter;
import org.junit.jupiter.api.Test;

public class JwtSecretKeyGeneratorTest {

    @Test
    public void generateKey() {
        byte[] encoded = Jwts.SIG.HS512.key().build().getEncoded();
        String secretKey = DatatypeConverter.printHexBinary(encoded);
        System.out.printf("Secret key generated: [%s]", secretKey);
    }

}
