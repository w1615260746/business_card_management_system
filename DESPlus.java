package cardcard;
import java.security.*;

import javax.crypto.*;

public class DESPlus {
	private static String strDefaultKey = "national";
    private Cipher encryptCipher = null;
    private Cipher decryptCipher = null;
    public static String byteArr2HexStr(byte[] arrB) {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }
    public static byte[] hexStr2ByteArr(String strIn) {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;
        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }
        return arrOut;
    }
    public DESPlus() throws Exception {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DESPlus(String strKey) {
        Key key;
        try {
            key = getKey(strKey.getBytes());
            encryptCipher = Cipher.getInstance("DES");
            encryptCipher.init(Cipher.ENCRYPT_MODE, key);
            decryptCipher = Cipher.getInstance("DES");
            decryptCipher.init(Cipher.DECRYPT_MODE, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加密字节数组
     */
    public byte[] encrypt(byte[] arrB){
        byte[] s = null ;
        try {
            s = encryptCipher.doFinal(arrB);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return s ;
    }

    /**
     * 将启动码strIn加密，返回加密后的启动码
     */
    public String encrypt(String strIn) {
        return byteArr2HexStr(encrypt(strIn.getBytes()));
    }

    /**
     * 解密字节数组
     */
    public byte[] decrypt(byte[] arrB)  {
        byte[] s = null ;
        try {
            s = decryptCipher.doFinal(arrB);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return s ;
    }
    /**
     * 解密 strIn 
     */
    public String decrypt(String strIn){
        return new String(decrypt(hexStr2ByteArr(strIn)));
    }
    /**
     * 从指定字符串生成密钥，密钥所需的字节数组长度为8位 不足8位时后面补0，超出8位只取前8位
     * 
     * @param arrBTmp
     *            构成该字符串的字节数组
     * @return 生成的密钥
     * @throws java.lang.Exception
     */
    private Key getKey(byte[] arrBTmp) {
        // 创建一个空的8位字节数组（默认值为0）
        byte[] arrB = new byte[8];
        // 将原始字节数组转换为8位
        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }
        // 生成密钥
        Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");
        return key;
       }
}
