import dash

import dash_bootstrap_components as dbc
from dash import html, dcc, Input, Output, State, callback,dash

import funtions as f

# Estilos externos
external_stylesheets = [dbc.themes.BOOTSTRAP]

# Menú desplegable
dropdown = dbc.DropdownMenu(
    children=[
        dbc.DropdownMenuItem("OBJETO 3D", href="/model"),
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
            dropdown,
            dbc.Row(
                [
                    dbc.Col(dbc.NavLink("JOIN", href="/join", className="me-2"),className="sep_2"),
                    dbc.Col(dbc.NavLink("LOGIN", href="/login"),className="sep_2"),
                ],
                align="center",
                className="g-0 ms-auto flex-nowrap mt-3 mt-md-0 sep",
            ),
        ],
        fluid=True,  
        className="Container_header"
    ),
    color="light",
    dark=False,
    sticky="top",
    expand="lg",  
    className="Container_header"
)


conexion = f.create_connection("yourminifactory.mysql.database.azure.com", "Administrador", "a5min#2023", "yourminifactory", 3306)
queytribues = 'SELECT * FROM tribu;'
l_tribus = f.execute_read_query(conexion,queytribues);

def get_image_path(index):
    return f"assets/Images_tribes/{index}.png"

def create_tribe_card(tribe, index):
    return dbc.Card(
        [
            dbc.CardImg(src=f"assets/Images_tribes/{index}.png", top=True),
            dbc.NavLink(
                dbc.CardBody(
                    [
                        html.H5(f"{tribe[0]} - {tribe[2]}", className="card-title"),
                        html.P(f"Members: {tribe[3]}", className="card-text"),
                    ]
                ),
                href="/pay"
            ),
        ],
        style={"width": "18rem",'heigth':'18rem','margin' : '10'}
    )

# Updated loop to use the create_tribe_card function
card_rows = []
current_row = []
for index, tribe in enumerate(l_tribus, start=1):
    card = create_tribe_card(tribe, index)
    current_row.append(dbc.Col(card, width=3))

    if index % 4 == 0:
        card_rows.append(dbc.Row(current_row, className="mb-4"))
        current_row = []

if current_row:
    card_rows.append(dbc.Row(current_row, className="mb-4"))

card_deck = html.Div(card_rows,className="Margin-left")

        
    
layout = html.Div([
    navbar,
    card_deck
], style={'margin': '0', 'padding': '0'})

