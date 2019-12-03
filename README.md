# MySQLProj
Simple GUI and MySQL stuff for our group project

Some notes about the MySQL loading and usage:
  - You can load the data into whatever schema name you choose.
    - The only thing is you must use the 'DDL.sql' file under 'sqlData' folder
    - In order to load the data you must then use the 'Load.sql' file under 'sqlData'
      - This requires you to edit all refrences of './' to the root folder where these scripts that the 'data' folder exist.
    - The application when started will make assumptions that you are logging in with a 'student' and 'password' with server address being local host. The other assumption is the schema name. You can change all of these under the settings menu.
    
The most simple way to run the demo would be to follow this tutorial: https://youtu.be/d7v0sK62bwQ
  - We simply use a mySqlWorkbench dump to export the database and import it into a new system. 
  - This makes handling things like directories and where files are located very easy
