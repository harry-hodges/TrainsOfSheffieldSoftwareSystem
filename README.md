# Team 060 Sheffield e-Train Store System
Welcome to Team 60 Sheffield e-Train Store System.

## How to clone our repository?
If you want to clone our repository, please follow these steps below:
1. Once you are in Team 60's Project repository, click on the green "Clone" button next to the download button.
  - You can git clone using HTTPS or SSH key.
2. Click on the button next to the link, to copy the url

3. Go to your terminal and type "git clone <LINK>". For example:
  - If you are using HTTPS:
```console
git clone https://github.com/ewills1/systems-project.git
```
  - If you are using the SSH key:
```console
git clone git@github.com:ewills1/systems-project.git
```

## How to run our web application?
**Commands to run in the terminal:**
1. This will redirect you into our project folder.
```console
cd system-project
```
2. Then import mySQL connector library (.jar) file that has been provided in the zip file to the referenced library of this project (system-project).

3. If not using the database from (team060 db schema), run the sql on the 'systems-project\project\Database.sql' on your database engine (like MySQL Workbench). If no error encountered, proceed to the next step.

4. Then execute the Main.java class.

5. This only created account is manager. The remaining account will need to go through the self-registration process. But here is the default account can be used for testing.

| Email                    | Default Password         |
| -------------------------| -------------------------|
| manager@manager.com      | 123456                   |
| staff@staff.com          | 123456                   | - might not available for a fresh db
| user@user.com            | 123456                   | - might not available for a fresh db


5. Closing any close icon will terminate the program and the user need to go through the login process back to access the system dashboard or prouct listing (as users).
