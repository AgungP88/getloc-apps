from app import app
from app.controller import WisataController, PaketController, IdToPlaceIdController
from flask import request


@app.route('/')
def index():
    return redirect("http://34.87.17.235/", code=302)
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


@app.route('/wisata/search/place_id', methods=['GET'])
def MultipleSearchId():
    # args = {"p1": v1, "p2": None, "p3": v3}
    args = request.args
    # filtered_args = {"p1": v1, "p3": v3}
    filtered_args = {k: v for k, v in args.items() if v is not None}

    array = []

    for i in filtered_args:
        array.append(" OR place_id = '" + filtered_args[i] + "'")

    s = str(array)

    del1 = s.replace('[', '')
    del2 = del1.replace(']', '')
    del3 = del2.replace('"', '')
    result = del3.replace(',', '')

    return WisataController.multipleSearchId(result)


@app.route('/paket', methods=['GET'])
def indexPaket():
    return PaketController.index()


@app.route('/paket/<id>', methods=['GET'])
def paketById(id):
    return PaketController.paketById(id)


@app.route('/idtoplaceid', methods=['GET'])
def idToPlaceIdIndex():
    return IdToPlaceIdController.index()


@app.route('/idtoplaceid/<id>', methods=['GET'])
def idToPlaceId(id):
    return IdToPlaceIdController.filterById(id)
