import dash
from dash import html
import dash_bootstrap_components as dbc
from dash import html, dcc, Input, Output, State, callback,dash
from dash.dependencies import MATCH

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
                        dbc.NavItem(dbc.NavLink("TIENDA", href="#")),
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
tribe_options = {
    "Silver": {
        "price": "Include VAT/Sales Tax (if applicable)",
        "features": [
            "Welcome Package",
            "Monthly Mini with Pre-Supported & Test-Printed STLs",
            "Illustrated Tabletop Roleplaying Stat Sheet",
            "Monthly one-month free trial membership invitation to one (or more!) small/new/upcoming MMF Tribe via the Creator Boost program!"
        ],
        "description": "Get a brand new, ultra-high detail 3x3 or larger 'Miniature' STL, and access to our Creator Boost program every month!"
    },
    "Gold": {
        "price": "Include VAT/Sales Tax (if applicable)",
        "features": [
            "Welcome Package",
            "Monthly Mini with Pre-Supported & Test-Printed STLs",
            "Oversized Bust STL of Monthly Mini",
            "Illustrated Tabletop Roleplaying Stat Sheet",
            "Monthly one-month free trial membership invitation to one (or more!) small/new/upcoming MMF Tribe via the Creator Boost program!",
            "50% Store Discount (found in pinned post)",
            "4 (or more!) Bonus Reward 'Miniatures' + Oversized Busts Yearly"
        ],
        "description": "Get a brand new, ultra-high detail 3x3 or larger 'Miniature' STL every month and their oversized Bust, access to our Creator Boost program, a 50% discount on our Store, and access to Seasonal and Bonus Rewards!"
    },
    "Platinum": {
        "price": "Include VAT/Sales Tax (if applicable)",
        "features": [
            "Welcome Package",
            "Monthly Mini with Pre-Supported & Test-Printed STLs",
            "Oversized Bust STL of Monthly Mini",
            "Illustrated Tabletop Roleplaying Stat Sheet",
            "Monthly one-month free trial membership invitation to one (or more!) small/new/upcoming MMF Tribe via the Creator Boost program!",
            "50% Store Discount (found in pinned post)",
            "4 (or more!) Bonus Reward 'Miniatures' + Oversized Busts Yearly"
        ],
        "description": "Get a brand new, ultra-high detail 3x3 or larger 'Miniature' STL every month and their oversized Bust, access to our Creator Boost program, a 50% discount on our Store, and access to Seasonal and Bonus Rewards! Plus help Witchsong/The Witchguild grow and assist the 3D-printing community!"
    }
}

def create_tribe_card(title, details):
    return dbc.Card(
        [
            dbc.CardHeader(title, className="text-center"),
            dbc.CardBody(
                [
                    html.P(details["price"], className="card-text"),
                    html.P(details["description"], className="card-text"),
                    html.Hr(),
                    html.H5("Items you will have access to:", className="card-title"),
                    html.Ul([html.Li(feature) for feature in details["features"]]),
                    dbc.Button("Join", color="primary", id=f"type-{title}"),
                ]
            ),
        ],
        className="m-2"
    )

tribes_section = dbc.Container(
    [
        dbc.Row(dbc.Col(html.H1("Join the Tribe"), width={"size": 6, "offset": 3}, className="text-center")),
        dbc.Row(
            [
                html.Div(id='join-message', style={'text-align': 'center'})
            ]
        ),
        dbc.Row(
            [
                dbc.Col(create_tribe_card("Silver", tribe_options["Silver"]), width=4),
                dbc.Col(create_tribe_card("Gold", tribe_options["Gold"]), width=4),
                dbc.Col(create_tribe_card("Platinum", tribe_options["Platinum"]), width=4),
            ],
            className="mb-4",
        ),
    ],
    fluid=True
)
# Agregar estilos CSS para asegurar que no haya margen o relleno afectando al navbar
# Placeholder para el mensaje


@callback(
    Output('join-message', 'children'),
    Input("type-Silver", 'n_clicks'),
    Input("type-Gold", 'n_clicks'),
    Input("type-Platinum", 'n_clicks'),
    prevent_initial_call=True
)
def on_join_click(silver_clicks, gold_clicks, platinum_clicks):
    if silver_clicks or gold_clicks or platinum_clicks:
        return f"Success in your new tribe !! "

# Agrega el placeholder al layout
layout = html.Div([
    navbar,
    tribes_section,
], style={'margin': '0', 'padding': '0'})
