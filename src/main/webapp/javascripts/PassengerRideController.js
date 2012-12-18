var PassengerRideController = function($scope, $http, $location) {
	var indexPath = '/passengerrides';

	$scope.destroy = function(resource) {
		if (confirm("Are you sure?")) {
			$http(resource.links.destroy).success(
					function() {
						$scope.passengerrides = _.reject($scope.passengerrides,
								function(element) {
									return element.id === resource.id;
								});
					}).error($scope.error);
		}
	};

	$scope.error = function() {
		alert("Operation failed!");
	};

	$scope.load = function() {
		$http.get(indexPath).success(function(data) {
			$scope.passengerrides = data;
		});
	};

	$scope.get = function(resourceId) {
		$http.get(indexPath + '/' + resourceId).success(function(data) {
			$scope.passengerride = data;
		});
	};

	$scope.create = function(resource) {
		$http.post(indexPath, resource).success(function() {
			$location.path(indexPath);
		}).error($scope.error);
	};

	$scope.update = function(resource) {
		var request = new Request(resource.links.update, resource);
		$http(request).success(function() {
			$location.path(indexPath);
		}).error($scope.error);
	};
	$scope.createrequest = function(ride, passenger) {
		var riderequest = {
			driver : ride.driver,
			passenger : passenger,
			route : ride.route
		};

		$http.post("/riderequests", riderequest).success(function() {
			alert("Requisição de carona criada!");
		});

	}
	return $scope;
};

var PassengerRideIndexController = function($scope, $http, $location) {
	var self = new PassengerRideController($scope, $http, $location);
	self.load();
};

var PassengerRideEditController = function($scope, $http, $routeParams,
		$location) {
	var self = new PassengerRideController($scope, $http, $location);
	self.get($routeParams.resourceId);
};

var PassengerRideCreationController = function($scope, $http, $location) {
	var self = new PassengerRideController($scope, $http, $location);
};

var PassengerRideDetailsController = function($scope, $http, $routeParams,
		$location) {
	var self = new PassengerRideController($scope, $http, $location);
	self.get($routeParams.resourceId);
};
