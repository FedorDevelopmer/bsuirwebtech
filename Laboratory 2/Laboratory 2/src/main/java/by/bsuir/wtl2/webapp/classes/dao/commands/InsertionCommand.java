package by.bsuir.wtl2.webapp.classes.dao.commands;

import by.bsuir.wtl2.webapp.classes.exceptions.DaoException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class InsertionCommand {

    private final static String COMMAND_TYPE = "insert";

    private final static Logger logger = Logger.getLogger(InsertionCommand.class.getName());

    public static void completeCommand(Connection dbConnection, String tableName,
                                List<String> attributes, Map<String,Object> params)
                                throws SQLException {
        try {
            String attributesSet = "(";
            String paramsSet = "(";
            StringBuilder commandFormer = new StringBuilder();
            commandFormer.append(COMMAND_TYPE)
                    .append(" into ")
                    .append(tableName)
                    .append(" ");

            for (String attribute : attributes) {
                attributesSet = attributesSet.concat(attribute + ",");
                paramsSet = paramsSet.concat("?" + ",");
            }
            attributesSet = attributesSet.substring(0, attributesSet.length() - 1);
            attributesSet = attributesSet.concat(")");
            paramsSet = paramsSet.substring(0, paramsSet.length() - 1);
            paramsSet = paramsSet.concat(")");
            commandFormer.append(attributesSet)
                    .append(" values ")
                    .append(paramsSet)
                    .append(";");
            String command = commandFormer.toString();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(command);
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(attributes.get(i)));
            }
            preparedStatement.execute();
            logger.log(Level.INFO,"Insertion command complete successfully");
        }catch (SQLException e){
            logger.log(Level.ERROR,"Error on completing insertion command");
            throw e;
        }
    }
}
