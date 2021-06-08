from ortools.constraint_solver import pywrapcp, routing_enums_pb2
from create_data import create_data
from scipy.spatial import distance_matrix
from flask import jsonify

def TSP(data, point, place):
  """
  Function to solve TSP with ortools
  Input (JSON):
    data = distance matrix
    point =  list of cordinates/place id
    place = list of place name
  Output:
    places order, total distance, total time 
  """

  manager = pywrapcp.RoutingIndexManager(len(data['distance_matrix']), 1, 0)
  routing = pywrapcp.RoutingModel(manager)

  def distance_callback(from_index, to_index):
    """Returns the distance between the two nodes."""
    # Convert from routing variable Index to distance matrix NodeIndex.
    from_node = manager.IndexToNode(from_index)
    to_node = manager.IndexToNode(to_index)
    return data['distance_matrix'][from_node][to_node]
    
  transit_callback_index = routing.RegisterTransitCallback(distance_callback)
    
  routing.SetArcCostEvaluatorOfAllVehicles(transit_callback_index)

  search_parameters = pywrapcp.DefaultRoutingSearchParameters()
  search_parameters.first_solution_strategy = (routing_enums_pb2.FirstSolutionStrategy.PATH_CHEAPEST_ARC)

  solution = routing.SolveWithParameters(search_parameters)

  total_distance = solution.ObjectiveValue()
  index = routing.Start(0)
  route = [] 

  while not routing.IsEnd(index):
    route.append(manager.IndexToNode(index))
    index = solution.Value(routing.NextVar(index))
  
  route = route+[0]

  place = [place[i] for i in route[1:]]
  point = [point[i] for i in route[1:]]
  distance = [data['distance_matrix'][i][j] for i,j in zip(route[:-1],route[1:])]
  time = [data['time'][i][j] for i,j in zip(route[:-1],route[1:])]
  
  output = [{"place":i, "distance":j, "time":k, "point":l} for i,j,k,l in zip(place, distance, time, point)]
  
  return jsonify({"data" : {"route": output, 
                          "total_distance":total_distance,
                          "total_time":sum(time)},
                  "message" : "success"})

def main(request):
  """
  Main function
  """
  address = request.get_json()
  if address:
    try:
      if all(map(lambda x: len(x)==2, address['point'])):
        data = {}
        data['distance_matrix'] = distance_matrix(address['point'],address['point'])*111139
        data['time'] = data['distance_matrix']/18
      else:
        point = list(map(lambda x:'place_id:'+x if len(x) > 2 else x, address['point']))
        data = create_data(address['api_key'], point)
    except: 
      return {"data":{},
              "message":"Please use valid API key and valid place ID/Cordinates"}
    return TSP(data, address['point'], address['place'])
  else:
    return {"data":{}, 
            "message":"Please input data"}
