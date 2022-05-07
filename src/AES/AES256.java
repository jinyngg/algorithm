package AES;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES256 {

    /* PKCS#5와 PKCS#7 */
    public static final String PADDING = "AES/CBC/PKCS5Padding";

    /* 256비트(32바이트)의 키 */
    private static final String KEY = "aes256encrypt123aes256encrypt123";

    /* initialization vector */
    private static byte[] getIv() {
        return new byte[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public static String encrypt(String plainText) throws Exception {
        /* 32byte IV initialization */
        byte[] iv = getIv();
        /* KEY String -> byte */
        byte[] keyData = KEY.getBytes();

        /* keyData를 key로 지정, AES algorithm사용 */
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        /* CBC PKCS5Padding 방식 사용 */
        Cipher cipher = Cipher.getInstance(PADDING);
        /* 랜덤 IV 생성 및 암호화 방식 사용 */
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(iv));

        /* 암호화 */
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText) throws Exception {
        // IOS url-encode 대응
        cipherText = cipherText.replace(" ", "+");

        /* 32byte IV initialization */
        byte[] iv = getIv();
        /* KEY String -> byte */
        byte[] keyData = KEY.getBytes();

        /* keyData를 key로 지정, AES algorithm사용 */
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        /* CBC PKCS5Padding 방식 사용 */
        Cipher cipher = Cipher.getInstance(PADDING);
        /* 랜덤 IV 생성 및 복호화 방식 사용 */
        cipher.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(iv));

        /* 복호화 */
        byte[] decodedBytes = Base64.getDecoder().decode(cipherText.getBytes());
        byte[] decrypted = cipher.doFinal(decodedBytes);

        return new String(decrypted, "UTF-8");
    }

}
