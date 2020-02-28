import java.lang.*;
import java.util.*;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class MegaSort {
    public static int[] arr;

    public static void main(String[] args){
        readFile(args[0]); //passing in one parameter, which is the file we are reading into
        sort(arr);

        for(int num : arr){ //for each number inside the array
            System.out.println(num);
        }
    }

    public static void readFile(String fileName){

        try(BufferedReader reader = Files.newBufferedReader(Path.of(fileName), StandardCharsets.UTF_8)){ //converts String to Path Object and opens into a Reader Object
            int i = 0; //counter for array that will populate

            arr = new int[1000000]; //array that is getting populated

            String line;

            while ((line = reader.readLine()) != null){ //reading the lines
                int num = Integer.parseInt(line); //converting string into int
                arr[i++] = num; //saving numbers into array and incrementing i by 1
            }
        }
        catch(Exception e){
            System.err.println("Error trying to read the file." + e.toString());
        }

    }

    public static void sort(int[] a) {
        MergeSort(a, 0, a.length - 1);
    }

    public static void MergeSort(int[] a, int left, int right){
        if(left < right)
        {
            int middle = (left + right) / 2; //gets the middle index of the array. Use this index to split array into two

            MergeSort(a, left, middle);
            MergeSort(a,middle + 1, right);

            merge(a, left, middle, right);
        }

    }

    private static void merge(int[] a, int left, int middle, int right){
        int leftArray[] = new int[middle - left + 1]; //splits the main array from the first index until middle
        int rightArray[] = new int[right - middle]; //splits the main array from middle until end

        //appends numbers to temp array
        for(int i = 0; i <(middle - left + 1); ++i)
        {
            leftArray[i] = a[left + i];
        }

        //appends number to temp array
        for (int j = 0; j < (right - middle); ++j)
        {
            rightArray[j] = a[middle + 1 + j];
        }

        int i = 0;
        int j = 0;

        int k = left;
        while (i < (middle - left + 1) && j < (right - middle))
        {

            if (leftArray[i] <= rightArray[j])
            {
                a[k] = leftArray[i];
                i++;
            }

            else
            {
                a[k] = rightArray[j];
                j++;
            }
            k++;
        }

        //Copies array from the left array to the main array
        while (i < (middle - left + 1))
        {
            a[k] = leftArray[i];
            i++;
            k++;
        }

        //Copies array from the right array to the main array
        while (j < (right - middle))
        {
            a[k] = rightArray[j];
            j++;
            k++;
        }

    }
}
