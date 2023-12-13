import dash
from dash import html
import dash_bootstrap_components as dbc
import os

# Estilos externos
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
def get_image_path(index):
    return f"assets/Images_compras/im_{index}.jpg"

models = [
    {'name': 'Modelo 1', 'price': 10.00},
    {'name': 'Modelo 2', 'price': 15.00},
    {'name': 'Modelo 3', 'price': 20.00},
    {'name': 'Modelo 4', 'price': 25.00},
    {'name': 'Modelo 5', 'price': 30.00},
    {'name': 'Modelo 6', 'price': 20.00},
    {'name': 'Modelo 7', 'price': 29.00},
    {'name': 'Modelo 8', 'price': 90.00},
]


def create_model_card(model, index):
    image_src = get_image_path(index)
    return dbc.Card(
        [
            dbc.CardImg(src=image_src, top=True),
            dbc.Button(
                dbc.CardBody(
                    [
                        html.H5(model['name'], className="card-title"),
                        html.P(f"Price: ${model['price']}", className="card-text"),
                    ]
                ),
                id=f"buy-{index}",
                color="primary",
                className="mt-auto"
            ),
        ],
        style={"width": "18rem"}
    )

card_rows = []
current_row = []
for index, model in enumerate(models):
    card = create_model_card(model, index)
    current_row.append(dbc.Col(card, width=3))
    if index % 4 == 0:
        card_rows.append(dbc.Row(current_row, className="mb-4"))
        current_row = []

if current_row:
    card_rows.append(dbc.Row(current_row, className="mb-4"))

card_deck = html.Div(card_rows)

layout = html.Div([
    navbar,
    card_deck
], style={'margin': '0', 'padding': '0'})