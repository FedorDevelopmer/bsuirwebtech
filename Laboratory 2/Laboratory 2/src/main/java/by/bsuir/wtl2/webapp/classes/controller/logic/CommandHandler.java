package by.bsuir.wtl2.webapp.classes.controller.logic;

import by.bsuir.wtl2.webapp.classes.controller.commands.*;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a singleton handler for commands to complete on request.
 * It stores instances of commands in hashmap with String key of command name.
 *
 * @author Fedor
 * @since 2023-11-27
 * @version 1.0
 */
public class CommandHandler {

    private static final CommandHandler instance = new CommandHandler();

    private static Map<String,ICommand> commandMap;

    public CommandHandler(){
        commandMap = new HashMap<>();
        commandMap.put("register",new RegisterCommand());
        commandMap.put("login",new LoginCommand());
        commandMap.put("next_page",new NextCoursesPageCommand());
        commandMap.put("previous_page",new PreviousCoursesPageCommand());
        commandMap.put("main",new MainPageUpdateCommand());
        commandMap.put("course_edit",new CourseEditCommand());
        commandMap.put("cart_add",new CartAddCommand());
        commandMap.put("logout",new LogOutCommand());
        commandMap.put("create_order",new CreateOrderCommand());
        commandMap.put("form_my_orders",new FormUserOrdersCommand());
        commandMap.put("load_user_to_edit",new LoadUserToEditCommand());
        commandMap.put("load_course_to_edit",new LoadCourseToEditCommand());
        commandMap.put("update_user",new UserUpdateCommand());
        commandMap.put("load_orders",new LoadOrdersCommand());
        commandMap.put("next_orders_page",new NextOrdersPageCommand());
        commandMap.put("prev_orders_page",new PreviousOrdersPageCommand());
        commandMap.put("change_order_status",new ChangeOrderStatusCommand());
        commandMap.put("orders_accept",new OrdersAcceptCommand());
        commandMap.put("order_delete",new DeleteOrderCommand());
        commandMap.put("add_course",new CreateCourseCommand());
        commandMap.put("finalize_order",new LoadCartCommand());
        commandMap.put("view_course",new LoadCourseToViewCommand());
        commandMap.put("delete_course",new DeleteCourseCommand());
    }

    /**
     * This method return the instance of handler.
     */
    public static CommandHandler getInstance(){
        return instance;
    }

    /**
     * This method return the instance of command by its name.
     * @param name The name of command in hashmap
     */
    public ICommand getCommandByName(String name){
        return commandMap.get(name);
    }

}
