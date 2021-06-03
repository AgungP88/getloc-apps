# Cloud Computing Path

Creating **RESTful APIs** and deploying to [Google Cloud Platform](https://cloud.google.com)
by using [Google App Engine](https://cloud.google.com/appengine) and [Google Compute Engine](https://cloud.google.com/compute) for communication between **Machine Learning** and **Mobile Development**. Also implementing **TSP** in [Cloud Function](https://cloud.google.com/functions) and creating database in [Cloud SQL](https://cloud.google.com/sql).

# RESTful APIs

In making the **RESTful APIs** we use [Python](https://github.com/python) using the [Flask Framework](https://flask.palletsprojects.com/en/2.0.x/) and for responses using **JSON** format. For each URL that can be used will be explained below.

## List Destination

In this section there is a list of all destinations that can be filtered by name, city or place ID. Response from each URL using **JSON** format.

**Base URL :**

> https://getloc-314510.et.r.appspot.com/

**Path :**

> /wisata

**Method :**

> `GET`

- **Show List Destination**
  > https://getloc-314510.et.r.appspot.com/wisata

```json
{
  "category": "Taman Hiburan",
  "city": "Semarang",
  "deskripsi": "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang ? Solo Km. 35. Lokasi Kampoeng Kopi Banaran yang berada di ketinggian 480 ? 600m dpl membuat suhu udara disana sejuk antara 23?C ? 27?C. Jadi cocok banget buat pelesir mencari udara dingin dan segar dengan pemandangan indah. Menghilangkan penat kesibukan Kota besar, di tengah perkebunan yang asri.",
  "name": "Kampoeng Kopi Banaran",
  "place_id": "ChIJ-9JlAnxwei4R0SmxRv26nXM",
  "price": "200000",
  "rating": "4.3",
  "spend_time": "90"
}
```

<br>

- **Show list destination filtering by place_id**

  > https://getloc-314510.et.r.appspot.com/wisata/{place_id}

  **Required**

  > place_id = [string]

  **Example request**

  > https://getloc-314510.et.r.appspot.com/wisata/ChIJ1RIjvSXmaC4Rn7u8rwSSLPw

```json
{
  "data": [
    {
      "category": "Tempat Ibadah",
      "city": "Bandung",
      "deskripsi": "Masjid Raya Bandung Provinsi Jawa Barat, yang dulu dikenal dengan nama Masjid Agung Bandung adalah masjid yang berada di Kota Bandung, Jawa Barat, Indonesia. Status masjid ini adalah sebagai masjid provinsi bagi Jawa Barat. ",
      "name": "Masjid Raya Bandung",
      "place_id": "ChIJ1RIjvSXmaC4Rn7u8rwSSLPw",
      "price": "0",
      "rating": "4.7",
      "spend_time": ""
    }
  ],
  "message": "success"
}
```

<br>

- **Show list destination search by name**

  > https://getloc-314510.et.r.appspot.com/wisata/search/name?nama={nama}

  **Required**

  > nama = [string]

  **Example request**

  > https://getloc-314510.et.r.appspot.com/wisata/search/name?nama=Borobudur

```json
{
  "data": [
    {
      "category": "Budaya",
      "city": "Yogyakarta",
      "deskripsi": "Borobudur adalah sebuah candi Buddha yang terletak di Borobudur, Magelang, Jawa Tengah, Indonesia. Candi ini terletak kurang lebih 100 km di sebelah barat daya Semarang, 86 km di sebelah barat Surakarta, dan 40 km di sebelah barat laut Yogyakarta.",
      "name": "Candi Borobudur",
      "place_id": "ChIJl9anCfCMei4Ry8NNdDRD0w0",
      "price": "50000",
      "rating": "4.7",
      "spend_time": "120"
    }
  ],
  "message": "success"
}
```

<br>

- **Show list destination search by city**

  > https://getloc-314510.et.r.appspot.com/wisata/search/city?kota={kota}

  **Required**

  > kota = [string]

  **Example request**

  > https://getloc-314510.et.r.appspot.com/wisata/search/city?kota=Semarang

```json
{
  "data": [
    {
      "category": "Taman Hiburan",
      "city": "Semarang",
      "deskripsi": "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang - Solo Km. 35.",
      "name": "Kampoeng Kopi Banaran",
      "place_id": "ChIJ-9JlAnxwei4R0SmxRv26nXM",
      "price": "200000",
      "rating": "4.3",
      "spend_time": "90"
    }
  ],
  "message": "success"
}
```

<br>

- **Select several destination filter by place_id**

  > https://getloc-314510.et.r.appspot.com/wisata/search/place_id?place_1={place_id}&place_2={place_id}

  **Params**

  > place_1 = [string] <br>.<br>.<br>.<br> place_N = [string]

  **Example request**

  > https://getloc-314510.et.r.appspot.com/wisata/search/place_id?place_1=ChIJ-9JlAnxwei4R0SmxRv26nXM&place_2=ChIJuTW9y2zxaS4RKwSl9KopVgI

```json
{
  "data": [
    {
      "category": "Taman Hiburan",
      "city": "Semarang",
      "deskripsi": "Kampoeng Kopi Banaran, sebuah agro wisata perkebunan kopi di Kabupaten Semarang. Tempat wisata ini memiliki luas 462 hektar yang sebagian dijadikan resort dan tempat wisata. Lokasinya berada di Areal Perkebunan Kopi Kebun Getas Afdeling Assinan tepatnya Jl. Raya Semarang-Solo Km. 35.",
      "name": "Kampoeng Kopi Banaran",
      "place_id": "ChIJ-9JlAnxwei4R0SmxRv26nXM",
      "price": "200000",
      "rating": "4.3",
      "spend_time": "90"
    },
    {
      "category": "Taman Hiburan",
      "city": "Jakarta",
      "deskripsi": "Taman Ayodya, yang dulu bernama Taman Barito, adalah sebuah taman kota yang berada di Jakarta Selatan. Taman ini berlokasi di atas lahan seluas 7.500 m2, dilengkapi dengan kolam buatan seluas 1.500 m2 di tengahnya.",
      "name": "Taman Ayodya",
      "place_id": "ChIJuTW9y2zxaS4RKwSl9KopVgI",
      "price": "0",
      "rating": "4.4",
      "spend_time": "45"
    }
  ],
  "message": "success"
}
```

<br>

## List Package

In this section there is a list of all package that can be filtered by Package ID. Response from each URL using **JSON** format.

**Base URL :**

> https://getloc-314510.et.r.appspot.com/

**Path :**

> /paket

**Method :**

> `GET`

- **List all package**

  > https://getloc-314510.et.r.appspot.com/paket

  **Example request**

  > https://getloc-314510.et.r.appspot.com/paket

```json
{
  "data": [
    {
      "city": "Jakarta",
      "id": "1",
      "place_id1": "ChIJY4ymfJv2aS4RhLkaN4U4tQU",
      "place_id2": "ChIJuTW9y2zxaS4RKwSl9KopVgI",
      "place_id3": "ChIJXVigIpv2aS4REJto8lZlJ9w",
      "place_id4": "",
      "place_id5": ""
    },
    {
      "city": "Jakarta",
      "id": "10",
      "place_id1": "ChIJV13T9zYeai4RlhpJBj3BhXE",
      "place_id2": "ChIJx7dLUwf2aS4RZXBT8uS36kI",
      "place_id3": "ChIJDzlr6wH2aS4Ro2gBJGt31G0",
      "place_id4": "ChIJO5twQf8dai4RJnN7visSUvg",
      "place_id5": "ChIJ_1nySP8dai4RU7hd4de_tv4"
    }
  ],
  "message": "success"
}
```

<br>

- **List package filter by Package ID**

  > https://getloc-314510.et.r.appspot.com/paket/{id}

  **Required**

  > id = [integer]

  **Example request**

  > https://getloc-314510.et.r.appspot.com/paket/68

```json
{
  "data": [
    {
      "city": "Semarang",
      "id": "68",
      "place_id1": "ChIJR6srjDaNcC4RcFQC-pZYtnw",
      "place_id2": "ChIJBTQQu1bzcC4Rz3QRRsvs5ls",
      "place_id3": "ChIJcRORPaf0cC4ROnmCDJKBHWM",
      "place_id4": "ChIJIQ81edWAcC4RIrSk1OEgaAA",
      "place_id5": ""
    }
  ],
  "message": "success"
}
```

<br>

## Convert ID to Place ID

This **RESTful API**s is used to convert the id from the **Machine Learning** model into a place id which is needed by **Mobile Development** to display tourist destination in the application. Response from each URL using **JSON** format.

**Base URL :**

> https://getloc-314510.et.r.appspot.com/

**Path :**

> /idtoplaceid

**Method :**

> `GET`

- **List all package**

  > https://getloc-314510.et.r.appspot.com/idtoplaceid

  **Example request**

  > https://getloc-314510.et.r.appspot.com/idtoplaceid

```json
{
  "data": [
    {
      "id": "1",
      "place_id": "ChIJLbFk59L1aS4RyLzp4OHWKj0"
    },
    {
      "id": "10",
      "place_id": "ChIJs6z3Ru_fQS4RYrPFtgQOLcI"
    },
    {
      "id": "100",
      "place_id": "ChIJkS1HFIZXei4R-LLpUckDyxM"
    }
  ],
  "message": "success"
}
```

<br>

- **List package filter by Package ID**

  > https://getloc-314510.et.r.appspot.com/idtoplaceid/{id}

  **Required**

  > id = [integer]

  **Example request**

  > https://getloc-314510.et.r.appspot.com/idtoplaceid/62

```json
{
  "data": [
    {
      "id": "62",
      "place_id": "ChIJe5NkwFztaS4RUDPOEOnFAA8"
    }
  ],
  "message": "success"
}
```

<br>

## Destination Recommendation

This **RESTful APIs** is used to connect data models that have been created by the **Machine Learning** team into data that can be consumed by **Mobile Development** to provide destination recommendations to users. Response from each URL using **JSON** format.

**Base URL :**

> http://34.101.230.116/getloc-api-recomendation/

**Path :**

> /predict

**Method :**

> `GET`

- **Prediction Place**

  > http://34.101.230.116/getloc-api-recomendation/predict/{user_id}

  **Required**

  > user_id = [integer]

  **Example request**

  > http://34.101.230.116/getloc-api-recomendation/predict/3

```json
{
  "data": 3,
  "prediction": [
    [
      {
        "id": "8",
        "place_id": "ChIJnToeJBoeai4RGjp1dqwxz54"
      }
    ],
    [
      {
        "id": "10",
        "place_id": "ChIJs6z3Ru_fQS4RYrPFtgQOLcI"
      }
    ],
    [
      {
        "id": "2",
        "place_id": "ChIJx7dLUwf2aS4RZXBT8uS36kI"
      }
    ]
  ]
}
```

<br>

# TSP Function
