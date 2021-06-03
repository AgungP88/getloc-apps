from googlemaps import Client

def matrix(data, travel='distance'):
  """ 
  Parsing input dari API distance matrix menjadi matrix
  Input:
    data = input dari API distance matrix
    travel= 'distance' untuk jarak tempuh
            'duration' untuk waktu tempuh
  Output:
    matrix berukuran NxN dengan N jumlah tempat 
    (Tempat yang akan dikunjungi + 1 lokasi user)
  """
  matrix = []
  for i in data['rows']:
    row = []
    for j in i['elements']:
      row.append(j[travel]['value'])
    matrix.append(row)
  return matrix

def create_data(api_key, point, mode=None):
  """
  Mengambil data dari API distance matrix
  Input:
    api_key = api_key project
    point = lokasi user + tempat yang akan dikunjung
    mode = Mode transportasi/kendaraan 
            "driving", "walking", "bicycling"
  Output:
    Dictionary dengan matrix jarak dan waktu
  """
  outs = {}
  gmaps = Client(api_key)
  data = gmaps.distance_matrix(point,point,mode=mode)
  outs['length'] = matrix(data, 'distance')
  outs['time'] = matrix(data,'duration')
  return outs