package com.vict.vict_new.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.UUID;

@Component
public class PasswordUtil {

    //비밀번호를 해싱해서 반환
    public static String hashPassword(String plainPassword){
        //gensalt(10) -> 10은 salt 라운드 수, 높을수록 보안은 올라가고 속도는 낮아짐
        //BCrypt가 내부적으로 Salt를 자동 생성하여 해시에 포함
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(10));
    }

    //로그인시 비밀번호 검증
    public static boolean checkPassword(String plainPassword, String hashedPassword){
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    // ================= User Key (Short UUID) =================
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static String generateUserKey() {
        UUID uuid = UUID.randomUUID();
        return toBase62(uuid);
    }

    private static String toBase62(UUID uuid) {
        BigInteger bigInt = new BigInteger(1, asBytes(uuid));
        return encodeBase62(bigInt);
    }

    private static byte[] asBytes(UUID uuid) {
        byte[] bytes = new byte[16];
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) ((msb >> (8 * (7 - i))) & 0xFF);
        }
        for (int i = 8; i < 16; i++) {
            bytes[i] = (byte) ((lsb >> (8 * (15 - i))) & 0xFF);
        }
        return bytes;
    }

    private static String encodeBase62(BigInteger value) {
        StringBuilder sb = new StringBuilder();
        BigInteger base = BigInteger.valueOf(62);
        while (value.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = value.divideAndRemainder(base);
            sb.append(BASE62.charAt(divmod[1].intValue()));
            value = divmod[0];
        }
        return sb.reverse().toString();
    }
}
