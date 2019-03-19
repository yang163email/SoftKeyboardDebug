package com.yan.testproject;

/**
 * @author : yan
 * @date : 2019/2/25 15:57
 * @desc : todo
 */
public class OuterClass {

    public class Father {

        public Father(String name) {

        }
    }

    public class Child extends Father {

        public Child(String name) {
            super(name);
        }
    }
}
