package com.Clivet268.Druid.Util;

public class Square {
    Point a, b;

    /**
     *
     * @param x smaller/right-bottom most point
     * @param y larger/left-top most point
     */
    public Square(Point x, Point y)
    {
        this.a = x;
        this.b = y;
    }

    public Square(int x, int y, int x1, int y1)
    {
        this.a = new Point(x,y);
        this.b = new Point(x1,y1);
    }

    public static boolean ovelapsqmark(Square one, Square two){
        if((Math.abs(two.a.x) >= Math.abs(one.a.x) &&  Math.abs(two.a.x) <=  Math.abs(one.b.x)) || (Math.abs(two.b.x) >= Math.abs(one.a.x) &&  Math.abs(two.b.x) <=  Math.abs(one.b.x)) || (Math.abs(two.a.x) < Math.abs(one.a.x) && Math.abs(two.b.x) > Math.abs(one.b.x) )){
            if((Math.abs(two.a.y) >= Math.abs(one.a.y) &&  Math.abs(two.a.y) <=  Math.abs(one.b.y)) || (Math.abs(two.b.y) >= Math.abs(one.a.y) &&  Math.abs(two.b.y) <=  Math.abs(one.b.y))|| (Math.abs(two.a.y) < Math.abs(one.a.y) && Math.abs(two.b.y) > Math.abs(one.b.y))){
                return true;
            }
        }

    return false;
    }

    public class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
