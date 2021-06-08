from googlemaps import Client

def matrix(data, travel='distance'):
  """ 
  Parse input from distance matrix API into matrix format
  Input:
    data = input from distance matrix API
    travel= 'distance' for travel distances
            'duration' for travel time
  Output:
    matrix of size NxN with N number of places
    (N = places to visit + 1 user location)
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
  Fetching data from distance matrix API
  Input:
    api_key = project api key
    point = user location + places to visit
    mode = Mode of transportation 
            "driving", "walking", "bicycling"
  Output:
    Dictionary with distance and time matrix
  """
  outs = {}
  gmaps = Client(api_key)
  data = gmaps.distance_matrix(point,point,mode=mode)
  outs['distance_matrix'] = matrix(data, 'distance')
  outs['time'] = matrix(data,'duration')
  return outs