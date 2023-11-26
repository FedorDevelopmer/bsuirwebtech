package by.bsuir.wtl2.webapp.classes.dao.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.List;

import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
public class DeleteCommand {

    private final static String COMMAND_TYPE = "delete";

    private final static Logger logger = Logger.getLogger(DeleteCommand.class.getName());
    public static void completeCommand(Connection dbConnection,String tableName,
                                List<String> attributes, Map<String,Object> params)
                                throws SQLException {
        try {
            StringBuilder commandFormer = new StringBuilder();
            commandFormer.append(COMMAND_TYPE)
                    .append(" from ")
                    .append(tableName)
                    .append(" where ")
                    .append("(");

            for (String attribute : attributes) {
                commandFormer.append(attribute + "=")
                             .append("?" + " and ");
            }
            commandFormer.delete(commandFormer.length() - 4, commandFormer.length() - 1);
            commandFormer.append(")")
                    .append(";");
            String command = commandFormer.toString();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(command);
            for(int i = 0; i < params.size(); i++){
                preparedStatement.setObject(i + 1,params.get(attributes.get(i)));
            }
            preparedStatement.execute();
            logger.log(Level.INFO,"Delete command completed successfully");
        }catch (SQLException e){
            logger.log(Level.ERROR,"Error on competing delete command",e);
            throw e;
        }
    }
}

