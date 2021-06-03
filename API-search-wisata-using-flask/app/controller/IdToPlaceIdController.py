from os import name
# from app.model.paket import Paket

from app import response, app, db
from flask import request
from app import routes
from sqlalchemy import text


def index():
    try:
        idtoplaceid = db.engine.execute(
            text("SELECT * FROM idtoplaceid"))
        data = formatarray(idtoplaceid)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def filterById(id):
    try:
        idtoplaceid = db.engine.execute(
            text("SELECT * FROM idtoplaceid where id=" + id))
        data = formatarray(idtoplaceid)
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
        'id': data.id,
        'place_id': data.place_id
    }

    return data
