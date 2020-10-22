package sample;

import com.sun.deploy.util.StringUtils;

public class DistanceCalculator {

    public DistanceCalculator(String cord1,String cord2){
        cordinateSource = cord1;
        cordinateTarget = cord2;
    }

    public DistanceCalculator(){

    }

    public String cordinateSource;
    public String cordinateTarget;
    public int[] cords1 = new int[2];
    public int[] cords2 = new int[2];

    public void convertCords(String cord1, String cord2){
        String[] temp = cord1.split("\\|",2);
        for(int i=0 ; i<2 ; i++){
            cords1[i] = Integer.parseInt(temp[i]);
        }

        temp = cord2.split("\\|",2);
        for(int i=0 ; i<2 ; i++){
            cords2[i] = Integer.parseInt(temp[i]);
        }

    }

    public Double calculateDistance(String cordinateSource, String cordinateTarget){
        convertCords(cordinateSource,cordinateTarget);  //NIEOPTYMALNE!
        int x, y;
        double result;
        x = Math.abs(cords1[0] - cords2[0]);  //wartosc bezwzgledna
        y = Math.abs(cords1[1] - cords2[1]);  //wartosc bezwzgledna
        result = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));


        System.out.println(x);
        System.out.println(y);
        System.out.println(result);
        return result;
    }

    public void calculateDistance(int[] cordinateSource, int[] cordinateTarget){
        int x, y;
        double result;
        x = Math.abs(cordinateTarget[0] - cordinateSource[0]);  //wartosc bezwzgledna
        y = Math.abs(cordinateTarget[1] - cordinateSource[1]);  //wartosc bezwzgledna
        result = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));

        System.out.println(x);
        System.out.println(y);
        System.out.println(result);
    }

    public void calculateTime(double distance){

    }
}
