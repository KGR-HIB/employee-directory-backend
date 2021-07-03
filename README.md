# Employee Directory Backend

## Build
```
./gradlew clean build
```

## Run

### Linux
```
./run.sh
```

### Windows - PowerShell
```
./run.ps1
```

## Develop to Heroku

### Install plugin
```
heroku plugins:install java
```

### Create app
```
heroku apps:create hbredapp
```

### Deploy
```
heroku deploy:jar employee-directory-services/build/libs/employee-directory-services-1.0.0-SNAPSHOT.jar --app hbredapp
```



## API

**Context:** /directoryServices

| Controller    | Description                                | Method | Endpoint                                         | Request body          | Response body                   | Srtatus |
| ------------- | ------------------------------------------ | ------ | ------------------------------------------------ | --------------------- | ------------------------------- | ------- |
| Position      | Positions list                             | GET    | /api/v1/positions                                |                       | Response<[Position]>            | ✅      |
| Department    | Departments list                           | GET    | /api/v1/departments                              |                       | Response<[Department]>          | ✅      |
| Project       | Projects list                              | GET    | /api/v1/projects                                 |                       | Response<[Project]>             | ✅      |
| Certification | Certifications list                        | GET    | /api/v1/certifications                           |                       | Response<[Certification]>       | ✅      |
| Skill         | Skills list                                | GET    | /api/v1/skills                                   |                       | Response<[Skill]>               | ✅      |
| City          | Cities list                                | GET    | /api/v1/cities                                   |                       | Response<[City]>                | ✅      |
| Employee      | Employee list                              | GET    | /api/v1/employees?query={query}                  |                       | Response<[SimpleEmployee]>      | ✅      |
| Employee      | Page employee list                         | POST   | /api/v1/employees/page?query={query}&page={page} | EmployeFilter         | Response<PageEmployees>         |         |
| Employee      | Get info employee                          | GET    | /api/v1/employees/{id}                           |                       | Response<[Employee]>            |         |
| Employee      | Create employee                            | POST   | /api/v1/employees/createOrUpdate                 | EmployeeManage        | Response<null>                  | ✅      |
| Employee      | Update primary information for an employee | POST   | /api/v1/employees/createOrUpdate                 | EmployeeManage        | Response<null>                  | ✅      |
| Employee      | Add a project to an employee               | POST   | /api/v1/employees/projects/add                   | EmployeeProject       | Response<EmployeeProject>       | ✅      |
| Employee      | Remove a project to an employee            | POST	  | /api/v1/employees/projects/add                   |                       | Response<null>                  | ✅       |
| Employee      | Add a certification to an employee         | POST   | /api/v1/employees/certifications                 | EmployeeCertification | Response<EmployeeCertification> |         |
| Employee      | Remove a certification to an employee      | DELETE | /api/v1/employees/certifications/{id}            |                       | Response<null>                  |         |
| Employee      | Add a skill to an employee                 | POST   | /api/v1/employees/skills                         | EmployeeSkill         | Response<EmployeeSkill>         |         |
| Employee      | Remove a skill to an employee              | DELETE | /api/v1/employees/skills/{id}                    |                       | Response<null>                  |         |
| Authorization | Login                                      | POST   | /api/auth/login                                  | {mail, password}      | Response<User>                  | ✅      |
| Authorization | Logout                                     | GET    | /api/auth/logout                                 |                       |                                 | ✅      |



### Schemas

#### User

```json
{
  "id": 1,
  "email": "lrodriguez@hiberus.com",
  "password": "123",
  "loginFirstTime": true,
  "role": Role
}
```

#### Functionality

```json
{
  "id": 1,
  "code": "employee-create",
  "description": "Crear un nuevo empleado"
}
```

### Role

```json
{
  "id": 1,
  "code": "admin",
  "name": "Admin",
  "functionalities": [
    {
      "id": 1,
      "code": "employe-create"
      "description": "Crear un nuevo empleado"
    },
    {
      "id": 2,
      "code": "list-directory"
      "description": "Listar directorio"
    },
  ]
}
```

### Position

```json
{
	"id": 1,
	"name": "Frontend Developer"
}
```

### Department

```json
{
  "id": 1,
  "name": "Operaciones"
}
```

### Project

```json
{
    "employeeId": 6,
    "projects": [
        {	
			"id": 1,
            "name": "Directorio de empleados"
        },
        {
            "name": "iOS SMX"
        }
    ]
}
```

### Certification

```json
{
  "id": 1,
  "name": "Spring Boot"
}
```

### Skill

```json
{
	"id": 1,
	"name": "Angular"
}
```

### City

```json
{
	"id": 1,
	"name": "Quito"
}
```



### Employee

```json
{
  "id": 1,
  "name": "Luis Miguel",
  "lastName": "Rodríguez Paredes",
  "phone": "593996123456",
  "photo": "base64",
  "user": {
    "id": 1,
    "email": "lrodriguez@hiberus.com",
    "roleCode": "admin"
  },
  "city": {
    "id": 1,
    "name": "Quito"
  },
  "position": {
    "id": 1,
    "name": "Frontend Developer"
  },
  "department": {
    "id": 1,
    "name": "Operaciones"
  },
  "projects": [
    {
      "id": 1,
      "name": "Directorio de empleados"
    },
    {
      "id": 2,
      "name": "Ecomerce"
    }
  ],
  "certifications": [
    {
      "id": 1,
      "name": "Spring Boot"
    },
    {
      "id": 2,
      "name": "Angular"
    }
  ],
  "skills": [
    {
      "id": 1,
			"name": "Angular"
		},
    {
      "id": 2,
			"name": "Java"
		}
  ]
}
```



### EmployeeFilter

```json
{
  "positions": [1,2,3],
  "deparments": [1,2,3],
  "projects": [1,2,3],
  "cities": [1,2,3],
  "skills": [1,2,3],
  "certifications": [4,25]
}
```

### SimpleEmployee

```json
{
  "id": 1,
  "name": "Luis Miguel",
  "lastName": "Rodríguez Paredes",
  "mail": "lrodriguez@hiberus.com",
  "phone": "593996123456",
  "departmentName": "Operaciones",
  "positionName": "Frontend Developer",
  "photo": null
}
```



### PageEmployees

```json
{
  "employes": [SimpleEmploye],
  "total": 100
}
```

### EmployeeManage

```json
{
    "name": "LEO",
    "lastName": "Messi",
    "phone": "593996123456",
    "department": {
        "name": "FIFA"
    },
    "position": {
        "name": "Delantero"
    },
    "city": {
        "name": "Rosario"
    },
    "user": {
        "email": "messi@hiberus.com",
        "password": "Password01",
        "roleId": 1
    },
    "immediateChiefId": 1
}
```



### Response

```json
{
  "data": ?,
  "success": true,
  "message": "Algun mensaje"
}
```
