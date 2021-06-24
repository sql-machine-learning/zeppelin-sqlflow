# zeppelin-sqlflow

## Project Description

Integrate Ant Financial Services Group open source machine learning tool [SQLFlow](http://sqlflow.org/sqlflow) into the web-based notebook tool [Apache Zeppelin](http://zeppelin.apache.org/) to expand the interpreter language supported by Zeppelin.

## Motivation

Integrate SQLFlow into Apache Zeppelin:

- Rich SQLFlow usage scenarios. Users can also use SQLFlow in this notebook environment;
- Expanded the interpreter language supported by Zeppelin;
- SQLFlow can be used in conjunction with other languages or data processors on this platform to fully demonstrate its convenience in AI applications.

## Quick Overview

Here are examples for training a TensorFlow [DNNClassifier](https://www.tensorflow.org/api_docs/python/tf/estimator/DNNClassifier) model using sample data Iris.train, and running prediction using the trained model in Zeppelin environment.

![twoparagraph](https://user-images.githubusercontent.com/65579930/122882581-ed896580-d36e-11eb-95d5-611a841c5349.jpg)

## How to install zeppelin-sqlflow

The installation is divided into the following steps:

- Prepare the interpreter project code jar package `zeppelin-sqlflow-0.9.0.jar` and the configuration file `interpreter-setting.json`;

- Switch to the installation directory of Zeppelin and create the `interpreter/sqlflow` subdirectory:
```bash
mkdir ~/zeppelin-0.9.0-SNAPSHOT/interpreter/sqlflow
```

- Copy the jar package and configuration file in step 1 to the directory created in step 2:
```bash
# This is the path on my machine, for reference only.
cp ~/zeppelin-sqlflow-0.9.0.jar ~/zeppelin-0.9.0-SNAPSHOT/interpreter/sqlflow
cp ~/interpreter-setting.json ~/zeppelin-0.9.0-SNAPSHOT/interpreter/sqlflow
```

- Start zeppelin
```bash
cd ~/zeppelin-0.9.0-SNAPSHOT/bin/
./zeppelin-daemon.sh start
```

- Refer to the manual [OperationGuide](https://github.com/sql-machine-learning/zeppelin-sqlflow/blob/develop/doc/OperationGuide.md) for specific use steps.

## Developer introduction
![在这里插入图片描述](https://img-blog.csdnimg.cn/20210324184811730.png)

The project was initiated by the foundation platform development team of the IT department in PCCC. It aims to strengthen the function expansion of the Zeppelin in data exploration and model training，which provides data scientists with more comprehensive and rich data processing tools.