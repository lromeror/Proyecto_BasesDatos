import dash

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
tribes = [
        {'name': 'Witchsong', 'description': 'Miniatures', 'members': 6892},
        {'name': 'Big Bad Evil Guy', 'description': 'Roleplaying Games', 'members': 2409},
        {'name': 'Lord of the Print', 'description': 'High-Quality Prints', 'members': 2399},
        {'name': 'The Witchguild', 'description': 'Fantasy Adventures', 'members': 2210},
        {'name': 'Resinant Miniatures', 'description': 'Detailed Miniature Collections', 'members': 48},
        {'name': 'Velrock Art Miniatures', 'description': 'Stylized Fantasy Figures', 'members': 175},
        {'name': 'Lord of the Print', 'description': 'Epic Scale Miniatures', 'members': 2399},
        {'name': 'Infinite Dimensions Games', 'description': 'Innovative Game Miniatures', 'members': 507},
        {'name': 'Josh', 'description': 'Custom Designed Miniatures', 'members': 24},
        {'name': 'Dark Realms Forge', 'description': 'Dark Fantasy World Creations', 'members': 121},
        {'name': 'Little Shop of Sigil', 'description': 'Mythical Creature Sculptures', 'members': 6},
        {'name': 'Nikita Breder', 'description': 'Historical Miniature Scenes', 'members': 26},
    ]


def get_image_path(index):
    return f"assets/Images_tribes/{index}.png"

card_info = [
    {
        "title": f"{tribe['name']} - {tribe['description']}",
        "members": str(tribe['members']),
        "image_src": get_image_path(index + 1),  
    }
    for index, tribe in enumerate(tribes)  
]

def create_tribe_card(tribe, index):
    return dbc.Card(
        [
            dbc.CardImg(src=f"assets/Images_tribes/{index}.png", top=True),
            dbc.NavLink(
                dbc.CardBody(
                    [
                        html.H5(f"{tribe['name']} - {tribe['description']}", className="card-title"),
                        html.P(f"Members: {tribe['members']}", className="card-text"),
                    ]
                ),
                href="/pay"
            ),
        ],
        style={"width": "18rem"}
    )

# Updated loop to use the create_tribe_card function
card_rows = []
current_row = []
for index, tribe in enumerate(tribes, start=1):
    card = create_tribe_card(tribe, index)
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