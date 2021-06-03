from os import name
from app.model.wisata import Wisata

from app import response, app, db
from flask import request
from sqlalchemy import text


def index():
    try:
        wisata = Wisata.query.all()
        data = formatarray(wisata)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def listById(id):
    try:
        wisata = db.session.query(Wisata).filter_by(place_id=id)
        data = formatarray(wisata)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def searchName(nama):
    param = '%' + nama + '%'
    try:
        wisata = db.session.query(Wisata).filter(Wisata.name.like(param)).all()
        data = formatarray(wisata)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def searchCity(kota):
    param = '%' + kota + '%'
    try:
        wisata = db.session.query(Wisata).filter(Wisata.city.like(param)).all()
        data = formatarray(wisata)
        return response.success(data, "success")
    except Exception as e:
        print(e)


def multipleSearchId(result):
    try:
        wisata = db.engine.execute(
            text("SELECT * FROM wisata WHERE place_id = ''" + result))
        data = formatarray(wisata)
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
        'place_id': data.place_id,
        'name': data.name,
        'category': data.category,
        'city': data.city,
        'price': data.price,
        'rating': data.rating,
        'spend_time': data.spend_time,
        'deskripsi': data.deskripsi
    }

    return data
