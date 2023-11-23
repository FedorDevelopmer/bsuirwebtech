package by.bsuir.wtl2.webapp.classes.dao.commands;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class InsertionCommand {

    private final static String COMMAND_TYPE = "insert";

    public static void completeCommand(Connection dbConnection, String tableName,
                                List<String> attributes, Map<String,Object> params) throws SQLException {
        String attributesSet = "(";
        String paramsSet = "(";
        StringBuilder commandFormer = new StringBuilder();
        commandFormer.append(COMMAND_TYPE)
                .append(" into ")
                .append(tableName)
                .append(" ");

        for (String attribute: attributes) {
            attributesSet = attributesSet.concat(attribute + ",");
            if(!(params.get(attribute) instanceof Number)) {
                paramsSet = paramsSet.concat("\"" + params.get(attribute) + "\"" + ",");
            } else {
                paramsSet = paramsSet.concat(params.get(attribute) + ",");
            }
        }
        attributesSet = attributesSet.substring(0,attributesSet.length() - 1);
        attributesSet = attributesSet.concat(")");
        paramsSet = paramsSet.substring(0,paramsSet.length() - 1);
        paramsSet = paramsSet.concat(")");
        commandFormer.append(attributesSet)
                     .append(" values ")
                     .append(paramsSet)
                     .append(";");
        Statement statement = dbConnection.createStatement();
        String query = commandFormer.toString();
        statement.execute(query);
    }
}
