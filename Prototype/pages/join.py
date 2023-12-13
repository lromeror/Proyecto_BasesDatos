import dash
from dash import html
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

# Contenido de la columna del formulario de registro
form_col = dbc.Col(
    [
        html.H3("Hi! Last step"),
        dbc.Form([
            dbc.CardGroup([
                dbc.Label("Name", className="form-label"),
                dbc.Input(type="text", placeholder="Nombre", required=True),
            ]),
            dbc.CardGroup([
                dbc.Label("Email", className="form-label"),
                dbc.Input(type="email", placeholder="Correo electrónico", required=True),
            ]),
            dbc.CardGroup([
                dbc.Label("Password", className="form-label"),
                dbc.Input(type="password", placeholder="Contraseña", required=True),
            ]),
            dbc.ButtonGroup([
                dbc.Button("BACK", color="secondary"),
                dbc.Button("JOIN", color="success"),
            ], className="mt-3"),
        ])
    ],
    md=6,  
)

# Layout principal
body = dbc.Container(
    dbc.Row([benefits_col, form_col]),
    className="mt-5",  
)
# Agregar estilos CSS para asegurar que no haya margen o relleno afectando al navbar
layout = html.Div([
    navbar,body
], style={'margin': '0', 'padding': '0'})

