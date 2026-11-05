
# -*- coding: utf-8 -*-

import threading
from http.server import BaseHTTPRequestHandler, HTTPServer

SERVER = None

class VidoraHandler(BaseHTTPRequestHandler):

    def do_GET(self):
        html = """
        <!DOCTYPE html>
        <html lang="ar" dir="rtl">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport"
                  content="width=device-width, initial-scale=1">
            <title>Vidora</title>
            <style>
                body {
                    background: #0f172a;
                    color: white;
                    font-family: Arial;
                    text-align: center;
                    padding: 30px;
                }
                .card {
                    background: #1e293b;
                    padding: 25px;
                    border-radius: 18px;
                    margin-top: 40px;
                }
                h1 { color: #38bdf8; }
            </style>
        </head>
        <body>
            <div class="card">
                <h1>Vidora</h1>
                <p>مرحباً بك في تطبيق Vidora</p>
                <p>جاري تجهيز أداة التحميل...</p>
            </div>
        </body>
        </html>
        """

        self.send_response(200)
        self.send_header("Content-Type", "text/html; charset=utf-8")
        self.end_headers()
        self.wfile.write(html.encode("utf-8"))

def start_server():
    global SERVER

    if SERVER is None:
        SERVER = HTTPServer(("127.0.0.1", 8080), VidoraHandler)
        threading.Thread(
            target=SERVER.serve_forever,
            daemon=True
        ).start()
