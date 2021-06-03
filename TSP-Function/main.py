from ortools.constraint_solver import pywrapcp, routing_enums_pb2
from create_data import create_data

def TSP(request):
  """
  Fungsi untuk menyelesaikan tsp dengan ortools
  Input (JSON):
    api_key = api_key project
    point = lokasi user + tempat yang akan dikunjungi
  Output:
    JSON dengan total jarak dan waktu, urutan tempat 
  """

  address = request.get_json()
  data = create_data(address['api_key'], address['point'])
  data['distance_matrix'] = data['length']

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

  point = [address['point'][i] for i in route[1:]]
  distance = [data['length'][i][j] for i,j in zip(route[:-1],route[1:])]
  time = [data['time'][i][j] for i,j in zip(route[:-1],route[1:])]
  
  output = [{"place":i, "distance":j, "time":k} for i,j,k in zip(point, distance, time)]
  
  return {"route": output, 
          "total_distance":total_distance,
          "total_time":sum(time)}