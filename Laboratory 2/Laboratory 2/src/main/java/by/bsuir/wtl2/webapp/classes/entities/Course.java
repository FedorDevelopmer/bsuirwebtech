package by.bsuir.wtl2.webapp.classes.entities;

/**
 * The Course class represents a course.
 * It includes properties such as ID, name, price, author, description, and main technology.
 *
 * @version 1.0
 * @author Fedor
 * @since 2023-11-29
 */
public class Course {

    private int id;
    private String name;

    private double price;

    private String author;

    private String description;

    private String mainTech;

    public Course() {

    }

    public Course(String courseName, double coursePrice, String courseAuthor, String description, String mainTech) {
        this.name = courseName;
        this.price = coursePrice;
        this.author = courseAuthor;
        this.description = description;
        this.mainTech = mainTech;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMainTech() {
        return mainTech;
    }

    public void setMainTech(String mainTech) {
        this.mainTech = mainTech;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
