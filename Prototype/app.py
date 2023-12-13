    
import dash
import dash_bootstrap_components as dbc
from dash import Dash, dcc, html, Input, Output, callback
<<<<<<< HEAD
from pages import home,join,login,tribus,comprar
=======
from pages import home,join,login,tribus,pay
>>>>>>> 95a0a0c959b891b864f84387abce43679f0a66e1

external_stylesheets = [dbc.themes.BOOTSTRAP]  

app = Dash(__name__, suppress_callback_exceptions=True, external_stylesheets=external_stylesheets,use_pages=True)
server = app.server



app.layout = html.Div([
    dcc.Location(id='url', refresh=False),
    html.Div(id='page-content')
])

@callback(Output('page-content', 'children'),
            Input('url', 'pathname'))
def display_page(pathname):
    if pathname == '/join':
        return join.layout 
    if pathname == '/login':
        return login.layout
    if pathname == '/tribus':
        return tribus.layout
<<<<<<< HEAD
    if pathname == '/comprar':
        return comprar.layout
=======
    if pathname == '/pay':
        return pay.layout
>>>>>>> 95a0a0c959b891b864f84387abce43679f0a66e1
    else:
        return home.layout


if __name__ == '__main__':
    app.run_server(debug=True,port=8050)