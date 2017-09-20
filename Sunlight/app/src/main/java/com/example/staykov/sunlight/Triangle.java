package com.example.staykov.sunlight;

/**
 * Created by Staykov on 2/20/2017.
 */

import java.lang.Object;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


/*
    Public class Triangle
    Constructs triangles to be used in the RayTriangle Intersection
    It is constructed as a container for Three Poin3d objects for the three points of the triangle
    And get methods for each point
 */
public class Triangle {


    Point3d[] points = new Point3d[3];

    public Triangle(Point3d point1, Point3d point2, Point3d point3) {
        points[0] = point1;
        points[1] = point2;
        points[2] = point3;
    }

    public Point3d getPointOne() {
        return points[0];
    }

    public Point3d getPointTwo() {
        return points[1];
    }

    public Point3d getPointThree() {
        return points[2];
    }

    public Point3d intersects(Point3d from, Vector3d dir) {
        Ray ray = new Ray(from, dir);
        return intersectRayTriangle(ray, this);
    }


    public static final double SMALL_NUM = 0.01;


    /*
    Ray Triangle Intersection algorithm

    taken from wiki, before the java style one was added
    this is the C++ version adapted for java


    Personal opinion: This code does not work 100% correctly,
    source for issues is unknown

     */

    public static Point3d intersectRayTriangle(Ray R, Triangle T) {
        Point3d    I;
        Vector3d    e1, e2, cross1, cross2;
        Vector3d    dir, w0, distance,origin,distance1;
        double     determinant,uParam,invDet, vParam, b;

        dir = new Vector3d(R.getDirection());
        origin = new Vector3d (R.getStart());

        //Find vectors for two edges sharing V1
        e1 = new Vector3d(T.getPointTwo());
        e1.sub(T.getPointOne());
        e2 = new Vector3d(T.getPointThree());
        e2.sub(T.getPointOne());

        //Begin calculating determinant - also used to calculate u parameter
        cross1 = new Vector3d(); // cross product
        cross1.cross(dir, e2); //P

        //if determinant is near zero, ray lies in plane of triangle or ray is parallel to plane of triangle
        determinant = cross1.dot(e1);


        if (determinant > (SMALL_NUM*(-1)) && determinant<SMALL_NUM){
            return null;
        }



        //calculate distance from V1 to ray origin
        distance1=new Vector3d(R.getStart()); //O for origin
        distance1.sub(T.getPointOne()); // T

        invDet = 1/determinant;
        //Calculate u parameter and test bound
        uParam = (distance1.dot(cross1))*invDet;
        //The intersection lies outside of the triangle
        if(uParam<0 || uParam>1){return null;}

        //Prepare to test v parameter
        cross2 = new Vector3d();
        cross2.cross(distance1,e1); //Q

        //Calculate V parameter and test bound
        vParam = cross2.dot(distance1)*invDet;
        //The intersection lies outside of the triangle
        if(vParam<0 || uParam+vParam >1){return null;}

        b = (e2.dot(cross2))*invDet;
        I = new Point3d(b,b,b);

        if(b>SMALL_NUM){return I;}
        else{return null;}



                /*
        if (n.length() == 0) {
            return null;
        }

        dir = new Vector3d(R.getDirection());
        w0 = new Vector3d(R.getStart());
        w0.sub(T.getPointOne());
        a = -(new Vector3d(n).dot(w0));
        b = new Vector3d(n).dot(dir);

        if ((double)Math.abs(b) < SMALL_NUM) {
            return null;
        }
        // b determinent , ignoring b might be outside of triangle
        r = a / b;
        if (r < 0.0) {
            return null;
        }

        I = new Point3d(R.getStart());
        I.x += r * dir.x;
        I.y += r * dir.y;
        I.z += r * dir.z;

*/
    }
}







/**
    public float[] V0;
    public float[] V1;
    public float[] V2;

    public Triangle(float[] V0, float[] V1, float[] V2){
        this.V0 = V0;
        this.V1 = V1;
        this.V2 = V2;
    }

public class Ray {



    public Ray(int width, int height, float xTouch, float yTouch) {

        int[] viewport = {0, 0, width, height};

        float[] nearCo0rds = new float[3];
        float[] farCo0rds = new float[3];
        float[] temp = new float[4];
        float[] temp2 = new float[4];

        this.P0 = farCo0rds;
        this.P1 = nearCo0rds;


    }
}
    private static final float SMALL_NUM =  0.00000001f; // anything that avoids division overflow


    // intersectRayAndTriangle(): intersect a ray with a 3D triangle
//    Input:  a ray R, and a triangle T
//    Output: *I = intersection point (when it exists)
//    Return: -1 = triangle is degenerate (a segment or point)
//             0 = disjoint (no intersect)
//             1 = intersect in unique point I1
//             2 = are in the same plane
    public static int intersectRayAndTriangle(Ray R, Triangle T, float[] I)
    {
        float[]    u, v, n;             // triangle vectors
        float[]    dir, w0, w;          // ray vectors
        float     r, a, b;             // params to calc ray-plane intersect

        // get triangle edge vectors and plane normal
        u =  Vector.minus(T.V1, T.V0);
        v =  Vector.minus(T.V2, T.V0);
        n =  Vector.crossProduct(u, v);             // cross product

        if (Arrays.equals(n, new float[]{0.0f,0.0f,0.0f})){           // triangle is degenerate
            return -1;                 // do not deal with this case
        }
        dir =  Vector.minus(R.P1, R.P0);             // ray direction vector
        w0 = Vector.minus( R.P0 , T.V0);
        a = - Vector.dot(n,w0);
        b =  Vector.dot(n,dir);
        if (Math.abs(b) < SMALL_NUM) {     // ray is parallel to triangle plane
            if (a == 0){                // ray lies in triangle plane
                return 2;
            }else{
                return 0;             // ray disjoint from plane
            }
        }

        // get intersect point of ray with triangle plane
        r = a / b;
        if (r < 0.0f){                   // ray goes away from triangle
            return 0;                  // => no intersect
        }
        // for a segment, also test if (r > 1.0) => no intersect

        float[] tempI =  Vector.addition(R.P0,  Vector.scalarProduct(r, dir));           // intersect point of ray and plane
        I[0] = tempI[0];
        I[1] = tempI[1];
        I[2] = tempI[2];

        // is I inside T?
        float    uu, uv, vv, wu, wv, D;
        uu =  Vector.dot(u,u);
        uv =  Vector.dot(u,v);
        vv =  Vector.dot(v,v);
        w =  Vector.minus(I, T.V0);
        wu =  Vector.dot(w,u);
        wv = Vector.dot(w,v);
        D = (uv * uv) - (uu * vv);

        // get and test parametric coords
        float s, t;
        s = ((uv * wv) - (vv * wu)) / D;
        if (s < 0.0f || s > 1.0f)        // I is outside T
            return 0;
        t = (uv * wu - uu * wv) / D;
        if (t < 0.0f || (s + t) > 1.0f)  // I is outside T
            return 0;

        return 1;                      // I is in T
    }


}
**/