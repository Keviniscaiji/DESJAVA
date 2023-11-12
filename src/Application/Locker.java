package Application;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.security.SecureRandom;

public class Locker {
    Key key;
    public Locker(String str) {
        // getKey(str);//生成密匙 ,该方法每次的key不一致
        //生成密匙 ，该方法指定了生成key值javax.crypto.spec.SecretKeySpec@30
        byte[] bys = new byte[] { 7, 16, -15, -3, 4, -42, 14, 64};
        this.key = new SecretKeySpec(bys, "DES");
    }
    /**
     * 根据参数生成KEY
     */
    public void getKey(String strKey) {
        try {
            KeyGenerator _generator = KeyGenerator.getInstance("DES");
            _generator.init(new SecureRandom(strKey.getBytes()));
            this.key = _generator.generateKey();
            // byte[] bys = key.getEncoded();
            _generator = null;
        } catch (Exception e) {
            throw new RuntimeException("Error initializing SqlMap class. Cause: " + e);
        }
    }


    /**
     * 文件file进行加密并保存目标文件destFile中
     *
     * @param file   要加密的文件 如mnt/sdcard/PateokeyNormal.txt
     * @param destFile 加密后存放的文件名 如mnt/sdcard/Pateokey.txt
     */
    public void encrypt(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        // cipher.init(Cipher.ENCRYPT_MODE, getKey());
        cipher.init(Cipher.ENCRYPT_MODE, this.key);
        InputStream is = new FileInputStream(file);
        OutputStream out = new FileOutputStream(destFile);
        CipherInputStream cis = new CipherInputStream(is, cipher);
        byte[] buffer = new byte[1024];
        int r;
        while ((r = cis.read(buffer)) > 0) {
            out.write(buffer, 0, r);
        }
//        System.out.println("KEY加密="+key);
        cis.close();
        is.close();
        out.close();
    }
    /**
     * 文件采用DES算法解密文件
     *
     * @param file 已加密的文件 如auth/Pateokey.txt
     *
     */
    public String decrypt(String file, String destFile) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, this.key);

        InputStream is = new FileInputStream(file);
        String PateoMessage = "";
        OutputStream out = new FileOutputStream(destFile);

//  start
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        CipherOutputStream cos = new CipherOutputStream(baos, cipher);
//  end
    CipherOutputStream cos = new CipherOutputStream(out, cipher);


        byte[] buffer = new byte[1024];
        int r;
        while ((r = is.read(buffer)) >= 0) {
            cos.write(buffer, 0, r);
        }

        cos.close();
        out.close();
        is.close();
        PateoMessage=baos.toString();
        baos.close();
        return PateoMessage;
    }

    public static void main(String[] args) {

    }
}

