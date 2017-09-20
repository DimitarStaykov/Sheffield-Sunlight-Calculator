package com.example.staykov.sunlight;

/**
 * Created by Staykov on 3/22/2017.
 */
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class MapTransform {

    public static String[][] myString(String X){

        String fD[] = X.split("@");
        String[][] fD2 =new String[fD.length][4] ;

		/* I want a 2 dimensional array where
		 * first number represents the line
		 * second number represent string split by space

		 * */
        for (int i=0;i<fD.length;i++){

            String change2[]=fD[i].split("\\s+");  // split2

            for (int j=0;j<change2.length;j++){
                fD2[i][j]=change2[j];
            }

        }
        int change=0;
        for (int a=0;a<fD.length;a++){

            //System.out.println(fD2[a][0]);
            //Thread.sleep(1000); // do nothing for 1000 miliseconds (1 second)
            if(fD2[a][0].equals("o") || fD2[a][0].equals("v")){
                change=change+1;
            }

        }

        String[][] fD3 = new String[change][4];
        int counter = 0;
        for (int b=0;b<fD.length;b++){
            if(fD2[b][0].equals("o") || fD2[b][0].equals("v")){
                if (counter<=change){
                    for(int c=0;c<4;c++){ fD3[counter][c]=fD2[b][c];}
                }
                counter=counter+1;
            }

        }

        // fD3 now contains only o and v lines

        return fD3;

    }
    //first step of map transform into String array


    ///////////////////////////////////////////////////////////////////////////////////////


    //FIND THE PLACES OF O
    public static int[] numberO (String[][] darray ){
        int O=0;
        int x = darray.length;
        int counter = 0; // how many "o"
        for (int i=0;i<x;i++){
            if (darray[i][0].equals("o")){
                counter=counter+1; //we get the number of "o"
            }
        }
        int counter2=0;
        int[] oRay = new int[counter];
        for (int j=0;j<x;j++){
            if (darray[j][0].equals("o")){
                //System.out.println("True");
                oRay[counter2]=j;
                counter2=counter2+1;
            }
        }

        return oRay;
    }
    // FIND PLACES OF 1,2,3,4,5,A,B,C,D,E in O

    public static int[] Positions(String[][] bigray,int[] O,String type){
        int size=0;
        for(int i=0;i<O.length;i++){
            int position = O[i];
            String checkMe = bigray[position][1];
            String compareMe = checkMe.substring(0,1);
            if (compareMe.equals(type)){
                size=size+1;
            }
        }
        int[] temp = new int[size];
        int counter=0;
        for(int j=0;j<O.length;j++){
            int position2 = O[j];
            String checkMe2 = bigray[position2][1];
            String compareMe2 = checkMe2.substring(0,1);
            if (compareMe2.equals(type)){
                temp[counter]=position2;
                counter=counter+1;
            }
        }


        return temp;
    }

    public static int pointsNum(int [] theO,int [] other){
        int result=0;
        for(int i=0;i<other.length;i++){
            //my0[87] = 1487 ; 1496
            if (theO[87]==other[i]){result=result + 1496 - 1487 -1;}
            else{
                for (int j=0;j<theO.length;j++){
                    if (theO[j]==other[i]){result=result+theO[j+1]-theO[j]-1;}
                }
            }


        }

        return result;
    }

    public static Point3d toPoint(String[] Z){

        Point3d myP=new Point3d(Double.parseDouble(Z[1]),(Double.parseDouble(Z[3])),Double.parseDouble(Z[2]));

        return myP;
    }

    public static Triangle[] toTriangle(String[][] base,int[] baseO,int[] type,int size){
        //int [] myO
        //    [] myA
        //


        Triangle[] TrRay = new Triangle [size]; // for the triangles
        int position = 0;

        for (int i=0;i<type.length;i++){
            for (int j=0;j<baseO.length;j++){
                //if(baseO[j]==type[i]&& type[i]==baseO[87]){
                if(baseO[j]==type[i]){
                    //start from baseO[j]+1
                    int start=baseO[j]+1;
                    int end=0;
                    if(j==87){end=1495;}
                    else{end=baseO[j+1]-1;}
                    // temporary array for the points of one cube
                    Point3d[] temporaryAr = new Point3d[end-start+1];
                    int counter=0;
                    //System.out.println(start+ " and  "+end);
                    for (int a=start;a<end+1;a++){
                        Point3d thepoint = toPoint(base[a]);
                        temporaryAr[counter]=thepoint;
                        counter=counter+1;

                    }
                    int tr = temporaryAr.length;
                    int numberOfTriangles= tr*(tr-1)*(tr-2)/6;
                    //temporary array for the triangles of one cube
                    int trianglePosition=0;
                    Triangle[] tempTrian = new Triangle[numberOfTriangles];

                    for(int pointA=tr-1;pointA>-1;pointA--){
                        for(int pointB=pointA-1;pointB>-1;pointB--){
                            for( int pointC=pointB-1;pointC>-1;pointC--){
                                if(pointA!=pointB && pointA!=pointC && pointB!=pointC){
                                    tempTrian[trianglePosition]= new Triangle(temporaryAr[pointA],temporaryAr[pointB],temporaryAr[pointC]);
                                    trianglePosition=trianglePosition+1;

                                }
                            }
                        }
                    }

                    for(int transfer=0;transfer<tempTrian.length;transfer++){
                        TrRay[position]=tempTrian[transfer];
                        position=position+1;

                    }



                }
            }
        }

        return TrRay;
    }

    public static int numberOfTriangles(String[][] base,int[] baseO,int[] type) {
        int Triangles = 0;

        int position = 0;

        for (int i = 0; i < type.length; i++) { // i takes from type which is myA , so the first i , is for the first point
            for (int j = 0; j < baseO.length; j++) {  // j takes from myO
                if (baseO[j] == type[i]) {  // here we are comparing the numbers contained , meaning the myA contains all the indexes of point starting with A
                    int start = baseO[j] + 1; // We take the first point which is one below the "o" point
                    int end = 0;
                    if (j == 87) {
                        end = 1495;
                    } // if j happens to be the last
                    else {
                        end = baseO[j + 1] - 1;
                    } //we take the end of the points for that cube AE 1 to 16
                    // temporary array for the points of one cube
                    Point3d[] temporaryAr = new Point3d[end - start + 1]; // we make an array just for those points
                    int counter = 0;
                    //System.out.println(start+ " and  "+end);
                    for (int a = start; a < end + 1; a++) { // this adds every point to the array from start to end
                        Point3d thepoint = toPoint(base[a]);
                        temporaryAr[counter] = thepoint;
                        counter = counter + 1;

                    }
                    int tr = temporaryAr.length; // how many points there were

                    int numberOfTriangles = tr * (tr - 1) * (tr - 2) / 6; // number of triangles for one cube

                    Triangles = Triangles + numberOfTriangles;


                }
            }
        }
        return Triangles;
    }

    public static Point3d cFMe(Ray R,Triangle[] basic,Triangle[] advanced){
        Point3d result = null;
        ///////////Point3d intersectRayTriangle(Ray R, Triangle T)
        for (int a=0;a<basic.length;a++){
            if(result!=Triangle.intersectRayTriangle(R,basic[a])){
                for (int b=0;b<advanced.length;b++){
                    result = Triangle.intersectRayTriangle(R,advanced[b]);
                    if(result!=null){
                        return result;
                    }
                }


            }
        }


        return result;
    }

}
