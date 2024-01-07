# import dash
# from dash import html,dash_table
# import dash_bootstrap_components as dbc
# import mysql.connector
# from mysql.connector import Error

# # Función para crear una conexión a la base de datos
# def create_connection(host_name, user_name, user_password, db_name, db_port):
#     connection = None
#     try:
#         connection = mysql.connector.connect(
#             host=host_name,
#             user=user_name,
#             passwd=user_password,
#             database=db_name,
#             port=db_port
#         )
#         print("Connection to MySQL DB successful")
#     except Error as e:
#         print(f"The error '{e}' occurred")
#     return connection

# # Conectar a la base de datos
# conexion = create_connection("yourminifactory.mysql.database.azure.com", "Administrador", "a5min#2023", "yourminifactory", 3306)

# def execute_read_query(connection, query):
#     cursor = connection.cursor()
#     result = None
#     try:
#         cursor.execute(query)
#         result = cursor.fetchall()
#         return result
#     except Error as e:
#         print(f"The error '{e}' occurred")

# UsuariosyTribus_s = """
# SELECT nombre AS user, descripcion AS name_tribu
# FROM usuario JOIN tribu ON usuario.id_tribu = tribu.id_tribu;
# """
# UsuariosyTribus = execute_read_query(conexion, UsuariosyTribus_s)
# data_dict3 = [{"Usuario": Usuario, "Tribu": Tribu} for Usuario, Tribu in UsuariosyTribus]
# Table3 = html.Div([
#     dash_table.DataTable(
#         id='table',
#         columns=[{"name": "Usuario", "id": "Usuario"}, {"name": "Tribu", "id": "Tribu"}],
#         data=data_dict3
#         ) 
#     ])

# # Estilos externos
# external_stylesheets = [dbc.themes.BOOTSTRAP]

# # Menú desplegable
# dropdown = dbc.DropdownMenu(
#     children=[
#         dbc.DropdownMenuItem("OBJETO 3D", href="#"),
#         dbc.DropdownMenuItem("CUSTOMIZER", href="#"),
#         dbc.DropdownMenuItem("IMAGEN", href="#"),
#         dbc.DropdownMenuItem("ARTÍCULO", href="#"),
#     ],
#     nav=True,
#     in_navbar=True,
#     label="UPLOAD",
#     className="dropdown"
# )

# # Barra de navegación
# navbar = dbc.Navbar(
#     dbc.Container(
#         [
#             #Imagen del logo
#             dbc.NavbarBrand(
#                 html.Img(
#                     src='assets/Resources/logo.svg',
#                     height="30px",
#                     className="logo"
#                     ),href="/home"),
#             dbc.NavbarToggler(id="navbar-toggler"),
#             dbc.Collapse(
#                 dbc.Nav(
#                     [
#                         dbc.NavItem(dbc.NavLink("TRIBES", href="/tribus")),
#                         dbc.NavItem(dbc.NavLink("TIENDA", href="/comprar")),
#                         dbc.NavItem(dbc.NavLink("CONSULTAS", href="/consultas")),
#                         dbc.NavItem(dbc.NavLink("CARRITO COMPRAS", href="/carrito_compras")),
#                     ],
#                     className="me-auto",
#                     navbar=True
#                 ),
#                 id="navbar-collapse",
#                 navbar=True,
#                 is_open=False
#             ),
#             # Formulario de búsqueda y botones de acción
#             dropdown,
#             dbc.Row(
#                 [
#                     dbc.Col(dbc.NavLink("JOIN", href="/join", className="me-2"),className="sep_2"),
#                     dbc.Col(dbc.NavLink("LOGIN", href="/login"),className="sep_2"),
#                     # Icono del carrito, si lo necesitas puedes añadirlo como un dbc.NavItem
#                 ],
#                 align="center",
#                 className="g-0 ms-auto flex-nowrap mt-3 mt-md-0 sep",
#             ),
#         ],
#         fluid=True,  # Establece el contenedor como fluido para que ocupe todo el ancho
#         className="Container_header"
#     ),
#     color="light",
#     dark=False,
#     sticky="top",
#     expand="lg",  # Asegúrate de que la barra de navegación se expanda en pantallas grandes
#     className="Container_header"
# )

# Ventas_por_Año_S= """
# SELECT YEAR(fecha_pago) as Anio, SUM(total) as IngresoTotal
# FROM carro_compra
# GROUP BY YEAR(fecha_pago)
# ORDER BY Anio DESC;
# """
# Campañas_dinerosRecaudados_S = """
# SELECT descricipcion, sum(dinerorecaudado) as total
# FROM campana 
# GROUP BY descricipcion
# ORDER BY total DESC;
# """

