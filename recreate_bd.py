import mysql.connector
from mysql.connector import errorcode

def recreate_database():
    try:
        # Conectar a MySQL
        cnx = mysql.connector.connect(user='root', host='localhost')
        cursor = cnx.cursor()

        # Eliminar la base de datos si existe
        cursor.execute("DROP DATABASE IF EXISTS bd_usuarios")
        print("Base de datos 'bd_stock' eliminada (si existía).")

        # Crear la base de datos de nuevo
        cursor.execute("CREATE DATABASE bd_usuarios")
        print("Base de datos 'bd_usuarios' creada.")

        # Cerrar el cursor y la conexión
        cursor.close()
        cnx.close()

    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
            print("Error: Problema con el usuario o la contraseña")
        elif err.errno == errorcode.ER_BAD_DB_ERROR:
            print("Error: La base de datos no existe")
        else:
            print(err)

if __name__ == "__main__":
    recreate_database()
