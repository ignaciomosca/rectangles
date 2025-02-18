# Rectangles

## Problem Description

You are required to write code in the language of your choice implementing certain algorithms that
analyze rectangles and features that exist among rectangles. Your implementation is required to cover
the following:

1. Intersection:​ You must be able to determine whether two rectangles have one or more
intersecting lines and produce a result identifying the points of intersection.

2. Containment: ​ You must​ ​ be able to determine whether a rectangle is wholly contained within
another rectangle.

3. Adjacency: ​ Implement the ability to detect whether two rectangles are adjacent. Adjacency is
defined as the sharing of at least one side. Side sharing may be proper, sub-line or partial. A
sub-line share is a share where one side of rectangle A is a line that exists as a set of points
wholly contained on some other side of rectangle B, where partial is one where some line
segment on a side of rectangle A exists as a set of points on some side of Rectangle B.

## The Solution

The implementation of the following problem relies on Set theory. Basically I create set of points and perform basic operations (like `intersection` and `subsetOf`) on them.

The use of pure functional programming seeks to create stable code, where illegal states are impossible to represent. For example preventing the user of creating things that are not rectangles. That way we avoid unpredictable behavior.

In order to make the code more interactive, I decided to create a little console program. It uses ZIO because it's the easiest way to create reliable code that validates the inputs and structures the flow in a functional way. I tried doing it without it but writing functional code that accepts inputs from console and gets validated becomes cumbersome.

## Dependencies

* Git
* JDK 1.8+
* sbt 1.3.2+
* Docker (in case you want to run the code in a container)

## Instructions

* git clone git@github.com:ignaciomosca/rectangles.git
* cd rectangles
* sbt compile
* sbt test

You can run the code and tests from the terminal by running `sbt run` or `sbt test` respectively

If you want to run the code in an isolated environment you can execute the following commands:

* `sbt clean assembly`

* `sbt docker:publishLocal`

* `docker run -it rectangles:1.0`