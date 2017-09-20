package com.example.staykov.sunlight;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * Created by Staykov on 2/23/2017.
 */

public class Ray {

        Point3d from = null;
        Vector3d dir = null;

        public Ray(Point3d from, Vector3d dir) {
            this.from = from;
            this.dir = dir;
        }

        public Point3d getStart() {
            return from;
        }

        public Vector3d getDirection() {
            return dir;
        }

}

