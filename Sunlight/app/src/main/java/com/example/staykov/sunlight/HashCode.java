package com.example.staykov.sunlight;

/**
 * Created by Staykov on 4/23/2017.
 */
/**
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */




public final class HashCode {
    /**
     * The hashCode value before any data is appended, equals to 1.
     * @see java.util.List#hashCode()
     */
    public static final int EMPTY_HASH_CODE = 1;
    private int hashCode = EMPTY_HASH_CODE;
    /**
     * Returns accumulated hashCode
     */
    public final int hashCode() {
        return hashCode;
    }
    /**
     * Combines hashCode of previous elements sequence and value's hashCode.
     * @param hashCode previous hashCode value
     * @param value new element
     * @return combined hashCode
     */
    public static int combine(int hashCode, boolean value) {
        int v = value ? 1231 : 1237;
        return combine(hashCode, v);
    }
    /**
     * Combines hashCode of previous elements sequence and value's hashCode.
     * @param hashCode previous hashCode value
     * @param value new element
     * @return combined hashCode
     */
    public static int combine(int hashCode, long value) {
        int v = (int) (value ^ (value >>> 32));
        return combine(hashCode, v);
    }
    /**
     * Combines hashCode of previous elements sequence and value's hashCode.
     * @param hashCode previous hashCode value
     * @param value new element
     * @return combined hashCode
     */
    public static int combine(int hashCode, float value) {
        int v = Float.floatToIntBits(value);
        return combine(hashCode, v);
    }
    /**
     * Combines hashCode of previous elements sequence and value's hashCode.
     * @param hashCode previous hashCode value
     * @param value new element
     * @return combined hashCode
     */
    public static int combine(int hashCode, double value) {
        long v = Double.doubleToLongBits(value);
        return combine(hashCode, v);
    }
    /**
     * Combines hashCode of previous elements sequence and value's hashCode.
     * @param hashCode previous hashCode value
     * @param value new element
     * @return combined hashCode
     */
    public static int combine(int hashCode, Object value) {
        return combine(hashCode, value.hashCode());
    }
    /**
     * Combines hashCode of previous elements sequence and value's hashCode.
     * @param hashCode previous hashCode value
     * @param value new element
     * @return combined hashCode
     */
    public static int combine(int hashCode, int value) {
        return 31 * hashCode + value;
    }
    /**
     * Appends value's hashCode to the current hashCode.
     * @param value new element
     * @return this
     */
    public final HashCode append(int value) {
        hashCode = combine(hashCode, value);
        return this;
    }
    /**
     * Appends value's hashCode to the current hashCode.
     * @param value new element
     * @return this
     */
    public final HashCode append(long value) {
        hashCode = combine(hashCode, value);
        return this;
    }
    /**
     * Appends value's hashCode to the current hashCode.
     * @param value new element
     * @return this
     */
    public final HashCode append(float value) {
        hashCode = combine(hashCode, value);
        return this;
    }
    /**
     * Appends value's hashCode to the current hashCode.
     * @param value new element
     * @return this
     */
    public final HashCode append(double value) {
        hashCode = combine(hashCode, value);
        return this;
    }
    /**
     * Appends value's hashCode to the current hashCode.
     * @param value new element
     * @return this
     */
    public final HashCode append(boolean value) {
        hashCode = combine(hashCode, value);
        return this;
    }
    /**
     * Appends value's hashCode to the current hashCode.
     * @param value new element
     * @return this
     */
    public final HashCode append(Object value) {
        hashCode = combine(hashCode, value);
        return this;
    }
}