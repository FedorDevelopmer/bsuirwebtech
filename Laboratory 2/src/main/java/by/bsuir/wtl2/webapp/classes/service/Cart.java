package by.bsuir.wtl2.webapp.classes.service;


import by.bsuir.wtl2.webapp.classes.entities.Course;

import java.util.ArrayList;
import java.util.List;


/**
 * The Cart class represents a shopping cart for courses.
 * It includes methods to add, remove, clear, check if a course is contained, retrieve all courses, and check if the cart is empty.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class Cart {
    private List<Course> courses = new ArrayList<>();

    public Cart() {

    }

    public boolean add(Course course){
        return courses.add(course);
    }

    public boolean remove(Course course){
        return courses.remove(course);
    }

    public void clear(){
        courses.clear();
    }

    public boolean contains(Course course){
        return courses.contains(course);
    }

    public List<Course> getAll() {
        return courses;
    }

    public boolean isEmpty() {
        return courses.isEmpty();
    }
}
