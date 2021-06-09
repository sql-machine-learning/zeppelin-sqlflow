The use are divided into the following steps

1. Enter the Zeppelin home page, click the user button in the upper right corner, and select “**interpreter**” to enter the parameter configuration page.
![interpreter](https://user-images.githubusercontent.com/65579930/112589464-18c9ea80-8e3c-11eb-9b61-b39d5ceb731a.jpg)

2. Fill in 5 configuration parameters, namely:
- **sqlflow.serverAddr**: sqlflow server address
- **mysql.username**: database login user name
- **mysql.password**: database login password
- **mysql.serverAddr**: database server address
- **mysql.databaseName**: database name
   ![参数配置页面2](https://user-images.githubusercontent.com/65579930/119923915-3aaf2d00-bfa5-11eb-92ff-78f403ad555f.jpg)
   
3. Go back to home page and create a new note. Select `sqlflow` as the default interpreter, which is shown in the figure below:
![create](https://user-images.githubusercontent.com/65579930/112590118-419eaf80-8e3d-11eb-80a4-9611ffde8115.jpg)
![Create_New_Note](https://user-images.githubusercontent.com/65579930/112590123-4499a000-8e3d-11eb-96ff-a30868244ce8.jpg)

4. Start to use sqlflow syntax in the open notebook, and then run to perform a functional test.
As the case shown in the figure below, the `DNNClassifier` classifier is trained on the [iris](https://en.wikipedia.org/wiki/Iris_flower_data_set) data set, and the trained `DNNClassifier` is used to predict three types of iris. The drawing can more intuitively show the incidence of each feature field on the model prediction more intuitively.

    Note: Each paragraph must begin with "**%sqlflow**".
    ![twoparagraph](https://user-images.githubusercontent.com/65579930/112591081-c4743a00-8e3e-11eb-82d7-a7d1cadf9923.jpg)
