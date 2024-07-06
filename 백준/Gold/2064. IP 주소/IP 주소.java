import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder network = new StringBuilder();
        StringBuilder subnet = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][4];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine(), ".");
            for(int j = 0; j < 4; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean check = false;
        for(int i = 0; i < 4; i++){
            int min = arr[0][i];
            int max = arr[0][i];

            for(int j = 1; j < n; j++){
                min = min & arr[j][i];
                max = max | arr[j][i];
            }

            if(check){
                network.append(0).append('.');
                subnet.append(0).append('.');
            } else {
                network.append(min).append('.');
                int diff = max - min;
                int mask = 255;
                while (diff > 0) {
                    mask <<= 1;
                    diff >>= 1;
                }
                subnet.append(mask & 255).append('.');
            }

            if(min != max) check = true;
        }

        network.setLength(network.length() - 1);
        subnet.setLength(subnet.length() - 1);
        System.out.println(network.toString());
        System.out.println(subnet.toString());
    }
}
