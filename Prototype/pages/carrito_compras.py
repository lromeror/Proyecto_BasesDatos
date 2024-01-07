import dash
from dash import html
import dash_bootstrap_components as dbc
from dash import Dash, dcc, html, Input, Output, State, callback
import pages.comprar as comprar
import pages.pay as pay

# Estilos externos
external_stylesheets = [dbc.themes.BOOTSTRAP]
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
# Función para obtener la ruta de la imagen
def get_image_path(index):
    return f"assets/Images_compras/im_{index}.jpg"

# Lista de modelos en el carrito de compras
models = [
    {'name': 'Modelo 1', 'price': 10.00},
    {'name': 'Modelo 2', 'price': 15.00},
    {'name': 'Modelo 3', 'price': 20.00},
    {'name': 'Modelo 6', 'price': 20.00},
    {'name': 'Modelo 8', 'price': 90.00},
]

# Función para crear una tarjeta de modelo
def create_model_card(model, index):
    image_src = get_image_path(index+1)
    return dbc.Card(
        [
            dbc.CardImg(src=image_src, top=True),
            dbc.CardBody(
                [
                    html.H5(model['name'], className="card-title"),
                    html.P(f"Price: ${model['price']}", className="card-text"),
                    dbc.Button("Eliminar", color="tertiary", className="mt-2")
                ]
            ),
        ],
        style={"width": "18rem"}
    )

# Crear las tarjetas de modelos en el carrito
card_rows = []
current_row = []
for index, model in enumerate(models,start=1):
    card = create_model_card(model, index)
    current_row.append(dbc.Col(card, width=3))
    if index % 4 == 0:
        card_rows.append(dbc.Row(current_row, className="mb-4"))
        current_row = []

if current_row:
    card_rows.append(dbc.Row(current_row, className="mb-4"))

# Botones de pagar y seguir comprando
buttons = dbc.Row(
    [
        dbc.Col(dbc.NavLink(dbc.Button("Pagar", color="primary", className="me-2",id="carrito_compras"), href="/pay"), width=2),
        dbc.Col(dbc.NavLink(dbc.Button("Seguir comprando", color="secondary", className="me-2", id="seguir-comprando-button"), href="/comprar"), width=2)
        
        
        ],
    className="mt-4"
)

# Crear el layout de la interfaz
layout = html.Div([
    navbar,
    dbc.Container(
        [
            dbc.Row(
                dbc.Col(html.H1("Carrito de Compras"), className="text-center mt-4")
            ),
            dbc.Row(card_rows),
            buttons
        ],
        fluid=True,
        className="Container_header"
    )
])

# Crear la aplicación Dash
app = dash.Dash(__name__, external_stylesheets=external_stylesheets)
app.layout = layout

@app.callback(
    Output('page-content', 'children'),
    Input("seguir-comprando-button", "n_clicks"),
)
def redirect_to_comprar(n_clicks):
    if n_clicks==1:
        return comprar.layout

@app.callback(
    Output('page-content', 'children'),
    Input("carrito_compras", "n_clicks"),
)
def redirect_to_comprar(n_clicks):
    if n_clicks==1:
        return pay.layout
    
def getTotalAmount():
    total_amount = 0
    for model in models:
        total_amount += model['price']
    return total_amount
            
            
# Ejecutar la aplicación
if __name__ == '__main__':
    app.run_server(debug=True)
