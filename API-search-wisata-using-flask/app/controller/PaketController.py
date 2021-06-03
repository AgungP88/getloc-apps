from os import name
from app.model.paket import Paket

from app import response, app, db
from flask import request
from app import routes
from sqlalchemy import text


def index():
    try:
        paket = db.engine.execute(
            text("SELECT * FROM paket"))
        data = formatarray(paket)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def paketById(id):
    try:
        paket = db.engine.execute(
            text("SELECT * FROM paket WHERE package_id=" + id))
        data = formatarray(paket)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def formatarray(datas):
    array = []

    for i in datas:
        array.append(singleObject(i))

    return array


def singleObject(data):
    data = {
        'id': data.package_id,
        'city': data.city,
        'place_id1': data.place_id1,
        'place_id2': data.place_id2,
        'place_id3': data.place_id3,
        'place_id4': data.place_id4,
        'place_id5': data.place_id5
    }

    return data
