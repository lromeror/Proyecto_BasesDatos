import dash
from dash import html
import dash_bootstrap_components as dbc

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

full_width_image = html.Div(
    dbc.Container(
        [
            dbc.Row(
                dbc.Col(
                    html.Img(
                        src="assets/img_Picture/nuevoDiagrama.jpeg",  # Reemplaza con la ruta de tu imagen
                        style={"width": "100%", "height": "auto"}
                    ),
                    width=12
                )
            )
        ],
        fluid=True,  # Esto hace que el contenedor sea de ancho completo
    ),
    className="full-width-image-container"  # Clase opcional para aplicar estilos adicionales
)

# Agregar estilos CSS para asegurar que no haya margen o relleno afectando al navbar
layout = html.Div([
    navbar,
    full_width_image
], style={'margin': '0', 'padding': '0'})


