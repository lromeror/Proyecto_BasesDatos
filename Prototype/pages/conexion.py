import mysql.connector
conexion = mysql.connector.connect(user="root", password="Angelo2023",
                                    host="localhost",
                                    database="YOURMINIFACTORY",
                                    port=3306)

print(conexion)