package Alogtisms;

import static Alogtisms.DESConstants.*;
import static Alogtisms.Des_basic_algorithms.*;
import static Alogtisms.Tools.generate_sub_key;

public class Code_generator {

    public static byte[] generate_encrypted_data(byte[] inputData, String key) {
        /*
         * input: data and key
         * output: data after encryption
         * */
        byte[] filled_key = Tools.fill_byte_data(key.getBytes());
        byte[] filled_data = Tools.fill_byte_data(inputData);
        int data_len = filled_data.length;
        byte[] encrypted_data = new byte[data_len];
        int multiple = data_len / 8;
        for (int i = 0; i < multiple; i++) {
            byte[] tem_key = new byte[8];
            byte[] tem_data = new byte[8];
            System.arraycopy(filled_key, 0, tem_key, 0, 8);
            System.arraycopy(filled_data, i * 8, tem_data, 0, 8);
            byte[] tem_result = encrypt_data(tem_key, tem_data, 1);
            System.arraycopy(tem_result, 0, encrypted_data, i * 8, 8);
        }
        String bitData = "";
        for (int i = 0; i < encrypted_data.length; i++) {
            bitData += String.valueOf(encrypted_data[i]);
        }
        String regEx = "[-]";
        String a = "";
        bitData.replaceAll(regEx, a);
        return encrypted_data;
    }


    public static byte[] generate_decrypted_data(byte[] inputData, String key) {
        /*
         * input: data and key
         * output: data after decryption
         * */
        byte[] filled_key = Tools.fill_byte_data(key.getBytes());
        byte[] filled_data = Tools.fill_byte_data(inputData);
        int data_len = filled_data.length;
        byte[] encrypted_data = new byte[data_len];
        int multiple = data_len / 8;

        for (int i = 0; i < multiple; i++) {
            byte[] tem_key = new byte[8];
            byte[] tem_data = new byte[8];
            System.arraycopy(filled_key, 0, tem_key, 0, 8);
            System.arraycopy(filled_data, i * 8, tem_data, 0, 8);
            byte[] tem_result = decrypt_data(tem_key, tem_data, 0);
            System.arraycopy(tem_result, 0, encrypted_data, i * 8, 8);
        }

        byte[] decrypted_data = null;
        for (byte e : encrypted_data) {
        }
        int total_len = data_len;
        int delete_len = encrypted_data[total_len - 8 - 1];
        if ((delete_len >= 1) && (delete_len <= 8)) {

        } else {
            delete_len = 0;
        }
        decrypted_data = new byte[total_len - delete_len - 8];
        boolean del_flag = true;
        for (int k = 0; k < delete_len; k++) {
            if (delete_len != encrypted_data[total_len - 8 - (k + 1)])
                del_flag = false;
        }
        if (del_flag == true) {
            System.arraycopy(encrypted_data, 0, decrypted_data, 0, total_len - delete_len - 8);
        }
        String bitData = "";
        for (int i = 0; i < decrypted_data.length; i++) {
            bitData += String.valueOf(decrypted_data[i]);
        }
        return decrypted_data;
    }

    private static byte[] encrypt_data(byte[] des_key, byte[] des_data, int flag) {
        // Process data provided whether encrypt or decrypt data
        int[] key = Tools.byte_to_binary(des_key);
        int[] encrypt_data = Tools.byte_to_binary(des_data);
        int[][] sub_key = generate_sub_key(key);
        int i;
        int flags = flag;

        int[] M = new int[64];

        int[] MIP_1 = new int[64];

        for (i = 0; i < 64; i++) {

            M[i] = encrypt_data[IP_FORM[i] - 1];
        }
// change position
        if (flags == 1) {
            for (i = 0; i < 16; i++) {
                f_function(M, i, flags, sub_key);
            }
        }
// change position
        for (i = 0; i < 64; i++) {

            MIP_1[i] = M[IP1_FORM[i] - 1];
        }

        byte[] result = Tools.binary_to_byte(MIP_1);
        return result;

    }
    
    private static byte[] decrypt_data(byte[] des_key, byte[] des_data, int flag) {
        // Process data provided whether encrypt or decrypt data
        int[] key = Tools.byte_to_binary(des_key);
        int[] decrypt_data = Tools.byte_to_binary(des_data);
        int[][] sub_key = generate_sub_key(key);
        int i;
        int flags = flag;

        int[] M = new int[64];

        int[] MIP_1 = new int[64];

        for (i = 0; i < 64; i++) {
        	// change position
            M[i] = decrypt_data[IP_FORM[i] - 1];
        }

        if (flags == 0) {
            for (i = 15; i > -1; i--) {
                f_function(M, i, flags, sub_key);
            }
        }

        for (i = 0; i < 64; i++) {

            MIP_1[i] = M[IP1_FORM[i] - 1];
        }

        byte[] result = Tools.binary_to_byte(MIP_1);
        return result;

    }
}