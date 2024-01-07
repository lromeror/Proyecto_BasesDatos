import dash
import base64
import datetime
import io
import os
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

page = html.Div([
    dcc.Location(id='url', refresh=False),
    html.Button('Upload Files', id='upload-button'),
    dcc.Upload(
        id='upload-data',
        children=html.Div([
            'Drag and Drop or ',
            html.A('Select Files')
        ]),
        style={
            'width': '100%',
            'height': '60px',
            'lineHeight': '60px',
            'borderWidth': '2px',
            'borderStyle': 'dashed',
            'borderRadius': '5px',
            'textAlign': 'center',
            'margin': '10px'
        },
        className='upload-dropzone'
    ),
    html.Div(id='output-data-upload')
])
page = dbc.Container([
    dbc.Row(
        dbc.Col(html.H1("Create a new 3D object"), width={"size": 6, "offset": 3})
    ),
    dbc.Row(
        dbc.Col(
            dcc.Upload(
                id='upload-data',
                children=html.Div([
                    'Drag and drop your 3D files here or ',
                    html.A('Select object files')
                ]),
                style={
                    'width': '100%', 'height': '60px', 'lineHeight': '60px',
                    'borderWidth': '1px', 'borderStyle': 'dashed',
                    'borderRadius': '5px', 'textAlign': 'center', 'margin': '10px'
                },
                multiple=True
            ), width=10
        )
    ),
    dbc.Row(
        dbc.Col(
            dbc.CardGroup([
                dbc.Label("Enter a name for your object"),
                dbc.Input(type="text", id="input-name"),
                dbc.Label("Enter tags"),
                dbc.Input(type="text", id="input-tags"),
                dbc.Label("Description"),
                dbc.Textarea(id="textarea-description"),
                dbc.Label("Visibility"),
                dbc.Select(
                    id="select-visibility",
                    options=[
                        {"label": "Public", "value": "public"},
                        {"label": "Private", "value": "private"}
                    ],
                    value="public",
                ),
                # Placeholder for Advanced Settings
            ]), width=10
        )
    ),
    dbc.Row(
        dbc.Col(
            html.Button('Upload pictures', id='upload-pictures-button', className="btn btn-success"),
            width={"size": 4, "offset": 4}
        )
    )
], fluid=True,className="Margin")

def save_file(name, content, folder="/Users/angelozurita/Repositorios_GitHub/Proyecto_BasesDatos/Prototype/assets/"):
    """Guarda un archivo en una ruta específica del servidor."""
    data = content.encode("utf8").split(b";base64,")[1]
    file_path = os.path.join(folder, name)
    os.makedirs(os.path.dirname(file_path), exist_ok=True)
    with open(file_path, "wb") as fp:
        fp.write(base64.decodebytes(data))

callback(
    Output('output-image-upload', 'children'),
    Input('upload-data', 'contents'),
    State('upload-data', 'filename'),
    prevent_initial_call=True
)
def update_output(list_of_contents, list_of_names):
    if list_of_contents is not None:
        children = []
        for name, content in zip(list_of_names, list_of_contents):
            save_file(name, content)
            children.append(html.Div(f'Archivo guardado: {name}'))
        return children
layout = html.Div([
    navbar,
    page
], style={'margin': '0', 'padding': '0'})