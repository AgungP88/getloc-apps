from app import app
from app.controller import WisataController
from flask import request


@app.route('/')
def index():
    return 'Hallo'
    # return request.args.get("page_number")


@app.route('/wisata', methods=['GET'])
def wisatas():
    return WisataController.index()


@app.route('/wisata/<id>', methods=['GET'])
def wisatasById(id):
    return WisataController.listById(id)


@app.route('/wisata/search/name', methods=['GET'])
def searchName():
    nama = request.args.get("nama")
    return WisataController.searchName(nama)


@app.route('/wisata/search/city', methods=['GET'])
def searchCity():
    kota = request.args.get("kota")
    return WisataController.searchCity(kota)
