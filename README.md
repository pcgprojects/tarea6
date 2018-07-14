# Tarea6
#### Alumno: Roy Rogger Cespedes Portocarrero

#### Código: u201401977

### Sentinel Api
  **URL API Rest: 178.128.155.0/apiv1/public**
Endpoints utilizados:

  1)Autenticación de usuario: /auth (POST)
    
    BodyParameters
      -username
      -password
    
    Response
      -code
      -msg
      -user_id
      -token
      
  2)Creación de usuarios: /user (POST)
  
     BbodyParameters
      -first_name
      -last_name
      -username
      -password
      
     Response
      -msg
      -user_id