# Ventas_por_Año = execute_read_query(conexion, Ventas_por_Año_S)
# Campañas_dinerosRecaudados = execute_read_query(conexion, Campañas_dinerosRecaudados_S)

# data_dict = [{"Año": year, "Ingreso": income} for year, income in Ventas_por_Año]
# Table = html.Div([
#     dash_table.DataTable(
#         id='table',
#         columns=[{"name": "Año", "id": "Año"}, {"name": "Ingreso", "id": "Ingreso"}],
#         data=data_dict
#     )
# ])
# data_dict2 = [{"Campaña": Campaña, "Total": Total} for Campaña, Total in Campañas_dinerosRecaudados]
# Table2 = html.Div([
#     dash_table.DataTable(
#         id='table',   
#         columns=[{"name": "Campaña", "id": "Campaña"}, {"name": "Total", "id": "Total"}],
#         data=data_dict2,
#         style_cell={'textAlign': 'left'},
#         style_data_conditional=[
#             {
#                 'if': {'column_id': 'descricipcion'},
#                 'fontSize': 20,  # Tamaño de la fuente de la columna "Campaña"
#                 'fontWeight': 'bold'  # Poner el texto en negrita
#             }
#         ]
#     )
# ])

# UsuariosyTribus_s = """
# SELECT nombre AS user, descripcion AS name_tribu
# FROM usuario JOIN tribu ON usuario.id_tribu = tribu.id_tribu;
# """
# UsuariosyTribus = execute_read_query(conexion, UsuariosyTribus_s)
# data_dict3 = [{"Usuario": Usuario, "Tribu": Tribu} for Usuario, Tribu in UsuariosyTribus]
# Table3 = html.Div([
#     dash_table.DataTable(
#         id='table',
#         columns=[{"name": "Usuario", "id": "Usuario"}, {"name": "Tribu", "id": "Tribu"}],
#         data=data_dict3
#         ) 
#     ])

# accordion = html.Div([
#     html.H1("Default Queries"),
#     dbc.Accordion(
#         [
#             dbc.AccordionItem(
#                 [
#                     dbc.Row(
#                         [
#                             dbc.Col(
#                                 Table, className="Margin-left"
#                             ),
#                             dbc.Col(
#                                 html.Div(
#                                     html.Pre(
#                                         """
#                                         SELECT YEAR(fecha_pago) as Anio, SUM(total) as IngresoTotal
#                                         FROM carro_compra
#                                         GROUP BY YEAR(fecha_pago)
#                                         ORDER BY Anio DESC;
#                                         """
#                                     ),
#                                 ),className="Margin-right"
#                             )
#                         ],
#                         justify="center",
#                         className="Container_center"
#                     )
#                 ],
#                 title="Ventas por año",
#             ),
#             dbc.AccordionItem(
#                 [
#                     dbc.Row(
#                         [
#                             dbc.Col(
#                                 Table2, className="Margin-left"
#                             ),
#                             dbc.Col(
#                                 html.Div(
#                                     html.Pre(
#                                         """
#                                             SELECT descricipcion, sum(dinerorecaudado) as total
#                                             FROM campana 
#                                             GROUP BY descricipcion
#                                             ORDER BY total DESC; """
#                                     ),
#                                 ),className="Margin-right"
#                             )
#                         ],
#                         justify="center",
#                         className="Container_center"
#                     )
#                 ],
#                 title="Dinero Recauado por Compaña",
#             ),
#             dbc.AccordionItem(
#                 [
#                     dbc.Row(
#                         [
#                             dbc.Col(
#                                 Table3, className="Margin-left"
#                             ),
#                             dbc.Col(
#                                 html.Div(
#                                     html.Pre(
#                                         """
#                                             SELECT nombre AS Usuario, descripcion AS Tribu
#                                             FROM usuario JOIN tribu ON usuario.id_tribu = tribu.id_tribu; """
#                                     ),
#                                 ),className="Margin-right"
#                             )
#                         ],
#                         justify="center",
#                         className="Container_center"
#                     )
#                 ],
#                 title="Usuarios y las tribus a las que pertenecen",
#             ),
#         ],
#     )],className="Container_center_c"
# )

# # Agregar estilos CSS para asegurar que no haya margen o relleno afectando al navbar
# layout = html.Div([
#     navbar,accordion
# ], style={'margin': '0', 'padding': '0'})

