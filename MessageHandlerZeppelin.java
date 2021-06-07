package org.apache.zeppelin.sqlflow.client;

import java.io.IOException;
import java.util.List;

import org.apache.zeppelin.interpreter.InterpreterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlflow.client.MessageHandler;

import com.google.protobuf.Any;
import com.google.protobuf.BoolValue;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;

public class MessageHandlerZeppelin implements MessageHandler {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MessageHandlerZeppelin.class);

	private InterpreterContext context;

	public MessageHandlerZeppelin(InterpreterContext context) {
		this.context = context;
	}

	@Override
	public void handleHTML(String html) {
		// "html" content for image, such as scatter,histogram, etc
		try {
			// context.out.write() is the method of Zeppelin foreground output content
			// "\n%html" is the image value that Zeppelin parses from SQLFlow
			context.out.write("\n%html " + html);
		} catch (IOException e) {
			LOGGER.error("html error", e);
		}
	}

	@Override
	public void handleText(String text) {
		// "text" content for the process of training, such as iterations,loss values, etc
		try {
			if (text != null) {
				context.out.write(text);
				context.out.flush();
			}
		} catch (IOException e) {
			LOGGER.error("handleText error", e);
		}
	}

	@Override
	public void handleHeader(List<String> columnNames) {
		// "columnNames" content for the column name
		int headerSize = columnNames.size();
		try {
			// %table：put the data into the table,in order to standarize the dispaly
			context.out.write("%table ");
		} catch (IOException e) {
			LOGGER.error("table error", e);
		}
		
		for (int i = 0; i < headerSize; i++) {
			String headerContent = columnNames.get(i);
			try {
				if (columnNames != null) {
					context.out.write(headerContent);
					if (i != headerSize - 1) {
						context.out.write("\t");
					}
					context.out.flush();
				}
			} catch (IOException e) {
				LOGGER.error("handleHeader error", e);
			}
		}
	}

	@Override
	public void handleRow(List<com.google.protobuf.Any> row) {
		// "row" content for the data
		int rowSize = row.size();
		try {
			context.out.write("\n");
		} catch (IOException e1) {
			LOGGER.error("context error", e1);
		}

		for (int i = 0; i < rowSize; i++) {
			try {
				// Parsing data from mysql
				String res = unpackToString(row.get(i));
				// "res" holds every value in a piece of data
				if (res != null) {
					context.out.write(res);
					context.out.write("\t");
					context.out.flush();
				}
			} catch (Exception e) {
				LOGGER.error("handleRow error", e);
			}
		}
	}

	public void handleEOE() {
		try {
			context.out.write("\n%text " + "Done training" + "\n");
		} catch (IOException e) {
			LOGGER.error("handleEOE error", e);
		}
	}

	// Parsing data from database
	public static String unpackToString(Any a) throws Exception {
		Object unpackObj = null;
		if (!a.isInitialized()) {
			unpackObj = "NULL";
		} else if (a.is(Int32Value.class)) {
			unpackObj = a.unpack(Int32Value.class).getValue();
		} else if (a.is(Int64Value.class)) {
			unpackObj = a.unpack(Int64Value.class).getValue();
		} else if (a.is(BoolValue.class)) {
			unpackObj = a.unpack(BoolValue.class).getValue();
		} else if (a.is(FloatValue.class)) {
			unpackObj = a.unpack(FloatValue.class).getValue();
		} else if (a.is(DoubleValue.class)) {
			unpackObj = a.unpack(DoubleValue.class).getValue();
		} else {
			// If a is not StringValue type, it would throw exception.
			unpackObj = a.unpack(StringValue.class).getValue();
		}
		return String.valueOf(unpackObj);
	}
}
