import dash_bootstrap_components as dbc
from dash import html, dcc, Input, Output, State, callback,dash

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

login_form = dbc.Container(
    [
        dbc.Row(
            dbc.Col(
                html.H2("Log in to MyMiniFactory", className="text-center mb-4"),
                width=6, lg={'size': 4, 'offset': 4}
            )
        ),
        dbc.Row(
            dbc.Col(
                dbc.CardGroup(
                    [
                        dbc.Label("Username or E-mail address", html_for="username-email", width=8),
                        dbc.Input(
                            type="text",
                            id="username-email",
                            placeholder="Enter username or email",
                            className="mb-3",  # Agrega margen en la parte inferior
                        ),
                    ],
                ),
                width=6, lg={'size': 4, 'offset': 4}
            )
        ),
        dbc.Row(
            dbc.Col(
                dbc.CardGroup(
                    [
                        dbc.Label("Password", html_for="password", width=4),
                        dbc.Input(
                            type="password",
                            id="password",
                            placeholder="Enter password",
                            className="mb-3",  # Agrega margen en la parte inferior
                        ),
                    ],
                ),
                width=6, lg={'size': 4, 'offset': 4}
            )
        ),
        dbc.Row([
            dbc.Col(
                dbc.Button("LOGIN", color="primary", className="btn-block mb-3", id="submit-button"),
                width=6, lg={'size': 4, 'offset': 4},id="Colbot"
            ),
            dbc.Col(
                id="label_",width=6, lg={'size': 4, 'offset': 4}
            )]
        ),
        dbc.Row(
            dbc.Col(
                html.Div(
                    [   dbc.NavLink([ 
                            html.Div(dbc.Label("Don't have an account? ", className="mb-3"), className="margin-top"),
                            dbc.Button("JOIN NOW", color="link", className="mt-3", id="join-button")],
                            href="/join",className="text-center")
                    ],
                ),
                width=6, lg={'size': 4, 'offset': 4}
            ),
        ),
    ],
    fluid=True,
    className="my-5",
)

@callback(
    [Output('url', 'pathname'), Output('label_', 'children')],
    [Input('submit-button', 'n_clicks')],
    [State('username-email', 'value'), State('password', 'value')],
    prevent_initial_call=True
)
def login(n_clicks, username, password):
    if n_clicks and username and password:
        return '/home', None
    elif n_clicks:
        return dash.no_update, "There are empty fields!"
    return dash.no_update, None

# Layout del archivo login.py
layout = html.Div([navbar, login_form])