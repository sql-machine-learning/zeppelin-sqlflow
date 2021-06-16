## 1. What is Apache Zeppelin Interprer
**Apache Zeppelin Interpreter** is a language backend. For example to use sqlflow code in Zeppelin, you need a **sqlflow** interpreter. We integrate the open source machine learning tool SQLFlow of Ant Group into the web-based notebook tool Apache Zeppelin, so as to expand the interpreter language supported by Zeppelin.
## 2. Environmental dependence
- Operating system: unlimited
- JDK: 1.8 or above
- Zeppelin version: zeppelin-0.9.0-SNAPSHOT, Installation documentation can refer to: [How to install and start Apache Zeppelin](https://blog.csdn.net/u013686990/article/details/102890085)

## 3. Write code
Create a new maven project in IDEA, the project name is `sqlflow-zeppelin-interpreter`, and then modify `pom.xml`:
- Add `org.apache.zeppelin:zeppelin-interpreter` dependency;
- Add maven plugins: `maven-enforcer-plugin`, `maven-dependency-plugin`, `maven-resources-plugin`

The specific code can refer to the `pom.xml` file. Then create three new class files:
- `SQLFlowInterpreter.java`
- `EnvironmentSpecificSQLFlowClient.java`
- `MessageHandlerZeeplin.java`

Finally, create the interpreter configuration file:`interpreter-setting.json` . The description and structure of the above code file have been explained in [issues #3 : Architecture of zeppelin-sqlflow](https://github.com/sql-machine-learning/zeppelin-sqlflow/issues/3).

## 4. Install the interpreter
The installation is divided into the following steps:

Step 1: Prepare the jar package of the interpreter project code: “zeppelin-sqlflow-0.9.0.jar” and the configuration file “interpreter-setting.json”;

Step 2: Switch to the installation directory of Zeppelin and create the subdirectory: “interpreter/sqlflow”;
```bash
mkdir ~/zeppelin-0.9.0-SNAPSHOT/interpreter/sqlflow
```
Step 3: Copy the jar package and configuration file in step 1 and paste into the subdirectory created in step 2;
```bash
# This is the path on my machine, for refernce only.
cp ~/zeppelin-sqlflow-0.9.0.jar ~/zeppelin-0.9.0-SNAPSHOT/interpreter/sqlflow
cp ~/interpreter-setting.json ~/zeppelin-0.9.0-SNAPSHOT/interpreter/sqlflow
```
Step 4: Start zeppelin
```bash
cd ~/zeppelin-0.9.0-SNAPSHOT/bin/
./zeppelin-daemon.sh start
```
## 5. Instruction
For detailed instructions, please refer to [`OperationGuide.md`](https://github.com/sql-machine-learning/zeppelin-sqlflow/issues/2)