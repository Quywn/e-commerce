package com.newwave.ecommerce.config;




import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private String secret = "my_secret_key";

//    // generate token for user
//    public String generateToken(UserDetails userDetails) throws JOSEException {
//        JWSSigner signer = new MACSigner(secret);
//
//        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                .subject(userDetails.getUsername())
//                .issueTime(new Date())
//                .expirationTime(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .build();
//
//        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
//        signedJWT.sign(signer);
//
//        return signedJWT.serialize();
//    }

    // validate token
    public Boolean validateToken(String token, UserDetails userDetails) throws JOSEException, ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWSVerifier verifier = new MACVerifier(secret);

        if (signedJWT.verify(verifier)) {
            String username = signedJWT.getJWTClaimsSet().getSubject();
            Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
            return username.equals(userDetails.getUsername()) && expiration.after(new Date());
        }

        return false;
    }

    // lấy username từ token jwt
    public String getUsernameFromToken(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

//    // lấy expiration date từ token jwt
//    public Date getExpirationDateFromToken(String token) throws ParseException {
//        SignedJWT signedJWT = SignedJWT.parse(token);
//        return signedJWT.getJWTClaimsSet().getExpirationTime();
//    }
}
