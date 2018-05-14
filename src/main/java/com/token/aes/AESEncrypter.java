package com.token.aes;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;  
import java.security.NoSuchAlgorithmException;  
import java.security.SecureRandom;  
import java.security.spec.AlgorithmParameterSpec;  

import javax.crypto.BadPaddingException;  
import javax.crypto.Cipher;  
import javax.crypto.IllegalBlockSizeException;  
import javax.crypto.KeyGenerator;  
import javax.crypto.NoSuchPaddingException;  
import javax.crypto.SecretKey;  
import javax.crypto.spec.IvParameterSpec; 

public class AESEncrypter {
	public static byte[] iv = new byte[] { 82, 22, 50, 44, -16, 124, -40, -114, -87, -40, 37, 23, -56, 23, -33, 75 };  
	  
    private static AESEncrypter aes = null;  
  
    public static byte[] key1 = new byte[] { -42, 35, 67, -86, 19, 29, -11, 84, 94, 111, 75, -104, 71, 46, 86, -21, -119, 110, -11, -32, -28, 91, -33, -46, 99, 49, 2, 66, -101, -11, -8, 56 };  
  
    private AESEncrypter() {  
  
    }  
  
    public static synchronized AESEncrypter getInstance() {  
        if (aes == null) {  
            aes = new AESEncrypter();  
        }  
        return aes;  
    }  
  
    public String encrypt(String msg) {  
  
        String str = "";  
        try {  
            KeyGenerator kgen = KeyGenerator.getInstance("AES");  
            kgen.init(128, new SecureRandom(key1));  
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);  
            SecretKey key = kgen.generateKey();  
            Cipher ecipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);  
            str = asHex(ecipher.doFinal(msg.getBytes()));  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (InvalidAlgorithmParameterException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        }  
        return str;  
    }  
  
    public String decrypt(String value) {  
        try {  
            KeyGenerator kgen = KeyGenerator.getInstance("AES");  
            kgen.init(128, new SecureRandom(key1));  
            AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);  
            SecretKey key = kgen.generateKey();  
            Cipher dcipher = Cipher.getInstance("AES/CBC/PKCS5Padding");  
            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);  
            return new String(dcipher.doFinal(asBin(value)));  
        } catch (BadPaddingException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (NoSuchPaddingException e) {  
            e.printStackTrace();  
        } catch (InvalidKeyException e) {  
            e.printStackTrace();  
        } catch (InvalidAlgorithmParameterException e) {  
            e.printStackTrace();  
        } catch (IllegalBlockSizeException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
  
    private String asHex(byte buf[]) {  
        StringBuffer strbuf = new StringBuffer(buf.length * 2);  
        int i;  
  
        for (i = 0; i < buf.length; i++) {  
            if (((int) buf[i] & 0xff) < 0x10)  
                strbuf.append("0");  
  
            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));  
        }  
  
        return strbuf.toString();  
    }  
  
    private byte[] asBin(String src) {  
        if (src.length() < 1)  
            return null;  
        byte[] encrypted = new byte[src.length() / 2];  
        for (int i = 0; i < src.length() / 2; i++) {  
            int high = Integer.parseInt(src.substring(i * 2, i * 2 + 1), 16);  
            int low = Integer.parseInt(src.substring(i * 2 + 1, i * 2 + 2), 16);  
  
            encrypted[i] = (byte) (high * 16 + low);  
        }  
        return encrypted;  
  
  
    }  
  
    public static void main(String args[]) {  
        String str = "123746387741252914302553849925547344812365093321993912528797776491942901535114435792002756791270455273837238892088158834215105741660407871703115188149797964521586509194716309158851998168269013735186994595288332337615396417230079616377277931034958854889462563105782359442084378606613313538085549845377663997159;107675992299505450219226327812598423566381308448357795685906007327609379479399969719009860212994018378104618537012975047377063643803140505237375020514066518894643932667190310524390554537701091533925122261010241437411067276539517889195847490380506252492196670159317869627433372284986596073074365520457057898513;0D89E6CFB6BB8EC05738DD2EA2C8278598173449CD0052F1FE94D332D232F82AA4D2A308DC4F840C630138668C664F2D0C559911922C9A06203972C66D4BAECD78C87DF97B76ED80920B61D73DB4569EA1D9165BC208090D57CF066C767064325C3993EAFCD0AB7669B50BC5275740A9B407C2C80C712A3CDC838D7365C79C86";  
        System.out.println(str);  
        str = AESEncrypter.getInstance().encrypt(str);  
        System.out.println(str.length());  
        System.out.println(AESEncrypter.getInstance().decrypt("02e3d2b45e3d1aa6c04ca3ef621f3ba88c9f87b96cb38713c5b8dd5a5ba2045659ea2abfbdab2802f5b0735f1243a8303b4906393a3b8643226447e7362ea04a60277e8f3b3982f1a71a06b46ddd62f059ba451e8d644516ae04c908ad8363532f3eaa9a62410268b73f857003e08739478ee0df70b23c30ea84b0f325a65a1f6c2f7f5f330f905fe9497a32e0ed5b18f74202afd29f3de78d6467cd86a379a43466022e7095bd582ba6670eb41cfe6cf248bd6cae1b1c8b060d932e92977682e31b9b8a4442ca7048c8bd2ae7c1171b598123494737627c113cb80769a46b8c0dde1748ff8f05fef96ab7e28b678f8682f8c73329f6884382e93ecd9fca0c427215524f2487a7602bbf807b7a36b489bcd2014d474adbc205d60cbcd45af1ed78641118835251c5713d4996e2a025c96d7dafda00f4160d49506599f069fa4f8a2c6154ca6c39b189bd372c938ed34fc3869f48a023923f4d3688d597413248bcd4e0d795dc637b39c4c7e1f5e73cf4cd413925f65e659e4259608468af54651a0ec1be2848422abb0865493ae4791a592197fb598ea246b02b03493bdf9eae6c382d76717f8a1fe0f574b743740a8f6a67f078b3c233c0d59f2950ffb76613e5052f6b359b1f98091ab62036da365f425e7b6d8db83a97a60e99f62aec44f440b513ba54128a1e231f049d4012f6382983629386795a944d7afab21bc8739f01df743bc7a4444ef262e82f890aafe70457036c11fff8a9af363f82fcaa0acc8bff590631f0675464b11a54934002d611533063ed01cea971242822a8e1681cf6587a7618ee82c2161f6719610c2c5b10994e7bdc3aabc3af4699cc8771573e0e12edd3d1c8172f72af205b2f167851e02df480a4409f270f7cc7ec1dcb0a3bdde1058af3cc152424ed0821f2f2cfd7a561e6511eb2ca8db3f454934747ccea0946bcb08ea7460586cee5d9b96658f45a069885b5beb9257b8e8993748bfc40235d354a1d5196a87b9c2e167e120531635e02d15a01185ca3be0732faf7737b845d2e0197491b646c01180e1e41fdd971a1d709efbfb9c2bfe4061ecf872245d6f9100a30e5ccef191515aa897fb76f040936c993125f5fe88726054b0e612efe5d18634b7d69d1727054120c3b8e127708a93a239c6ae8756e3f1b08b0bd3216cee8a6f964f9f4930b626f8a4553639f2de1826657f913d35abfb485a8c749a682806661c8b79bb8fe21788d57a40a"));  
    }  
}
