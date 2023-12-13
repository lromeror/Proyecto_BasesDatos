import dash
from dash import html, dcc
import dash_bootstrap_components as dbc

# Estilos externos
external_stylesheets = [dbc.themes.BOOTSTRAP]

app = dash.Dash(__name__, external_stylesheets=external_stylesheets)

# Contenido de la columna de beneficios
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
    md=6,  # Tamaño para pantallas medianas
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
    md=6,  # Tamaño para pantallas medianas
)

# Layout principal
app.layout = dbc.Container(
    dbc.Row([benefits_col, form_col]),
    className="mt-5",  # Margin top
)

if __name__ == "__main__":
    app.run_server(debug=True)
