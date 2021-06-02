import json
import numpy as np
from keras.models import load_model
import requests
from flask import Flask, request, jsonify

app = Flask(__name__)

# load model
model = load_model('./recommendation_rating_model.h5')


@app.route("/")
def hello():
    return "Hello, World!"


@app.route("/predict/<int:id>", methods=["GET"])
def predict(id):
    id_place = range(1, 20)
    # Creating dataset for making recommendations for the first user
    tourism_data = np.array(list(set(id_place)))
    tourism_data[:10]

    id_user = id

    user = np.array([id_user for i in range(len(tourism_data))])
    user[:10]

    predictions = model.predict([user, tourism_data])

    predictions = np.array([a[0] for a in predictions])

    recommended_tourism_ids = (predictions).argsort()[:10]
    list_recommended_tourism_ids = recommended_tourism_ids.tolist()

    # get data from api to convert id to place id
    place_id = []
    for i in list_recommended_tourism_ids:
        r = requests.get(
            'https://getloc-314510.et.r.appspot.com/idtoplaceid/' + str(i))
        s = r.json()
        place_id.append(s['data'])

    #
    response_json = {
        "data": id_user,
        "prediction": place_id
    }

    # return a response in json format
    return jsonify(response_json)
