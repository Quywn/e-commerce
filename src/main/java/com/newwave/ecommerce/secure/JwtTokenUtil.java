package com.newwave.ecommerce.secure;




import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;


@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private String expiration;

    public String generateToken(String username) throws JOSEException {
        JWSSigner signer = new MACSigner(secretKey);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issueTime(new Date())
                .expirationTime(new Date(System.currentTimeMillis() + expiration))
                .build();

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public Boolean validateToken(String token, UserDetails userDetails) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(secretKey);

        if (signedJWT.verify(verifier)) {
            String username = signedJWT.getJWTClaimsSet().getSubject();
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return username.equals(userDetails.getUsername()) && expiration.after(new Date());
        }

        return false;
    }

    public String getUsernameFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public Date getExpirationDateFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getExpirationTime();
    }
}
