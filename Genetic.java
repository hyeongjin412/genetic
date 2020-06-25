import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Genetic {
    public static int[] init1(){
        Random r = new Random();
        int[] arr1 = new int[4];

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = r.nextInt(31)+1;
        }
        return arr1;
    } // arr1 선출

    public static int[] init2() {
        Random r = new Random();
        int[] arr2 = new int[4];
        for(int i=0; i<arr2.length; i++) {
            arr2[i] = r.nextInt(31)+1;
        }
        return arr2;
    } // arr2 선출

    public static int MSE(int a, int b ,int[] x, int[] y){
        int SEE;
        int MSE =0;

        for (int i = 0; i < x.length; i++) {
            int y1 = a*x[i]+b;
            SEE = y[i]-(y1);
            MSE += Math.pow(SEE,2);
        }

        return MSE;
    } // MSE 

    public static int[] selection( int[] a, int[] b, int[] x, int[] y) {
        int[] f = new int[a.length];
        int sum=0;


        for (int i = 0; i < a.length; i++) {
            f[i] = MSE(a[i],b[i],x,y);
            sum += f[i];
        }
        for (int i = 0; i < a.length; i++) {
            f[i] = sum-f[i];
        }
        sum=0;
        for (int i = 0; i < a.length; i++) {
            sum += f[i];
        } 

        double[] ratio = new double[a.length];
        for (int i = 0; i < ratio.length; i++) {
            if(i==0) ratio[i] = (double)(f[i])/(double)sum;
            else ratio[i] = ratio[i-1]+((double)(f[i])/(double)sum);
        }
        int[] result = new int[a.length*2];

        Random r = new Random();

        for (int i = 0; i < a.length; i++) {
            double m = r.nextInt();
            if(m<ratio[0]){
                result[i] = a[0];
                result[i+a.length] = b[0];
            }
            if(m<ratio[1]){
                result[i] = a[1];
                result[i+a.length] = b[1];
            }
            if(m<ratio[2]){
                result[i] = a[2];
                result[i+a.length] = b[2];
            }
            else {
                result[i] = a[3];
                result[i+a.length] = b[3];
            }
        }
        return result;
    } //선택연산

    public static String int2String(String x) {
        return String.format("%8s", x).replace(' ', '0');
    }

    public static String[] crossover(int[] x) {
        String[] arr = new String[x.length];
        for(int i=0; i<x.length; i+=2) {
            String bit1 = int2String(Integer.toBinaryString(x[i]));
            String bit2 = int2String(Integer.toBinaryString(x[i+1]));

            arr[i] = bit1.substring(0, 2) + bit2.substring(3);
            arr[i+1] = bit2.substring(0, 2) + bit1.substring(3);
        }

        return arr;
    } //교차연산

    public static int invert(String x) {
        Random r = new Random();
        int a = Integer.parseInt(x, 2);
        for(int i=0; i<x.length(); i++) {
            double p = (double)1/ (double)32;
            if(r.nextDouble() < p) {
                a = 1 << i ^ a;
            }
        }
        return a;
    }  //돌연변이

    public static int[] mutation(String[] x) {
        int[] a = new int[x.length];
        for (int i=0; i<x.length; i++) {
            a[i] = invert(x[i]);
        }
        return a;
    }

    public static void main(String[] args) {

        int[] x ={1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10};
        int[] y ={5,8,7,6,9,11,14,17,16,19,18,24,21,25,23,27,26,30,29,31};


        int[] arr1 = init1();
        int[] arr2 = init2();

        int[] MSE = new int[arr1.length];ㅜ
        double min = 300.0;
        int a=0;
        int b=0;

        for(int i=0; i<500; i++) {
            int[] sx = selection(arr1,arr2,x,y);

            String[] cross = crossover(sx);
            int[] mx = mutation(cross);

            for (int j = 0; j <arr1.length ; j++) {
                arr1[j] = mx[j];
                arr2[j] = mx[j+arr1.length];
            }
            for(int j = 0; j <arr1.length; j++) {
                MSE[j] = MSE(arr1[j],arr2[j],x,y); 
                if(min>MSE[j]){
                    min = MSE[j];
                    a = arr1[j];
                    b = arr2[j];

                    System.out.println("회귀식: y="+a+"x+"+b);
                    System.out.println("MSE값 :" +min);

                }

            }
        }
        System.out.println("회귀식: y="+a+"x+"+b);
        System.out.println("MSE값 :" +min);

    }
}
