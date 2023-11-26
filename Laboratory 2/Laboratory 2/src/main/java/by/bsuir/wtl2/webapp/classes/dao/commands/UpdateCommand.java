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

public class UpdateCommand {

    private final static String COMMAND_TYPE = "update";

    private final static Logger logger = Logger.getLogger(UpdateCommand.class.getName());
    public static void completeCommand(Connection dbConnection, String tableName,
                                       List<String> updateAttributes, Map<String,Object> params,
                                       List<String> selectAttributes,Map<String,Object> newParams)
                                       throws SQLException {
        try {
            StringBuilder commandFormer = new StringBuilder();
            commandFormer.append(COMMAND_TYPE)
                    .append(" ")
                    .append(tableName)
                    .append(" set ");
            for (String attribute : updateAttributes) {
                commandFormer.append(attribute)
                        .append("=")
                        .append("?" + ", ");
            }
            commandFormer.delete(commandFormer.length() - 2, commandFormer.length());
            commandFormer.append(" where ");
            for (String attribute : selectAttributes) {
                commandFormer.append(attribute)
                        .append("=")
                        .append("?")
                        .append(" and ");
            }
            commandFormer.delete(commandFormer.length() - 4, commandFormer.length());
            commandFormer.append(";");
            String command = commandFormer.toString();
            PreparedStatement preparedStatement = dbConnection.prepareStatement(command);
            for (int i = 0; i < newParams.size(); i++) {
                preparedStatement.setObject(i + 1, newParams.get(updateAttributes.get(i)));
            }
            for (int i = newParams.size(); i < params.size() + newParams.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(selectAttributes.get(i - newParams.size())));
            }
            preparedStatement.execute();
            logger.log(Level.INFO,"Update command completed successfully");
        }catch (SQLException e){
            logger.log(Level.ERROR,"Error while completing update command");
            throw e;
        }
    }
}
