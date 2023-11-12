package Alogtisms;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static Alogtisms.DESConstants.*;

public class Tools {
    // Enter the path of the file and return content inside the file
    public static String read_file(String path) throws IOException {
        StringBuilder file = new StringBuilder();

        FileInputStream fis = new FileInputStream(path);

        InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            file.append(line);
        }
        br.close();
        isr.close();
        fis.close();
        return file.toString();
    }


    public static byte[] binary_to_byte(int[] binary_data) {
        //  transform binary to bytes
        byte[] value = new byte[8];
        int i, j;
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                value[i] += (binary_data[(i << 3) + j] << (7 - j));
            }
        }
        for (i = 0; i < 8; i++) {
            value[i] %= 256;
            if (value[i] > 128) {
                value[i] -= 255;
            }
        }
        return value;
    }

    static int[] byte_to_binary(byte[] byte_data) {
//        transform byte to binary
        int i;
        int j;
        int[] IntDa = new int[8];
        for (i = 0; i < 8; i++) {
            IntDa[i] = byte_data[i];
            if (IntDa[i] < 0) {
                IntDa[i] += 256;
                IntDa[i] %= 256;
            }
        }
        int[] IntVa = new int[64];
        for (i = 0; i < 8; i++) {
            for (j = 0; j < 8; j++) {
                IntVa[((i * 8) + 7) - j] = IntDa[i] % 2;
                IntDa[i] = IntDa[i] / 2;
            }
        }
        return IntVa;
    }

    //make the length of texts fit requirement, f
    public static byte[] fill_byte_data(byte[] data) {
        int len = data.length;
        int need = 8 - (len % 8);
        byte[] fillData = new byte[len + need];
        System.arraycopy(data, 0, fillData, 0, len);
        for (int i = len; i < len + need; i++)
            fillData[i] = (byte) need;
        return fillData;
    }
    

    private static void left_move(int[] arr, int offset) {
//        base on the left move form,transform the code

        int[] tem_array1 = new int[28];
        int[] tem_array2 = new int[28];
        int[] new_array1 = new int[28];
        int[] new_array2 = new int[28];

        System.arraycopy(arr, 0, tem_array1, 0, 28);
        System.arraycopy(arr, 28, tem_array2, 0, 28);


        if (offset == 1) {
            System.arraycopy(tem_array1, 1, new_array1, 0, 27);
            System.arraycopy(tem_array2, 1, new_array2, 0, 27);

            new_array1[27] = tem_array1[0];
            new_array2[27] = tem_array2[0];
        } else if (offset == 2) {

            System.arraycopy(tem_array1, 2, new_array1, 0, 26);
            System.arraycopy(tem_array2, 2, new_array2, 0, 26);

            new_array1[26] = tem_array1[0];
            new_array2[26] = tem_array2[0];
            new_array1[27] = tem_array1[1];
            new_array2[27] = tem_array2[1];
        }


        System.arraycopy(new_array1, 0, arr, 0, 28);
        System.arraycopy(new_array2, 0, arr, 28, 28);

    }

    protected static int[][] generate_sub_key(int[] key) {
//        generate sub key of the given key
        int[][] sub_key = new int[16][48];
        int i, j;
        int[] K0 = new int[56];
        for (i = 0; i < 56; i++) {
            K0[i] = key[PC1_FORM[i] - 1];
        }
        for (i = 0; i < 16; i++) {
            left_move(K0, LEFT_MOVE_FORM[i]);
            for (j = 0; j < 48; j++) {
                sub_key[i][j] = K0[PC2_FORM[j] - 1];
            }
        }
        return sub_key;
    }


}
