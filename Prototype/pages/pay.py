import dash
from dash import html
import dash_bootstrap_components as dbc
from dash import html, dcc, Input, Output, State, callback,dash
from dash.dependencies import MATCH
import pages.carrito_compras as carrito_compras

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
                    ),href="/home"),
            dbc.NavbarToggler(id="navbar-toggler"),
            dbc.Collapse(
                dbc.Nav(
                    [
                        dbc.NavItem(dbc.NavLink("TRIBES", href="/tribus")),
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
total = carrito_compras.getTotalAmount()
pay_section = html.Div([
    html.H2('Secure Payment', style={'textAlign': 'center'}),
    html.Div([
        html.Div([
            html.Label('Card Number'),
            dcc.Input(placeholder='Enter card number...', type='text')
        ], style={'padding': '10px'}),
        html.Div([
            html.Label('Expiry Date'),
            dcc.Input(placeholder='MM/YY', type='text')
        ], style={'padding': '10px'}),
        html.Div([
            html.Label('CVV'),
            dcc.Input(placeholder='CVV', type='text')
        ], style={'padding': '10px'}),
        html.Div([
            html.Label('Cardholder Name'),
            dcc.Input(placeholder='Enter cardholder name...', type='text')
        ], style={'padding': '10px'})
    ], style={'display': 'flex', 'flexDirection': 'column'}),
    html.Div([
        dcc.Dropdown(
            options=[
                {'label': 'Visa', 'value': 'VISA'},
                {'label': 'MasterCard', 'value': 'MC'},
                # Add other card types here
            ],
            placeholder='Select card type'
        )
    ], style={'padding': '10px'}),
    html.Div([
        html.Label('Total Amount: ' + str(total) + ' USD')
    ], style={'padding': '10px', 'fontWeight': 'bold'}),
    html.Button('Pay Now', id='pay-button', n_clicks=0, style={'width': '100%', 'padding': '10px'})
], style={'width': '400px', 'margin': '0 auto', 'boxShadow': '0px 0px 5px #ccc', 'padding': '20px'})

# Agrega el placeholder al layout
layout = html.Div([
    navbar,
    pay_section,
], style={'margin': '0', 'padding': '0'})

