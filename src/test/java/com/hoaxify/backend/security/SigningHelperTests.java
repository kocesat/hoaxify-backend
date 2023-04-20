package com.hoaxify.backend.security;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.DigestInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.crypto.Cipher;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Base64;

class SigningHelperTests {

  @Test
  void testSignAndVerify() {
    try {
      // loading private key from our keystore
      InputStream is = new ClassPathResource("store/sender_keystore.jks").getInputStream();
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(is, "changeit".toCharArray());
      PrivateKey privateKey = (PrivateKey)keyStore.getKey("senderKeyPair", "changeit".toCharArray());
      // loading the document to be signed
      Resource resource = new ClassPathResource("example.txt");
      byte[] message = resource.getInputStream().readAllBytes();
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initSign(privateKey);
      signature.update(message);
      byte[] signatureBytes = signature.sign();
      String encodedSignature = Base64.getEncoder().encodeToString(signatureBytes);
      Assertions.assertTrue(verify(message, encodedSignature));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testSignAndVerify_byLowLevelApi() {
    try {
      // loading private key from our keystore
      InputStream is = new ClassPathResource("store/sender_keystore.jks").getInputStream();
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(is, "changeit".toCharArray());
      PrivateKey privateKey = (PrivateKey)keyStore.getKey("senderKeyPair", "changeit".toCharArray());
      // loading the document to be signed
      Resource resource = new ClassPathResource("example.txt");
      byte[] message = resource.getInputStream().readAllBytes();
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] hash = messageDigest.digest(message);
      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE, privateKey);
      byte[] signatureBytes = cipher.doFinal(hash);
      String encodedSignature = Base64.getEncoder().encodeToString(signatureBytes);
      Assertions.assertTrue(verifyByLowLevelApi(message, encodedSignature));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean verify(byte[] content, String encodedSignature) {
    try {
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(new ClassPathResource("store/receiver_keystore.jks").getInputStream(), "changeit".toCharArray());
      Certificate certificate = keyStore.getCertificate("receiverKeyPair");
      PublicKey publicKey = certificate.getPublicKey();
      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initVerify(publicKey);
      signature.update(content);
      return signature.verify(Base64.getDecoder().decode(encodedSignature));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private boolean verifyByLowLevelApi(byte[] content, String encodedSignature) {
    try {
      KeyStore keyStore = KeyStore.getInstance("JKS");
      keyStore.load(new ClassPathResource("store/receiver_keystore.jks").getInputStream(), "changeit".toCharArray());
      Certificate certificate = keyStore.getCertificate("receiverKeyPair");
      PublicKey publicKey = certificate.getPublicKey();
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] contentHash = md.digest(content);
      var cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.DECRYPT_MODE, publicKey);
      byte[] readHash = cipher.doFinal(Base64.getDecoder().decode(encodedSignature));
      return Base64.getEncoder().encodeToString(contentHash).equals(
        Base64.getEncoder().encodeToString(readHash)
      );
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
