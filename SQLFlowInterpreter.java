package org.apache.zeppelin.sqlflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.zeppelin.interpreter.Interpreter;
import org.apache.zeppelin.interpreter.InterpreterContext;
import org.apache.zeppelin.interpreter.InterpreterException;
import org.apache.zeppelin.interpreter.InterpreterResult;
import org.apache.zeppelin.sqlflow.client.MessageHandlerZeppelin;
import org.sqlflow.client.SQLFlow;
import org.apache.zeppelin.sqlflow.client.utils.EnvironmentSpecificSQLFlowClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang3.StringUtils;

public class SQLFlowInterpreter extends Interpreter {
	// Print Log
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SQLFlowInterpreter.class);

	private Map<String, String> parameters = new HashMap<String, String>();

	public static final String SQLFLOW_SERVERADDR = "sqlflow.serverAddr";
	public static final String MYSQL_USERNAME = "mysql.username";
	public static final String MYSQL_PASSWORD = "mysql.password";
	public static final String MYSQL_SERVERADDR = "mysql.serverAddr";
	public static final String MYSQL_DATABASENAME = "mysql.databaseName";

	public SQLFlowInterpreter(Properties properties) {
		super(properties);
	}

	@Override
	public void open() throws InterpreterException {
		// get the configured parameters from the front page
		String sqlflowServerAddr = getProperty(SQLFLOW_SERVERADDR);
		String mysqlUserName = getProperty(MYSQL_USERNAME);
		String mysqlPassword = getProperty(MYSQL_PASSWORD);
		String mysqlAddr = getProperty(MYSQL_SERVERADDR);
		String mysqlDatabaseName = getProperty(MYSQL_DATABASENAME);

		parameters.put(SQLFLOW_SERVERADDR, sqlflowServerAddr);
		parameters.put(MYSQL_USERNAME, mysqlUserName);
		parameters.put(MYSQL_PASSWORD, mysqlPassword);
		parameters.put(MYSQL_SERVERADDR, mysqlAddr);
		parameters.put(MYSQL_DATABASENAME, mysqlDatabaseName);
	}

	@Override
	public void cancel(InterpreterContext arg0) throws InterpreterException {
	}

	@Override
	public void close() throws InterpreterException {
	}

	@Override
	public FormType getFormType() throws InterpreterException {
		return FormType.SIMPLE;
	}

	@Override
	public int getProgress(InterpreterContext arg0) throws InterpreterException {
		return 20;
	}

	@Override
	public InterpreterResult interpret(String query, InterpreterContext context)
			throws InterpreterException {
		if (StringUtils.isBlank(query)) {
			return new InterpreterResult(InterpreterResult.Code.SUCCESS);
		}

		// Create user
		SQLFlow client = EnvironmentSpecificSQLFlowClient.getClient(
				new MessageHandlerZeppelin(context), parameters);
		try {
			// Run custom sqlflow code
			client.run(query);
			// Release user
			client.release();
			
			return new InterpreterResult(InterpreterResult.Code.SUCCESS);
		} catch (InterruptedException e) {
			LOGGER.error("client error", e);
			return new InterpreterResult(InterpreterResult.Code.ERROR);
		}
	}
}
