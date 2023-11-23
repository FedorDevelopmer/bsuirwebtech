package by.bsuir.wtl2.webapp.classes.controller.logic;

public class PageName {

    private String name;

    private boolean isRedirect;
    public PageName(){

    }
    public PageName(String name,boolean isRedirect){
        this.name = name;
        this.isRedirect = isRedirect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }
}
