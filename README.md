# Model

## Entities

* User
* Role
* Functionality
* Employee
* Position
* Department
* Project
* Certification
* Skill
* City

### User

```json
{
  "userId": 1,
  "mail": "lrodriguez@hiberus.com",
  "password": "123",
  "hasInitSession": true,
  "role": {}
}
```

### Funcitonality

```json
{
  "functionalityId": 1,
  "code": "employee-create",
  "description": "Crear un nuevo empleado"
}
```

### Role

```json
{
  "roleId": 1,
  "code": "admin",
  "name": "Admin",
  "funtionalities": [
    {
      "functionalityId": 1,
      "code": "employe-create"
      "description": "Crear un nuevo empleado"
    },
    {
      "functionalityId": 2,
      "code": "list-directory"
      "description": "Listar directorio"
    },
  ]
}
```

### Position

```json
{
	"positionId": 1,
	"name": "Frontend Developer"
}
```

### Department

```json
{
  "departmentId": 1,
  "name": "Operaciones"
}
```

### Project

```json
{
  "projectId": 1,
  "name": "Directorio de empleados"
}
```

### Certification

```json
{
  "certificationId": 1,
  "name": "Spring Boot"
}
```

### Skill

```json
{
	"skillId": 1,
	"name": "Angular"
}
```

### City

```json
{
	"cityId": 1,
	"name": "Quito"
}
```



### Employee

```json
{
  "employeeId": 1,
  "name": "Luis Miguel",
  "lastName": "Rodríguez Paredes",
  "phone": "593996123456",
  "photo": "base64",
  "user": {
    "userId": 1,
    "mail": "lrodriguez@hiberus.com",
    "roleCode": "admin"
  },
  "city": {
    "cityId": 1,
    "name": "Quito"
  },
  "position": {
    "positionId": 1,
    "name": "Frontend Developer"
  },
  "department": {
    "departmentId": 1,
    "name": "Operaciones"
  },
  "projects": [
    {
      "projectId": 1,
      "name": "Directorio de empleados"
    },
    {
      "projectId": 2,
      "name": "Ecomerce"
    }
  ],
  "certifications": [
    {
      "certificationId": 1,
      "name": "Spring Boot"
    },
    {
      "certificationId": 2,
      "name": "Angular"
    }
  ],
  "skills": [
    {
      "skillId": 1,
			"name": "Angular"
		},
    {
      "skillId": 2,
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
  "employeeId": 1,
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
	"name": "Luis Miguel",
  "lastName": "Rodríguez Paredes",
  "mail": "lrodriguez@hiberus.com",
  "phone": "593996123456",
  "deparment": {
    "departmentId": null,
    "name": "Nombre"
  },
  "position": {
    "positionId": null,
    "name": "Nombre"
  },
  "city": {
    "cityId": null,
    "name": "Nombre"
  },
  "immediateChiefId": 1,
  "password": "12341"
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





## Endpoins

### Administración de acceso

```text
Login: POST - /ws/api/v1/login - Request: {mail, password}, Response: Response<User>
Login: GET - /ws/api/v1/logout - Response: Response<null>
```

### Directorio de empleados

```
Búscar empleados: POST - /ws/api/v1/employees?query={query}&page={page} - Request: EmployeFilter, Response: Response<PageEmployes>

Mostrar ficha: GET - /ws/api/v1/employees/{employeeId} - Response: Employee

Lista de cargos: GET - /ws/api/v1/positions - Response: [Position]

Lista de responsable: GET - /ws/api/v1/employees?query={query} - Response [SimpleEmployee] - Backend

Lista de áreas: GET - /ws/api/v1/departments - Response: [Department]

Lista de proyectos: GET - /ws/api/v1/projects - Response: [Project]

Lista de certificaciones: GET - /ws/api/v1/certifications - Response: [Certification]

Lista de tecnologías: GET - /ws/api/v1/skills - Response: [Skill]

Dar de alta un empleado: POST - /ws/api/v1/employees/create - Request EmployeeManage, Response Response<null>

Actualizar información principal: PUT - /ws/api/v1/employes - Request [EmployeeManage], Response Response<null>
```

### Catálogos

```tex
Agregar proyecto - POST - /ws/api/v1/employees/projects - Request [Project], Response: Response<Project>
Eliminar proyecto - DELETE - /ws/api/v1/employees/projects/{projectId} - Response: Response<null>

Agregar certificaciones - POST - /ws/api/v1/employees/certifications - Request [Certification], Response: Response<Certification>
Eliminar certificafción - DELETE - /ws/api/v1/employees/certifications/{certificationId} - Response: Response<null>

Agregar tecnologías - POST - /ws/api/v1/employees/skills - Request [Skill], Response: Response<Skill>
Eliminar tecnología - DELETE - /ws/api/v1/employees/skills/{skillId} - Response: Response<null>

```

