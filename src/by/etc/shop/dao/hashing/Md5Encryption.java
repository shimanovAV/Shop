package by.etc.shop.dao.hashing;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;


public class Md5Encryption {
        private static final String ALGORITHM = "md5";
        private static final String CHARSET_UTF_8 = "utf-8";

            public static String encrypt(String password) {
                StringBuffer sb = new StringBuffer();
               String hashPassword = new String();
                try {
                    final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
                    md.update(password.getBytes(CHARSET_UTF_8));
                    byte[] mdBytes = md.digest();
                    for (int j = 0; j < mdBytes.length; j++) {
                        String s = Integer.toHexString(0xff & mdBytes[j]);
                        s = (s.length() == 1) ? "0" + s : s;
                        sb.append(s);
                    }
                    hashPassword = sb.toString();
                } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {

                } finally {
                    return hashPassword;
                }
            }
    }

