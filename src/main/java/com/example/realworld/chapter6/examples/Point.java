package com.example.realworld.chapter6.examples;

public class Point {

    private final int x;
    private final int y;

    Point(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
    // end::Point[]

    // tag::equals_hashcode[]
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
    // end::equals_hashcode[]

    final Point p1 = new Point(1, 2);
    final Point p2 = new Point(1, 2);


}