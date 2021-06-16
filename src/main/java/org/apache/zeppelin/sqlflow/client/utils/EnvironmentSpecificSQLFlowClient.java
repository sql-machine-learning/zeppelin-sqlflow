package org.apache.zeppelin.sqlflow.client.utils;

import java.util.Map;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.lang3.StringUtils;
import org.sqlflow.client.SQLFlow;
import org.sqlflow.client.MessageHandler;
import proto.Sqlflow;

public class EnvironmentSpecificSQLFlowClient {

	public static SQLFlow getClient(MessageHandler messageHandler,
			Map<String, String> parameters) {
		// Get the configuration parameters from the page side
		String serverAddr = parameters.get("sqlflow.serverAddr");
		String submitter = "mlp";
		String username = parameters.get("mysql.username");
		String password = parameters.get("mysql.password");
		String mysqlAddr = parameters.get("mysql.serverAddr");
		String databaseName = parameters.get("mysql.databaseName");
		String dataSource = "mysql://" + username + ":" + password + "(" + mysqlAddr
				+ ")/" + databaseName + "?maxAllowedPacket=0";
		String userId = "314";
		if (StringUtils.isAnyBlank(serverAddr, submitter, dataSource, userId)) {
			return null;
		}

		ManagedChannel chan = ManagedChannelBuilder.forTarget(serverAddr)
				.usePlaintext().build();
		Sqlflow.Session session = Sqlflow.Session.newBuilder()
				.setUserId(userId).setSubmitter(submitter)
				.setDbConnStr(dataSource).build();
		return SQLFlow.Builder.newInstance().withSession(session)
				.withIntervalFetching(2000).withMessageHandler(messageHandler)
				.withChannel(chan).build();
	}
}