import dash
import mysql.connector
from mysql.connector import Error
from dash import Dash, dcc, html, Input, Output, callback, State,no_update
import dash_bootstrap_components as dbc

# Estilos externos
external_stylesheets = [dbc.themes.BOOTSTRAP]

# Menú desplegable
dropdown = dbc.DropdownMenu(
    children=[
        dbc.DropdownMenuItem("OBJETO 3D", href="#"),
        dbc.DropdownMenuItem("CUSTOMIZER", href="#"),
        dbc.DropdownMenuItem("IMAGEN", href="#"),
        dbc.DropdownMenuItem("ARTÍCULO", href="#"),
    ],
    nav=True,
    in_navbar=True,
    label="UPLOAD",
    className="dropdown"
)

# Barra de navegación
navbar = dbc.Navbar(
    dbc.Container(
        [
            #Imagen del logo
            dbc.NavbarBrand(
                html.Img(
                    src='assets/Resources/logo.svg',
                    height="30px",
                    className="logo"
                    ),href="#"),
            dbc.NavbarToggler(id="navbar-toggler"),
            dbc.Collapse(
                dbc.Nav(
                    [
                        dbc.NavItem(dbc.NavLink("TRIBES", href="/tribu")),
                        dbc.NavItem(dbc.NavLink("TIENDA", href="/comprar")),
                         dbc.NavItem(dbc.NavLink("CONSULTAS", href="/consultas")),
                         dbc.NavItem(dbc.NavLink("CARRITO COMPRAS", href="/carrito_compras")),
                    ],
                    className="me-auto",
                    navbar=True
                ),
                id="navbar-collapse",
                navbar=True,
                is_open=False
            ),
            # Formulario de búsqueda y botones de acción
            dropdown,
            dbc.Row(
                [
                    dbc.Col(dbc.NavLink("JOIN", href="/join", className="me-2"),className="sep_2"),
                    dbc.Col(dbc.NavLink("LOGIN", href="/login"),className="sep_2"),
                    # Icono del carrito, si lo necesitas puedes añadirlo como un dbc.NavItem
                ],
                align="center",
                className="g-0 ms-auto flex-nowrap mt-3 mt-md-0 sep",
            ),
        ],
        fluid=True,  # Establece el contenedor como fluido para que ocupe todo el ancho
        className="Container_header"
    ),
    color="light",
    dark=False,
    sticky="top",
    expand="lg",  # Asegúrate de que la barra de navegación se expanda en pantallas grandes
    className="Container_header"
)

benefits_col = dbc.Col(
    [
        html.H2("Join the Community!"),
        html.P("Register for free"),
        html.Div([
            html.Img(src="assets/Resources/1.png",height='40px'),
            html.P("Thousands of 3D printable designs")
        ],className="line"),
        html.Div([
            html.Img(src="assets/Resources/2.png", height='40px'),
            html.P("Organize your 3D files")
        ],className="line"),
        html.Div([
            html.Img(src="assets/Resources/3.png", height='40px'),
            html.P("Engage with the community")
        ],className="line"),
        html.Div([
            html.Img(src="assets/Resources/4.png", height='40px'),
            html.P("Become 3D designer")
        ],className="line"),
    ],
    md=6,  
)


form_col = dbc.Col(
    [
        html.H3("¡Hola! Último paso"),
        dbc.Form([
            dbc.CardGroup([
                dbc.Label("Nombre", className="form-label"),
                dbc.Input(id='name-input', type="text", placeholder="Nombre", required=True),
            ]),
            dbc.CardGroup([
                dbc.Label("Correo Electrónico", className="form-label"),
                dbc.Input(id='email-input', type="email", placeholder="Correo electrónico", required=True),
            ]),
            dbc.CardGroup([
                dbc.Label("Contraseña", className="form-label"),
                dbc.Input(id='password-input', type="password", placeholder="Contraseña", required=True),
            ]),
            dbc.CardGroup([  # Nuevo campo de fecha de nacimiento
                dbc.Label("Fecha de Nacimiento", className="form-label"),
                dbc.Input(id='birthdate-input', type="date", required=True),
            ]),
            dbc.ButtonGroup([
                dbc.Button("REGRESAR", color="secondary"),
                dbc.Button("UNIRSE", id='join-button', color="success"),
            ], className="mt-3"),
        ])
    ],
    md=6,
)
@callback(
    Output('output-container', 'children'),
    Input('join-button', 'n_clicks'),
    State('name-input', 'value'),
    State('email-input', 'value'),
    State('password-input', 'value'),
    State('birthdate-input', 'value')  
)
def update_output(n_clicks, name, email, password, birthdate):
    if n_clicks is None or n_clicks == 0:
        return no_update
    else:
        conexion = create_connection("yourminifactory.mysql.database.azure.com", "Administrador", "a5min#2023", "yourminifactory", 3306)
        insertar_usuario(conexion, name, password, email, birthdate)
        return no_update

output_container = html.Div(id='output-container')

body = dbc.Container(
    dbc.Row([benefits_col, form_col,output_container]),
    className="mt-5",
)


layout = html.Div([
    navbar, body
], style={'margin': '0', 'padding': '0'})


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


def insertar_usuario(conexion,name,password,email,birthdate):
    try:    
        cursor = conexion.cursor()
        query = "INSERT INTO usuario (nombre, fecha_impresion, contrasena, correo, id_tribu) VALUES (%s, %s, %s,%s,%s)"
        valores = (name,birthdate,password,email,9)
        cursor.execute(query, valores)
        conexion.commit()
        print("Se anandio de forma exitosa")
    except mysql.connector.Error as e:
        print(f"Error al insertar el usuario: {e}")