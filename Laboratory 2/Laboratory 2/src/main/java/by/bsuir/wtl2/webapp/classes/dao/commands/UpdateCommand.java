package by.bsuir.wtl2.webapp.classes.dao.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class UpdateCommand {

    private final static String COMMAND_TYPE = "update";
    public static void completeCommand(Connection dbConnection, String tableName,
                                       List<String> updateAttributes, Map<String,Object> params,
                                       List<String> selectAttributes,Map<String,Object> newParams) throws SQLException {
        StringBuilder commandFormer = new StringBuilder();
        commandFormer.append(COMMAND_TYPE)
                .append(" ")
                .append(tableName)
                .append(" set ");
        for (String attribute: updateAttributes) {
            commandFormer.append(attribute)
                    .append("=");
            if(!(params.get(attribute) instanceof Number)) {
                commandFormer.append("\"" + newParams.get(attribute) + "\"" + ",");
            } else {
                commandFormer.append(newParams.get(attribute) + ",");
            }
        }
        commandFormer.delete(commandFormer.length() - 1,commandFormer.length());
        commandFormer.append(" where ");
        for (String attribute : selectAttributes) {
            commandFormer.append(attribute)
                    .append("=");
            if(!(params.get(attribute) instanceof Number)) {
                commandFormer.append("\"" + params.get(attribute) + "\"" + " and ");
            } else {
                commandFormer.append(params.get(attribute) + " and ");
            }
        }
        commandFormer.delete(commandFormer.length() - 4,commandFormer.length());
        commandFormer.append(";");
        String command = commandFormer.toString();
        Statement statement = dbConnection.createStatement();
        statement.execute(command);
    }
}
