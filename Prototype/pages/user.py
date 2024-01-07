import dash
from dash import html,dash_table
from dash import Dash, dcc, html, Input, Output, callback, State,no_update
import dash_bootstrap_components as dbc
import mysql.connector
from mysql.connector import Error

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

conexion = create_connection("yourminifactory.mysql.database.azure.com", "Administrador", "a5min#2023", "yourminifactory", 3306)

external_stylesheets = [dbc.themes.BOOTSTRAP]

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

navbar = dbc.Navbar(
    dbc.Container(
        [
            #Imagen del logo
            dbc.NavbarBrand(
                html.Img(
                    src='assets/Resources/logo.svg',
                    height="30px",
                    className="logo"
                    ),href="/home"),
            dbc.NavbarToggler(id="navbar-toggler"),
            dbc.Collapse(
                dbc.Nav(
                    [
                        dbc.NavItem(dbc.NavLink("TRIBES", href="/tribus")),
                        dbc.NavItem(dbc.NavLink("TIENDA", href="/comprar")),
                        dbc.NavItem(dbc.NavLink("CONSULTAS", href="/consultas")),
                        dbc.NavItem(dbc.NavLink("CARRITO COMPRAS", href="/carrito_compras")),
                        dbc.NavItem(dbc.NavLink("USER",href="/user"))
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
                    #dbc.Col(dbc.NavLink("JOIN", href="/join", className="me-2"),className="sep_2"),
                    dbc.Col(dbc.NavLink("HOME", href="/login"),className="sep_2"),
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

choose = dbc.Container([
    dbc.Row(
        dbc.Col(
            [
                dbc.Button("MODEL", id='btn-model1', color="primary", className="me-2"),
                dbc.Button("COMPANY", id='btn-company2', color="secondary")
            ],
            width={"size": 6, "offset": 3},  
            className="d-flex justify-content-center" 
        ),
        className="align-items-center",  
        style={"height": "100px"} 
    ),
    dbc.Row(
        dbc.Col(id='output-container1', width=12),
        justify="center"
    ),
    dbc.Row(
        dbc.Col(id='output-container2', width=12),
        justify="center"
    )
])

@callback(
    Output('output-container1', 'children'),
    [Input('btn-model1', 'n_clicks')],
    prevent_initial_call=True
)
def handle_model_click(btn_model_clicks):
    if btn_model_clicks:
            children=[tab for tab in DinamicoLibreria(conexion)]            
            return dbc.Container(children,fluid=False)
    return None


@callback(
    Output('output-container2', 'children'),
    [Input('btn-company2', 'n_clicks')],
    prevent_initial_call=True
)
def handle_company_click(btn_company_clicks):
    if btn_company_clicks:
        return f'El botón "COMPANY" ha sido clickeado {btn_company_clicks} veces.'
    return "Esperando clic en 'COMPANY'..."


def DinamicoLibreria(conexion):
    query = """SELECT * from libreria;"""
    tuplaInfo=execute_read_query(conexion,query)
    listTablas=[]
    query2 = """select titulo,precio,id_libreria from modelo;"""
    tuplaInfo2=execute_read_query(conexion,query2)
    for fila in tuplaInfo:
       listTablas.append(tablasLibreria(fila[0],tuplaInfo2))
    return listTablas

def tablasLibreria(id_libreria,tuplaInfo2):
    infoFil=[]
    for info in tuplaInfo2:
        if(info[2]==id_libreria):
            infoFil.append(info)
    data = []
    for titulo, price,x in infoFil:
        data.append({"Titulo": titulo, "Precio": price})
    Table = html.Div([
    dash_table.DataTable(
        id='table', 
        data=data
        ) 
    ])
    return Table

def execute_read_query(connection, query):
    cursor = connection.cursor()
    result = None
    try:
        cursor.execute(query)
        result = cursor.fetchall()
        return result
    except Error as e:
        print(f"The error '{e}' occurred")

banner = html.Div(
    dbc.Container(
        html.Div(
            html.Img(
                src='assets/img_Picture/foto.png', 
                style={
                    'width': '50%', 
                    'height': 'auto',  
                    'display': 'block',  
                    'margin-left': 'auto', 
                    'margin-right': 'auto' 
                }
            ),
            className="d-flex justify-content-center" 
        ),
        fluid=True,  
        className="p-0"  
    ),
    style={
        'margin-bottom': '1rem' 
    }
)

layout = html.Div([
    navbar,
    banner,
    choose,
    html.Div([],id='output-container1'), 
    html.Div([],id='output-container2')   
], style={'margin': '0', 'padding': '0'})