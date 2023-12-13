import dash
from dash import html
import dash_bootstrap_components as dbc
from dash import Dash, dcc, html, Input, Output, callback
from pages import comprar, pay
# Estilos externos
external_stylesheets = [dbc.themes.BOOTSTRAP]

# Función para obtener la ruta de la imagen
def get_image_path(index):
    return f"assets/Images_compras/im_{index}.jpg"

# Lista de modelos en el carrito de compras
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

# Función para crear una tarjeta de modelo
def create_model_card(model, index):
    image_src = get_image_path(index)
    return dbc.Card(
        [
            dbc.CardImg(src=image_src, top=True),
            dbc.CardBody(
                [
                    html.H5(model['name'], className="card-title"),
                    html.P(f"Price: ${model['price']}", className="card-text"),
                ]
            ),
        ],
        style={"width": "18rem"}
    )

# Crear las tarjetas de modelos en el carrito
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

# Botones de pagar y seguir comprando
buttons = dbc.Row(
    [
        dbc.Col(dbc.Button("Pagar", color="primary", className="me-2"), width=2),
        dbc.Col(dbc.Button("Seguir comprando", color="secondary", id="seguir-comprando-button"), width=2),
        ],
    className="mt-4"
)

# Crear el layout de la interfaz
layout = html.Div([
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
        Input("seguir-comprando-button", "n_clicks")
    )
def redirect_to_comprar(n_clicks):
    if n_clicks is not None:
        return comprar.layout
            
            
# Ejecutar la aplicación
if __name__ == '__main__':
    app.run_server(debug=True)
