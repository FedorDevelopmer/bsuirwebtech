package by.bsuir.wtl2.webapp.classes.dao.commands;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class SelectionCommand {

    private final static String COMMAND_TYPE = "select";

    private final static Logger logger = Logger.getLogger(SelectionCommand.class.getName());
    public static ResultSet completeCommand(Connection dbConnection, String tableName,
                                            String selectionAttribute, List<String> attributes,
                                            Map<String,Object> params) throws SQLException {
        try {
            StringBuilder commandFormer = new StringBuilder();
            commandFormer.append(COMMAND_TYPE)
                    .append(" ")
                    .append(selectionAttribute)
                    .append(" from ")
                    .append(tableName)
                    .append(" where ")
                    .append("(");
            for (String attribute : attributes) {
                commandFormer.append(attribute + "=" + "\"" + params.get(attribute) + "\"")
                        .append(" and ");
            }
            commandFormer.delete(commandFormer.length() - 4, commandFormer.length());
            commandFormer.append(")")
                    .append(";");
            Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String command = commandFormer.toString();
            ResultSet selectionResult = statement.executeQuery(command);
            logger.log(Level.DEBUG,"Selection command completed successfully");
            return selectionResult;
        }catch (SQLException e){
            logger.log(Level.ERROR,"Error while completing selection command");
            throw e;

        }
    }
    public static ResultSet completeCommandForPagination(Connection dbConnection, String tableName,
                                                         String selectionAttribute, int offset, int limit)
                                                         throws SQLException{
        try {
            StringBuilder commandFormer = new StringBuilder();
            commandFormer.append(COMMAND_TYPE)
                    .append(" ")
                    .append(selectionAttribute)
                    .append(" from ")
                    .append(tableName)
                    .append(" limit ")
                    .append(limit)
                    .append(" offset ")
                    .append(offset)
                    .append(";");
            Statement statement = dbConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            String command = commandFormer.toString();
            ResultSet selectionResult = statement.executeQuery(command);
            logger.log(Level.DEBUG,"Selection command completed successfully");
            return selectionResult;
        }catch (SQLException e){
            logger.log(Level.ERROR,"Error while completing selection command");
            throw e;
        }
    }
}
