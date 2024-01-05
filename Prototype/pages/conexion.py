import mysql.connector
from mysql.connector import Error

# Función para crear una conexión a la base de datos
def create_connection(host_name, user_name, user_password, db_name, db_port):
    connection = None
    try:
        connection = mysql.connector.connect(
            host=host_name,
            user=user_name,
            passwd=user_password,
            database=db_name,
            port=db_port
        )
        print("Connection to MySQL DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")
    return connection

# Conectar a la base de datos
conexion = create_connection("yourminifactory.mysql.database.azure.com", "Administrador", "a5min#2023", "yourminifactory", 3306)

# Consultas


UsuariosyTribus_s = """
SELECT nombre AS user, descripcion AS name_tribu
FROM usuario JOIN tribu ON usuario.id_tribu = tribu.id_tribu;
"""

# Función para ejecutar consultas
def execute_read_query(connection, query):
    cursor = connection.cursor()
    result = None
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except Error as e:
        print(f"The error '{e}' occurred")

# Ejecutar las consultas
UsuariosyTribus = execute_read_query(conexion, UsuariosyTribus_s)
print(UsuariosyTribus)
# Imprimir los resultados
print("Ventas por Ventas_por_Año:")

print("\nUsuarios Más Activos:")
for row in UsuariosyTribus:
    print(row)
